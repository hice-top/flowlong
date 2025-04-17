package test.config;


import io.github.hicetop.bpm.engine.entity.FlwTaskActor;
import io.github.hicetop.bpm.engine.impl.GeneralAccessStrategy;

import java.util.List;
import java.util.Objects;

/**
 * 测试任务访问策略类
 */
public class TestTaskAccessStrategy extends GeneralAccessStrategy {

    @Override
    public FlwTaskActor isAllowed(String userId, List<FlwTaskActor> taskActors) {
        if (null != taskActors) {
            // 角色部门情况测试不做验证直接允许认领，实际生产环境必须验证
            if (taskActors.stream().anyMatch(t -> !Objects.equals(0, t.getActorType()))) {
                return taskActors.get(0);
            }
        }
        return super.isAllowed(userId, taskActors);
    }
}
