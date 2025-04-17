/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.entity.FlwInstance;

import java.util.List;
import java.util.Optional;

/**
 * 流程实例数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwInstanceDao {

    boolean insert(FlwInstance flwInstance);

    boolean deleteById(String id);

    boolean deleteByProcessId(String processId);

    boolean updateById(FlwInstance instance);

    Long selectCountByParentInstanceId(String parentInstanceId);

    FlwInstance selectById(String id);

    Optional<List<FlwInstance>> selectListByParentInstanceId(String parentInstanceId);

    Optional<List<FlwInstance>> selectListByBusinessKey(String businessKey);
}
