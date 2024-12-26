/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.listener;

import io.github.hicetop.bpm.engine.core.enums.TaskEventType;
import io.github.hicetop.bpm.engine.entity.FlwTask;

/**
 * 流程任务监听
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface TaskListener extends FlowLongListener<TaskEventType, FlwTask> {

}
