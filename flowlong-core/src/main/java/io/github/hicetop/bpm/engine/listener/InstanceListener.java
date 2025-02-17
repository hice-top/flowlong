/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.listener;

import io.github.hicetop.bpm.engine.core.enums.InstanceEventType;
import io.github.hicetop.bpm.engine.entity.FlwHisInstance;

/**
 * 流程实例监听
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface InstanceListener extends FlowLongListener<InstanceEventType, FlwHisInstance> {

}
