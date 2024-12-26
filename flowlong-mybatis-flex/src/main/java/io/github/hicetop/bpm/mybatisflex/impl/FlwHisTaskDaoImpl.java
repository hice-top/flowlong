/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwHisTaskDao;
import io.github.hicetop.bpm.engine.entity.FlwHisTask;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwHisTaskMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 历史任务数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwHisTaskDaoImpl implements FlwHisTaskDao {
    private final FlwHisTaskMapper hisTaskMapper;

    public FlwHisTaskDaoImpl(FlwHisTaskMapper hisTaskMapper) {
        this.hisTaskMapper = hisTaskMapper;
    }

    @Override
    public boolean insert(FlwHisTask hisTask) {
        return hisTaskMapper.insert(hisTask) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return hisTaskMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByInstanceIds(List<String> instanceIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTask::getInstanceId).in(instanceIds);
        return hisTaskMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean updateById(FlwHisTask hisTask) {
        return hisTaskMapper.update(hisTask) > 0;
    }

    @Override
    public FlwHisTask selectById(String id) {
        return hisTaskMapper.selectOneById(id);
    }

    @Override
    public List<FlwHisTask> selectListByInstanceIdAndTaskName(String instanceId, String taskName) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTask::getInstanceId).eq(instanceId)
                .and(FlwHisTask::getTaskName).eq(taskName);
        return hisTaskMapper.selectListByQuery(wrapper);
    }

    @Override
    public Optional<List<FlwHisTask>> selectListByInstanceId(String instanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTask::getInstanceId).eq(instanceId);
        return Optional.ofNullable(hisTaskMapper.selectListByQuery(wrapper));
    }

    @Override
    public List<FlwHisTask> selectListByCallProcessIdAndCallInstanceId(String callProcessId, String callInstanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTask::getCallProcessId).eq(callProcessId)
                .and(FlwHisTask::getCallInstanceId).eq(callInstanceId);
        return hisTaskMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwHisTask> selectListByParentTaskId(String parentTaskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTask::getParentTaskId).eq(parentTaskId);
        return hisTaskMapper.selectListByQuery(wrapper);
    }

    @Override
    public Collection<FlwHisTask> selectListByInstanceIdAndTaskNameAndParentTaskId(String instanceId, String taskName, String parentTaskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTask::getInstanceId).eq(instanceId)
                .and(FlwHisTask::getTaskName).eq(taskName)
                .and(FlwHisTask::getParentTaskId).eq(parentTaskId);
        return hisTaskMapper.selectListByQuery(wrapper);
    }
}
