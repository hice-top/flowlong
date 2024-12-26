/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwTaskDao;
import io.github.hicetop.bpm.engine.entity.FlwTask;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwTaskMapper;

import java.util.Date;
import java.util.List;

/**
 * 任务数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwTaskDaoImpl implements FlwTaskDao {
    private final FlwTaskMapper taskMapper;

    public FlwTaskDaoImpl(FlwTaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public boolean insert(FlwTask flwTask) {
        return taskMapper.insert(flwTask) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return taskMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByInstanceIds(List<String> instanceIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getInstanceId).in(instanceIds);
        return taskMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean deleteByIds(List<String> ids) {
        return taskMapper.deleteBatchByIds(ids) > 0;
    }

    @Override
    public boolean updateById(FlwTask flwTask) {
        return taskMapper.update(flwTask) > 0;
    }

    @Override
    public FlwTask selectById(String id) {
        return taskMapper.selectOneById(id);
    }

    @Override
    public Long selectCountByParentTaskId(String parentTaskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getParentTaskId).eq(parentTaskId);
        return taskMapper.selectCountByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListByInstanceId(String instanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getInstanceId).eq(instanceId);
        return taskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListByInstanceIdAndTaskName(String instanceId, String taskName) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getInstanceId).eq(instanceId)
                .and(FlwTask::getTaskName).eq(taskName);
        return taskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListByInstanceIdAndTaskKey(String instanceId, String taskKey) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getInstanceId).eq(instanceId)
                .and(FlwTask::getTaskKey).eq(taskKey);
        return taskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListByInstanceIdAndTaskNames(String instanceId, List<String> taskNames) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getInstanceId).eq(instanceId)
                .and(FlwTask::getTaskName).in(taskNames);
        return taskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListTimeoutOrRemindTasks(Date currentDate) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getExpireTime).le(currentDate)
                .or(FlwTask::getRemindTime).le(currentDate);
        return taskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListByParentTaskId(String parentTaskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getParentTaskId).eq(parentTaskId);
        return taskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTask> selectListByParentTaskIds(List<String> parentTaskIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTask::getParentTaskId).in(parentTaskIds);
        return taskMapper.selectListByQuery(wrapper);
    }
}
