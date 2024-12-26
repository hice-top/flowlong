/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.hicetop.bpm.engine.dao.FlwExtInstanceDao;
import io.github.hicetop.bpm.engine.entity.FlwExtInstance;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwExtInstanceMapper;

/**
 * 扩展流程实例数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwExtInstanceDaoImpl implements FlwExtInstanceDao {
    private final FlwExtInstanceMapper extInstanceMapper;

    public FlwExtInstanceDaoImpl(FlwExtInstanceMapper extInstanceMapper) {
        this.extInstanceMapper = extInstanceMapper;
    }

    @Override
    public boolean insert(FlwExtInstance extInstance) {
        return extInstanceMapper.insert(extInstance) > 0;
    }

    @Override
    public boolean deleteByProcessId(String processId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwExtInstance::getProcessId).eq(processId);
        return extInstanceMapper.deleteByQuery(wrapper) > 0;
    }

    @Override
    public boolean deleteById(String instanceId) {
        return extInstanceMapper.deleteById(instanceId) > 0;
    }

    @Override
    public boolean updateById(FlwExtInstance extInstance) {
        return extInstanceMapper.update(extInstance) > 0;
    }

    @Override
    public FlwExtInstance selectById(String id) {
        return extInstanceMapper.selectOneById(id);
    }
}
