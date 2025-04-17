/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.github.hicetop.bpm.engine.FlowConstants;
import io.github.hicetop.bpm.engine.ProcessModelCache;
import io.github.hicetop.bpm.engine.core.FlowLongContext;
import io.github.hicetop.bpm.engine.model.ProcessModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * 扩展流程实例实体类
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
@Table(value = "flw_ext_instance")
public class FlwExtInstance implements ProcessModelCache, Serializable {
    /**
     * 主键ID
     */
    @Id(keyType = KeyType.Generator, value = "flexId")
    private String id;
    /**
     * 租户ID
     */
    @Column(value = "tenant_id")
    private String tenantId;
    /**
     * 流程定义ID
     */
    @Column(value = "process_id")
    private String processId;
    /**
     * 流程定义名称（冗余业务直接可用）
     */
    @Column(value = "process_name")
    protected String processName;
    /**
     * 流程定义类型（冗余业务直接可用）
     */
    @Column(value = "process_type")
    protected String processType;
    /**
     * 流程模型定义JSON内容
     * <p>
     * 在发起的时候拷贝自流程定义模型内容，用于记录当前实例节点的动态改变。
     * </p>
     */
    @Column(value = "model_content")
    private String modelContent;

    public static FlwExtInstance of(FlwInstance flwInstance, FlwProcess flwProcess) {
        FlwExtInstance ext = new FlwExtInstance();
        ext.id = flwInstance.getId();
        ext.tenantId = flwInstance.getTenantId();
        ext.processId = flwInstance.getProcessId();
        ext.processName = flwProcess.getProcessName();
        ext.processType = flwProcess.getProcessType();
        ext.modelContent = flwProcess.getModelContent();
        return ext;
    }

    @Override
    public String modelCacheKey() {
        return FlowConstants.processInstanceCacheKey + this.id;
    }

    public static ProcessModel cacheProcessModelById(String id, Supplier<ProcessModel> supplier) {
        ProcessModel processModel = FlowLongContext.parseProcessModel(null, FlowConstants.processInstanceCacheKey + id, false);
        if (null == processModel && null != supplier) {
            processModel = supplier.get();
        }
        return processModel;
    }

}
