CREATE TABLE `uk_qc_mission` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '任务名称（系统分配生成）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `assuser` varchar(50) DEFAULT NULL COMMENT '分配执行人',
  `asstime` datetime DEFAULT NULL COMMENT '分配时间',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `filtertype` varchar(32) DEFAULT NULL COMMENT '筛选类型（callevent通话筛选/workorders工单筛选/agentservice会话筛选）',
  `dataid` varchar(50) DEFAULT NULL COMMENT '数据ID（通话记录ID/工单记录ID/会话记录ID）',
  `datakey` text DEFAULT NULL COMMENT '数据（通话（主叫号码）/工单记录（工单标题）/会话记录（访客用户名））',
  `datavalue` text DEFAULT NULL COMMENT '数据（通话（被叫号码）/工单记录（处理人）/会话记录（服务坐席））',
  `templateid` varchar(32) DEFAULT NULL COMMENT '质检模板ID',
  `actid` varchar(32) DEFAULT NULL COMMENT '质检活动ID',
  `formfilterid` varchar(32) DEFAULT NULL COMMENT '质检筛选表单ID',
  `filterid` varchar(32) DEFAULT NULL COMMENT '质检筛选记录ID',
  `taskid` varchar(32) DEFAULT NULL COMMENT '质检任务ID',
  `datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `qualitystatus` varchar(20) DEFAULT NULL COMMENT '质检状态',
  `qualitydisorgan` varchar(32) DEFAULT NULL COMMENT '分配的质检部门',
  `qualitydisuser` varchar(32) DEFAULT NULL COMMENT '分配的质检用户',
  `qualityorgan` varchar(32) DEFAULT NULL COMMENT '实际质检部门',
  `qualityuser` varchar(32) DEFAULT NULL COMMENT '实际质检人',
  `qualityscore` int(11) DEFAULT '0' COMMENT '质检得分',
  `qualitytime` datetime DEFAULT NULL COMMENT '质检时间',
  `qualitytype` varchar(20) DEFAULT NULL COMMENT '质检类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 任务主表';

ALTER TABLE uk_qc_template_item ADD type varchar(32) DEFAULT NULL COMMENT '质检项分类（plus评分/minus扣分/taboo禁忌项）';


INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876e8c0b0182', '知识配置', 'pub', 'A14_A09_A02', NULL, 'auth', '402888816686bff701668767c78b010e', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:40', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/config/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876e48c8017e', '知识库专家', 'pub', 'A14_A09_A01', NULL, 'auth', '402888816686bff701668767c78b010e', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:23', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/experts/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876e20d7017a', '已发布知识', 'pub', 'A14_A08_A08', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/overdue/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876df2030176', '知识回收', 'pub', 'A14_A08_A07', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:47:01', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/recycle/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876dc49c0172', '搜索历史', 'pub', 'A14_A08_A06', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:49', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/searchcon/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876d91f9016e', '版本管理', 'pub', 'A14_A08_A05', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:36', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmversion/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876d64d6016a', '维度管理', 'pub', 'A14_A08_A04', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:25', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/dimension/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876d354c0166', '评论管理', 'pub', 'A14_A08_A03', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:46:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/comments/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876cf4550162', '审核管理', 'pub', 'A14_A08_A02', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:45:56', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmallaudit/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876cbf42015e', '知识库管理', 'pub', 'A14_A08_A01', NULL, 'auth', '402888816686bff701668767976b010b', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:45:42', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/knowbase/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876c748d015a', '我收到的评论', 'pub', 'A14_A07_A02', NULL, 'auth', '402888816686bff70166876760180108', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:45:23', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/comments/becomments/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876c08aa0156', '我发出的评论', 'pub', 'A14_A07_A01', NULL, 'auth', '402888816686bff70166876760180108', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:56', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/comments/mycomments/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876bceb80152', '待审核的知识', 'pub', 'A14_A06_A02', NULL, 'auth', '402888816686bff7016687672ec30105', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:41', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmbeaudit/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876ba547014e', '我审核的知识', 'pub', 'A14_A06_A01', NULL, 'auth', '402888816686bff7016687672ec30105', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:30', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmaudit/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876b68e2014a', '被收藏的知识', 'pub', 'A14_A05_A02', NULL, 'auth', '402888816686bff701668766d10b0102', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:44:15', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmbecollect/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876b27500147', '我收藏的知识', 'pub', 'A14_A05_A01', NULL, 'auth', '402888816686bff701668766d10b0102', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:58', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmcollect/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876af24a0144', '分享给我的知识', 'pub', 'A14_A04_A02', NULL, 'auth', '402888816686bff701668766827600ff', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:44', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmbeshare/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876ab5450140', '我分享的知识', 'pub', 'A14_A04_A01', NULL, 'auth', '402888816686bff701668766827600ff', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:29', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/ekmshare/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876a7792013c', '浏览历史', 'pub', 'A14_A03_A02', NULL, 'auth', '402888816686bff70166876640a400fc', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/record/view/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876a43b30138', '访客记录', 'pub', 'A14_A03_A01', NULL, 'auth', '402888816686bff70166876640a400fc', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:43:00', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/record/visitors/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668769daff0134', '回收站', 'pub', 'A14_A02_A07', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:42:33', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/recycle/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668769ac410130', '已撤回', 'pub', 'A14_A02_A06', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:42:21', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/downed/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876977eb012c', '被驳回', 'pub', 'A14_A02_A05', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:42:08', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/rejected/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876942360128', '审核中', 'pub', 'A14_A02_A04', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:54', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/auditing/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668768f97b0124', '已发布', 'pub', 'A14_A02_A03', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:35', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/published/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668768b2440120', '全部知识', 'pub', 'A14_A02_A02', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:17', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/self/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff7016687688d83011c', '我的知识库', 'pub', 'A14_A02_A01', NULL, 'auth', '402888816686bff701668765e45c00f9', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:41:08', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/knowbase/mybase/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876852c90118', '知识管理', 'pub', 'A14_A01_A02', NULL, 'auth', '402888816686bff70166876563ed00f6', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:53', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/knowledge/add.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876808a40114', '知识门户', 'pub', 'A14_A01_A01', NULL, 'auth', '402888816686bff70166876563ed00f6', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:34', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668767c78b010e', '业务配置', 'pub', 'A14_A09', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:17', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668767976b010b', '知识管理', 'pub', 'A14_A08', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:40:05', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876760180108', '评论库', 'pub', 'A14_A07', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:39:50', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff7016687672ec30105', '审核库', 'pub', 'A14_A06', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:39:38', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668766d10b0102', '收藏库', 'pub', 'A14_A05', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:39:14', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668766827600ff', '分享库', 'pub', 'A14_A04', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:38:54', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876640a400fc', '相关记录', 'pub', 'A14_A03', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:38:37', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff701668765e45c00f9', '我的知识', 'pub', 'A14_A02', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:38:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff70166876563ed00f6', '知识总览', 'pub', 'A14_A01', NULL, 'auth', '402888816686bff7016687649b8300f1', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:37:40', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('402888816686bff7016687649b8300f1', '知识库', 'pub', 'A14', NULL, 'auth', '402888815d2fe37f015d2fe75cc80002', NULL, NULL, '<i class=\"kfont\" style=\"position: relative;\">&#xe62a;</i>', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-10-18 21:36:49', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/ekm/index.html', 'webim', '1', NULL, 'left');
 

alter table uk_agentservice modify sessionid varchar(50);
alter table uk_agentuser modify sessionid varchar(50);

alter table uk_blacklist modify sessionid varchar(50);

alter table uk_kbs_topic modify sessionid varchar(50);
alter table uk_userevent modify sessionid varchar(50);
alter table uk_workorders modify sessionid varchar(50);
alter table uk_xiaoe_scene modify sessionid varchar(50);
alter table uk_xiaoe_topic modify sessionid varchar(50);
 
 ALTER TABLE uk_qc_mission ADD agentdata text DEFAULT NULL COMMENT '会话质检（访客用户名）';
 
 CREATE TABLE `uk_qc_activity_task` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '任务代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `NAMENUM` int(11) DEFAULT '0' COMMENT '批次包含的名单总数',
  `VALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的有效名单总数',
  `INVALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的无效名单总数',
  `ASSIGNED` int(11) DEFAULT '0' COMMENT '已分配名单总数',
  `NOTASSIGNED` int(11) DEFAULT '0' COMMENT '未分配名单总数',
  `ENABLE` tinyint(4) DEFAULT '0' COMMENT '分类状态',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `DESCRIPTION` text COMMENT '备注信息',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `SOURCE` varchar(255) DEFAULT NULL COMMENT '来源信息',
  `BATID` varchar(32) DEFAULT NULL COMMENT '批次ID',
  `FILTERID` varchar(32) DEFAULT NULL COMMENT '筛选ID',
  `ASSIGNEDORGAN` int(11) DEFAULT '0' COMMENT '分配给部门',
  `exectype` varchar(32) DEFAULT NULL COMMENT '执行类型',
  `renum` int(11) DEFAULT '0' COMMENT '分配数量',
  `reorgannum` int(11) DEFAULT '0' COMMENT '分配到部门数量',
  `assignedai` int(11) DEFAULT '0' COMMENT '分配到AI的名单数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='质检活动 - 任务表';
 
CREATE TABLE `uk_qc_formfilter` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '筛选表单名称',
  `code` varchar(50) DEFAULT NULL COMMENT '筛选表单代码',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `status` varchar(50) DEFAULT NULL COMMENT '状态',
  `filtertype` varchar(32) DEFAULT NULL COMMENT '筛选类型（callevent通话筛选/workorders工单筛选/agentservice会话筛选）',
  `tablename` varchar(50) DEFAULT NULL COMMENT '对应的数据表名',
  `datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `organ` varchar(32) DEFAULT NULL COMMENT '部门',
  `description` text COMMENT '备注信息',
  `filternum` int(11) DEFAULT '0' COMMENT '筛选次数',
  `conditional` int(11) DEFAULT '0' COMMENT '条件个数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 筛选表单';

CREATE TABLE `uk_qc_formfilter_item` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `qcformfilterid` varchar(32) DEFAULT NULL COMMENT '筛选器ID',
  `field` varchar(32) DEFAULT NULL COMMENT '字段',
  `cond` varchar(32) DEFAULT NULL COMMENT '条件',
  `value` varchar(32) DEFAULT NULL COMMENT '取值',
  `contype` varchar(32) DEFAULT NULL COMMENT '条件类型',
  `itemtype` varchar(32) DEFAULT NULL COMMENT '类型',
  `comp` varchar(50) DEFAULT NULL COMMENT '逻辑条件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 筛选项';

CREATE TABLE `uk_qc_filter_his` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '筛选名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '筛选代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `NAMENUM` int(11) DEFAULT '0' COMMENT '批次包含的名单总数',
  `VALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的有效名单总数',
  `INVALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的无效名单总数',
  `ASSIGNED` int(11) DEFAULT '0' COMMENT '已分配名单总数',
  `NOTASSIGNED` int(11) DEFAULT '0' COMMENT '未分配名单总数',
  `ENABLE` tinyint(4) DEFAULT '0' COMMENT '分类状态',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `DESCRIPTION` text COMMENT '备注',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `SOURCE` varchar(255) DEFAULT NULL COMMENT '来源',
  `BATID` varchar(32) DEFAULT NULL COMMENT '批次ID',
  `FILTERID` varchar(32) DEFAULT NULL COMMENT '筛选表单ID',
  `ASSIGNEDORGAN` int(11) DEFAULT '0' COMMENT '分配部门',
  `exectype` varchar(32) DEFAULT NULL COMMENT '执行类型',
  `renum` int(11) DEFAULT '0' COMMENT '分配数量',
  `reorgannum` int(11) DEFAULT '0' COMMENT '部门分配数量',
  `assignedai` int(11) DEFAULT '0' COMMENT '分配到AI的名单数量',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='筛选记录表';

ALTER TABLE uk_ekm_knowledge ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';
ALTER TABLE uk_ekm_knowledge_master ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';
ALTER TABLE uk_ekm_knowledge_publish ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';
ALTER TABLE uk_ekm_knowledge_verison ADD attr text DEFAULT NULL COMMENT '知识分类id,维度分类id';

CREATE TABLE `uk_spt_salespatter` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '话术名称',
  `type` varchar(32) DEFAULT NULL COMMENT '话术类型',
  `scene` varchar(32) DEFAULT NULL COMMENT '话术适用场景',
  `welword` varchar(255) DEFAULT NULL COMMENT '话术欢迎语（文字）',
  `welvoice` varchar(255) DEFAULT NULL COMMENT '话术欢迎语ID（语音）',
  `weltype` varchar(32) DEFAULT NULL COMMENT '话术欢迎语类型',
  `endword` varchar(255) DEFAULT NULL COMMENT '话术结束语（文字）',
  `endvoice` varchar(255) DEFAULT NULL COMMENT '话术结束语ID（语音）',
  `endtype` varchar(32) DEFAULT NULL COMMENT '话术结束语类型',
  `totalscore` varchar(32) DEFAULT NULL COMMENT '参考评分值',
  `score` varchar(32) DEFAULT '0' COMMENT '是否评分（0否1是）',
  `memo` text COMMENT '备注',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `prostatus` varchar(32) DEFAULT '0' COMMENT '话术状态（0未发布1发布）',
  `sumscore` varchar(32) DEFAULT NULL COMMENT '总评分值',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='话术调查表';

ALTER TABLE uk_callcenter_event ADD qualitydistime datetime DEFAULT NULL COMMENT '质检分配的时间';
ALTER TABLE uk_workorders ADD qualitydistime datetime DEFAULT NULL COMMENT '质检分配的时间';
ALTER TABLE uk_agentservice ADD qualitydistime datetime DEFAULT NULL COMMENT '质检分配的时间';

ALTER TABLE uk_qc_formfilter ADD execnum int DEFAULT '0' COMMENT '执行次数';

CREATE TABLE `uk_qc_result` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `remarks` text COMMENT '质检备注',
  `adcom` text COMMENT '优点评语',
  `qacom` text COMMENT 'QA评语',
  `imcom` text COMMENT '改进评语',
  `score` int(11) DEFAULT '0' COMMENT '实际得分',
  `totalscore` int(11) DEFAULT '0' COMMENT '总分',
  `passscore` int(11) DEFAULT '0' COMMENT '合格分',
  `arithmetic` varchar(32) DEFAULT NULL COMMENT '算分机制(plus评分/minus扣分)',
  `missionid` varchar(32) DEFAULT NULL COMMENT '质检任务id',
  `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID（通话记录ID/工单记录ID/会话记录ID）',
  `qualityuser` varchar(32) DEFAULT NULL COMMENT '实际质检人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 结果主表';

CREATE TABLE `uk_qc_result_item` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `maxscore` int(32) DEFAULT NULL COMMENT '最高分数',
  `minscore` int(32) DEFAULT NULL COMMENT '最低分数',
  `score` int(32) DEFAULT NULL COMMENT '评分',
  `scheme` text COMMENT '评分方案',
  `remarks` text COMMENT '质检备注',
  `parentid` varchar(32) DEFAULT NULL COMMENT '质检项父级id',
  `type` varchar(32) DEFAULT NULL COMMENT '质检项分类（plus评分/minus扣分/taboo禁忌项）',
  `resultid` varchar(32) DEFAULT NULL COMMENT '质检结果id',
  `itemid` varchar(32) DEFAULT NULL COMMENT '质检项id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 结果子表';


 ALTER TABLE uk_jobdetail ADD filtertype varchar(50) DEFAULT NULL COMMENT '活动筛选类型';
 
ALTER TABLE uk_workorders ADD assuser varchar(50) DEFAULT NULL COMMENT '分配执行人';
ALTER TABLE uk_callcenter_event ADD assuser varchar(50) DEFAULT NULL COMMENT '分配执行人';
ALTER TABLE uk_agentservice ADD assuser varchar(50) DEFAULT NULL COMMENT '分配执行人';

ALTER TABLE uk_workorders ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板id';
ALTER TABLE uk_callcenter_event ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板id';
ALTER TABLE uk_agentservice ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板id';

ALTER TABLE uk_qc_result ADD status varchar(32) DEFAULT NULL COMMENT '状态（已质检done、已归档archive、复检中recheck）';
ALTER TABLE uk_qc_result ADD archivedate datetime DEFAULT NULL COMMENT '归档日期';

ALTER TABLE uk_qc_result ADD qualitytype varchar(32) DEFAULT NULL COMMENT '质检类型（callevent通话/workorders工单/agentservice会话）';

CREATE TABLE `uk_qc_appeal` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `remarks` text COMMENT '申诉备注',
  `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID（通话记录ID/工单记录ID/会话记录ID）',
  `resultid` varchar(32) DEFAULT NULL COMMENT '质检结果id',
  `appealuser` varchar(32) DEFAULT NULL COMMENT '申诉人',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 质检申诉表';

 ALTER TABLE uk_jobdetail ADD templateid varchar(50) DEFAULT NULL COMMENT '质检模板ID';
 
 CREATE TABLE `uk_qc_mission_his` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `dataid` varchar(50) DEFAULT NULL COMMENT '数据ID（uk_callcenter_event 通话/uk_workorders 工单/uk_agentservice 在线客服）',
  `templateid` varchar(50) DEFAULT NULL COMMENT '质检模板ID',
  `actid` varchar(50) DEFAULT NULL COMMENT '质检活动ID',
  `formfilterid` varchar(32) DEFAULT NULL COMMENT '质检筛选表单ID',
  `filterid` varchar(32) DEFAULT NULL COMMENT '质检筛选记录ID',
  `taskid` varchar(32) DEFAULT NULL COMMENT '质检任务ID',
  `datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `qualitystatus` varchar(20) DEFAULT NULL COMMENT '质检状态',
  `qualitydisorgan` varchar(32) DEFAULT NULL COMMENT '分配的质检部门',
  `qualitydisuser` varchar(32) DEFAULT NULL COMMENT '分配的质检用户',
  `qualityorgan` varchar(32) DEFAULT NULL COMMENT '实际质检部门',
  `qualityuser` varchar(32) DEFAULT NULL COMMENT '实际质检人',
  `qualityscore` int(11) DEFAULT '0' COMMENT '质检得分',
  `qualitytime` datetime DEFAULT NULL COMMENT '质检时间',
  `qualitytype` varchar(20) DEFAULT NULL COMMENT '质检类型',
  `agentdata` text COMMENT '会话质检（访客用户名）',
  `organ` varchar(50) DEFAULT NULL COMMENT '部门',
  `orgi` varchar(50) DEFAULT NULL COMMENT '租户ID',
  `assuser` varchar(50) DEFAULT NULL COMMENT '分配执行人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='QC质检 - 任务历史表';

ALTER TABLE uk_workorders ADD qualitydistype varchar(32) DEFAULT NULL COMMENT '分配状态  ，未分配not/分配到部门disorgan/分配到坐席disagent';
ALTER TABLE uk_callcenter_event ADD qualitydistype varchar(32) DEFAULT NULL COMMENT '分配状态  ，未分配not/分配到部门disorgan/分配到坐席disagent';
ALTER TABLE uk_agentservice ADD qualitydistype varchar(32) DEFAULT NULL COMMENT '分配状态  ，未分配not/分配到部门disorgan/分配到坐席disagent';
ALTER TABLE uk_qc_result modify column dataid varchar(50)DEFAULT NULL COMMENT '数据id';
ALTER TABLE uk_qc_appeal modify column dataid varchar(50)DEFAULT NULL COMMENT '数据id';

ALTER TABLE uk_workorders ADD qualityactid varchar(50) DEFAULT NULL COMMENT '质检活动id';
ALTER TABLE uk_callcenter_event ADD qualityactid varchar(50) DEFAULT NULL COMMENT '质检活动id';
ALTER TABLE uk_agentservice ADD qualityactid varchar(50) DEFAULT NULL COMMENT '质检活动id';

ALTER TABLE uk_workorders ADD qualityfilterid varchar(50) DEFAULT NULL COMMENT '筛选表单id';
ALTER TABLE uk_callcenter_event ADD qualityfilterid varchar(50) DEFAULT NULL COMMENT '筛选表单id';
ALTER TABLE uk_agentservice ADD qualityfilterid varchar(50) DEFAULT NULL COMMENT '筛选表单id';

ALTER TABLE uk_qc_mission_his ADD createtime datetime DEFAULT NULL;
ALTER TABLE uk_qc_mission_his ADD updatetime datetime DEFAULT NULL;


ALTER TABLE uk_qc_appeal ADD datatype varchar(32) DEFAULT NULL COMMENT '数据类型（callevent通话/workorders工单/agentservice会话）';

ALTER TABLE uk_qc_mission_his ADD appealremarks text DEFAULT NULL COMMENT '申诉备注';
ALTER TABLE uk_qc_mission_his ADD arbremarks text DEFAULT NULL COMMENT '仲裁备注';
ALTER TABLE uk_qc_mission_his ADD rejectremarks text DEFAULT NULL COMMENT '驳回备注';
ALTER TABLE uk_qc_mission_his ADD resultid varchar(32) DEFAULT NULL COMMENT '结果id';

ALTER TABLE uk_qc_result ADD isvp int(11) DEFAULT '0' COMMENT '是否有否决权（1是/0否）';
ALTER TABLE uk_qc_result ADD isadcom int(11) DEFAULT '0' COMMENT '是否有优点评语（1是/0否）';
ALTER TABLE uk_qc_result ADD isqacom int(11) DEFAULT '0' COMMENT '是否QA评语（1是/0否）';
ALTER TABLE uk_qc_result ADD isimcom int(11) DEFAULT '0' COMMENT '是否有改进评语（1是/0否）';
ALTER TABLE uk_qc_result ADD isrmk int(11) DEFAULT '0' COMMENT '质检时是否有备注（1是/0否）';
ALTER TABLE uk_qc_result ADD isitemrmk int(11) DEFAULT '0' COMMENT '质检项是否能填备注（1是/0否）';
ALTER TABLE uk_qc_result ADD isitemdir int(11) DEFAULT '0' COMMENT '质检项是否有说明（1是/0否）';

CREATE TABLE `uk_voice_transcription` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `callid` text NOT NULL COMMENT '通话记录ID',
  `recordfile` text COMMENT '录音文件名',
  `bg` varchar(50) DEFAULT NULL COMMENT '名称',
  `ed` varchar(50) DEFAULT NULL COMMENT '代码',
  `onebest` text COMMENT '代码',
  `speaker` varchar(50) DEFAULT NULL COMMENT '代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `organ` varchar(32) DEFAULT NULL COMMENT '企业ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='录音转写表';

CREATE TABLE `uk_qc_config` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `phonetic` tinyint(4) DEFAULT '0' COMMENT '是否开启语音转写（0关闭1打开）',
  `engine` varchar(50) DEFAULT NULL COMMENT '转写引擎',
  `appid` varchar(50) DEFAULT NULL COMMENT '引擎的 AppId',
  `secretkey` varchar(50) DEFAULT NULL COMMENT '引擎的 secret_key',
  `lfasrhost` text COMMENT '引擎的 api接口网址',
  `filepiecesize` varchar(50) DEFAULT NULL COMMENT '引擎的上传录音文件最大尺寸',
  `storepath` text COMMENT '引擎的转写结果保存位置',
  `maxthreads` int(11) DEFAULT '0' COMMENT '最大线程数',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `organ` varchar(32) DEFAULT NULL COMMENT '企业ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='质监系统 - 配置表';


ALTER TABLE uk_callcenter_event ADD transbegin datetime DEFAULT NULL COMMENT '语音转写开始时间';
ALTER TABLE uk_callcenter_event ADD transend datetime DEFAULT NULL COMMENT '语音转写结束时间';
ALTER TABLE uk_callcenter_event ADD transtime varchar(32) DEFAULT NULL COMMENT '语音转写用时';
ALTER TABLE uk_callcenter_event ADD trans tinyint(4) DEFAULT '0' COMMENT '是否语音转写（0未转写1已转写）';
ALTER TABLE uk_callcenter_event ADD transtatus varchar(32) DEFAULT NULL COMMENT '语音转写状态';
ALTER TABLE uk_callcenter_event ADD transcost tinyint(4) DEFAULT '0' COMMENT '语音转写费用';
ALTER TABLE uk_callcenter_event ADD engine varchar(32) DEFAULT NULL COMMENT '语音转写引擎';

ALTER TABLE uk_callcenter_event ADD qualitypass tinyint(4) DEFAULT '2' COMMENT '质检是否合格(默认2为未质检)';
ALTER TABLE uk_agentservice ADD qualitypass tinyint(4) DEFAULT '2' COMMENT '质检是否合格(默认2为未质检)';
ALTER TABLE uk_workorders ADD qualitypass tinyint(4) DEFAULT '2' COMMENT '质检是否合格(默认2为未质检)';

ALTER TABLE uk_qc_mission_his ADD qualitypass tinyint(4) DEFAULT '2' COMMENT '质检是否合格(默认2为未质检)';
ALTER TABLE uk_qc_mission_his ADD qualityappeal tinyint(4) DEFAULT '2' COMMENT '质检是否申诉过(默认2为未质检)';
ALTER TABLE uk_qc_mission_his ADD qualityarbitrate tinyint(4) DEFAULT '2' COMMENT '质检是否仲裁过(默认2为未质检)';

ALTER TABLE uk_qc_config ADD archivetime int(11) DEFAULT '3' COMMENT '质检自动归档时间，默认3天';
ALTER TABLE uk_qc_config ADD aplarchivetime int(11) DEFAULT '3' COMMENT '已申诉质检自动归档时间，默认3天';

ALTER TABLE uk_callcenter_event ADD tranid varchar(50) DEFAULT NULL COMMENT '语音转写任务ID';

ALTER TABLE uk_consult_invite ADD leavemsgunlimit tinyint DEFAULT 0 COMMENT '启用无限制留言功能';



CREATE TABLE `uk_sms_templet` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `DESCRIPTION` longtext COMMENT '描述',
  `CODE` varchar(255) DEFAULT NULL COMMENT '代码',
  `GROUPID` varchar(255) DEFAULT NULL COMMENT '组ID',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `USERID` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `TEMPLETTITLE` varchar(500) DEFAULT NULL COMMENT '模板标题内容',
  `TEMPLETTEXT` longtext COMMENT '模板内容',
  `TEMPLETTYPE` varchar(255) DEFAULT NULL COMMENT '模板类型',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `ICONSTR` varchar(255) DEFAULT NULL COMMENT '自定义样式',
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `ORDERINDEX` int(11) DEFAULT NULL COMMENT '排序位置',
  `TYPEID` varchar(32) DEFAULT NULL COMMENT '分类ID',
  `SELDATA` tinyint(4) DEFAULT NULL COMMENT '启用外键',
  `layoutcols` int(11) DEFAULT '0' COMMENT '布局列数',
  `datatype` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `charttype` varchar(32) DEFAULT NULL COMMENT '图表类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='短信模板表';




CREATE TABLE `uk_sms_record` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '模板名称',
  `DESCRIPTION` longtext COMMENT '描述',
  `CODE` varchar(255) DEFAULT NULL COMMENT '代码',
  `GROUPID` varchar(255) DEFAULT NULL COMMENT '组ID',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `USERID` varchar(255) DEFAULT NULL COMMENT '创建人ID',
  `TEMPLETTITLE` varchar(500) DEFAULT NULL COMMENT '模板标题内容',
  `SMSTEXT` longtext COMMENT '模板内容',
  `SUBTIME` datetime COMMENT '提交时间',
  `SENDTIME` datetime COMMENT '发送时间',
  `SMSID` varchar(32) COMMENT '短信网关ID',
  `SENDRESULT` text COMMENT '短信发送结果',
  `SENDOK` tinyint DEFAULT 0 COMMENT '是否发送成功',
  `APPKEY` VARCHAR(100) DEFAULT NULL COMMENT '分配类型',
  `SMSTYPE` VARCHAR(100) DEFAULT NULL COMMENT '分配类型',
  `DISTYPE` VARCHAR(100) DEFAULT NULL COMMENT '分配类型',
  `DATAID` VARCHAR(100) DEFAULT NULL COMMENT '数据ID',
  `TASKID` VARCHAR(100) DEFAULT NULL COMMENT '任务ID',
  `FILTERID` VARCHAR(100) DEFAULT NULL COMMENT '筛选表单ID',
  `PHONENUMBER` VARCHAR(100) DEFAULT NULL COMMENT '电话号码',
  `ACTID` VARCHAR(32) DEFAULT NULL COMMENT '活动ID',
  `BATID` VARCHAR(32) DEFAULT NULL COMMENT '活动ID',
  `ORGAN` VARCHAR(32) DEFAULT NULL COMMENT '部门',
  `EXTENTION` VARCHAR(32) DEFAULT NULL COMMENT '分机ID',
  `EXTNO` VARCHAR(32) DEFAULT NULL COMMENT '分机号码',

  `TEMPLETTYPE` varchar(255) DEFAULT NULL COMMENT '模板类型',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `ICONSTR` varchar(255) DEFAULT NULL COMMENT '自定义样式',
  `MEMO` varchar(255) DEFAULT NULL COMMENT '备注',
  `ORDERINDEX` int(11) DEFAULT NULL COMMENT '排序位置',
  `TYPEID` varchar(32) DEFAULT NULL COMMENT '分类ID',
  `SELDATA` tinyint(4) DEFAULT NULL COMMENT '启用外键',
  `layoutcols` int(11) DEFAULT '0' COMMENT '布局列数',
  `datatype` varchar(32) DEFAULT NULL COMMENT '数据类型',
  `charttype` varchar(32) DEFAULT NULL COMMENT '图表类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='短信发送记录表';



INSERT INTO `uk_sysdic` VALUES ('4028811b671beae801671bfa71a0025e', '短信模板类型', 'pub', 'com.dic.sms.templetype', null, 'data', '0', '', null, null, null, null, '4028cac3614cd2f901614cf8be1f0324', '2018-11-16 18:04:17', null, '1', '0', null, '0', '0', null, null, null, null, null);
INSERT INTO `uk_sysdic` VALUES ('4028811b671beae801671bfae9c30264', '电销名单', 'pub', 'sales', 'ukewo', null, '4028811b671beae801671bfa71a0025e', null, null, null, null, null, '4028cac3614cd2f901614cf8be1f0324', '2018-11-16 18:04:47', '2018-11-16 18:04:47', '0', '1', '4028811b671beae801671bfa71a0025e', '0', '0', null, null, null, null, null);
INSERT INTO `uk_sysdic` VALUES ('4028811b671beae801671bfae9d70265', '电销商品', 'pub', 'pro', 'ukewo', null, '4028811b671beae801671bfa71a0025e', null, null, null, null, null, '4028cac3614cd2f901614cf8be1f0324', '2018-11-16 18:04:47', '2018-11-16 18:04:47', '0', '2', '4028811b671beae801671bfa71a0025e', '0', '0', null, null, null, null, null);

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297efe59672a2af0016736561ed002f6', '短信发送记录', 'pub', 'A08_A06', NULL, 'auth', '402881ef612b1f5b01612cc5d9710545', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-21 20:54:32', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297efe59672a2af001673655955502f1', '短信模板设置', 'pub', 'A06_A10', NULL, 'auth', '402881ef612b1f5b01612cc5a2040543', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-21 20:53:57', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');



ALTER TABLE uk_sales_product ADD url varchar(255) DEFAULT NULL COMMENT '访问地址';
ALTER TABLE uk_sales_product ADD field1 varchar(255) DEFAULT NULL COMMENT '备用字段1';
ALTER TABLE uk_sales_product ADD field2 varchar(255) DEFAULT NULL COMMENT '备用字段2';
ALTER TABLE uk_sales_product ADD field3 varchar(255) DEFAULT NULL COMMENT '备用字段3';
ALTER TABLE uk_sales_product ADD field4 varchar(255) DEFAULT NULL COMMENT '备用字段4';
ALTER TABLE uk_sales_product ADD field5 varchar(255) DEFAULT NULL COMMENT '备用字段5';
ALTER TABLE uk_sales_product ADD field6 varchar(255) DEFAULT NULL COMMENT '备用字段6';
ALTER TABLE uk_sales_product ADD field7 varchar(255) DEFAULT NULL COMMENT '备用字段7';
ALTER TABLE uk_sales_product ADD field8 varchar(255) DEFAULT NULL COMMENT '备用字段8';
ALTER TABLE uk_sales_product ADD field9 varchar(255) DEFAULT NULL COMMENT '备用字段9';
ALTER TABLE uk_sales_product ADD field10 varchar(255) DEFAULT NULL COMMENT '备用字段10';

 ALTER TABLE uk_jobdetail ADD extention varchar(50) DEFAULT NULL COMMENT '机器人ID';
 ALTER TABLE uk_act_callnames ADD ownerai varchar(50) DEFAULT NULL COMMENT '机器人ID';
ALTER TABLE uk_jobdetail ADD enabletaithreads tinyint(4) DEFAULT 0 COMMENT '机器人是否限制并发';
ALTER TABLE uk_jobdetail ADD aithreads tinyint(4) DEFAULT 0 COMMENT '并发数';

ALTER TABLE uk_callcenter_siptrunk ADD prefixstr varchar(50) DEFAULT NULL COMMENT '加拨前缀号码';



ALTER TABLE uk_jobdetail ADD starttime varchar(50) DEFAULT NULL COMMENT '任务起始时间';
ALTER TABLE uk_jobdetail ADD endtime varchar(50) DEFAULT NULL COMMENT '任务结束时间';


CREATE TABLE `uk_spt_level` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '评级名称',
  `sortindex` int(11) DEFAULT '0' COMMENT '序号',
  `minscore` int(11) DEFAULT '0' COMMENT '开始评分范围',
  `maxscore` int(11) DEFAULT NULL COMMENT '结束评分范围',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `processid` varchar(32) DEFAULT NULL COMMENT '话术ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='话术-评级表';

CREATE TABLE `uk_spt_point` (
  `id` varchar(32) NOT NULL,
  `questionid` varchar(32) DEFAULT NULL COMMENT '问题ID',
  `pointtype` varchar(32) DEFAULT NULL COMMENT '关注点类型（0关键字 1通话时长）',
  `focusword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `mincalltime` int(11) DEFAULT '0' COMMENT '开始通话时长范围',
  `maxcalltime` int(11) DEFAULT NULL COMMENT '结束通话时长范围',
  `score` int(11) DEFAULT '0' COMMENT '评分',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='话术-关注点表';


ALTER TABLE uk_que_survey_question ADD title varchar(255) DEFAULT NULL COMMENT '标题';

ALTER TABLE uk_que_survey_question ADD offsetx int(11) DEFAULT '0' COMMENT '位置x';

ALTER TABLE uk_que_survey_question ADD offsety int(11) DEFAULT '0' COMMENT '位置y';

ALTER TABLE uk_que_survey_answer ADD title varchar(255) DEFAULT NULL COMMENT '标题';

ALTER TABLE uk_que_survey_process ADD questionid varchar(32) DEFAULT NULL COMMENT '开始问题id';

ALTER TABLE uk_spt_salespatter ADD questionid varchar(32) DEFAULT NULL COMMENT '开始问题id';

ALTER TABLE uk_que_result ADD busstype varchar(32) DEFAULT NULL COMMENT '业务类型（sale,quesurvey';

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e740666d3bbd30166d3c979880024', '质检系统（语音转写引擎）', 'pub', 'com.quality.phonetic.transcription', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-02 17:38:08', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e740666d3bbd30166d3c9f47c0029', '科大讯飞', 'pub', 'qcphonetic', 'ukewo', 'layui-icon', '297e740666d3bbd30166d3c979880024', '', NULL, '', '', NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-02 17:38:39', NULL, '1', '0', '297e740666d3bbd30166d3c979880024', '0', '0', NULL, NULL, NULL, NULL, NULL);

ALTER TABLE uk_que_result ADD focustimes int(11) DEFAULT NULL COMMENT '关注点次数';

ALTER TABLE uk_que_result ADD level varchar(32) DEFAULT NULL COMMENT '客户评级（a,b,c,d）';

ALTER TABLE uk_spt_point ADD name varchar(255) DEFAULT NULL COMMENT '关注点名称';

CREATE TABLE `uk_que_result_question` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `eventid` varchar(100) DEFAULT NULL COMMENT '通话记录ID',
	`resultid` varchar(32) DEFAULT NULL COMMENT '结果主表ID',
  `processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
  `questionid` varchar(32) DEFAULT NULL COMMENT '问题ID',
  `processtime` int(11) DEFAULT '0' COMMENT '问答时长',
  `asktimes` int(11) DEFAULT '0' COMMENT '提问次数',
  `answertimes` int(11) DEFAULT '0' COMMENT '回答次数',
  `answertime` int(11) DEFAULT '0' COMMENT '回答时长（每个回答时间总和）',
  `errortimes` int(11) DEFAULT '0' COMMENT '回答错误次数',
  `timeouttimes` int(11) DEFAULT '0' COMMENT '回答超时次数',
  `retimes` int(11) DEFAULT '0' COMMENT '重复次数',
  `nameid` varchar(32) DEFAULT NULL COMMENT '名单ID',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `sumscore` int(11) DEFAULT '0' COMMENT '问题总评分',
	`score` int(11) DEFAULT '0' COMMENT '问题评分',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `organ` varchar(32) DEFAULT NULL COMMENT '部门',
  `answer` text DEFAULT NULL COMMENT '回答内容 ，分割',
  `focustimes` int(11) DEFAULT NULL COMMENT '关注点次数',

  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='问卷问题结果';

CREATE TABLE `uk_que_result_point` (
  `id` varchar(32) NOT NULL,
  `questionid` varchar(32) DEFAULT NULL COMMENT '问题ID',
  `eventid` varchar(100) DEFAULT NULL COMMENT '通话记录ID',
  `resultid` varchar(32) DEFAULT NULL COMMENT '结果主表ID',
  `processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
  `resultqueid` varchar(32) DEFAULT NULL COMMENT '结果问题表ID',
  `pointid` varchar(32) DEFAULT NULL COMMENT '关注点id',
  `nameid` varchar(32) DEFAULT NULL COMMENT 'es名单id',
	`answer` varchar(32) DEFAULT NULL COMMENT '当时回答',
	`answertime` int(11) DEFAULT '0' COMMENT '当时回答时长',
  `pointname` varchar(255) DEFAULT NULL COMMENT '关注点名称',
  `pointtype` varchar(32) DEFAULT NULL COMMENT '关注点类型（0关键字 1通话时长）',
  `focusword` varchar(255) DEFAULT NULL COMMENT '关键字',
  `mincalltime` int(11) DEFAULT '0' COMMENT '开始通话时长范围',
  `maxcalltime` int(11) DEFAULT NULL COMMENT '结束通话时长范围',
  `score` int(11) DEFAULT '0' COMMENT '评分',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='结果关注点表';


INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763def0cf0168', '业务概况', 'pub', 'A16_A01', NULL, 'auth', '402888816686bff7016687649b8300f2', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:06:54', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/index.html', 'webim', '2', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763df1a71016c', '机器人列表', 'pub', 'A16_A02', 'ukewo', 'auth', '402888816686bff7016687649b8300f2', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:07:04', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/ailist.html', 'webim', '2', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763df50c30170', '拨打任务', 'pub', 'A16_A03', 'ukewo', 'auth', '402888816686bff7016687649b8300f2', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:07:18', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/task/index.html', 'webim', '2', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763dfa8fe0173', '机器人名单', 'pub', 'A16_A04', 'ukewo', 'auth', '402888816686bff7016687649b8300f2', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:07:41', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763e020a20178', '所有名单', 'pub', 'A16_A04_A01', 'ukewo', 'auth', '4028811c6763b3a2016763dfa8fe0173', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:08:12', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/data.html', 'webim', '3', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763e04dda017c', '未拨打名单', 'pub', 'A16_A04_A02', 'ukewo', 'auth', '4028811c6763b3a2016763dfa8fe0173', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:08:23', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/notcall.html', 'webim', '3', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763e0acd20180', '拨打成功名单', 'pub', 'A16_A04_A03', 'ukewo', 'auth', '4028811c6763b3a2016763dfa8fe0173', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:08:47', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/success.html', 'webim', '3', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028811c6763b3a2016763e0e5ed0185', '拨打失败名单', 'pub', 'A16_A04_A04', 'ukewo', 'auth', '4028811c6763b3a2016763dfa8fe0173', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 17:09:02', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/faild.html', 'webim', '3', NULL, 'left');
INSERT INTO `uckefu`.`uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816686bff7016687649b8300f2', '外呼机器人', 'pub', 'A16', 'ukewo', 'auth', '402888815d2fe37f015d2fe75cc80002', NULL, NULL, '<i class=\"kfont\" style=\"position: relative;\">&#xe691;</i>', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-11-30 16:54:03', NULL, '0', '0', '402888815d2fe37f015d2fe75cc80002', '0', '0', '/apps/callout/ai/index.html', 'webim', '1', NULL, 'left');

ALTER TABLE uk_consult_invite ADD showcontacts tinyint(4) DEFAULT 0 COMMENT '启用访客端聊天显示联系人名称';
ALTER TABLE uk_consult_invite ADD agentshowcontacts tinyint(4) DEFAULT 0 COMMENT '启用坐席端聊天显示联系人名称';

ALTER TABLE uk_sessionconfig ADD transmsg varchar(255) DEFAULT NULL COMMENT '转接提示信息';
ALTER TABLE uk_sessionconfig ADD enabletransmsg tinyint(4) DEFAULT 0 COMMENT '是否启用转接提示';

ALTER TABLE uk_sales_product modify column price DECIMAL(9,2) DEFAULT 0;

ALTER TABLE uk_jobdetailproduct modify column price DECIMAL(9,2) DEFAULT 0;

ALTER TABLE uk_sessionconfig ADD enablersession tinyint(4) DEFAULT 0 COMMENT '是否启用恢复对话功能';


ALTER TABLE uk_act_config ADD forecast tinyint(4) DEFAULT 0 COMMENT '启用预测式外呼';
ALTER TABLE uk_act_config ADD forecastratio int DEFAULT 0 COMMENT '预测阀值';
ALTER TABLE uk_act_config ADD fmaxavgtime int DEFAULT 0 COMMENT '预计最大平均通话时长';
ALTER TABLE uk_act_config ADD fminavgtime int DEFAULT 0 COMMENT '预计最小平均通话时长';
ALTER TABLE uk_act_config ADD favgaftertime int DEFAULT 0 COMMENT '预计平均后处理时长';

ALTER TABLE uk_act_config ADD enableauto tinyint(4) DEFAULT 0 COMMENT '启用全自动的预览式外呼';


ALTER TABLE uk_que_survey_question ADD interrupt tinyint(4) DEFAULT 0 COMMENT '打断 0不允许';
ALTER TABLE uk_que_survey_question ADD trans VARCHAR(255) DEFAULT NULL COMMENT '转接号码';
ALTER TABLE uk_que_survey_question ADD maxspreak int DEFAULT 10 COMMENT '最大说话时长';
ALTER TABLE uk_que_survey_question ADD interrupttime int DEFAULT 0 COMMENT '打断开始时间';

ALTER TABLE uk_act_config ADD enablefthreads tinyint(4) DEFAULT 0 COMMENT '启用预测式外呼并发控制';
ALTER TABLE uk_act_config ADD fthreads int DEFAULT 0 COMMENT '预测式外呼并发数量';


ALTER TABLE uk_act_callnames ADD ownerqueue varchar(50) DEFAULT NULL COMMENT '队列';
ALTER TABLE uk_act_task ADD assignedforecast int DEFAULT 0 COMMENT '分配到队列的数量';
ALTER TABLE uk_act_filter_his ADD assignedforecast int DEFAULT 0 COMMENT '分配到队列的数量';
ALTER TABLE uk_act_callnames ADD ownerforecast VARCHAR(50) DEFAULT NULL COMMENT '分配的队列';

ALTER TABLE uk_jobdetail ADD forecastid varchar(50) DEFAULT NULL COMMENT '预测式外呼 队列ID';



ALTER TABLE uk_callcenter_skill ADD siptrunk varchar(50) DEFAULT NULL COMMENT '绑定网关';

ALTER TABLE uk_callcenter_event ADD forecast tinyint(4) DEFAULT 0 COMMENT '预测式外呼';
ALTER TABLE uk_callcenter_event ADD skill varchar(50) DEFAULT NULL COMMENT '外呼队列';
ALTER TABLE uk_callcenter_event ADD forecastid varchar(50) DEFAULT NULL COMMENT '外呼队列ID';

ALTER TABLE uk_systemconfig ADD whitelistip text DEFAULT NULL COMMENT '白名单';
ALTER TABLE uk_callcenter_event ADD callresult varchar(500) DEFAULT NULL COMMENT '拨打结果信息';


INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df844cea04e9', '拨打异常名单', 'pub', 'A18_A05_A04', NULL, 'auth', '4028801e67df2f6e0167df82dca904d9', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:20:48', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df83fc9604e5', '拨打失败名单', 'pub', 'A18_A05_A03', NULL, 'auth', '4028801e67df2f6e0167df82dca904d9', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:20:28', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df83a75d04e1', '拨打成功名单', 'pub', 'A18_A05_A02', NULL, 'auth', '4028801e67df2f6e0167df82dca904d9', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:20:06', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df833dcd04dd', '未拨打名单', 'pub', 'A18_A05_A01', NULL, 'auth', '4028801e67df2f6e0167df82dca904d9', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:19:39', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df82dca904d9', '所有名单', 'pub', 'A18_A05', NULL, 'auth', '4028801e67df2f6e0167df814cb604c5', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:19:14', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df82837f04d5', '队列名单', 'pub', 'A18_A04', NULL, 'auth', '4028801e67df2f6e0167df814cb604c5', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:18:51', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df822bc904d1', '拨打任务', 'pub', 'A18_A03', NULL, 'auth', '4028801e67df2f6e0167df814cb604c5', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:18:29', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df81dd6904cd', '队列列表', 'pub', 'A18_A02', NULL, 'auth', '4028801e67df2f6e0167df814cb604c5', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:18:09', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' ', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df819cc504c9', '业务概况', 'pub', 'A18_A01', NULL, 'auth', '4028801e67df2f6e0167df814cb604c5', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:17:52', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' ', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df814cb604c5', '预测式外呼', 'pub', 'A18', NULL, 'auth', '402888815d2fe37f015d2fe75cc80002', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-13 17:58:59', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' ', 'webim', '1', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df80d45404c1', '拨打异常名单', 'pub', 'A17_A02_A11', NULL, 'auth', '402880fb67722ac2016776cab52100f0', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:17:01', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' ', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` VALUES ('4028801e67df2f6e0167df807fd904bd', '拨打异常名单', 'pub', 'A16_A04_A05', NULL, 'auth', '4028811c6763b3a2016763dfa8fe0173', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-12-24 17:16:39', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' 拨打异常名单', 'webim', '3', NULL, 'left');


CREATE TABLE `uk_callcenter_pbxhostlog` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '语音服务器名称',
  `code` varchar(50) DEFAULT NULL COMMENT '语音服务器代码',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `username` varchar(32) DEFAULT NULL COMMENT '创建人用户名',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `result` tinyint(4) DEFAULT '0' COMMENT '语音服务器重连结果',
  `hostid` varchar(50) DEFAULT NULL COMMENT '语音服务器ID',
  `execupdate` tinyint(4) DEFAULT '0' COMMENT '是否执行了更新操作',
  `description` varchar(255) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='语音服务器自动连接日志';

ALTER TABLE uk_callcenter_event ADD dtmf tinyint(4) DEFAULT 0 COMMENT '记录DTMF事件';
ALTER TABLE uk_callcenter_event ADD dtmfrec varchar(255) DEFAULT NULL COMMENT 'DTMF记录';
ALTER TABLE uk_xiaoe_topic ADD relevance text COMMENT '关联知识';

ALTER TABLE uk_act_config ADD appointment tinyint(4) DEFAULT '0' COMMENT '外销弹屏页，是否预约项的默认值（0是/1否）';

ALTER TABLE uk_qc_config ADD phonetrans tinyint(4) DEFAULT '0' COMMENT '启用语音转写配置';

ALTER TABLE uk_callcenter_event ADD workstatus varchar(32) DEFAULT NULL COMMENT '名单业务状态';

ALTER TABLE uk_que_survey_answer ADD anstype VARCHAR(2) DEFAULT '0' COMMENT '话术答案类型 0默认 1公共';


ALTER TABLE uk_callcenter_event ADD hangupcase varchar(32) DEFAULT NULL COMMENT '挂断原因';
ALTER TABLE uk_callcenter_event ADD hangupinitiator varchar(32) DEFAULT NULL COMMENT '挂断方';

ALTER TABLE uk_user ADD disabledesk tinyint(4) DEFAULT 0 COMMENT '关闭默认进入操作指南页';

CREATE TABLE `uk_notice` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `title` text COMMENT '标题',
  `content` text COMMENT '内容',
  `summary` text COMMENT '摘要',
  `tags` text COMMENT '标签',
  `keyword` text COMMENT '关键字',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '修改时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `organ` varchar(32) DEFAULT NULL COMMENT '推送部门',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公告表';

CREATE TABLE `uk_noticemsg` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createrusername` varchar(255) DEFAULT NULL COMMENT '发布推送人name',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `target` varchar(32) DEFAULT NULL COMMENT '接收人id',
  `title` text COMMENT '标题',
  `content` text COMMENT '内容',
  `summary` text COMMENT '摘要',
  `tags` text COMMENT '标签',
  `keyword` text COMMENT '关键字',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公告-接收内容表';

CREATE TABLE `uk_noticetarget` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `targettype` varchar(32) DEFAULT NULL COMMENT '接收类型（坐席、部门）',
  `target` varchar(32) DEFAULT NULL COMMENT '接收对象',
  `noticeid` varchar(32) DEFAULT NULL COMMENT '公告ID',
  `checked` int(11) DEFAULT '0' COMMENT '选中状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公告-接收目标表';


ALTER TABLE uk_noticemsg ADD status tinyint(4) DEFAULT '0' COMMENT '消息阅读状态（0 未读| 1 已读）';
ALTER TABLE uk_noticemsg ADD datastatus tinyint(4) DEFAULT '0' COMMENT '消息数据状态（0 未删除| 1 已删除到回收站）';

ALTER TABLE uk_notice ADD smsserver varchar(32) DEFAULT null COMMENT '短信网关id';
ALTER TABLE uk_notice ADD emailserver varchar(32) DEFAULT null COMMENT '邮件服务器id';
ALTER TABLE uk_notice ADD smstemplate varchar(32) DEFAULT null COMMENT '短信模板id';
ALTER TABLE uk_notice ADD emailtemplate varchar(32) DEFAULT null COMMENT '邮件模板id';

ALTER TABLE uk_consult_invite ADD loadhismsg tinyint DEFAULT 1 COMMENT '启用加载历史消息';




CREATE TABLE `uk_callcenter_routeritem` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `type` varchar(32) DEFAULT NULL COMMENT '子项类别',
  `child` tinyint(4) DEFAULT '0' COMMENT '是否有下级',
  `parentid` varchar(32) DEFAULT NULL COMMENT '上级子项ID',
  `action` varchar(32) DEFAULT NULL COMMENT '动作类型',
  `sortindex` int DEFAULT 0 COMMENT '排序序号',
  `param` varchar(255) DEFAULT NULL COMMENT '参数名称',
  `value` varchar(255) DEFAULT NULL COMMENT '参数值',
  `hostid` varchar(32) DEFAULT NULL COMMENT '服务器ID',
  `routeid` varchar(255) DEFAULT NULL COMMENT '路由ID',
  `data` text DEFAULT NULL COMMENT '参数值',
  `field` varchar(50) DEFAULT NULL COMMENT '匹配参数名称',
  `expression` varchar(255) DEFAULT NULL COMMENT '匹配表达式',
  `onbreak` varchar(255) DEFAULT NULL COMMENT '是否终止',
  `ctype` varchar(255) DEFAULT NULL COMMENT '条件类型',
  `year` varchar(255) DEFAULT NULL COMMENT '年',
  `yday` varchar(255) DEFAULT NULL COMMENT '年日期',
  `mon` varchar(255) DEFAULT NULL COMMENT '月',
  `mday` varchar(255) DEFAULT NULL COMMENT '月日期',
  `week` varchar(255) DEFAULT NULL COMMENT '周',

  `mweek` varchar(255) DEFAULT NULL COMMENT '周序号',
  `wday` varchar(255) DEFAULT NULL COMMENT '周日期',
  `hour` varchar(255) DEFAULT NULL COMMENT '小时',
  `minute` varchar(255) DEFAULT NULL COMMENT '分钟',
  `minuteofday` varchar(255) DEFAULT NULL COMMENT '当天分钟数',
  `timeofday` varchar(255) DEFAULT NULL COMMENT '当天时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='路由子项表';

ALTER TABLE uk_callcenter_pbxhost ADD maxnumlength int DEFAULT 0 COMMENT '最大号码长度';
ALTER TABLE uk_callcenter_pbxhost ADD minnumlength int DEFAULT 0 COMMENT '最小号码长度';

ALTER TABLE uk_callcenter_pbxhost ADD ipregionblack varchar(255) DEFAULT NULL COMMENT '地区黑名单';
ALTER TABLE uk_callcenter_pbxhost ADD ipregionwhite varchar(255) DEFAULT NULL COMMENT '地区白名单';


ALTER TABLE uk_callcenter_event ADD sipaddr varchar(50) DEFAULT NULL COMMENT '客户端IP';

ALTER TABLE uk_noticemsg ADD type varchar(32) DEFAULT null COMMENT '业务类型business、系统类型system、平台类型platform';
ALTER TABLE uk_notice ADD type varchar(32) DEFAULT null COMMENT '业务类型business、系统类型system、平台类型platform';


ALTER TABLE uk_callcenter_extention ADD waittime int(11) DEFAULT 3000 COMMENT '无对话提示等待时长';
ALTER TABLE uk_callcenter_extention ADD waittiptimes int(11) DEFAULT 3 COMMENT '无对话提示次数';

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402813816878483a01687851b79b0024', '公告邮件', 'pub', 'noticemail', 'ukewo', 'layui-icon', '297e63f05d1da6be015d1dae6de20002', '', NULL, '', '', NULL, '4028cac3614cd2f901614cf8be1f0324', '2019-1-23 09:27:27', NULL, 1, 0, '297e63f05d1da6be015d1dae6de20002', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402813816878838d01687889041a0028', '公告短信', 'pub', 'noticesms', 'ukewo', 'layui-icon', '297e63f05d1da6be015d1dae6de20002', '', NULL, '', '', NULL, '4028cac3614cd2f901614cf8be1f0324', '2019-1-23 10:27:51', NULL, 1, 0, '297e63f05d1da6be015d1dae6de20002', 0, 0, NULL, NULL, NULL, NULL, NULL);



ALTER TABLE uk_callcenter_pbxhost ADD savekillevent tinyint(4) DEFAULT 1 COMMENT '保存呼叫拦截记录';

ALTER TABLE uk_noticemsg ADD terracetype varchar(32) DEFAULT NULL COMMENT '平台公告类型（通知 notice /系统升级 system）';

ALTER TABLE uk_noticemsg ADD jarurl text DEFAULT NULL COMMENT '下载jar包地址';

ALTER TABLE uk_que_result ADD discalled VARCHAR(32) DEFAULT NULL COMMENT '被叫号码';
ALTER TABLE uk_que_result ADD distype VARCHAR(32) DEFAULT NULL COMMENT '号码隐藏方式';

ALTER TABLE uk_noticemsg ADD confirm tinyint(4) DEFAULT 0 COMMENT '是否确认升级';

CREATE TABLE `uk_system_updatecon` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `schedule` tinyint(4) DEFAULT '0' COMMENT '启用后，系统会根据设定的时间和通知方式，在即将重启升级前，进行提前通知(0关闭/1开启)',
  `upgradetime` datetime DEFAULT NULL COMMENT '升级时间',
  `scheduletimes` datetime DEFAULT NULL COMMENT '升级前通知的时间间隔',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统升级设置表';

ALTER TABLE uk_system_updatecon DROP COLUMN scheduletimes;
ALTER TABLE uk_system_updatecon ADD scheduletimes int(11) DEFAULT 0 COMMENT '升级前通知的时间间隔';
ALTER TABLE uk_system_updatecon ADD updatetime datetime DEFAULT NULL COMMENT '更新时间';


/*优化  uk_callcenter_event 和uk_chat_message 数据量大时的查询 */
CREATE INDEX index_1 ON uk_callcenter_event  (`ORGI`,`DISCALLER`,`DISCALLED`,`MISSCALL`,`DURATION`,`RINGDURATION`,`calltype`,`servicestatus`,`direction`,`userid`,`organ`,`createtime`,`starttime`) USING BTREE;

CREATE INDEX index_transtatus_transbegin ON uk_callcenter_event  (`transtatus`,`transbegin`) USING BTREE;

CREATE INDEX index_1 ON uk_chat_message  (`chatype`,`agentserviceid`,`ORGI`) USING BTREE;