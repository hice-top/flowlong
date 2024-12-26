/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.entity.FlwInstance;

import java.util.List;
import java.util.Optional;

/**
 * 流程实例 Mapper
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwInstanceMapper extends BaseMapper<FlwInstance> {

    default Optional<List<FlwInstance>> listByParentInstanceId(Long parentInstanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwInstance::getParentInstanceId).eq(parentInstanceId);
        return Optional.ofNullable(this.selectListByQuery(wrapper));
    }
}
