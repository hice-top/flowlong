/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.impl;

import io.github.hicetop.bpm.engine.FlowLongIdGenerator;

/**
 * 数据访问层ID生成器默认实现
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class DefaultFlowLongIdGenerator implements FlowLongIdGenerator {

    @Override
    public String getId(String id) {
        // 不做任何处理
        return id;
    }
}
