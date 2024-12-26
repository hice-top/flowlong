/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwTaskActorDao;
import io.github.hicetop.bpm.engine.entity.FlwTaskActor;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwTaskActorMapper;

import java.util.List;

/**
 * 任务参与者数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwTaskActorDaoImpl implements FlwTaskActorDao {
    private final FlwTaskActorMapper taskActorMapper;

    public FlwTaskActorDaoImpl(FlwTaskActorMapper taskActorMapper) {
        this.taskActorMapper = taskActorMapper;
    }

    @Override
    public boolean insert(FlwTaskActor taskActor) {
        return taskActorMapper.insert(taskActor) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return taskActorMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByIds(List<String> ids) {
        return taskActorMapper.deleteBatchByIds(ids) > 0;
    }

    @Override
    public boolean deleteByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId);
        return taskActorMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean deleteByInstanceIds(List<String> instanceIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getInstanceId).in(instanceIds);
        return taskActorMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean deleteByTaskIdAndAgentType(String taskId, int agentType) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId)
                .and(FlwTaskActor::getAgentType).eq(agentType);
        return taskActorMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean deleteByTaskIdAndActorIds(String taskId, List<String> actorIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId)
                .and(FlwTaskActor::getActorId).in(actorIds);
        return taskActorMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean updateById(FlwTaskActor taskActor) {
        return taskActorMapper.update(taskActor) > 0;
    }

    @Override
    public List<FlwTaskActor> selectListByInstanceId(String instanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getInstanceId).eq(instanceId);
        return taskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTaskActor> selectListByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId);
        return taskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTaskActor> selectListByTaskIds(List<String> taskIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).in(taskIds);
        return taskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTaskActor> selectListByTaskIdAndActorId(String taskId, String actorId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId)
                .and(FlwTaskActor::getActorId).eq(actorId);
        return taskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwTaskActor> selectListByActorId(String actorId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getActorId).eq(actorId);
        return taskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public Long selectCountByTaskIdAndActorId(String taskId, String actorId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwTaskActor::getTaskId).eq(taskId)
                .and(FlwTaskActor::getActorId).eq(actorId);
        return taskActorMapper.selectCountByQuery(wrapper);
    }
}
