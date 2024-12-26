/*
 * Copyright 2023-2025 Licensed under the Dual Licensing
 * website: https://aizuda.com
 */
package io.github.hicetop.bpm.engine.impl;

import io.github.hicetop.bpm.engine.TaskActorProvider;
import io.github.hicetop.bpm.engine.assist.ObjectUtils;
import io.github.hicetop.bpm.engine.core.Execution;
import io.github.hicetop.bpm.engine.core.enums.NodeSetType;
import io.github.hicetop.bpm.engine.entity.FlwTaskActor;
import io.github.hicetop.bpm.engine.model.NodeAssignee;
import io.github.hicetop.bpm.engine.model.NodeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 普遍的任务参与者提供处理类
 *
 * <p>
 * <a href="https://aizuda.com">官网</a>尊重知识产权，不允许非法使用，后果自负
 * </p>
 *
 * @author hubin
 * @since 1.0
 */
public class GeneralTaskActorProvider implements TaskActorProvider {

    private static Integer getActorType(NodeModel nodeModel) {
        // 0，用户
        if (NodeSetType.specifyMembers.eq(nodeModel.getSetType())
                || NodeSetType.initiatorThemselves.eq(nodeModel.getSetType())
                || NodeSetType.initiatorSelected.eq(nodeModel.getSetType())) {
            return 0;
        }

        // 1，角色
        if (NodeSetType.role.eq(nodeModel.getSetType())) {
            return 1;
        }

        // 2，部门
        if (NodeSetType.department.eq(nodeModel.getSetType())) {
            return 2;
        }
        return null;
    }

    public List<FlwTaskActor> getTaskActors(NodeModel nodeModel, Execution execution) {
        List<FlwTaskActor> flwTaskActors = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(nodeModel.getNodeAssigneeList())) {
            final Integer actorType = getActorType(nodeModel);
            if (null != actorType) {
                for (NodeAssignee nodeAssignee : nodeModel.getNodeAssigneeList()) {
                    flwTaskActors.add(FlwTaskActor.of(nodeAssignee, actorType));
                }
            }
        }
        return ObjectUtils.isEmpty(flwTaskActors) ? null : flwTaskActors;
    }
}
