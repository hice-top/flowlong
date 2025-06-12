/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import io.github.hicetop.bpm.engine.core.FlowLongContext;
import io.github.hicetop.bpm.engine.core.enums.InstancePriority;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 流程实例实体类
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
@Table(value = "flw_instance")
public class FlwInstance extends FlowEntity {
    /**
     * 流程定义ID
     */
    @Column(value = "process_id")
    protected String processId;
    /**
     * 父流程实例ID
     */
    @Column(value = "parent_instance_id")
    protected String parentInstanceId;
    /**
     * 流程实例优先级 0，正常 1，异步
     */
    @Column(value = "priority")
    protected Integer priority;
    /**
     * 流程实例编号
     */
    @Column(value = "instance_no")
    protected String instanceNo;
    /**
     * 业务KEY（用于关联业务逻辑实现预留）
     *
     * <p>
     * 子流程情况，该字段用于存放父流程所在节点KEY
     * </p>
     */
    @Column(value = "business_key")
    protected String businessKey;
    /**
     * 变量json
     */
    @Column(value = "variable")
    protected String variable;
    /**
     * 当前所在节点名称
     */
    @Column(value = "current_node_name")
    protected String currentNodeName;
    /**
     * 当前所在节点key
     */
    @Column(value = "current_node_key")
    protected String currentNodeKey;
    /**
     * 流程实例期望完成时间
     */
    @Column(value = "expire_time")
    protected Date expireTime;
    /**
     * 流程实例上一次更新人
     */
    @Column(value = "last_update_by")
    protected String lastUpdateBy;
    /**
     * 流程实例上一次更新时间
     */
    @Column(value = "last_update_time")
    protected Date lastUpdateTime;

    public FlwInstance() {
        // 默认优先级 0
        this.priority = 0;
    }

    public static FlwInstance of(String businessKey) {
        FlwInstance flwInstance = new FlwInstance();
        flwInstance.setBusinessKey(businessKey);
        return flwInstance;
    }

    public void priority(InstancePriority instancePriority) {
        this.priority = instancePriority.getValue();
    }

    @SuppressWarnings({"all"})
    public Map<String, Object> variableToMap() {
        Map<String, Object> map = FlowLongContext.fromJson(this.variable, Map.class);
        return null != map ? map : new HashMap<>();
    }

    public void putAllVariable(Map<String, Object> args) {
        this.variable = FlowLongContext.putAllVariable(this.variable, args);
    }

}
