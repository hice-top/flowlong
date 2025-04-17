package test;

import io.github.hicetop.bpm.engine.FlowLongEngine;
import io.github.hicetop.bpm.engine.entity.FlwTask;
import io.github.hicetop.bpm.engine.entity.FlwTaskActor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.config.SqlConfigTest;

import java.util.List;

public class TestJump extends SqlConfigTest {

    @Autowired
    protected FlowLongEngine flowLongEngine;

    @Test
    public void testJump() {
        String processId = this.deployByResource("test/testJumpTaskActor.json", testCreator);
        flowLongEngine.startInstanceById(processId, testCreator).ifPresent(instance -> {
            executeTask(instance.getId(), testCreator, flwTask -> {
                flowLongEngine.executeJumpTask(flwTask.getId(), "flk1733380972879", testCreator);
            });

            // 跳转后，应该把当前的任务迁移到历史表，重新生成跳转节点的任务
            List<FlwTask> flwTaskList = flowLongEngine.queryService().getTasksByInstanceId(instance.getId());
            Assertions.assertEquals(1, flwTaskList.size());
            FlwTask flwTask = flwTaskList.get(0);
            List<FlwTaskActor> taskActors = flowLongEngine.queryService().getTaskActorsByTaskId(flwTask.getId());
            Assertions.assertEquals(1, taskActors.size());
            Assertions.assertEquals("陈小辉", taskActors.get(0).getActorName());
        });


    }
}