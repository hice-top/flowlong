/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.entity.FlwTask;

import java.util.List;

/**
 * 任务 Mapper
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwTaskMapper extends BaseMapper<FlwTask> {

    /**
     * 根据流程实例ID获取任务列表
     *
     * @param instanceId 流程实例ID
     * @return 任务列表
     */
    default List<FlwTask> selectListByInstanceId(String instanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getInstanceId).eq(instanceId);
        return this.selectListByQuery(wrapper);
    }

    /**
     * 根据父任务ID获取任务列表
     *
     * @param parentTaskId 父任务ID
     * @return 任务列表
     */
    default List<FlwTask> selectListByParentTaskId(String parentTaskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getParentTaskId).eq(parentTaskId);
        return this.selectListByQuery(wrapper);
    }

    /**
     * 根据父任务ID获取任务数量
     *
     * @param parentTaskId 父任务ID
     * @return 任务数量
     */
    default Long selectCountByParentTaskId(String parentTaskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getParentTaskId).eq(parentTaskId);
        return this.selectCountByQuery(wrapper);
    }

}
