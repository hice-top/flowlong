package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.config.SqlConfigTest;

/**
 * 测试触发器任务结束
 */
@Slf4j
public class TestTriggerEnd extends SqlConfigTest {

    @BeforeEach
    public void before() {
        processId = this.deployByResource("test/testTriggerEnd.json", testCreator);
    }

    @Test
    public void test() {
        flowLongEngine.startInstanceById(processId, testCreator).ifPresent(instance -> {

            // 人事审批
            this.executeActiveTasks(instance.getId(), t ->
                    flowLongEngine.executeTask(t.getId(), test3Creator)
            );

            // 忽略查询时间执行定时器任务
            this.executeActiveTasks(instance.getId(), flwTask ->

                    // 模拟自动完成定时触发器任务
                    flowLongEngine.autoCompleteTask(flwTask.getId()));
        });
    }
}