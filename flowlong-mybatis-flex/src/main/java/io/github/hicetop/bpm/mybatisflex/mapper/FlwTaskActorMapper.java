/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.entity.FlwTaskActor;

import java.util.List;

/**
 * 任务参与者 Mapper
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwTaskActorMapper extends BaseMapper<FlwTaskActor> {

    /**
     * 通过任务ID获取参与者列表
     *
     * @param taskId 任务ID
     * @return 参与者列表
     */
    default List<FlwTaskActor> selectListByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId);
        return this.selectListByQuery(wrapper);
    }

    /**
     * 通过任务ID列表获取参与者列表
     *
     * @param taskIds 任务ID列表
     * @return 参与者列表
     */
    default List<FlwTaskActor> selectListByTaskIds(List<String> taskIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).in(taskIds);
        return this.selectListByQuery(wrapper);
    }

    /**
     * 通过流程实例ID获取参与者列表
     *
     * @param instanceId 流程实例ID
     * @return 参与者列表
     */
    default List<FlwTaskActor> selectListByInstanceId(String instanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getInstanceId).eq(instanceId);
        return this.selectListByQuery(wrapper);
    }

    /**
     * 通过任务ID删除参与者
     *
     * @param taskId 任务ID
     * @return true 成功 false 失败
     */
    default boolean deleteByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId);
        return this.deleteByQuery(wrapper) > 0;
    }

    /**
     * 通过任务ID删除参与者
     *
     * @param taskIds 任务ID列表
     * @return true 成功 false 失败
     */
    default boolean deleteByTaskIds(List<String> taskIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).in(taskIds);
        return this.deleteByQuery(wrapper) > 0;
    }

}
