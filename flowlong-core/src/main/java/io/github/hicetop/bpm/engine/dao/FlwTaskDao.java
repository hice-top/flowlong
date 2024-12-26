/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.assist.Assert;
import io.github.hicetop.bpm.engine.entity.FlwTask;

import java.util.Date;
import java.util.List;

/**
 * 任务数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwTaskDao {

    boolean insert(FlwTask task);

    boolean deleteById(String id);

    boolean deleteByInstanceIds(List<String> instanceIds);

    boolean deleteByIds(List<String> ids);

    boolean updateById(FlwTask flwTask);

    FlwTask selectById(String id);

    default FlwTask selectCheckById(String id) {
        FlwTask task = selectById(id);
        Assert.isNull(task, "The specified task [id=" + id + "] does not exist");
        return task;
    }

    Long selectCountByParentTaskId(String parentTaskId);

    List<FlwTask> selectListByInstanceId(String instanceId);

    List<FlwTask> selectListByInstanceIdAndTaskName(String instanceId, String taskName);

    /**
     * 根据流程实例ID和任务KEY查询任务
     *
     * @param instanceId 流程实例ID
     * @param taskKey    任务KEY
     * @return 任务列表
     */
    List<FlwTask> selectListByInstanceIdAndTaskKey(String instanceId, String taskKey);

    List<FlwTask> selectListByInstanceIdAndTaskNames(String instanceId, List<String> taskNames);

    List<FlwTask> selectListTimeoutOrRemindTasks(Date currentDate);

    List<FlwTask> selectListByParentTaskId(String parentTaskId);

    List<FlwTask> selectListByParentTaskIds(List<String> parentTaskIds);
}
