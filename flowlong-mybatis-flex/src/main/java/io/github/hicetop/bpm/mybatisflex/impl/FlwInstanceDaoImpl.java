/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwInstanceDao;
import io.github.hicetop.bpm.engine.entity.FlwInstance;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwInstanceMapper;

import java.util.List;
import java.util.Optional;

/**
 * 流程实例数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwInstanceDaoImpl implements FlwInstanceDao {
    private final FlwInstanceMapper instanceMapper;

    public FlwInstanceDaoImpl(FlwInstanceMapper instanceMapper) {
        this.instanceMapper = instanceMapper;
    }

    @Override
    public boolean insert(FlwInstance instance) {
        return instanceMapper.insert(instance) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return instanceMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByProcessId(String processId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwInstance::getProcessId).eq(processId);
        return instanceMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean updateById(FlwInstance instance) {
        return instanceMapper.update(instance) > 0;
    }

    @Override
    public Long selectCountByParentInstanceId(String parentInstanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwInstance::getParentInstanceId).eq(parentInstanceId);
        return instanceMapper.selectCountByQuery(wrapper);
    }

    @Override
    public FlwInstance selectById(String id) {
        return instanceMapper.selectOneById(id);
    }

    @Override
    public Optional<List<FlwInstance>> selectListByParentInstanceId(String parentInstanceId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwInstance::getParentInstanceId).eq(parentInstanceId);
        return Optional.ofNullable(instanceMapper.selectListByQuery(wrapper));
    }

    @Override
    public Optional<List<FlwInstance>> selectListByBusinessKey(String businessKey) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwInstance::getBusinessKey).eq(businessKey);
        return Optional.ofNullable(instanceMapper.selectListByQuery(wrapper));
    }
}
