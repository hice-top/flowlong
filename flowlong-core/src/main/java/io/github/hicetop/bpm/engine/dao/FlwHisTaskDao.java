/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.dao;

import io.github.hicetop.bpm.engine.assist.Assert;
import io.github.hicetop.bpm.engine.entity.FlwHisTask;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * 历史任务数据访问层接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlwHisTaskDao {

    boolean insert(FlwHisTask hisTask);

    boolean deleteById(String id);

    boolean deleteByInstanceIds(List<String> instanceIds);

    boolean updateById(FlwHisTask hisTask);

    FlwHisTask selectById(String id);

    default FlwHisTask selectCheckById(String id) {
        FlwHisTask flwHisTask = selectById(id);
        Assert.isNull(flwHisTask, "The specified hisTask [id=" + id + "] does not exist");
        return flwHisTask;
    }

    List<FlwHisTask> selectListByInstanceIdAndTaskName(String instanceId, String taskName);

    Optional<List<FlwHisTask>> selectListByInstanceId(String instanceId);

    List<FlwHisTask> selectListByCallProcessIdAndCallInstanceId(String callProcessId, String callInstanceId);

    List<FlwHisTask> selectListByParentTaskId(String parentTaskId);

    Collection<FlwHisTask> selectListByInstanceIdAndTaskNameAndParentTaskId(String instanceId, String taskName, String parentTaskId);
}
