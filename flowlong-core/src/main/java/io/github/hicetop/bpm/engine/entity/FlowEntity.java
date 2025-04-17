/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import io.github.hicetop.bpm.engine.core.FlowCreator;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 流程表实体基类
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
public class FlowEntity implements Serializable {
    /**
     * 主键ID
     */
    @Id(keyType = KeyType.Generator, value = "flexId")
    protected String id;
    /**
     * 租户ID
     */
    @Column(value = "tenant_id")
    protected String tenantId;
    /**
     * 创建人ID
     */
    @Column(value = "create_id")
    protected String createId;
    /**
     * 创建人名称
     */
    @Column(value = "create_by")
    protected String createBy;
    /**
     * 创建时间
     */
    @Column(value = "create_time")
    protected Date createTime;

    public void setFlowCreator(FlowCreator flowCreator) {
        this.tenantId = flowCreator.getTenantId();
        this.createId = flowCreator.getCreateId();
        this.createBy = flowCreator.getCreateBy();
    }
}
