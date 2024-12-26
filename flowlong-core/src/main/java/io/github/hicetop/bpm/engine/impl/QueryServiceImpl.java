/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.impl;

import io.github.hicetop.bpm.engine.QueryService;
import io.github.hicetop.bpm.engine.dao.*;
import io.github.hicetop.bpm.engine.entity.*;

import java.util.List;
import java.util.Optional;

/**
 * 查询服务实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class QueryServiceImpl implements QueryService {
    private final FlwInstanceDao instanceDao;
    private final FlwHisInstanceDao hisInstanceDao;
    private final FlwExtInstanceDao extInstanceDao;
    private final FlwTaskDao taskDao;
    private final FlwTaskActorDao taskActorDao;
    private final FlwHisTaskDao hisTaskDao;
    private final FlwHisTaskActorDao hisTaskActorDao;

    public QueryServiceImpl(FlwInstanceDao instanceDao, FlwHisInstanceDao hisInstanceDao,
                            FlwExtInstanceDao extInstanceDao, FlwTaskDao taskDao, FlwTaskActorDao taskActorDao,
                            FlwHisTaskDao hisTaskDao, FlwHisTaskActorDao hisTaskActorDao) {
        this.instanceDao = instanceDao;
        this.hisInstanceDao = hisInstanceDao;
        this.extInstanceDao = extInstanceDao;
        this.taskDao = taskDao;
        this.taskActorDao = taskActorDao;
        this.hisTaskDao = hisTaskDao;
        this.hisTaskActorDao = hisTaskActorDao;
    }

    @Override
    public FlwInstance getInstance(String instanceId) {
        return instanceDao.selectById(instanceId);
    }

    @Override
    public FlwTask getTask(String taskId) {
        return taskDao.selectById(taskId);
    }

    @Override
    public FlwHisInstance getHistInstance(String instanceId) {
        return hisInstanceDao.selectById(instanceId);
    }

    @Override
    public FlwExtInstance getExtInstance(String instanceId) {
        return extInstanceDao.selectById(instanceId);
    }

    @Override
    public boolean existActiveSubProcess(String instanceId) {
        return instanceDao.selectCountByParentInstanceId(instanceId) > 0;
    }

    @Override
    public FlwHisTask getHistTask(String taskId) {
        return hisTaskDao.selectById(taskId);
    }

    @Override
    public Optional<List<FlwHisTask>> getHisTasksByName(String instanceId, String taskName) {
        return Optional.ofNullable(hisTaskDao.selectListByInstanceIdAndTaskName(instanceId, taskName));
    }

    @Override
    public List<FlwTask> getTasksByInstanceId(String instanceId) {
        return taskDao.selectListByInstanceId(instanceId);
    }

    @Override
    public List<FlwTask> getTasksByInstanceIdAndTaskName(String instanceId, String taskName) {
        return taskDao.selectListByInstanceIdAndTaskName(instanceId, taskName);
    }

    @Override
    public List<FlwTask> getTasksByInstanceIdAndTaskKey(String instanceId, String taskKey) {
        return taskDao.selectListByInstanceIdAndTaskKey(instanceId, taskKey);
    }

    @Override
    public Optional<List<FlwTaskActor>> getActiveTaskActorsByInstanceId(String instanceId) {
        return Optional.ofNullable(taskActorDao.selectListByInstanceId(instanceId));
    }

    @Override
    public List<FlwTaskActor> getTaskActorsByTaskId(String taskId) {
        return taskActorDao.selectListByTaskId(taskId);
    }

    @Override
    public List<FlwTaskActor> getTaskActorsByTaskIdAndActorId(String taskId, String actorId) {
        return taskActorDao.selectListByTaskIdAndActorId(taskId, actorId);
    }

    @Override
    public List<FlwHisTaskActor> getHisTaskActorsByTaskId(String taskId) {
        return hisTaskActorDao.selectListByTaskId(taskId);
    }

    @Override
    public List<FlwHisTaskActor> getHisTaskActorsByTaskIdAndActorId(String taskId, String actorId) {
        return hisTaskActorDao.selectListByTaskIdAndActorId(taskId, actorId);
    }

    @Override
    public List<FlwTask> getActiveTasks(String instanceId, List<String> taskNames) {
        return taskDao.selectListByInstanceIdAndTaskNames(instanceId, taskNames);
    }

    @Override
    public Optional<List<FlwHisTask>> getHisTasksByInstanceId(String instanceId) {
        return hisTaskDao.selectListByInstanceId(instanceId);
    }

    @Override
    public Optional<List<FlwInstance>> getSubProcessByInstanceId(String instanceId) {
        return instanceDao.selectListByParentInstanceId(instanceId);
    }

    @Override
    public Optional<List<FlwInstance>> getInstancesByBusinessKey(String businessKey) {
        return instanceDao.selectListByBusinessKey(businessKey);
    }

    @Override
    public Optional<List<FlwHisInstance>> getHisInstancesByBusinessKey(String businessKey) {
        return hisInstanceDao.selectListByBusinessKey(businessKey);
    }
}
