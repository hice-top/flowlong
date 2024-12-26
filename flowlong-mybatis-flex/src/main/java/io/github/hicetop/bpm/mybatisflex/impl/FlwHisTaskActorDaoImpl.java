/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwHisTaskActorDao;
import io.github.hicetop.bpm.engine.entity.FlwHisTaskActor;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwHisTaskActorMapper;

import java.util.List;

/**
 * 历史任务参与者数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwHisTaskActorDaoImpl implements FlwHisTaskActorDao {
    private final FlwHisTaskActorMapper hisTaskActorMapper;

    public FlwHisTaskActorDaoImpl(FlwHisTaskActorMapper hisTaskActorMapper) {
        this.hisTaskActorMapper = hisTaskActorMapper;
    }

    @Override
    public boolean insert(FlwHisTaskActor hisTaskActor) {
        return hisTaskActorMapper.insert(hisTaskActor) > 0;
    }

    @Override
    public boolean deleteByInstanceIds(List<String> instanceIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getInstanceId).in(instanceIds);
        return hisTaskActorMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean deleteByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getTaskId).eq(taskId);
        return hisTaskActorMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public List<FlwHisTaskActor> selectListByTaskId(String taskId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getTaskId).eq(taskId);
        return hisTaskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwHisTaskActor> selectListByTaskIds(List<String> taskIds) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getTaskId).in(taskIds);
        return hisTaskActorMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<FlwHisTaskActor> selectListByTaskIdAndActorId(String taskId, String actorId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisTaskActor::getTaskId).eq(taskId)
                .and(FlwHisTaskActor::getActorId).eq(actorId);
        return hisTaskActorMapper.selectListByQuery(wrapper);
    }
}
