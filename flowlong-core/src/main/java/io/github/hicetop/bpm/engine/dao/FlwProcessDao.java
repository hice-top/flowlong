/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.entity.FlwProcess;

import java.util.List;

/**
 * 流程定义数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwProcessDao {

    boolean insert(FlwProcess process);

    boolean deleteById(String id);

    boolean updateById(FlwProcess process);

    boolean updateByProcessKey(FlwProcess process, String tenantId, String processKey);

    FlwProcess selectById(String id);

    List<FlwProcess> selectListByProcessKeyAndVersion(String tenantId, String processKey, Integer version);
}
