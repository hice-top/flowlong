/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.impl;

import io.github.hicetop.bpm.engine.FlowConstants;
import io.github.hicetop.bpm.engine.QueryService;
import io.github.hicetop.bpm.engine.RuntimeService;
import io.github.hicetop.bpm.engine.TaskService;
import io.github.hicetop.bpm.engine.assist.Assert;
import io.github.hicetop.bpm.engine.assist.DateUtils;
import io.github.hicetop.bpm.engine.core.Execution;
import io.github.hicetop.bpm.engine.core.FlowCreator;
import io.github.hicetop.bpm.engine.core.FlowLongContext;
import io.github.hicetop.bpm.engine.core.enums.InstanceEventType;
import io.github.hicetop.bpm.engine.core.enums.InstanceState;
import io.github.hicetop.bpm.engine.core.enums.TaskEventType;
import io.github.hicetop.bpm.engine.core.enums.TaskType;
import io.github.hicetop.bpm.engine.dao.FlwExtInstanceDao;
import io.github.hicetop.bpm.engine.dao.FlwHisInstanceDao;
import io.github.hicetop.bpm.engine.dao.FlwInstanceDao;
import io.github.hicetop.bpm.engine.entity.*;
import io.github.hicetop.bpm.engine.listener.InstanceListener;
import io.github.hicetop.bpm.engine.model.ConditionNode;
import io.github.hicetop.bpm.engine.model.ModelHelper;
import io.github.hicetop.bpm.engine.model.NodeModel;
import io.github.hicetop.bpm.engine.model.ProcessModel;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 流程实例运行业务类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class RuntimeServiceImpl implements RuntimeService {
    private final InstanceListener instanceListener;
    private final QueryService queryService;
    private final TaskService taskService;
    private final FlwInstanceDao instanceDao;
    private final FlwHisInstanceDao hisInstanceDao;
    private final FlwExtInstanceDao extInstanceDao;

    public RuntimeServiceImpl(InstanceListener instanceListener, QueryService queryService, TaskService taskService,
                              FlwInstanceDao instanceDao, FlwHisInstanceDao hisInstanceDao, FlwExtInstanceDao extInstanceDao) {
        this.instanceListener = instanceListener;
        this.queryService = queryService;
        this.taskService = taskService;
        this.instanceDao = instanceDao;
        this.hisInstanceDao = hisInstanceDao;
        this.extInstanceDao = extInstanceDao;
    }

    /**
     * 创建活动实例
     */
    @Override
    public FlwInstance createInstance(FlwProcess flwProcess, FlowCreator flowCreator, Map<String, Object> args, NodeModel nodeModel, Supplier<FlwInstance> supplier) {
        FlwInstance flwInstance = null;
        if (null != supplier) {
            flwInstance = supplier.get();
        }
        if (null == flwInstance) {
            flwInstance = new FlwInstance();
        }
        flwInstance.setCreateTime(DateUtils.getCurrentDate());
        flwInstance.setFlowCreator(flowCreator);
        flwInstance.setCurrentNodeName(nodeModel.getNodeName());
        flwInstance.setCurrentNodeKey(nodeModel.getNodeKey());
        flwInstance.setLastUpdateBy(flwInstance.getCreateBy());
        flwInstance.setLastUpdateTime(flwInstance.getCreateTime());
        flwInstance.setProcessId(flwProcess.getId());
        flwInstance.setMapVariable(args);

        // 重新加载流程模型
        ModelHelper.reloadProcessModel(flwProcess.model(), t -> flwProcess.setModelContent2Json(t.cleanParentNode()));

        // 保存实例
        this.saveInstance(flwInstance, flwProcess, flowCreator);
        return flwInstance;
    }

    /**
     * 根据流程实例ID获取流程实例模型
     *
     * @param instanceId 流程实例ID
     * @return {@link ProcessModel}
     */
    @Override
    public ProcessModel getProcessModelByInstanceId(String instanceId) {
        FlwExtInstance flwExtInstance = extInstanceDao.selectById(instanceId);
        Assert.isNull(flwExtInstance, "The process instance model does not exist.");
        return flwExtInstance.model();
    }

    @Override
    public boolean addVariable(String instanceId, Map<String, Object> args, Function<FlwInstance, FlwInstance> function) {
        FlwInstance flwInstance = instanceDao.selectById(instanceId);
        Assert.isNull(flwInstance, "not found instance");
        FlwInstance fi = function.apply(flwInstance);
        fi.setId(instanceId);
        Map<String, Object> data = flwInstance.variableToMap();
        data.putAll(args);
        fi.setMapVariable(data);
        return instanceDao.updateById(fi);
    }

    /**
     * 删除活动流程实例数据，更新历史流程实例的状态、结束时间
     */
    @Override
    public boolean endInstance(Execution execution, String instanceId, NodeModel endNode) {
        FlwInstance flwInstance = instanceDao.selectById(instanceId);
        if (null != flwInstance) {
            instanceDao.deleteById(instanceId);
            hisInstanceDao.updateById(this.getFlwHisInstance(instanceId, endNode, flwInstance));
            // 流程实例监听器通知
            this.instanceNotify(InstanceEventType.end, () -> hisInstanceDao.selectById(instanceId), execution.getFlowCreator());

            /*
             * 实例为子流程，重启动父流程任务
             */
            if (null != flwInstance.getParentInstanceId()) {
                // 结束调用外部流程任务
                taskService.endCallProcessTask(flwInstance.getProcessId(), flwInstance.getId());

                // 重启父流程实例
                FlwInstance parentFlwInstance = instanceDao.selectById(flwInstance.getParentInstanceId());
                execution.setFlwInstance(parentFlwInstance);
                String currentNodeKey = flwInstance.getBusinessKey();
                if (null == currentNodeKey) {
                    // 子流程节点为空，则取父流程当前节点
                    currentNodeKey = parentFlwInstance.getCurrentNodeKey();
                }
                execution.restartProcessInstance(parentFlwInstance.getProcessId(), currentNodeKey);
            }
        }
        return true;
    }

    protected FlwHisInstance getFlwHisInstance(String instanceId, NodeModel endNode, FlwInstance flwInstance) {
        FlwHisInstance his = new FlwHisInstance();
        his.setId(instanceId);
        InstanceState instanceState = InstanceState.complete;
        his.setInstanceState(instanceState);
        String currentNodeName = instanceState.name();
        String currentNodeKey = instanceState.name();
        if (null != endNode) {
            NodeModel childNode = endNode.getChildNode();
            if (null == childNode || TaskType.end.ne(childNode.getType())) {
                childNode = endNode;
            }
            // 记录结束节点
            currentNodeName = childNode.getNodeName();
            currentNodeKey = childNode.getNodeKey();
        }
        his.setCurrentNodeName(currentNodeName);
        his.setCurrentNodeKey(currentNodeKey);
        his.setCreateTime(flwInstance.getCreateTime());
        his.setLastUpdateBy(flwInstance.getLastUpdateBy());
        his.setLastUpdateTime(flwInstance.getLastUpdateTime());
        his.calculateDuration();
        return his;
    }

    protected void instanceNotify(InstanceEventType eventType, Supplier<FlwHisInstance> supplier, FlowCreator flowCreator) {
        if (null != instanceListener) {
            instanceListener.notify(eventType, supplier, null, flowCreator);
        }
    }

    /**
     * 流程实例数据会保存至活动实例表、历史实例表
     *
     * @param flwInstance 流程实例对象
     * @param flwProcess  流程定义对象
     * @param flowCreator 处理人员
     */
    @Override
    public void saveInstance(FlwInstance flwInstance, FlwProcess flwProcess, FlowCreator flowCreator) {
        // 保存流程实例
        instanceDao.insert(flwInstance);

        // 保存历史实例设置为活的状态
        FlwHisInstance flwHisInstance = FlwHisInstance.of(flwInstance, InstanceState.active);
        hisInstanceDao.insert(flwHisInstance);

        // 保存扩展流程实例
        extInstanceDao.insert(FlwExtInstance.of(flwInstance, flwProcess));

        // 流程实例监听器通知
        this.instanceNotify(InstanceEventType.start, () -> flwHisInstance, flowCreator);
    }

    @Override
    public boolean reject(String instanceId, FlowCreator flowCreator) {
        return this.forceComplete(instanceId, flowCreator, InstanceState.reject, TaskEventType.reject);
    }

    @Override
    public boolean revoke(String instanceId, FlowCreator flowCreator) {
        return this.forceComplete(instanceId, flowCreator, InstanceState.revoke, TaskEventType.revoke);
    }

    @Override
    public boolean timeout(String instanceId, FlowCreator flowCreator) {
        return this.forceComplete(instanceId, flowCreator, InstanceState.timeout, TaskEventType.timeout);
    }

    /**
     * 强制终止活动实例,并强制完成活动任务
     *
     * @param instanceId  流程实例ID
     * @param flowCreator 处理人员
     */
    @Override
    public boolean terminate(String instanceId, FlowCreator flowCreator) {
        return this.forceComplete(instanceId, flowCreator, InstanceState.terminate, TaskEventType.terminate);
    }

    /**
     * 强制完成流程实例
     *
     * @param instanceId    流程实例ID
     * @param flowCreator   处理人员
     * @param instanceState 流程实例最终状态
     * @param eventType     监听事件类型
     */
    protected boolean forceComplete(String instanceId, FlowCreator flowCreator,
                                    InstanceState instanceState, TaskEventType eventType) {
        FlwInstance flwInstance = instanceDao.selectById(instanceId);
        if (null == flwInstance) {
            return false;
        }

        final String parentInstanceId = flwInstance.getParentInstanceId();
        if (null != parentInstanceId) {
            // 找到主流程去执行完成逻辑
            this.forceComplete(parentInstanceId, flowCreator, instanceState, eventType);
        } else {
            // 结束所有子流程实例
            instanceDao.selectListByParentInstanceId(flwInstance.getId()).ifPresent(f -> f.forEach(t ->
                    this.forceCompleteAll(t, flowCreator, instanceState, eventType)));
        }

        // 结束当前流程实例
        this.forceCompleteAll(flwInstance, flowCreator, instanceState, eventType);
        return true;
    }

    /**
     * 强制完成流程所有实例
     */
    protected void forceCompleteAll(FlwInstance flwInstance, FlowCreator flowCreator,
                                    InstanceState instanceState, TaskEventType eventType) {

        // 实例相关任务强制完成
        taskService.forceCompleteAllTask(flwInstance.getId(), flowCreator, instanceState, eventType);

        // 更新历史实例设置状态为终止
        FlwHisInstance flwHisInstance = FlwHisInstance.of(flwInstance, instanceState);
        hisInstanceDao.updateById(flwHisInstance);

        // 删除实例
        instanceDao.deleteById(flwInstance.getId());

        // 流程实例监听器通知
        this.instanceNotify(InstanceEventType.forceComplete, () -> flwHisInstance, flowCreator);
    }

    /**
     * 更新活动实例
     */
    @Override
    public void updateInstance(FlwInstance flwInstance) {
        Assert.illegal(null == flwInstance || null == flwInstance.getId(),
                "instance id cannot be empty");
        instanceDao.updateById(flwInstance);
    }

    @Override
    public boolean updateInstanceModelById(String instanceId, ProcessModel processModel) {
        // 使缓存失效
        FlowLongContext.invalidateProcessModel(FlowConstants.processInstanceCacheKey + instanceId);

        // 更新流程实例模型
        FlwExtInstance extInstance = new FlwExtInstance();
        extInstance.setId(instanceId);
        extInstance.setModelContent(FlowLongContext.toJson(processModel.cleanParentNode()));
        return extInstanceDao.updateById(extInstance);
    }

    /**
     * 级联删除指定流程实例的所有数据
     * <p>
     * 删除表 flw_his_task_actor, flw_his_task, flw_task_actor, flw_task, flw_his_instance, flw_ext_instance, flw_instance
     * </p>
     *
     * @param processId 流程ID
     */
    @Override
    public void cascadeRemoveByProcessId(String processId) {
        hisInstanceDao.selectListByProcessId(processId).ifPresent(hisInstances -> {

            // 删除活动任务相关信息
            taskService.cascadeRemoveByInstanceIds(hisInstances.stream().map(FlowEntity::getId).collect(Collectors.toList()));

            // 删除扩展实例
            extInstanceDao.deleteByProcessId(processId);

            // 删除历史实例
            hisInstanceDao.deleteByProcessId(processId);

            // 删除实例
            instanceDao.deleteByProcessId(processId);
        });
    }

    @Override
    public void cascadeRemoveByInstanceId(String instanceId) {
        if (taskService.cascadeRemoveByInstanceIds(Collections.singletonList(instanceId))) {
            // 删除扩展实例
            extInstanceDao.deleteById(instanceId);

            // 删除历史实例
            hisInstanceDao.deleteById(instanceId);

            // 删除实例
            instanceDao.deleteById(instanceId);
        }
    }

    @Override
    public void appendNodeModel(String taskId, NodeModel nodeModel, boolean beforeAfter) {
        FlwTask flwTask = queryService.getTask(taskId);
        FlwExtInstance flwExtInstance = extInstanceDao.selectById(flwTask.getInstanceId());
        final String appendTaskKey = flwTask.getTaskKey();

        ProcessModel processModel = flwExtInstance.model();
        NodeModel selectNode = processModel.getNode(appendTaskKey);
        if (beforeAfter) {
            // 前置追溯父节点
            selectNode = selectNode.getParentNode();
        }
        if (null != selectNode.getConditionNodes()) {
            // 如果直接跟着条件节点，找到分支作为父节点
            for (ConditionNode conditionNode : selectNode.getConditionNodes()) {
                NodeModel conditionChildNode = conditionNode.getChildNode();
                if (Objects.equals(conditionChildNode.getNodeKey(), appendTaskKey)) {
                    nodeModel.setChildNode(conditionChildNode);
                    conditionNode.setChildNode(nodeModel);
                    break;
                }
            }
        } else {
            // 当前节点即为真实父节点
            nodeModel.setChildNode(selectNode.getChildNode());
            selectNode.setChildNode(nodeModel);
        }

        // 更新最新模型
        FlwExtInstance temp = new FlwExtInstance();
        temp.setId(flwExtInstance.getId());
        temp.setModelContent(FlowLongContext.toJson(processModel.cleanParentNode()));
        Assert.isFalse(extInstanceDao.updateById(temp), "Update FlwExtInstance Failed");

        // 使缓存失效
        FlowLongContext.invalidateProcessModel(flwExtInstance.modelCacheKey());
    }
}
