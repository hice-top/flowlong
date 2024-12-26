package test;

import io.github.hicetop.bpm.engine.TaskTrigger;
import io.github.hicetop.bpm.engine.core.Execution;
import io.github.hicetop.bpm.engine.model.NodeModel;

public class TaskTriggerImpl implements TaskTrigger {

    @Override
    public boolean execute(NodeModel nodeModel, Execution execution) {
        System.out.println("执行了触发器 args = " + nodeModel.getExtendConfig().get("args"));
        return true;
    }
}
