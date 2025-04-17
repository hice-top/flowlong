/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.entity.FlwHisInstance;

import java.util.List;
import java.util.Optional;

/**
 * 历史流程实例数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwHisInstanceDao {

    boolean insert(FlwHisInstance hisInstance);

    boolean deleteById(String id);

    boolean deleteByProcessId(String processId);

    boolean updateById(FlwHisInstance hisInstance);

    FlwHisInstance selectById(String id);

    Optional<List<FlwHisInstance>> selectListByProcessId(String processId);

    Optional<List<FlwHisInstance>> selectListByBusinessKey(String businessKey);
}
