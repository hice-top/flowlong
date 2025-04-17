/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.processor.util.StrUtil;
import io.github.hicetop.bpm.engine.entity.FlwProcess;

import java.util.List;

/**
 * 流程定义 Mapper
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwProcessMapper extends BaseMapper<FlwProcess> {

    default List<FlwProcess> selectListByProcessKey(String tenantId, String processKey) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwProcess::getProcessKey).eq(processKey)
                .and(FlwProcess::getTenantId).eq(tenantId, !StrUtil.isBlank(tenantId))
                .orderBy(FlwProcess::getProcessVersion).desc();
        return this.selectListByQuery(wrapper);
    }
}
