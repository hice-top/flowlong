/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.listener;

import io.github.hicetop.bpm.engine.core.FlowCreator;
import io.github.hicetop.bpm.engine.model.NodeModel;

import java.util.function.Supplier;

/**
 * 流程引擎监听接口
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public interface FlowLongListener<E, T> {

    /**
     * 流程引擎监听通知
     *
     * @param eventType   事件类型
     * @param supplier    监听实体提供者
     * @param nodeModel   当前执行节点 {@link NodeModel}
     * @param flowCreator 处理人员
     * @return 通知结果 true 成功 false 失败
     */
    boolean notify(E eventType, Supplier<T> supplier, NodeModel nodeModel, FlowCreator flowCreator);

}
