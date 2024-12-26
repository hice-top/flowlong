/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package test;

import io.github.hicetop.bpm.engine.FlowLongEngine;
import io.github.hicetop.bpm.engine.core.FlowCreator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试流程引擎抽象类
 */
public abstract class TestFlowLong {
    /**
     * 流程定义ID
     */
    protected String processId;
    /**
     * 测试用户1
     */
    protected String testUser1 = "test001";
    /**
     * 测试用户2
     */
    protected String testUser2 = "test002";

    /**
     * 测试用户3
     */
    protected String testUser3 = "test003";

    @Autowired
    protected FlowLongEngine flowLongEngine;

    protected String deployByResource(String resourceName, FlowCreator flowCreator) {
        return flowLongEngine.processService().deployByResource(resourceName, flowCreator, false);
    }
}
