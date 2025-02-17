USE [flowlong]
GO
/****** Object:  Table [dbo].[flw_ext_instance]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_ext_instance]
(
    [id]            [varchar](64)   NOT NULL,
    [tenant_id]     [varchar](64)   NULL,
    [process_id]    [varchar](64)   NOT NULL,
    [process_name]  [nvarchar](128) NULL,
    [process_type]  [nvarchar](128) NULL,
    [model_content] [nvarchar](max) NULL,
    CONSTRAINT [PK__flw_ext___3213E83F37E5C7FB] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_his_instance]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_his_instance]
(
    [id]                 [varchar](64)   NOT NULL,
    [tenant_id]          [varchar](64)   NULL,
    [create_id]          [varchar](64)   NOT NULL,
    [create_by]          [varchar](64)   NOT NULL,
    [create_time]        [datetime]      NOT NULL,
    [process_id]         [varchar](64)   NOT NULL,
    [parent_instance_id] [varchar](64)   NULL,
    [priority]           [int]           NULL,
    [instance_no]        [varchar](64)   NULL,
    [business_key]       [nvarchar](128) NULL,
    [variable]           [nvarchar](max) NULL,
    [current_node_name]  [nvarchar](128) NOT NULL,
    [current_node_key]   [nvarchar](128) NOT NULL,
    [expire_time]        [datetime]      NULL,
    [last_update_by]     [varchar](64)   NULL,
    [last_update_time]   [datetime]      NULL,
    [instance_state]     [int]           NOT NULL,
    [end_time]           [datetime]      NULL,
    [duration]           [bigint]        NULL,
    CONSTRAINT [PK__flw_his___3213E83F45F15907] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_his_task]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_his_task]
(
    [id]               [varchar](64)   NOT NULL,
    [tenant_id]        [varchar](64)   NULL,
    [create_id]        [varchar](64)   NOT NULL,
    [create_by]        [varchar](64)   NOT NULL,
    [create_time]      [datetime]      NOT NULL,
    [instance_id]      [varchar](64)   NOT NULL,
    [parent_task_id]   [varchar](64)   NULL,
    [call_process_id]  [varchar](64)   NULL,
    [call_instance_id] [varchar](64)   NULL,
    [task_name]        [nvarchar](128) NOT NULL,
    [task_key]         [nvarchar](128) NOT NULL,
    [task_type]        [int]           NOT NULL,
    [perform_type]     [int]           NULL,
    [action_url]       [nvarchar](256) NULL,
    [variable]         [nvarchar](max) NULL,
    [assignor_id]      [varchar](64)   NULL,
    [assignor]         [nvarchar](128) NULL,
    [expire_time]      [datetime]      NULL,
    [remind_time]      [datetime]      NULL,
    [remind_repeat]    [int]           NOT NULL,
    [viewed]           [int]           NOT NULL,
    [finish_time]      [datetime]      NULL,
    [task_state]       [int]           NOT NULL,
    [duration]         [bigint]        NULL,
    CONSTRAINT [PK__flw_his___3213E83F302645DE] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_his_task_actor]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_his_task_actor]
(
    [id]          [varchar](64)   NOT NULL,
    [tenant_id]   [varchar](64)   NULL,
    [instance_id] [varchar](64)   NOT NULL,
    [task_id]     [varchar](64)   NOT NULL,
    [actor_id]    [varchar](64)   NOT NULL,
    [actor_name]  [varchar](64)   NOT NULL,
    [actor_type]  [int]           NOT NULL,
    [weight]      [int]           NULL,
    [agent_id]    [varchar](64)   NULL,
    [agent_type]  [int]           NULL,
    [extend]      [nvarchar](max) NULL,
    CONSTRAINT [PK__flw_his___3213E83F21439DD9] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_instance]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_instance]
(
    [id]                 [varchar](64)   NOT NULL,
    [tenant_id]          [varchar](64)   NULL,
    [create_id]          [varchar](64)   NOT NULL,
    [create_by]          [varchar](64)   NOT NULL,
    [create_time]        [datetime]      NOT NULL,
    [process_id]         [varchar](64)   NOT NULL,
    [parent_instance_id] [varchar](64)   NULL,
    [priority]           [int]           NULL,
    [instance_no]        [varchar](64)   NULL,
    [business_key]       [nvarchar](128) NULL,
    [variable]           [nvarchar](max) NULL,
    [current_node_name]  [nvarchar](128) NOT NULL,
    [current_node_key]   [nvarchar](128) NOT NULL,
    [expire_time]        [datetime]      NULL,
    [last_update_by]     [varchar](64)   NULL,
    [last_update_time]   [datetime]      NULL,
    CONSTRAINT [PK__flw_inst__3213E83FB487C1DB] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_process]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_process]
(
    [id]              [varchar](64)   NOT NULL,
    [tenant_id]       [varchar](64)   NULL,
    [create_id]       [varchar](64)   NOT NULL,
    [create_by]       [varchar](64)   NOT NULL,
    [create_time]     [datetime]      NOT NULL,
    [process_key]     [nvarchar](128) NOT NULL,
    [process_name]    [nvarchar](128) NOT NULL,
    [process_icon]    [varchar](64)   NULL,
    [process_type]    [varchar](64)   NULL,
    [process_version] [int]           NOT NULL,
    [instance_url]    [nvarchar](256) NULL,
    [remark]          [nvarchar](256) NULL,
    [use_scope]       [int]           NOT NULL,
    [process_state]   [int]           NOT NULL,
    [model_content]   [nvarchar](max) NULL,
    [sort]            [int]           NULL,
    CONSTRAINT [PK__flw_proc__3213E83FA3A8EBBD] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_task]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_task]
(
    [id]             [varchar](64)   NOT NULL,
    [tenant_id]      [varchar](64)   NULL,
    [create_id]      [varchar](64)   NOT NULL,
    [create_by]      [varchar](64)   NOT NULL,
    [create_time]    [datetime]      NOT NULL,
    [instance_id]    [varchar](64)   NOT NULL,
    [parent_task_id] [varchar](64)   NULL,
    [task_name]      [nvarchar](128) NOT NULL,
    [task_key]       [nvarchar](128) NOT NULL,
    [task_type]      [int]           NOT NULL,
    [perform_type]   [int]           NULL,
    [action_url]     [nvarchar](256) NULL,
    [variable]       [nvarchar](max) NULL,
    [assignor_id]    [varchar](64)   NULL,
    [assignor]       [varchar](64)   NULL,
    [expire_time]    [datetime]      NULL,
    [remind_time]    [datetime]      NULL,
    [remind_repeat]  [int]           NOT NULL,
    [viewed]         [int]           NOT NULL,
    CONSTRAINT [PK__flw_task__3213E83FC7AF38B5] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[flw_task_actor]    Script Date: 2024/12/26 10:02:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[flw_task_actor]
(
    [id]          [varchar](64)   NOT NULL,
    [tenant_id]   [varchar](64)   NULL,
    [instance_id] [varchar](64)   NOT NULL,
    [task_id]     [varchar](64)   NOT NULL,
    [actor_id]    [varchar](64)   NOT NULL,
    [actor_name]  [varchar](64)   NOT NULL,
    [actor_type]  [int]           NOT NULL,
    [weight]      [int]           NULL,
    [agent_id]    [varchar](64)   NULL,
    [agent_type]  [int]           NULL,
    [extend]      [nvarchar](max) NULL,
    CONSTRAINT [PK__flw_task__3213E83FA3010FCD] PRIMARY KEY CLUSTERED
        (
         [id] ASC
            ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
ALTER TABLE [dbo].[flw_ext_instance]
    WITH CHECK ADD CONSTRAINT [fk_ext_instance_id] FOREIGN KEY ([id])
        REFERENCES [dbo].[flw_his_instance] ([id])
GO
ALTER TABLE [dbo].[flw_ext_instance]
    CHECK CONSTRAINT [fk_ext_instance_id]
GO
ALTER TABLE [dbo].[flw_his_instance]
    WITH CHECK ADD CONSTRAINT [fk_his_instance_process_id] FOREIGN KEY ([process_id])
        REFERENCES [dbo].[flw_process] ([id])
GO
ALTER TABLE [dbo].[flw_his_instance]
    CHECK CONSTRAINT [fk_his_instance_process_id]
GO
ALTER TABLE [dbo].[flw_his_task]
    WITH CHECK ADD CONSTRAINT [fk_his_task_instance_id] FOREIGN KEY ([instance_id])
        REFERENCES [dbo].[flw_his_instance] ([id])
GO
ALTER TABLE [dbo].[flw_his_task]
    CHECK CONSTRAINT [fk_his_task_instance_id]
GO
ALTER TABLE [dbo].[flw_his_task_actor]
    WITH CHECK ADD CONSTRAINT [fk_his_task_actor_task_id] FOREIGN KEY ([task_id])
        REFERENCES [dbo].[flw_his_task] ([id])
GO
ALTER TABLE [dbo].[flw_his_task_actor]
    CHECK CONSTRAINT [fk_his_task_actor_task_id]
GO
ALTER TABLE [dbo].[flw_instance]
    WITH CHECK ADD CONSTRAINT [fk_instance_process_id] FOREIGN KEY ([process_id])
        REFERENCES [dbo].[flw_process] ([id])
GO
ALTER TABLE [dbo].[flw_instance]
    CHECK CONSTRAINT [fk_instance_process_id]
GO
ALTER TABLE [dbo].[flw_task]
    WITH CHECK ADD CONSTRAINT [fk_task_instance_id] FOREIGN KEY ([instance_id])
        REFERENCES [dbo].[flw_instance] ([id])
GO
ALTER TABLE [dbo].[flw_task]
    CHECK CONSTRAINT [fk_task_instance_id]
GO
ALTER TABLE [dbo].[flw_task_actor]
    WITH CHECK ADD CONSTRAINT [fk_task_actor_task_id] FOREIGN KEY ([task_id])
        REFERENCES [dbo].[flw_task] ([id])
GO
ALTER TABLE [dbo].[flw_task_actor]
    CHECK CONSTRAINT [fk_task_actor_task_id]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_ext_instance', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_ext_instance', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程定义ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_ext_instance', @level2type=N'COLUMN', @level2name=N'process_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程名称', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_ext_instance', @level2type=N'COLUMN', @level2name=N'process_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程类型', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_ext_instance', @level2type=N'COLUMN', @level2name=N'process_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程模型定义JSON内容', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_ext_instance', @level2type=N'COLUMN', @level2name=N'model_content'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'扩展流程实例表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_ext_instance'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'create_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程定义ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'process_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'parent_instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'优先级', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'priority'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例编号', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'instance_no'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务KEY', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'business_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'变量json', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'variable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'当前所在节点名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'current_node_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'当前所在节点key', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'current_node_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'期望完成时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'expire_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上次更新人', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'last_update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上次更新时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'last_update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description',
     @value=N'状态 0，审批中 1，审批通过 2，审批拒绝 3，撤销审批 4，超时结束 5，强制终止', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'instance_state'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'结束时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'end_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'处理耗时', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_instance', @level2type=N'COLUMN', @level2name=N'duration'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'历史流程实例表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_instance'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'create_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父任务ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'parent_task_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'调用外部流程定义ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'call_process_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'调用外部流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'call_instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务名称', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'task_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务 key 唯一标识', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'task_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务类型', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'task_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与类型', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'perform_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务处理的url', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'action_url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'变量json', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'variable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'委托人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'assignor_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'委托人', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'assignor'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务期望完成时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'expire_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提醒时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'remind_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提醒次数', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'remind_repeat'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已阅 0，否 1，是', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'viewed'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务完成时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'finish_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description',
     @value=N'任务状态 0，活动 1，跳转 2，完成 3，拒绝 4，撤销审批  5，超时 6，终止 7，驳回终止', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'task_state'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'处理耗时', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task', @level2type=N'COLUMN', @level2name=N'duration'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'历史任务表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键 ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'task_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与者ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'actor_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与者名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'actor_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与者类型 0，用户 1，角色 2，部门',
     @level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task_actor',
     @level2type=N'COLUMN', @level2name=N'actor_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description',
     @value=N'权重，票签任务时，该值为不同处理人员的分量比例，代理任务时，该值为 1 时为代理人', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'weight'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代理人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'agent_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代理人类型 0，代理 1，被代理 2，认领角色 3，认领部门',
     @level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task_actor',
     @level2type=N'COLUMN', @level2name=N'agent_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'扩展json', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_his_task_actor', @level2type=N'COLUMN', @level2name=N'extend'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'历史任务参与者表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_his_task_actor'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'create_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程定义ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'process_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'parent_instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'优先级', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'priority'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例编号', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'instance_no'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'业务KEY', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'business_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'变量json', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'variable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'当前所在节点名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'current_node_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'当前所在节点key', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'current_node_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'期望完成时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'expire_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上次更新人', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'last_update_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上次更新时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance', @level2type=N'COLUMN', @level2name=N'last_update_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_instance'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'create_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程定义 key 唯一标识', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'process_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程定义名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'process_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程图标地址', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'process_icon'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程类型', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'process_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程版本，默认 1', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'process_version'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'实例地址', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'instance_url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注说明', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'remark'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'使用范围 0，全员 1，指定人员（业务关联） 2，均不可提交',
     @level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN',
     @level2name=N'use_scope'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程状态 0，不可用 1，可用 2，历史版本',
     @level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN',
     @level2name=N'process_state'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程模型定义JSON内容', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'model_content'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'排序', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_process', @level2type=N'COLUMN', @level2name=N'sort'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程定义表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_process'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'create_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'create_by'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'create_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父任务ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'parent_task_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务名称', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'task_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务 key 唯一标识', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'task_key'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务类型', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'task_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与类型', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'perform_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务处理的url', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'action_url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'变量json', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'variable'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'委托人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'assignor_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'委托人', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'assignor'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务期望完成时间', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'expire_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提醒时间', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'remind_time'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提醒次数', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'remind_repeat'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已阅 0，否 1，是', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task', @level2type=N'COLUMN', @level2name=N'viewed'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务表', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键 ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'租户ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'tenant_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'流程实例ID', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'instance_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'task_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与者ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'actor_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与者名称', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'actor_name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'参与者类型 0，用户 1，角色 2，部门',
     @level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task_actor',
     @level2type=N'COLUMN', @level2name=N'actor_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description',
     @value=N'权重，票签任务时，该值为不同处理人员的分量比例，代理任务时，该值为 1 时为代理人', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'weight'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代理人ID', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'agent_id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'代理人类型 0，代理 1，被代理 2，认领角色 3，认领部门',
     @level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task_actor',
     @level2type=N'COLUMN', @level2name=N'agent_type'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'扩展json', @level0type=N'SCHEMA', @level0name=N'dbo',
     @level1type=N'TABLE', @level1name=N'flw_task_actor', @level2type=N'COLUMN', @level2name=N'extend'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务参与者表', @level0type=N'SCHEMA',
     @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'flw_task_actor'
GO
