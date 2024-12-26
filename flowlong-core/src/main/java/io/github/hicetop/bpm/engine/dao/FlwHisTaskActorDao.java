/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.entity.FlwHisTaskActor;

import java.util.List;

/**
 * 历史任务参与者数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwHisTaskActorDao {

    boolean insert(FlwHisTaskActor hisTaskActor);

    boolean deleteByInstanceIds(List<String> instanceIds);

    boolean deleteByTaskId(String taskId);

    List<FlwHisTaskActor> selectListByTaskId(String taskId);

    List<FlwHisTaskActor> selectListByTaskIds(List<String> taskIds);

    List<FlwHisTaskActor> selectListByTaskIdAndActorId(String taskId, String actorId);
}
