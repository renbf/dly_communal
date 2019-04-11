DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `id` char(32) NOT NULL COMMENT '课程ID',
  `` int(11) DEFAULT NULL COMMENT '培训类型123',
  `course_type` int(11) DEFAULT NULL COMMENT '课程分类id（字典表）',
  `` varchar(100) DEFAULT NULL COMMENT '课程视频url',
  `` varchar(100) DEFAULT NULL COMMENT '课程文件url',
  `` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `` int(11) DEFAULT NULL COMMENT '企业分类id',
  `` int(11) DEFAULT NULL COMMENT '专业分类id',
  `` int(11) DEFAULT NULL COMMENT '是否计时1是0否',
  `` int(11) DEFAULT NULL COMMENT '是否收费1是0否',
  `` decimal(8,2) DEFAULT NULL COMMENT '价格',
  `` bigint(20) DEFAULT NULL COMMENT '预览分钟（秒）',
  `` text DEFAULT NULL COMMENT '课程简介',
  `` int(11) DEFAULT NULL COMMENT '课程来源',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';

DROP TABLE IF EXISTS `t_paper`;
CREATE TABLE `t_paper` (
  `id` char(32) NOT NULL COMMENT '考卷ID',
  `` varchar(100) DEFAULT NULL COMMENT '考卷名称',
  `` int(11) DEFAULT NULL COMMENT '考卷时间',
  `` int(11) DEFAULT NULL COMMENT '考卷类型',
  `` int(11) DEFAULT NULL COMMENT '题目数量',
  `` int(11) DEFAULT NULL COMMENT '企业类型',
  `` int(11) DEFAULT NULL COMMENT '专业类型',
  `` decimal(8,2) DEFAULT NULL COMMENT '考卷金额',
  `` int(11) DEFAULT NULL COMMENT '及格分数',
  `` int(11) DEFAULT NULL COMMENT '题目数量',
  `` int(11) DEFAULT NULL COMMENT '考卷来源',
  `` int(11) DEFAULT NULL COMMENT '所属者',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考卷表';

DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` char(32) NOT NULL COMMENT '题库ID',
  `` varchar(100) DEFAULT NULL COMMENT '题目',
  `` varchar(100) DEFAULT NULL COMMENT '注释',
  `` int(11) DEFAULT NULL COMMENT '题目类型',
  `` int(11) DEFAULT NULL COMMENT '企业类型',
  `` int(11) DEFAULT NULL COMMENT '专业类型',
  `` varchar(10) DEFAULT NULL COMMENT '正确答案',
  `` int(11) DEFAULT NULL COMMENT '题库来源',
  `` int(11) DEFAULT NULL COMMENT '企业id',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题库表';

DROP TABLE IF EXISTS `t_subject_paper`;
CREATE TABLE `t_subject_paper` (
  `paper_id` char(32) NOT NULL COMMENT '考卷ID',
  `subject_id` char(32) NOT NULL COMMENT '题库ID',
  `` int(11) DEFAULT NULL COMMENT '题目分数',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`paper_id`,`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考卷题目关系表';

DROP TABLE IF EXISTS `t_subject_option`;
CREATE TABLE `t_subject_option` (
  `subject_id` char(32) NOT NULL COMMENT '题库ID',
  `option_id` char(32) NOT NULL COMMENT '选项ID',
  `option_value` char(1) DEFAULT NULL COMMENT '选项',
  `content` varchar(100) DEFAULT NULL COMMENT '内容',
  `is_answer` int(11) DEFAULT NULL COMMENT '是否是答案1是0否',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`subject_id`,`option_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题库选项关系表';

DROP TABLE IF EXISTS `t_user_subject`;
CREATE TABLE `t_user_subject` (
  `id` char(32) NOT NULL COMMENT '题库ID',
  `user_id` char(32) NOT NULL COMMENT '用户ID',
  `paper_id` char(32) NOT NULL COMMENT '考卷ID',
  `subject_id` char(32) NOT NULL COMMENT '题库ID',
  `` varchar(10) DEFAULT NULL COMMENT '用户答案',
  `` int(11) DEFAULT NULL COMMENT '是否收藏本题1是0否',
  `create_date` datetime DEFAULT NULL COMMENT '答题开始时间',
  `commit_date` datetime DEFAULT NULL COMMENT '交卷时间时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户答题记录表';


DROP TABLE IF EXISTS `t_course_plan`;
CREATE TABLE `t_course_plan` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `` varchar(100) DEFAULT NULL COMMENT '年份',
  `` varchar(100) DEFAULT NULL COMMENT '标题',
  `` char(32) DEFAULT NULL COMMENT '部门id',
  `` varchar(100) DEFAULT NULL COMMENT '计划课时公司级',
  `` varchar(100) DEFAULT NULL COMMENT '计划课时车间（部门、分厂）级',
  `` varchar(100) DEFAULT NULL COMMENT '计划课时岗位级',
  `` varchar(100) DEFAULT NULL COMMENT '附件url',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程计划表';

DROP TABLE IF EXISTS `t_user_course`;
CREATE TABLE `t_user_course` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `user_id` char(32) NOT NULL COMMENT '用户ID',
  `start_date` date DEFAULT NULL COMMENT '开始时间',
  `course_type` int(11) DEFAULT NULL COMMENT '课程分类id（字典表）',
  `course_id` char(32) DEFAULT NULL COMMENT '课程id',
  `state` int(11) DEFAULT NULL COMMENT '学习状态0为学习1学习中2已完成',
  `` bigint(20) DEFAULT NULL COMMENT '课时（秒）',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '发布人id',
  `publish_date` date DEFAULT NULL COMMENT '发布日期',
  `update_date` date DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程安排表';

DROP TABLE IF EXISTS `t_risk`;
CREATE TABLE `t_risk` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `` varchar(100) DEFAULT NULL COMMENT '风险部位',
  `` varchar(100) DEFAULT NULL COMMENT '风险名称',
  `` varchar(100) DEFAULT NULL COMMENT '风险分级',
  `` varchar(100) DEFAULT NULL COMMENT '防范措施',
  `` int(11) DEFAULT NULL COMMENT '可能导致事故类型',
  `risk_date` date DEFAULT NULL COMMENT '辨识日期',
  `` varchar(100) DEFAULT NULL COMMENT '依据',
  `dept_id` char(32) NOT NULL COMMENT '部门ID',
  `admin_id` char(32) NOT NULL COMMENT '责任人ID',
  `` varchar(100) DEFAULT NULL COMMENT '附件url',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风险分级管控表';

DROP TABLE IF EXISTS `t_inspect_plan`;
CREATE TABLE `t_inspect_plan` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `` varchar(100) DEFAULT NULL COMMENT '标题',
  `` varchar(100) DEFAULT NULL COMMENT '年度',
  `` varchar(100) DEFAULT NULL COMMENT '计划检查时间',
  `` char(32) DEFAULT NULL COMMENT '类型',
  `` char(32) DEFAULT NULL COMMENT '部门',
  `user_id` char(32) DEFAULT NULL COMMENT '责任人',
  `` char(32) DEFAULT NULL COMMENT '检查项目',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查计划表';

DROP TABLE IF EXISTS `t_inspect_record`;
CREATE TABLE `t_inspect_record` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `inspect_plan_id` char(32) DEFAULT NULL COMMENT '检查计划id',
  #`inspect_plan_id` char(32) DEFAULT NULL COMMENT '检查项目id',
  `inspect_user_id` char(32) DEFAULT NULL COMMENT '检查人id',
  `inspectd_admin_id` char(32) DEFAULT NULL COMMENT '被检查人id',
  `` char(1) DEFAULT NULL COMMENT '检查结果1通过0未通过',
  `` datetime DEFAULT NULL COMMENT '检查日期',
  `` int(11) DEFAULT NULL COMMENT '检查类型',
  `` varchar(100) DEFAULT NULL COMMENT '检查人签字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='检查记录表';

DROP TABLE IF EXISTS `t_danger`;
CREATE TABLE `t_danger` (
  `id` char(32) NOT NULL COMMENT 'ID',
  `dept_id` char(32) NOT NULL COMMENT '受检部门ID',
  `user_id` char(32) NOT NULL COMMENT '受检对象ID',
  `user_id` char(32) NOT NULL COMMENT '检查人ID',
  `inspect_date` date DEFAULT NULL COMMENT '检查日期',
  `` int(11) DEFAULT NULL COMMENT '检查类型',
  `` int(11) DEFAULT NULL COMMENT '隐患部位',
  `dept_id` char(32) NOT NULL COMMENT '隐患部门ID',
  `` int(11) DEFAULT NULL COMMENT '隐患类型',
  `` int(11) DEFAULT NULL COMMENT '隐患等级',
  `` varchar(100) DEFAULT NULL COMMENT '风险源',
  `` int(11) DEFAULT NULL COMMENT '可能后果',
  `` varchar(100) DEFAULT NULL COMMENT '隐患照片',
  `` varchar(100) DEFAULT NULL COMMENT '备注',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `add_admin_id` char(32) DEFAULT NULL COMMENT '创建人id',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_admin_id` char(32) DEFAULT NULL COMMENT '修改人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隐患表';













