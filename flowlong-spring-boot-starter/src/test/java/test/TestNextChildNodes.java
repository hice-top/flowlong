package test;

import io.github.hicetop.bpm.engine.FlowLongEngine;
import io.github.hicetop.bpm.engine.core.Execution;
import io.github.hicetop.bpm.engine.model.ModelHelper;
import io.github.hicetop.bpm.engine.model.NodeModel;
import io.github.hicetop.bpm.engine.model.ProcessModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TestNextChildNodes extends TestModel {

    @Autowired
    protected FlowLongEngine flowLongEngine;

    @Test
    public void testNextChildNodes() {
        ProcessModel processModel = getProcessModel("test/testNextChildNodes.json");
        processModel.buildParentNode(processModel.getNodeConfig());

        assertEquals("flk1733396309547", "flk1733380944015", processModel);
        assertEquals("flk1733380972879", "flk1733396309547", processModel);
        assertEquals("flk1733446929109", "flk1733380972879", processModel);

        assertEquals("flk1733446923640", "flk1733380972879", processModel, new HashMap<String, Object>() {{
            put("aaa", "11");
        }});

        // 下一步执行到并行分支
        assertConsumer("flk1733446929109", processModel, null, nodeModels -> Assertions.assertEquals(2, nodeModels.size()));

        assertEquals("flk1733446917296", "flk1733446912655", processModel);

        // 路由分支
        assertEquals("flk1733446923640", "flk1733446917296", processModel, new HashMap<String, Object>() {{
            put("aaa", "11");
        }});

        // 跳过路由分支，包容分支执行默认条件
        assertEquals("flk1733658019276", "flk1733446917296", processModel);

        // 包容分支
        assertEquals("flk1733658095760", "flk1733446917296", processModel, new HashMap<String, Object>() {{
            put("bbb", "11");
        }});

        assertEquals("flk1733658019276", "flk1733658095760", processModel);

        // 结束
        assertConsumer("flk1733658019276", processModel, null, nodeModels -> Assertions.assertTrue(nodeModels.isEmpty()));
    }

    private void assertEquals(String nextNodeKey, String nodeKey, ProcessModel processModel) {
        assertEquals(nextNodeKey, nodeKey, processModel, null);
    }

    private void assertEquals(String nextNodeKey, String nodeKey, ProcessModel processModel, Map<String, Object> args) {
        assertConsumer(nodeKey, processModel, args, nodeModels -> Assertions.assertEquals(nextNodeKey, nodeModels.get(0).getNodeKey()));
    }

    private void assertConsumer(String nodeKey, ProcessModel processModel, Map<String, Object> args, Consumer<List<NodeModel>> consumer) {
        List<NodeModel> nodeModels = ModelHelper.getNextChildNodes(flowLongEngine.getContext(), new Execution(testCreator, args),
                processModel.getNodeConfig(), nodeKey);
        if (null != consumer) {
            consumer.accept(nodeModels);
        }
    }
}
