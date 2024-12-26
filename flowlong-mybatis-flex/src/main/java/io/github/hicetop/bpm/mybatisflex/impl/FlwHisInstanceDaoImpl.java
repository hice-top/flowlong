/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwHisInstanceDao;
import io.github.hicetop.bpm.engine.entity.FlwHisInstance;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwHisInstanceMapper;

import java.util.List;
import java.util.Optional;

/**
 * 历史流程实例数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwHisInstanceDaoImpl implements FlwHisInstanceDao {
    private final FlwHisInstanceMapper hisInstanceMapper;

    public FlwHisInstanceDaoImpl(FlwHisInstanceMapper hisInstanceMapper) {
        this.hisInstanceMapper = hisInstanceMapper;
    }

    @Override
    public boolean insert(FlwHisInstance hisInstance) {
        return hisInstanceMapper.insert(hisInstance) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return hisInstanceMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteByProcessId(String processId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisInstance::getProcessId).eq(processId);
        return hisInstanceMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean updateById(FlwHisInstance hisInstance) {
        return hisInstanceMapper.update(hisInstance) > 0;
    }

    @Override
    public FlwHisInstance selectById(String id) {
        return hisInstanceMapper.selectOneById(id);
    }

    private Optional<List<FlwHisInstance>> ofNullable(List<FlwHisInstance> hisInstances) {
        return Optional.ofNullable(null == hisInstances || hisInstances.isEmpty() ? null : hisInstances);
    }

    @Override
    public Optional<List<FlwHisInstance>> selectListByProcessId(String processId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisInstance::getProcessId).eq(processId);
        return this.ofNullable(hisInstanceMapper.selectListByQuery(wrapper));
    }

    @Override
    public Optional<List<FlwHisInstance>> selectListByBusinessKey(String businessKey) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwHisInstance::getBusinessKey).eq(businessKey);
        return this.ofNullable(hisInstanceMapper.selectListByQuery(wrapper));
    }
}
