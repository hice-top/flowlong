/*
 Navicat Premium Dump SQL

 Source Server         : 127.0.0.1-mysql
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : 127.0.0.1:3306
 Source Schema         : flowlong

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 26/12/2024 10:08:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flw_ext_instance
-- ----------------------------
DROP TABLE IF EXISTS ` flw_ext_instance `;
CREATE TABLE ` flw_ext_instance `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `process_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程定义ID',
  `process_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流程名称',
  `process_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流程类型',
  `model_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '流程模型定义JSON内容',
  PRIMARY KEY (`id`) USING BTREE,
  CONSTRAINT `fk_ext_instance_id` FOREIGN KEY (`id`) REFERENCES `flw_his_instance` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '扩展流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_his_instance
-- ----------------------------
DROP TABLE IF EXISTS ` flw_his_instance `;
CREATE TABLE ` flw_his_instance `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `create_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `process_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程定义ID',
  `parent_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '父流程实例ID',
  `priority` int NULL DEFAULT NULL COMMENT '优先级',
  `instance_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `business_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务KEY',
  `variable` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '变量json',
  `current_node_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前所在节点名称',
  `current_node_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前所在节点key',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '期望完成时间',
  `last_update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上次更新人',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '上次更新时间',
  `instance_state` int NOT NULL COMMENT '状态 0，审批中 1，审批通过 2，审批拒绝 3，撤销审批 4，超时结束 5，强制终止',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `duration` bigint NULL DEFAULT NULL COMMENT '处理耗时',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_his_instance_process_id`(`process_id` ASC) USING BTREE,
  CONSTRAINT `fk_his_instance_process_id` FOREIGN KEY (`process_id`) REFERENCES `flw_process` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '历史流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_his_task
-- ----------------------------
DROP TABLE IF EXISTS ` flw_his_task `;
CREATE TABLE ` flw_his_task `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `create_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程实例ID',
  `parent_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '父任务ID',
  `call_process_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调用外部流程定义ID',
  `call_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调用外部流程实例ID',
  `task_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
  `task_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务 key 唯一标识',
  `task_type` int NOT NULL COMMENT '任务类型',
  `perform_type` int NULL DEFAULT NULL COMMENT '参与类型',
  `action_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '任务处理的url',
  `variable` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '变量json',
  `assignor_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '委托人ID',
  `assignor` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '委托人',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '任务期望完成时间',
  `remind_time` datetime NULL DEFAULT NULL COMMENT '提醒时间',
  `remind_repeat` int NOT NULL COMMENT '提醒次数',
  `viewed` int NOT NULL COMMENT '已阅 0，否 1，是',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '任务完成时间',
  `task_state` int NOT NULL COMMENT '任务状态 0，活动 1，跳转 2，完成 3，拒绝 4，撤销审批  5，超时 6，终止 7，驳回终止',
  `duration` bigint NULL DEFAULT NULL COMMENT '处理耗时',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_his_task_instance_id`(`instance_id` ASC) USING BTREE,
  INDEX `idx_his_task_parent_task_id`(`parent_task_id` ASC) USING BTREE,
  CONSTRAINT `fk_his_task_instance_id` FOREIGN KEY (`instance_id`) REFERENCES `flw_his_instance` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '历史任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_his_task_actor
-- ----------------------------
DROP TABLE IF EXISTS ` flw_his_task_actor `;
CREATE TABLE ` flw_his_task_actor `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键 ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程实例ID',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务ID',
  `actor_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参与者ID',
  `actor_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参与者名称',
  `actor_type` int NOT NULL COMMENT '参与者类型 0，用户 1，角色 2，部门',
  `weight` int NULL DEFAULT NULL COMMENT '权重，票签任务时，该值为不同处理人员的分量比例，代理任务时，该值为 1 时为代理人',
  `agent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '代理人ID',
  `agent_type` int NULL DEFAULT NULL COMMENT '代理人类型 0，代理 1，被代理 2，认领角色 3，认领部门',
  `extend` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '扩展json',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_his_task_actor_task_id`(`task_id` ASC) USING BTREE,
  CONSTRAINT `fk_his_task_actor_task_id` FOREIGN KEY (`task_id`) REFERENCES `flw_his_task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '历史任务参与者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_instance
-- ----------------------------
DROP TABLE IF EXISTS ` flw_instance `;
CREATE TABLE ` flw_instance `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `create_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `process_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程定义ID',
  `parent_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '父流程实例ID',
  `priority` int NULL DEFAULT NULL COMMENT '优先级',
  `instance_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `business_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务KEY',
  `variable` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '变量json',
  `current_node_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前所在节点名称',
  `current_node_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '当前所在节点key',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '期望完成时间',
  `last_update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '上次更新人',
  `last_update_time` datetime NULL DEFAULT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_instance_process_id`(`process_id` ASC) USING BTREE,
  CONSTRAINT `fk_instance_process_id` FOREIGN KEY (`process_id`) REFERENCES `flw_process` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_process
-- ----------------------------
DROP TABLE IF EXISTS ` flw_process `;
CREATE TABLE ` flw_process `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `create_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `process_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程定义 key 唯一标识',
  `process_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程定义名称',
  `process_icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流程图标地址',
  `process_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '流程类型',
  `process_version` int NOT NULL COMMENT '流程版本，默认 1',
  `instance_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '实例地址',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '备注说明',
  `use_scope` int NOT NULL COMMENT '使用范围 0，全员 1，指定人员（业务关联） 2，均不可提交',
  `process_state` int NOT NULL COMMENT '流程状态 0，不可用 1，可用 2，历史版本',
  `model_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '流程模型定义JSON内容',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_name`(`process_name` ASC) USING BTREE
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '流程定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_task
-- ----------------------------
DROP TABLE IF EXISTS ` flw_task `;
CREATE TABLE ` flw_task `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `create_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程实例ID',
  `parent_task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '父任务ID',
  `task_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务名称',
  `task_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务 key 唯一标识',
  `task_type` int NOT NULL COMMENT '任务类型',
  `perform_type` int NULL DEFAULT NULL COMMENT '参与类型',
  `action_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '任务处理的url',
  `variable` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '变量json',
  `assignor_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '委托人ID',
  `assignor` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '委托人',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '任务期望完成时间',
  `remind_time` datetime NULL DEFAULT NULL COMMENT '提醒时间',
  `remind_repeat` int NOT NULL COMMENT '提醒次数',
  `viewed` int NOT NULL COMMENT '已阅 0，否 1，是',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_task_instance_id`(`instance_id` ASC) USING BTREE,
  CONSTRAINT `fk_task_instance_id` FOREIGN KEY (`instance_id`) REFERENCES `flw_instance` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_task_actor
-- ----------------------------
DROP TABLE IF EXISTS ` flw_task_actor `;
CREATE TABLE ` flw_task_actor `
(
    `
    id ` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键 ID',
  `tenant_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '租户ID',
  `instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '流程实例ID',
  `task_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务ID',
  `actor_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参与者ID',
  `actor_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '参与者名称',
  `actor_type` int NOT NULL COMMENT '参与者类型 0，用户 1，角色 2，部门',
  `weight` int NULL DEFAULT NULL COMMENT '权重，票签任务时，该值为不同处理人员的分量比例，代理任务时，该值为 1 时为代理人',
  `agent_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '代理人ID',
  `agent_type` int NULL DEFAULT NULL COMMENT '代理人类型 0，代理 1，被代理 2，认领角色 3，认领部门',
  `extend` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '扩展json',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_task_actor_task_id`(`task_id` ASC) USING BTREE,
  CONSTRAINT `fk_task_actor_task_id` FOREIGN KEY (`task_id`) REFERENCES `flw_task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE =
    InnoDB
    CHARACTER
SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '任务参与者表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
