/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import io.github.hicetop.bpm.engine.assist.Assert;
import io.github.hicetop.bpm.engine.assist.DateUtils;
import io.github.hicetop.bpm.engine.core.FlowLongContext;
import io.github.hicetop.bpm.engine.core.enums.PerformType;
import io.github.hicetop.bpm.engine.core.enums.TaskType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * 任务实体类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
@Getter
@Setter
@ToString
@Table(value = "flw_task")
public class FlwTask extends FlowEntity {
    /**
     * 流程实例ID
     */
    @Column(value = "instance_id")
    protected String instanceId;
    /**
     * 父任务ID
     */
    @Column(value = "parent_task_id")
    protected String parentTaskId;
    /**
     * 任务名称
     */
    @Column(value = "task_name")
    protected String taskName;
    /**
     * 任务 key 唯一标识
     */
    @Column(value = "task_key")
    protected String taskKey;
    /**
     * 任务类型 {@link TaskType}
     */
    @Column(value = "task_type")
    protected Integer taskType;
    /**
     * 参与方式 {@link PerformType}
     */
    @Column(value = "perform_type")
    protected Integer performType;
    /**
     * 任务关联的表单url
     */
    @Column(value = "action_url")
    protected String actionUrl;
    /**
     * 变量json
     */
    @Column(value = "variable")
    protected String variable;
    /**
     * 委托人ID
     */
    @Column(value = "assignor_id")
    protected String assignorId;
    /**
     * 委托人
     */
    @Column(value = "assignor")
    protected String assignor;
    /**
     * 期望任务完成时间
     */
    @Column(value = "expire_time")
    protected Date expireTime;
    /**
     * 提醒时间
     */
    @Column(value = "remind_time")
    protected Date remindTime;
    /**
     * 提醒次数
     */
    @Column(value = "remind_repeat")
    protected Integer remindRepeat;
    /**
     * 已阅 0，否 1，是
     */
    @Column(value = "viewed")
    protected Integer viewed;

    public boolean major() {
        return Objects.equals(this.taskType, TaskType.major.getValue());
    }

    public Map<String, Object> variableMap() {

        if (null == this.variable) {
            return null;
        }
        return FlowLongContext.fromJson(this.variable, Map.class);
    }

    public void taskType(TaskType taskType) {
        this.taskType = taskType.getValue();
    }

    public void setTaskType(Integer taskType) {
        Assert.isNull(TaskType.get(taskType), "illegal type [taskType=" + taskType + "]");
        this.taskType = taskType;
    }

    public void performType(PerformType performType) {
        this.performType = performType.getValue();
    }

    public void setPerformType(Integer performType) {
        Assert.isNull(PerformType.get(performType), "illegal type [performType=" + performType + "]");
        this.performType = performType;
    }

    public void putAllVariable(Map<String, Object> args) {
        if (null != args && !args.isEmpty()) {
            Map<String, Object> varMap = this.variableMap();
            if (null != varMap) {
                // 合并变量
                varMap.forEach(args::putIfAbsent);
            }
            this.variable = FlowLongContext.toJson(args);
        }
    }

    /**
     * 从扩展配置中加载期望任务完成时间
     *
     * @param extendConfig 扩展配置
     * @param checkEmpty   检查是否为空
     */
    public void loadExpireTime(Map<String, Object> extendConfig, boolean checkEmpty) {
        Date expireTime = null;
        if (null != extendConfig) {
            String time = (String) extendConfig.get("time");
            if (null != time) {
                expireTime = DateUtils.parseTimerTaskTime(time);
            }
        }
        if (checkEmpty) {
            Assert.isEmpty(expireTime, "Timer task config error");
        }
        this.expireTime = expireTime;
    }

    /**
     * 开始节点判断
     *
     * @return true 是 false 非
     */
    public boolean startNode() {
        return Objects.equals(0L, this.parentTaskId);
    }

    public FlwTask cloneTask(FlwHisTaskActor flwHisTaskActor) {
        if (null != flwHisTaskActor) {
            this.createId = flwHisTaskActor.getActorId();
            this.createBy = flwHisTaskActor.getActorName();
        }
        return cloneTask(createId, createBy);
    }

    public FlwTask cloneTask(String createId, String createBy) {
        FlwTask newFlwTask = new FlwTask();
        newFlwTask.setTenantId(tenantId);
        newFlwTask.setInstanceId(instanceId);
        newFlwTask.setParentTaskId(parentTaskId);
        newFlwTask.setTaskName(taskName);
        newFlwTask.setTaskKey(taskKey);
        newFlwTask.setTaskType(taskType);
        newFlwTask.setPerformType(performType);
        newFlwTask.setActionUrl(actionUrl);
        newFlwTask.setVariable(variable);
        newFlwTask.setAssignorId(assignorId);
        newFlwTask.setAssignor(assignor);
        newFlwTask.setExpireTime(expireTime);
        newFlwTask.setRemindTime(remindTime);
        newFlwTask.setRemindRepeat(remindRepeat);
        newFlwTask.setViewed(viewed);
        newFlwTask.setCreateId(createId);
        newFlwTask.setCreateBy(createBy);
        newFlwTask.setCreateTime(DateUtils.getCurrentDate());
        return newFlwTask;
    }
}
