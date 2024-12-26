/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.entity.FlwExtInstance;

/**
 * 扩展流程实例数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwExtInstanceDao {

    boolean insert(FlwExtInstance extInstance);

    boolean deleteByProcessId(String processId);

    boolean deleteById(String instanceId);

    boolean updateById(FlwExtInstance extInstance);

    FlwExtInstance selectById(String id);
}
