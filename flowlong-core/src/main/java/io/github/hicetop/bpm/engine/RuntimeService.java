/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine;

import io.github.hicetop.bpm.engine.core.Execution;
import io.github.hicetop.bpm.engine.core.FlowCreator;
import io.github.hicetop.bpm.engine.entity.FlwInstance;
import io.github.hicetop.bpm.engine.entity.FlwProcess;
import io.github.hicetop.bpm.engine.model.NodeModel;
import io.github.hicetop.bpm.engine.model.ProcessModel;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

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
public interface RuntimeService {

    /**
     * 根据流程、创建人员、父流程实例ID创建流程实例
     *
     * @param flwProcess  流程定义对象
     * @param flowCreator 流程实例任务创建者
     * @param args        参数列表
     * @param nodeModel   当前所在节点
     * @param supplier    初始化流程实例提供者
     * @return 活动流程实例对象
     */
    FlwInstance createInstance(FlwProcess flwProcess, FlowCreator flowCreator, Map<String, Object> args, NodeModel nodeModel, Supplier<FlwInstance> supplier);

    /**
     * 根据流程实例ID获取流程实例模型
     *
     * @param instanceId 流程实例ID
     * @return {@link ProcessModel}
     */
    ProcessModel getProcessModelByInstanceId(String instanceId);

    /**
     * 根据 流程实例ID 更新流程实例全局变量
     *
     * @param instanceId 流程实例ID
     * @param args       流程实例参数
     * @param function   待更新实例回调处理函数
     */
    boolean addVariable(String instanceId, Map<String, Object> args, Function<FlwInstance, FlwInstance> function);

    /**
     * 根据 流程实例ID 更新流程实例全局变量
     *
     * @param instanceId 流程实例ID
     * @param args       流程实例参数
     */
    default boolean addVariable(String instanceId, Map<String, Object> args) {
        return this.addVariable(instanceId, args, t -> new FlwInstance());
    }

    /**
     * 结束流程实例（审批通过）
     *
     * @param execution  {@link Execution}
     * @param instanceId 流程实例ID
     * @param endNode    结束节点
     * @return true 成功 false 失败
     */
    boolean endInstance(Execution execution, String instanceId, NodeModel endNode);

    /**
     * 保存流程实例
     *
     * @param flwInstance 流程实例对象
     * @param flwProcess  流程定义对象
     * @param flowCreator 处理人员
     */
    void saveInstance(FlwInstance flwInstance, FlwProcess flwProcess, FlowCreator flowCreator);

    /**
     * 流程实例拒绝审批强制终止（用于后续审核人员认为该审批不再需要继续，拒绝审批强行终止）
     *
     * @param instanceId  流程实例ID
     * @param flowCreator 处理人员
     */
    boolean reject(String instanceId, FlowCreator flowCreator);

    /**
     * 流程实例撤销（用于错误发起审批申请，发起人主动撤销）
     *
     * @param instanceId  流程实例ID
     * @param flowCreator 处理人员
     */
    boolean revoke(String instanceId, FlowCreator flowCreator);

    /**
     * 流程实例超时（设定审批时间超时，自动结束）
     *
     * @param instanceId  流程实例ID
     * @param flowCreator 处理人员
     */
    boolean timeout(String instanceId, FlowCreator flowCreator);

    /**
     * 流程实例超时（忽略操作权限）
     *
     * @param instanceId 流程实例ID
     */
    default void timeout(String instanceId) {
        this.timeout(instanceId, FlowCreator.ADMIN);
    }

    /**
     * 流程实例强制终止
     *
     * @param instanceId  流程实例ID
     * @param flowCreator 处理人员
     */
    boolean terminate(String instanceId, FlowCreator flowCreator);

    /**
     * 更新流程实例
     *
     * @param flwInstance 流程实例对象
     */
    void updateInstance(FlwInstance flwInstance);

    /**
     * 根据 流程实例ID 更新流程实例模型内容
     *
     * @param instanceId   流程实例ID
     * @param processModel 流程模型
     * @return true 成功 false 失败
     */
    boolean updateInstanceModelById(String instanceId, ProcessModel processModel);

    /**
     * 级联删除指定流程实例的所有数据
     *
     * @param processId 流程ID
     */
    void cascadeRemoveByProcessId(String processId);

    /**
     * 级联删除表 flw_his_task_actor, flw_his_task, flw_task_actor, flw_task, flw_instance, flw_his_instance
     *
     * @param instanceId 流程实例ID
     */
    void cascadeRemoveByInstanceId(String instanceId);

    /**
     * 追加节点模型（不执行任务跳转）
     * <p>
     * 执行追加节点模型调用 {@link FlowLongEngine#executeAppendNodeModel(String, NodeModel, FlowCreator, boolean)}
     * </p>
     *
     * @param taskId      任务ID
     * @param nodeModel   节点模型
     * @param beforeAfter true 前置 false 后置
     */
    void appendNodeModel(String taskId, NodeModel nodeModel, boolean beforeAfter);
}
