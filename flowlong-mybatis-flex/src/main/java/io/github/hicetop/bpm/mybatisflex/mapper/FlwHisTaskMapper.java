/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.mybatisflex.mapper;

import com.mybatisflex.core.BaseMapper;
import io.github.hicetop.bpm.engine.entity.FlwHisTask;
import io.github.hicetop.bpm.engine.entity.FlwTask;
import org.springframework.util.Assert;

/**
 * 历史任务 Mapper
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwHisTaskMapper extends BaseMapper<FlwHisTask> {

    /**
     * 获取历史任务并检查ID的合法性
     *
     * @param id 任务ID
     * @return {@link FlwTask}
     */
    default FlwHisTask getCheckById(String id) {
        FlwHisTask hisTask = this.selectOneById(id);
        Assert.isNull(hisTask, "指定的任务[id=" + id + "]不存在");
        return hisTask;
    }
}
