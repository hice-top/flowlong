/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.processor.util.StrUtil;
import io.github.hicetop.bpm.engine.dao.FlwProcessDao;
import io.github.hicetop.bpm.engine.entity.FlwProcess;
import io.github.hicetop.bpm.mybatisflex.mapper.FlwProcessMapper;

import java.util.List;

/**
 * 流程定义数据访问层接口实现类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class FlwProcessDaoImpl implements FlwProcessDao {
    private final FlwProcessMapper processMapper;

    public FlwProcessDaoImpl(FlwProcessMapper processMapper) {
        this.processMapper = processMapper;
    }

    @Override
    public boolean insert(FlwProcess process) {
        return processMapper.insert(process) > 0;
    }

    @Override
    public boolean deleteById(String id) {
        return processMapper.deleteById(id) > 0;
    }

    @Override
    public boolean updateById(FlwProcess process) {
        return processMapper.update(process) > 0;
    }

    @Override
    public boolean updateByProcessKey(FlwProcess process, String tenantId, String processKey) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwProcess::getTenantId).eq(tenantId, !StrUtil.isBlank(tenantId))
                .and(FlwProcess::getProcessKey).eq(processKey);
        return processMapper.updateByQuery(process, wrapper) > 0;
    }

    @Override
    public FlwProcess selectById(String id) {
        return processMapper.selectOneById(id);
    }

    @Override
    public List<FlwProcess> selectListByProcessKeyAndVersion(String tenantId, String processKey, Integer version) {
        QueryWrapper wrapper = QueryWrapper.create()
                .where(FlwProcess::getTenantId).eq(tenantId, !StrUtil.isBlank(tenantId))
                .and(FlwProcess::getProcessKey).eq(processKey);
        if (null != version) {
            wrapper.and(FlwProcess::getProcessVersion).eq(version);
        } else {
            wrapper.orderBy(FlwProcess::getProcessVersion).desc();
        }
        return processMapper.selectListByQuery(wrapper);
    }
}
