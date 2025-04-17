package io.github.hicetop.bpm.spring.example.controller;

import io.github.hicetop.bpm.engine.FlowLongEngine;
import io.github.hicetop.bpm.engine.core.FlowCreator;
import io.github.hicetop.bpm.engine.entity.FlwHisInstance;
import io.github.hicetop.bpm.engine.entity.FlwInstance;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/process")
@AllArgsConstructor
public class TestController {
    protected FlowLongEngine flowLongEngine;

    protected static FlowCreator testCreator = FlowCreator.of("test001", "测试001");

    /**
     * <a href="http://localhost:8000/process/deploy">流程部署</a>
     */
    @GetMapping("/deploy")
    public String deployByResource() {
        String deployed = "";
        try {
            deployed = flowLongEngine.processService().deployByResource("process.json", testCreator, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deployed;
    }

    /**
     * <a href="http://localhost:8000/process/instance-start">启动流程实例</a>
     */
    @GetMapping("/instance-start")
    public FlwInstance instanceStart() {
        Map<String, Object> args = new HashMap<>();
        args.put("day", 8);
        args.put("assignee", "test001");
        FlwInstance process = new FlwHisInstance();
        try {
            process = flowLongEngine.startInstanceByProcessKey("process", null, testCreator, args).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return process;
    }

}
