/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.entity.FlwHisTaskActor;

import java.util.List;

/**
 * 历史任务参与者 Mapper
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwHisTaskActorMapper extends BaseMapper<FlwHisTaskActor> {

    /**
     * 通过任务ID获取参与者列表
     *
     * @param taskId 任务ID
     * @return 参与者列表
     */
    default List<FlwHisTaskActor> selectListByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getTaskId).eq(taskId);
        return this.selectListByQuery(wrapper);
    }

    /**
     * 通过任务ID获取参与者列表
     *
     * @param taskIds 任务ID列表
     * @return 历史任务参与者列表
     */
    default List<FlwHisTaskActor> selectListByTaskIds(List<String> taskIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getTaskId).in(taskIds);
        return this.selectListByQuery(wrapper);
    }

}
