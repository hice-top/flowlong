/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.entity.FlwTaskActor;

import java.util.List;

/**
 * 任务参与者数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwTaskActorDao {

    boolean insert(FlwTaskActor taskActor);

    boolean deleteById(String id);

    boolean deleteByIds(List<String> ids);

    boolean deleteByTaskId(String taskId);

    boolean deleteByInstanceIds(List<String> instanceIds);

    boolean deleteByTaskIdAndAgentType(String taskId, int agentType);

    boolean deleteByTaskIdAndActorIds(String taskId, List<String> actorIds);

    boolean updateById(FlwTaskActor taskActor);

    List<FlwTaskActor> selectListByInstanceId(String instanceId);

    List<FlwTaskActor> selectListByTaskId(String taskId);

    List<FlwTaskActor> selectListByTaskIds(List<String> taskIds);

    List<FlwTaskActor> selectListByTaskIdAndActorId(String taskId, String actorId);

    List<FlwTaskActor> selectListByActorId(String actorId);

    Long selectCountByTaskIdAndActorId(String taskId, String actorId);
}
