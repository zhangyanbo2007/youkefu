ALTER TABLE uk_act_batch ADD execnum int DEFAULT 0 COMMENT "导入次数";

CREATE TABLE `uk_act_formfilter` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `FILTERTYPE` varchar(32) DEFAULT NULL COMMENT '筛选类型（批次筛选/元数据筛选）',
  `BATID` varchar(32) DEFAULT NULL COMMENT '筛选表单使用的批次ID',
  `TABLEID` varchar(32) DEFAULT NULL COMMENT '筛选表单使用元数据ID',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `EXECNUM` INT DEFAULT '0' COMMENT '执行次数',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `ORGAN` varchar(32) DEFAULT NULL,
  `DESCRIPTION` text,
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `filternum` int(11) DEFAULT '0' COMMENT '筛选次数',
  `conditional` int(11) DEFAULT '0' COMMENT '条件个数',

  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


ALTER TABLE uk_callcenter_event ADD datestr varchar(32) DEFAULT 0 COMMENT '坐席通话日期（yyyy-MM-dd）用于每小时通话数量折线图';
ALTER TABLE uk_callcenter_event ADD hourstr varchar(32) DEFAULT 0 COMMENT '坐席通话时间小时（HH）用于每小时通话数量折线图';



ALTER TABLE uk_historyreport ADD dataid varchar(32) COMMENT "数据ID";
ALTER TABLE uk_historyreport ADD title varchar(100) COMMENT "标题";

ALTER TABLE uk_webim_monitor ADD worktype varchar(50) COMMENT "操作类型";
ALTER TABLE uk_webim_monitor ADD workresult varchar(50) COMMENT "操作结果";
ALTER TABLE uk_webim_monitor ADD dataid varchar(50) COMMENT "数据ID";

ALTER TABLE uk_tableproperties ADD phonenumber tinyint DEFAULT 0 COMMENT "是否电话号码";
ALTER TABLE uk_tableproperties ADD phonetype varchar(50) COMMENT "电话号码类型";
ALTER TABLE uk_tableproperties ADD phonememo varchar(50) COMMENT "电话号码备注";

ALTER TABLE uk_tableproperties ADD secfield tinyint DEFAULT 0 COMMENT "隐藏字段";
ALTER TABLE uk_tableproperties ADD secdistype varchar(50) COMMENT "字段隐藏方式";


ALTER TABLE uk_callcenter_event ADD taskid varchar(50) COMMENT "外呼任务ID";
ALTER TABLE uk_callcenter_event ADD actid varchar(50) COMMENT "外呼活动ID";
ALTER TABLE uk_callcenter_event ADD batid varchar(50) COMMENT "外呼批次ID";

ALTER TABLE uk_callcenter_event ADD batid varchar(50) COMMENT "外呼名单ID";
ALTER TABLE uk_callcenter_event ADD statustype varchar(50) COMMENT "外呼名单状态";

ALTER TABLE uk_callcenter_event ADD disphonenum varchar(50) COMMENT "外呼名单号码";
ALTER TABLE uk_callcenter_event ADD distype varchar(50) COMMENT "外呼名单号码隐藏方式";
ALTER TABLE uk_tableproperties ADD styletype varchar(50) COMMENT "显示样式";

CREATE TABLE `uk_jobdetail` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '创建人',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '任务状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `NAMENUM` int(11) DEFAULT '0' COMMENT '批次包含的名单总数',
  `VALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的有效名单总数',
  `INVALIDNUM` int(11) DEFAULT '0' COMMENT '批次包含的无效名单总数',
  `ASSIGNED` int(11) DEFAULT '0' COMMENT '已分配名单总数',
  `NOTASSIGNED` int(11) DEFAULT '0' COMMENT '未分配名单总数',
  `ENABLE` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分类状态',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `AREA` text COMMENT '分类描述',
  `imptype` varchar(50) DEFAULT NULL COMMENT '导入类型',
  `batchtype` varchar(32) DEFAULT NULL COMMENT '批次类型',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `impurl` text COMMENT '导入URL',
  `filetype` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `dbtype` varchar(50) DEFAULT NULL COMMENT '数据库类型',
  `jdbcurl` text COMMENT '数据库URL',
  `driverclazz` varchar(255) DEFAULT NULL COMMENT '数据库驱动',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `DESCRIPTION` text COMMENT '描述信息',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `SOURCE` varchar(255) DEFAULT NULL COMMENT '来源',
  `CLAZZ` varchar(255) DEFAULT NULL COMMENT '执行的Resource类',
  `TASKFIRETIME` datetime DEFAULT NULL COMMENT '启动时间',
  `CRAWLTASKID` varchar(255) DEFAULT NULL COMMENT '任务ID',
  `EMAIL` varchar(255) DEFAULT NULL COMMENT '邮件地址',
  `NICKNAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `USERID` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `TASKTYPE` varchar(255) DEFAULT NULL COMMENT '任务类型',
  `TASKID` varchar(255) DEFAULT NULL COMMENT '任务ID',
  `FETCHER` smallint(6) NOT NULL COMMENT '当前状态',
  `PAUSE` smallint(6) NOT NULL COMMENT '暂停',
  `PLANTASK` smallint(6) NOT NULL COMMENT '计划任务',
  `SECURE_ID` varchar(32) DEFAULT NULL COMMENT '安全规则ID',
  `CONFIGURE_ID` varchar(32) DEFAULT NULL COMMENT '配置项ID',
  `TAKSPLAN_ID` varchar(32) DEFAULT NULL COMMENT '计划任务ID',
  `CRAWLTASK` varchar(32) DEFAULT NULL COMMENT '当前任务状态',
  `TARGETTASK` varchar(32) DEFAULT NULL COMMENT '目标任务',
  `STARTINDEX` int(11) DEFAULT NULL COMMENT '开始位置',
  `LASTDATE` datetime DEFAULT NULL COMMENT '最后更新时间',
  `CREATETABLE` tinyint(4) DEFAULT NULL COMMENT '是否创建数据表',
  `MEMO` text COMMENT '备注',
  `NEXTFIRETIME` datetime DEFAULT NULL COMMENT '下次启动时间',
  `CRONEXP` varchar(255) DEFAULT NULL COMMENT 'CRON表达式',
  `TASKSTATUS` varchar(32) DEFAULT NULL COMMENT '任务执行状态',
  `usearea` varchar(255) DEFAULT NULL COMMENT '用户数据',
  `areafield` varchar(255) DEFAULT NULL COMMENT '用户字段',
  `areafieldtype` varchar(32) DEFAULT NULL COMMENT '用户数据类型',
  `arearule` varchar(255) DEFAULT NULL COMMENT '用户数据值',
  `minareavalue` varchar(255) DEFAULT NULL COMMENT '最小值',
  `maxareavalue` varchar(255) DEFAULT NULL COMMENT '最大值',
  `formatstr` varchar(255) DEFAULT NULL COMMENT '格式化字符串',
  `DATAID` varchar(1000) DEFAULT NULL COMMENT '报表id字符串',
  `DICID` varchar(1000) DEFAULT NULL COMMENT '目录id字符串',
  `taskinfo` longtext COMMENT 'taskinfo信息',
  `FILTERID` varchar(32) DEFAULT NULL COMMENT '筛选表单ID',
  `FETCH_SIZE` int(11) DEFAULT NULL COMMENT '采集数据窗口大小',
  `LASTINDEX` int(11) DEFAULT NULL COMMENT '结束位置',
  `PAGES` int(11) DEFAULT NULL COMMENT '页面数量',
  `plantaskreadtorun` tinyint(4) DEFAULT NULL COMMENT '计划任务',
  `priority` int(11) DEFAULT NULL COMMENT '策略',
  `runserver` varchar(100) DEFAULT NULL COMMENT '运行服务器',
  `actype` varchar(50) DEFAULT NULL COMMENT '活动类型',
  `distype` varchar(32) DEFAULT NULL COMMENT '分配类型',
  `distpolicy` varchar(50) DEFAULT NULL COMMENT '分配策略',
  `policynum` int(11) DEFAULT NULL COMMENT '分配数量',
  `busstype` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `disnum` varchar(32) DEFAULT NULL COMMENT '默认分配数量',
  `execmd` varchar(32) DEFAULT NULL COMMENT '执行次数',
  `exectarget` varchar(50) DEFAULT NULL COMMENT '执行分配目标',
  `exectype` varchar(32) DEFAULT NULL COMMENT '执行类型',
  `execto` varchar(32) DEFAULT NULL COMMENT '回收数据位置',
  `threads` int(11) DEFAULT '0' COMMENT '启动任务的线程数量',
  `siptrunk` varchar(32) DEFAULT NULL COMMENT '线路信息',
  `province` varchar(32) DEFAULT NULL COMMENT '线路所在省份',
  `city` varchar(32) DEFAULT NULL COMMENT '线路所在城市',
  `prefix` tinyint(4) DEFAULT '0' COMMENT '线路资源拨号前缀',
  `reportid` varchar(32) DEFAULT NULL COMMENT '数据表ID',
  `mapping` tinyint(4) DEFAULT '0' COMMENT '默认映射结构',
  `organid` varchar(32) DEFAULT NULL COMMENT '获取远程批次时的部门ID',
  `localserver` varchar(255) DEFAULT NULL COMMENT '获取远程的本地服务URL',
  PRIMARY KEY (`ID`,`ENABLE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='作业调度任务/活动/批次表';

ALTER TABLE uk_contacts CHANGE mobile mobileno varchar(40);


CREATE TABLE `uk_act_config` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `username` varchar(32) DEFAULT NULL COMMENT '创建人用户名',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `enablecallout` tinyint(4) DEFAULT '0' COMMENT '启用自动外呼功能',
  `countdown` int(11) DEFAULT '0' COMMENT '倒计时时长',
  `enabletagentthreads` tinyint(4) DEFAULT '0' COMMENT '启用坐席外呼并发控制',
  `agentthreads` int(11) DEFAULT '0' COMMENT '坐席外呼并发数量',
  `enabletaithreads` tinyint(4) DEFAULT '0' COMMENT '启用机器人外呼并发控制',
  `aithreads` int(11) DEFAULT '0' COMMENT '机器人并发数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


ALTER TABLE uk_callcenter_siptrunk ADD province varchar(20) COMMENT "省份";
ALTER TABLE uk_callcenter_siptrunk ADD city varchar(20) COMMENT "城市";

ALTER TABLE uk_workorders ADD dataid varchar(50) COMMENT "业务数据ID";
ALTER TABLE uk_workorders ADD eventid varchar(50) COMMENT "通话ID";
ALTER TABLE uk_workorders ADD ani varchar(50) COMMENT "主叫号码";


CREATE TABLE `uk_act_callnames` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `BATID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `DATASTATUS` varchar(32) DEFAULT NULL COMMENT '数据状态',
  `CALLS` int(11) DEFAULT '0' COMMENT '拨打次数',
  `FAILDCALLS` int(11) DEFAULT '0' COMMENT '拨打失败次数',
  `invalid` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `failed` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `WORKSTATUS` varchar(32) DEFAULT NULL,
  `OPTIME` datetime DEFAULT NULL,
  `ORGAN` varchar(32) DEFAULT NULL,
  `BATNAME` varchar(100) DEFAULT NULL,
  `TASKNAME` varchar(100) DEFAULT NULL,
  `owneruser` varchar(100) DEFAULT NULL,
  `ownerdept` varchar(100) DEFAULT NULL,
  `dataid` varchar(100) DEFAULT NULL,
  `taskid` varchar(100) DEFAULT NULL,
  `filterid` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  `leavenum` int(11) DEFAULT '0',
  `metaname` varchar(100) DEFAULT NULL,
  `distype` varchar(100) DEFAULT NULL,
  `previewtime` int(11) DEFAULT '0',
  `previewtimes` int(11) DEFAULT '0',
  `servicetype` text,
  `reservation` tinyint(4) DEFAULT '0',
  `memo` text,
  `firstcalltime` datetime DEFAULT NULL,
  `firstcallstatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;






ALTER TABLE uk_callcenter_event ADD siptrunk varchar(32) COMMENT "线路";
ALTER TABLE uk_callcenter_event ADD prefix tinyint(4) DEFAULT 0 COMMENT "号码加拨0";

CREATE TABLE `uk_act_role` (
  `id` varchar(32) NOT NULL,
  `rolename` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `roleid` varchar(50) DEFAULT NULL COMMENT '角色id',
  `bustype` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `organid` text COMMENT '授权部门id',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

ALTER TABLE uk_callcenter_extention ADD siptrunk varchar(32) COMMENT "线路";
ALTER TABLE uk_callcenter_siptrunk ADD defaultsip tinyint(4) DEFAULT 0 COMMENT "默认网关";
ALTER TABLE uk_callcenter_siptrunk ADD title varchar(50) DEFAULT 0 COMMENT "网关标题";

ALTER TABLE uk_jobdetail ADD mapping tinyint(4) DEFAULT 0 COMMENT "默认映射结构";
ALTER TABLE uk_tableproperties ADD sysfield tinyint(4) DEFAULT 0 COMMENT "系统字段";

ALTER TABLE uk_act_formfilter_item ADD comp varchar(50) COMMENT "逻辑条件";

ALTER TABLE uk_historyreport ADD organ varchar(50) COMMENT "组织机构";

ALTER TABLE uk_xiaoe_config ADD enableother tinyint(4) DEFAULT 0 COMMENT "启用外部机器人";
ALTER TABLE uk_xiaoe_config ADD otherfirst tinyint(4) DEFAULT 0 COMMENT "外部机器人优先";
ALTER TABLE uk_xiaoe_config ADD otherssl tinyint(4) DEFAULT 0 COMMENT "外部机器人启用SSL";
ALTER TABLE uk_xiaoe_config ADD otherurl varchar(255) COMMENT "外部机器人URL";
ALTER TABLE uk_xiaoe_config ADD otherlogin tinyint(4) DEFAULT 0 COMMENT "外部机器人是否需要登录";

ALTER TABLE uk_xiaoe_config ADD othermethod varchar(20) COMMENT "外部机器人提交方式";

ALTER TABLE uk_xiaoe_config ADD otherappkey varchar(255) COMMENT "外部机器人APPKey";
ALTER TABLE uk_xiaoe_config ADD otherappsec varchar(255) COMMENT "外部机器人APPSec";
ALTER TABLE uk_xiaoe_config ADD otherparam text COMMENT "外部机器人参数";

ALTER TABLE uk_xiaoe_config ADD othertempletinput varchar(32) COMMENT "外部机器人提交参数模板";
ALTER TABLE uk_xiaoe_config ADD othertempletoutput varchar(32) COMMENT "外部机器人回复参数解析模板";

ALTER TABLE uk_xiaoe_config ADD oqrdetailurl varchar(255) COMMENT "外部机器人内容URL";
ALTER TABLE uk_xiaoe_config ADD oqrdetailinput varchar(32) COMMENT "外部机器人详情输入参数";
ALTER TABLE uk_xiaoe_config ADD oqrdetailoutput varchar(32) COMMENT "外部机器人详情输出参数";




ALTER TABLE uk_consult_invite ADD agentshortcutkey varchar(32) COMMENT "坐席默认回复消息快捷键";
ALTER TABLE uk_consult_invite ADD usershortcutkey varchar(32) COMMENT "访客默认回复消息快捷键";

ALTER TABLE uk_xiaoe_config ADD enablesuggest tinyint(4) DEFAULT 0 COMMENT "启用推荐功能";
ALTER TABLE uk_xiaoe_config ADD suggestmsg text COMMENT "推荐的提示信息";
ALTER TABLE uk_xiaoe_config ADD othersuggestmsg text COMMENT "命中结果的推荐的提示信息";

ALTER TABLE uk_chat_message ADD suggestmsg text COMMENT "推荐的提示信息";

ALTER TABLE uk_sessionconfig ADD agentautoleave tinyint(4) DEFAULT 0 COMMENT "关闭浏览器自动离线";

ALTER TABLE uk_sessionconfig ADD otherquickplay tinyint(4) DEFAULT 0 COMMENT "启用外部快捷回复功能";

ALTER TABLE uk_sessionconfig ADD oqrsearchurl varchar(255) COMMENT "外部快捷回复搜索地址";
ALTER TABLE uk_sessionconfig ADD oqrsearchinput varchar(32) COMMENT "外部快捷回复搜索输入参数";
ALTER TABLE uk_sessionconfig ADD oqrsearchoutput varchar(32) COMMENT "外部快捷回复搜索输出参数";

ALTER TABLE uk_sessionconfig ADD oqrdetailurl varchar(255) COMMENT "外部快捷回复内容URL";
ALTER TABLE uk_sessionconfig ADD oqrdetailinput varchar(32) COMMENT "外部快捷回复详情输入参数";
ALTER TABLE uk_sessionconfig ADD oqrdetailoutput varchar(32) COMMENT "外部快捷回复详情输出参数";

ALTER TABLE uk_sessionconfig ADD enablequick tinyint(32) DEFAULT 0 COMMENT "启用快捷回复功能";

ALTER TABLE uk_sessionconfig ADD otherssl tinyint(4) DEFAULT 0 COMMENT "外部知识库启用SSL";



ALTER TABLE uk_consult_invite ADD agentctrlenter tinyint(4) DEFAULT 0 COMMENT "启用坐席端CTRL+Enter发送消息";
ALTER TABLE uk_consult_invite ADD ctrlenter tinyint(4) DEFAULT 0 COMMENT "启用访客端CTRL+Enter发送消息";


ALTER TABLE uk_sessionconfig ADD agentctrlenter tinyint(4) DEFAULT 0 COMMENT "启用坐席端CTRL+Enter发送消息";
ALTER TABLE uk_sessionconfig ADD ctrlenter tinyint(4) DEFAULT 0 COMMENT "启用访客端CTRL+Enter发送消息";

ALTER TABLE uk_callcenter_siptrunk ADD busyext varchar(50) COMMENT "坐席忙的时候转到号码";

ALTER TABLE uk_callcenter_siptrunk ADD notready varchar(50) COMMENT "坐席不在线的时候转到号码";

ALTER TABLE uk_callcenter_siptrunk ADD noname varchar(50) COMMENT "未找到名单或未分配的时候转到号码";

ALTER TABLE uk_callcenter_siptrunk ADD enablecallagent tinyint(4) DEFAULT 0 COMMENT "坐席不在线转手机";




INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028888163ffe467016400f640f30646', '外呼系统', 'pub', 'A11', NULL, 'auth', '402888815d2fe37f015d2fe75cc80002', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:01:35', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/index.html', 'webim', '1', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('4028888163ffe467016400f692c7064b', '坐席监控', 'pub', 'A12', NULL, 'auth', '402888815d2fe37f015d2fe75cc80002', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:01:56', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callcenter/service/monitor/allcall.html', 'webim', '1', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f911016401056d340533', '业务概况', 'pub', 'A11_A01', NULL, 'auth', '4028888163ffe467016400f640f30646', NULL, NULL, '', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:18:10', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/index.html', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f911016401185cbb057d', '外呼名单', 'pub', 'A11_A02', NULL, 'auth', '4028888163ffe467016400f640f30646', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:38:51', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f91101640118c86d0581', '外呼活动', 'pub', 'A11_A03', NULL, 'auth', '4028888163ffe467016400f640f30646', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:39:18', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164011a16b90586', '部门名单', 'pub', 'A11_A04', NULL, 'auth', '4028888163ffe467016400f640f30646', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:40:44', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164011a6a57058a', '我的名单', 'pub', 'A11_A05', NULL, 'auth', '4028888163ffe467016400f640f30646', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:41:05', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164011abffe058e', '业务管理', 'pub', 'A11_A06', NULL, 'auth', '4028888163ffe467016400f640f30646', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 09:41:27', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f91101640138fb6b05f4', '批次管理', 'pub', 'A11_A02_A01', NULL, 'auth', '402888816400f911016401185cbb057d', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:14:28', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/batch.html', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013a100c0604', '筛选表单', 'pub', 'A11_A02_A02', NULL, 'auth', '402888816400f911016401185cbb057d', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:15:39', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/formfilter.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013a5fcf0609', '筛选记录', 'pub', 'A11_A02_A03', NULL, 'auth', '402888816400f911016401185cbb057d', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:16:00', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/filter.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013ab717060e', '导入记录', 'pub', 'A11_A02_A04', NULL, 'auth', '402888816400f911016401185cbb057d', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:16:22', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/report/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013c5a060615', '活动管理', 'pub', 'A11_A03_A01', NULL, 'auth', '402888816400f91101640118c86d0581', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:18:09', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/activity.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013ca2e8061a', '拨打任务', 'pub', 'A11_A03_A02', NULL, 'auth', '402888816400f91101640118c86d0581', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:18:28', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/task.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013e27380625', '我的部门名单', 'pub', 'A11_A04_A01', NULL, 'auth', '402888816400f9110164011a16b90586', NULL, NULL, '&#x756e646566696e6564;', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:20:07', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/calloutmyorgan.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013e9b4e062a', '部门预约名单', 'pub', 'A11_A04_A02', NULL, 'auth', '402888816400f9110164011a16b90586', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:20:37', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/myorganapstatus.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013efaca062f', '部门坐席', 'pub', 'A11_A04_A03', NULL, 'auth', '402888816400f9110164011a16b90586', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:21:02', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/myorganuser.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164013f400d0634', '已拨打名单结果', 'pub', 'A11_A04_A04', NULL, 'auth', '402888816400f9110164011a16b90586', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:21:19', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/record/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f91101640141e8ee063d', '我的所有名单', 'pub', 'A11_A05_A01', NULL, 'auth', '402888816400f9110164011a6a57058a', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:24:14', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164014266cd0642', '未拨打名单', 'pub', 'A11_A05_A02', NULL, 'auth', '402888816400f9110164011a6a57058a', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:24:46', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/alcallout.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f91101640142d1490647', '拨打成功名单', 'pub', 'A11_A05_A03', NULL, 'auth', '402888816400f9110164011a6a57058a', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:25:13', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/notcallout.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f911016401433610064c', '拨打失败名单', 'pub', 'A11_A05_A04', NULL, 'auth', '402888816400f9110164011a6a57058a', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:25:39', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/calloutfaild.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f91101640143cab00651', '预约拨打名单', 'pub', 'A11_A05_A05', NULL, 'auth', '402888816400f9110164011a6a57058a', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:26:17', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/names/calloutapp.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f91101640144fcdf0658', '产品或增值服务', 'pub', 'A11_A06_A01', NULL, 'auth', '402888816400f9110164011abffe058e', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:27:35', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/product/control/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f911016401457aa7065f', '电销设置', 'pub', 'A11_A06_A02', NULL, 'auth', '402888816400f9110164011abffe058e', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:28:07', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/config.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('402888816400f9110164014915340680', '角色授权', 'pub', 'A11_A06_A06', NULL, 'auth', '402888816400f9110164011abffe058e', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-15 10:32:04', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callout/role/index.html', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('40288881640eba2a01640ed33b1906b8', '我的拨打结果', 'pub', 'A11_A05_A06', NULL, 'auth', '402888816400f9110164011a6a57058a', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-18 01:38:01', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' ', 'webim', '3', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('40288881640eba2a01640f85359107e6', '坐席监控', 'pub', 'A12_A01', NULL, 'auth', '4028888163ffe467016400f692c7064b', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-18 04:52:25', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, ' ', 'webim', '2', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('40288881640eba2a01640f8599db07ec', '全部坐席', 'pub', 'A12_A01_A01', NULL, 'auth', '40288881640eba2a01640f85359107e6', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-18 04:52:51', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, '/apps/callcenter/service/monitor/allcall.html', 'webim', '1', NULL, 'left');
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('40288881640eba2a01640f85f8af07f0', '坐席状态', 'pub', 'A12_A02', NULL, 'auth', '4028888163ffe467016400f692c7064b', NULL, NULL, ' ', NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-6-18 04:53:15', NULL, 0, 0, '402888815d2fe37f015d2fe75cc80002', 0, 0, 'javascript:;', 'webim', '2', NULL, 'left');




ALTER TABLE uk_callcenter_extention ADD enableai tinyint DEFAULT 0  COMMENT '启用AI机器人';
ALTER TABLE uk_callcenter_extention ADD aiid varchar(32) COMMENT 'AI机器人';
ALTER TABLE uk_callcenter_extention ADD sceneid varchar(32) COMMENT '启用场景配置';
ALTER TABLE uk_callcenter_extention ADD welcomemsg text  COMMENT '机器人欢迎语';
ALTER TABLE uk_callcenter_extention ADD waitmsg text  COMMENT '机器人等待提示语';
ALTER TABLE uk_callcenter_extention ADD tipmessage text  COMMENT '机器人提示客户说话';


ALTER TABLE uk_callcenter_extention ADD asrrecordpath varchar(255)  COMMENT 'ASR结果路径';
ALTER TABLE uk_callcenter_extention ADD ttsrecordpath varchar(255)  COMMENT 'ASR结果路径';
ALTER TABLE uk_callcenter_extention ADD errormessage varchar(255)  COMMENT '异常提示消息';

ALTER TABLE uk_act_task ADD assignedai int default 0  COMMENT '分配到AI的名单数量';


ALTER TABLE uk_act_filter_his ADD assignedai int(11) default 0  COMMENT '分配到AI的名单数量';





ALTER TABLE uk_callcenter_extention ADD enablewebrtc tinyint default 0  COMMENT '启用WebRTC';

ALTER TABLE uk_callcenter_pbxhost ADD enablewebrtc tinyint default 0  COMMENT '启用WebRTC';
ALTER TABLE uk_callcenter_pbxhost ADD webrtcaddress varchar(100) COMMENT 'WebRTC地址';
ALTER TABLE uk_callcenter_pbxhost ADD webrtcport varchar(100) COMMENT 'WebRTC端口';
ALTER TABLE uk_callcenter_pbxhost ADD webrtcssl tinyint default 0  COMMENT 'WebRTC启用SSL';


ALTER TABLE uk_systemconfig ADD loginlogo varchar(255) COMMENT '登陆页面Logo';
ALTER TABLE uk_systemconfig ADD consolelogo varchar(255) COMMENT '后台页面Logo';
ALTER TABLE uk_systemconfig ADD favlogo varchar(255) COMMENT '系统Fav图标Logo';

/*
Navicat MySQL Data Transfer

Source Server         : UCKeFu
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : uckefu

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-17 22:00:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `uk_que_survey_answer`
-- ----------------------------
CREATE TABLE `uk_que_survey_answer` (
  `id` varchar(32) NOT NULL,
  `questionid` varchar(32) DEFAULT NULL COMMENT '问题ID',
  `questionname` varchar(255) DEFAULT NULL COMMENT '问题名称',
  `answer` text COMMENT '问题答案',
  `queid` varchar(32) DEFAULT NULL COMMENT '跳转问题ID',
  `answerscore` int(11) DEFAULT '0' COMMENT '答案评分',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
  `correct` varchar(32) DEFAULT NULL COMMENT '是否是正确答案（0正确1不正确）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='问卷调查-问题答案表';
-- ----------------------------
-- Table structure for `uk_que_survey_process`
-- ----------------------------
CREATE TABLE `uk_que_survey_process` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL COMMENT '问卷名称',
  `scene` varchar(32) DEFAULT NULL COMMENT '问卷适用场景（机器人呼出/坐席手动）',
  `welword` varchar(255) DEFAULT NULL COMMENT '问卷欢迎语（文字）',
  `welvoice` varchar(255) DEFAULT NULL COMMENT '问卷欢迎语ID（语音）',
  `weltype` varchar(32) DEFAULT NULL COMMENT '问卷欢迎语类型',
  `endword` varchar(255) DEFAULT NULL COMMENT '问卷结束语（文字）',
  `endvoice` varchar(255) DEFAULT NULL COMMENT '问卷结束语ID（语音）',
  `endtype` varchar(32) DEFAULT NULL COMMENT '问卷结束语类型',
  `totalscore` varchar(32) DEFAULT NULL COMMENT '参考评分值',
  `score` varchar(32) DEFAULT '0' COMMENT '是否评分（0否1是）',
  `memo` text COMMENT '备注',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `prostatus` varchar(32) DEFAULT '0' COMMENT '问卷状态（0未发布1发布）',
  `sumscore` varchar(32) DEFAULT NULL COMMENT '总评分值',
  `description` text COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='问卷调查表';

-- ----------------------------
-- Table structure for `uk_que_survey_question`
-- ----------------------------
CREATE TABLE `uk_que_survey_question` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '问题名称',
  `sortindex` varchar(32) DEFAULT NULL COMMENT '问题序号',
  `quetype` int(11) DEFAULT '0' COMMENT '问题类型（0选择题1问答题）',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `description` text COMMENT '描述',
  `memo` text COMMENT '备注',
  `score` int(11) DEFAULT NULL COMMENT '问题分值',
  `processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
  `wvtype` varchar(32) DEFAULT NULL COMMENT '类型（文字/语音）',
  `quevoice` varchar(32) DEFAULT NULL COMMENT '语音ID',
  `confirmtype` varchar(32) DEFAULT NULL COMMENT '答案确认语类型',
  `confirmword` varchar(255) DEFAULT NULL COMMENT '答案确认语文字',
  `confirmvoice` varchar(255) DEFAULT NULL COMMENT '答案确认语语音',
  `overtimetype` varchar(255) DEFAULT NULL COMMENT '回答超时语',
  `overtimeword` varchar(255) DEFAULT NULL COMMENT '回答超时语文字',
  `overtimevoice` varchar(255) DEFAULT NULL COMMENT '回答超时语语音',
  `errortype` varchar(255) DEFAULT NULL COMMENT '回答错误语',
  `errorword` varchar(255) DEFAULT NULL COMMENT '回答错误语文字',
  `errorvoice` varchar(255) DEFAULT NULL COMMENT '回答错误语语音',
  `replykeyword` varchar(255) DEFAULT NULL COMMENT '关键词重复',
  `replytype` varchar(255) DEFAULT NULL COMMENT '重复提示类型',
  `replyword` varchar(255) DEFAULT NULL COMMENT '重复提示语文字',
  `replyvoice` varchar(255) DEFAULT NULL COMMENT '重复提示语语音',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='问卷调查-问题表';



CREATE TABLE `uk_callcenter_event_kill` (
  `ID` varchar(100) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `SOURCE` varchar(50) DEFAULT NULL COMMENT '来源',
  `ANSWER` varchar(50) DEFAULT NULL COMMENT '应答时间',
  `scurrent` tinyint(4) DEFAULT NULL COMMENT '是否当前通话',
  `INIT` tinyint(4) DEFAULT NULL COMMENT '初始通话',
  `CALLER` varchar(50) DEFAULT NULL COMMENT '呼叫发起号码',
  `CALLING` varchar(50) DEFAULT NULL COMMENT '呼叫对象',
  `CALLED` varchar(50) DEFAULT NULL COMMENT '被叫号码',
  `AGENTYPE` varchar(50) DEFAULT NULL COMMENT '坐席类型',
  `QUENE` varchar(50) DEFAULT NULL COMMENT '队列名称',
  `ANI` varchar(50) DEFAULT NULL COMMENT '主叫号码',
  `TOUSER` varchar(50) DEFAULT NULL COMMENT '目标用户',
  `DIRECTION` varchar(50) DEFAULT NULL COMMENT '呼叫方向',
  `STATE` varchar(50) DEFAULT NULL COMMENT '状态',
  `AGENT` varchar(50) DEFAULT NULL COMMENT '坐席工号',
  `ACTION` varchar(50) DEFAULT NULL COMMENT '事件动作',
  `HOST` varchar(50) DEFAULT NULL COMMENT '时间主机',
  `IPADDR` varchar(50) DEFAULT NULL COMMENT '主机IP',
  `LOCALDATETIME` varchar(50) DEFAULT NULL COMMENT '时间发起时间',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态代码',
  `TIME` decimal(20,0) DEFAULT NULL COMMENT '时间秒值',
  `STARTTIME` datetime DEFAULT NULL COMMENT '通话开始时间',
  `ENDTIME` datetime DEFAULT NULL COMMENT '通话结束时间',
  `DURATION` int(11) DEFAULT NULL COMMENT '通话时长',
  `INSIDE` tinyint(4) DEFAULT NULL COMMENT '内线',
  `MISSCALL` tinyint(4) DEFAULT NULL COMMENT '是否漏话',
  `srecord` tinyint(4) DEFAULT NULL COMMENT '是否录音',
  `RECORDTIME` int(11) DEFAULT NULL COMMENT '录音时长',
  `STARTRECORD` datetime DEFAULT NULL COMMENT '开始录音时间',
  `ENDRECORD` datetime DEFAULT NULL COMMENT '结束录音时间',
  `ANSWERTIME` datetime DEFAULT NULL COMMENT '应答时间',
  `RINGDURATION` int(11) DEFAULT NULL COMMENT '振铃时长',
  `SERVICESUMMARY` tinyint(4) DEFAULT NULL COMMENT '是否记录服务小结',
  `SERVICEID` varchar(32) DEFAULT NULL COMMENT '服务记录ID',
  `RECORDFILE` varchar(255) DEFAULT NULL COMMENT '录音文件名',
  `CALLBACK` tinyint(4) DEFAULT NULL COMMENT '回呼',
  `CCQUENE` varchar(50) DEFAULT NULL COMMENT '转接队列',
  `SERVICESTATUS` varchar(20) DEFAULT NULL COMMENT '当前呼叫状态',
  `CHANNELSTATUS` varchar(50) DEFAULT NULL COMMENT '事件中的呼叫状态',
  `COUNTRY` varchar(50) DEFAULT NULL COMMENT '来电国家',
  `PROVINCE` varchar(50) DEFAULT NULL COMMENT '来电号码归属省份',
  `CITY` varchar(50) DEFAULT NULL COMMENT '来电归属号码城市',
  `ISP` varchar(50) DEFAULT NULL COMMENT '来电号码运营商',
  `VOICECALLED` varchar(50) DEFAULT NULL COMMENT '语音呼叫',
  `CONTACTSID` varchar(32) DEFAULT NULL COMMENT '联系人ID',
  `EXTENTION` varchar(32) DEFAULT NULL COMMENT '分机ID',
  `HOSTID` varchar(32) DEFAULT NULL COMMENT 'PBX服务器ID',
  `CALLTYPE` varchar(20) DEFAULT NULL COMMENT '呼叫方向类型|计费类型',
  `CALLDIR` varchar(30) DEFAULT NULL COMMENT '呼叫方向',
  `OTHERDIR` varchar(30) DEFAULT NULL COMMENT '对边呼叫方向',
  `OTHERLEGDEST` varchar(50) DEFAULT NULL COMMENT '呼叫另一方号码',
  `BRIDGEID` varchar(100) DEFAULT NULL COMMENT '桥接ID',
  `BRIDGE` tinyint(4) DEFAULT NULL COMMENT '是否有桥接',
  `RECORDFILENAME` varchar(100) DEFAULT NULL COMMENT '录音文件名',
  `DISCALLER` varchar(50) DEFAULT NULL COMMENT '显示主叫',
  `DISCALLED` varchar(50) DEFAULT NULL COMMENT '显示被叫',
  `SATISF` tinyint(4) DEFAULT '0' COMMENT '满意度',
  `SATISFACTION` varchar(32) DEFAULT NULL COMMENT '满意度结果',
  `SATISFDATE` datetime DEFAULT NULL COMMENT '满意度时间',
  `datestr` varchar(32) DEFAULT '0' COMMENT '坐席通话日期（yyyy-MM-dd）用于每小时通话数量折线图',
  `hourstr` varchar(32) DEFAULT '0' COMMENT '坐席通话时间小时（HH）用于每小时通话数量折线图',
  `taskid` varchar(32) DEFAULT NULL COMMENT '任务ID',
  `actid` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `batid` varchar(32) DEFAULT NULL COMMENT '批次ID',
  `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID',
  `statustype` varchar(32) DEFAULT NULL COMMENT '号码隐藏状态',
  `disphonenum` varchar(32) DEFAULT NULL COMMENT '号码',
  `distype` varchar(32) DEFAULT NULL COMMENT '显示类型',
  `nameid` varchar(50) DEFAULT NULL COMMENT '名单ID',
  `siptrunk` varchar(32) DEFAULT NULL COMMENT '拨打的网关',
  `prefix` tinyint(4) DEFAULT '0' COMMENT '是否在号码前加拨0',
  `userid` varchar(32) DEFAULT NULL COMMENT '坐席用户ID',
  `organ` varchar(32) DEFAULT NULL COMMENT '坐席用户所属部门',
  `tracesip` text COMMENT 'SIP消息记录',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='通话记录表';



ALTER TABLE uk_callcenter_extention ADD bustype varchar(32) default null  COMMENT '业务类型（电销sale/问卷quesurvey）';
ALTER TABLE uk_callcenter_extention ADD proid varchar(32) default null  COMMENT '（产品ID）';
ALTER TABLE uk_callcenter_extention ADD queid varchar(32) default null  COMMENT '（问卷ID）';
ALTER TABLE uk_callcenter_extention ADD aitype varchar(32) default null  COMMENT '机器人类型（smartai/quesurvey）';


ALTER TABLE uk_sales_product ADD provoice varchar(255) default null  COMMENT '语音介绍';

ALTER TABLE uk_callcenter_pbxhost ADD dissipphone tinyint default 0 COMMENT '外呼隐藏话机上的号码';

ALTER TABLE uk_que_survey_question ADD replyrepeat varchar(32) DEFAULT NULL COMMENT '重复确认语-最大重复次数';
ALTER TABLE uk_que_survey_question ADD replyoperate varchar(32) DEFAULT NULL COMMENT '重复确认语-到达最大次数的操作（转接trans/挂断/handup）';
ALTER TABLE uk_que_survey_question ADD replytrans varchar(32) DEFAULT NULL COMMENT '重复确认语-转接号码';
ALTER TABLE uk_que_survey_question ADD replytypeup varchar(32) DEFAULT NULL COMMENT '重复确认语-转接号码';
ALTER TABLE uk_que_survey_question ADD replywordup varchar(255) DEFAULT NULL COMMENT '重复确认语-挂断提示语（文字）';
ALTER TABLE uk_que_survey_question ADD replyvoiceup varchar(32) DEFAULT NULL COMMENT '重复确认语-挂断提示语（语音ID）';

ALTER TABLE uk_que_survey_question ADD overtimerepeat varchar(32) DEFAULT NULL COMMENT '回答超时语-最大重复次数';
ALTER TABLE uk_que_survey_question ADD overtimeoperate varchar(32) DEFAULT NULL COMMENT '回答超时语-到达最大次数的操作（转接trans/挂断/handup）';
ALTER TABLE uk_que_survey_question ADD overtimetrans varchar(32) DEFAULT NULL COMMENT '回答超时语-转接号码';
ALTER TABLE uk_que_survey_question ADD overtimetypeup varchar(32) DEFAULT NULL COMMENT '回答超时语-挂断提示语类型';
ALTER TABLE uk_que_survey_question ADD overtimewordup varchar(255) DEFAULT NULL COMMENT '回答超时语-挂断提示语（文字）';
ALTER TABLE uk_que_survey_question ADD overtimevoiceup varchar(32) DEFAULT NULL COMMENT '回答超时语-挂断提示语（语音ID）';

ALTER TABLE uk_que_survey_question ADD errorepeat varchar(32) DEFAULT NULL COMMENT '回答错误语-最大重复次数';
ALTER TABLE uk_que_survey_question ADD erroroperate varchar(32) DEFAULT NULL COMMENT '回答错误语-到达最大次数的操作（转接trans/挂断/handup）';
ALTER TABLE uk_que_survey_question ADD errortrans varchar(32) DEFAULT NULL COMMENT '回答错误语-转接号码';
ALTER TABLE uk_que_survey_question ADD errortypeup varchar(32) DEFAULT NULL COMMENT '回答错误语-挂断提示语类型';
ALTER TABLE uk_que_survey_question ADD errorwordup varchar(255) DEFAULT NULL COMMENT '回答错误语-挂断提示语（文字）';
ALTER TABLE uk_que_survey_question ADD errorvoiceup varchar(32) DEFAULT NULL COMMENT '回答错误语-挂断提示语（语音ID）';

ALTER TABLE uk_que_survey_answer ADD hanguptype varchar(32) DEFAULT NULL COMMENT '挂断提示语类型';
ALTER TABLE uk_que_survey_answer ADD hangupmsg varchar(255) DEFAULT NULL COMMENT '挂断提示语文字';
ALTER TABLE uk_que_survey_answer ADD hangupvoice varchar(32) DEFAULT NULL COMMENT '挂断提示语语音';


ALTER TABLE uk_jobdetail ADD organid varchar(32) DEFAULT NULL COMMENT '获取远程批次时的部门ID';
ALTER TABLE uk_jobdetail ADD localserver varchar(255) DEFAULT NULL COMMENT '获取远程的本地服务URL';

ALTER TABLE uk_callcenter_event ADD userid varchar(32) DEFAULT NULL  COMMENT '坐席用户ID';

ALTER TABLE uk_callcenter_event ADD organ varchar(32) DEFAULT NULL  COMMENT '坐席用户所属部门';

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e74066464004b01646402379d068f', '活动状态分类', 'pub', 'com.dic.callout.activity', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-07-04 14:37:04', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);


ALTER TABLE uk_callcenter_pbxhost ADD sipport int default 5060  COMMENT 'SIP服务端口';
ALTER TABLE uk_callcenter_pbxhost ADD blacklist varchar(255) COMMENT '黑名单筛选条件';


ALTER TABLE uk_callcenter_event ADD tracesip text COMMENT 'SIP消息记录';


ALTER TABLE uk_consult_invite ADD enableother tinyint DEFAULT 0 COMMENT '启用外部推荐内容';

ALTER TABLE uk_consult_invite ADD otherurl varchar(255) COMMENT '启用外部推荐内容';
ALTER TABLE uk_consult_invite ADD otherssl tinyint DEFAULT 0 COMMENT '外部推荐启用SSL';
ALTER TABLE uk_consult_invite ADD otherlogin tinyint DEFAULT 0 COMMENT '需要登录';

ALTER TABLE uk_consult_invite ADD otherappkey varchar(50) COMMENT '外部推荐APPKey';
ALTER TABLE uk_consult_invite ADD otherappsec varchar(100) COMMENT '推荐AppSec';

ALTER TABLE uk_consult_invite ADD otherparam varchar(255) COMMENT '推荐AppSec';
ALTER TABLE uk_consult_invite ADD othermethod varchar(50) COMMENT '外部推荐请求方式';
ALTER TABLE uk_consult_invite ADD othertempletinput varchar(50) COMMENT '外部推荐输入格式化模板';
ALTER TABLE uk_consult_invite ADD othertempletoutput varchar(50) COMMENT '外部推荐输出格式化模板';

ALTER TABLE uk_consult_invite ADD oqrdetailurl varchar(255) COMMENT '外部推荐内容URL';
ALTER TABLE uk_consult_invite ADD oqrdetailinput varchar(32) COMMENT '外部推荐详情输入格式化模板';
ALTER TABLE uk_consult_invite ADD oqrdetailoutput varchar(32) COMMENT '外部推荐详情输出格式化模板';
ALTER TABLE uk_consult_invite ADD suggestnum int DEFAULT 0 COMMENT '显示外部推荐条数';


ALTER TABLE uk_callcenter_pbxhost ADD webrtcring varchar(255) COMMENT '来电铃声';

ALTER TABLE uk_consult_invite ADD tipicon varchar(255) DEFAULT NULL COMMENT '气泡提示图标';

ALTER TABLE uk_consult_invite ADD tipagent tinyint DEFAULT 0 COMMENT '在坐席端弹出气泡提示';
ALTER TABLE uk_consult_invite ADD tipuser tinyint DEFAULT 0 COMMENT '在访客端弹出气泡提示';
ALTER TABLE uk_consult_invite ADD tipagenticon varchar(255) DEFAULT NULL COMMENT '坐席端气泡提醒的图标';
ALTER TABLE uk_consult_invite ADD tipagenttitle varchar(100) DEFAULT NULL COMMENT '坐席端气泡提醒的标题';
ALTER TABLE uk_consult_invite ADD tipusertitle varchar(100) DEFAULT NULL COMMENT '访客端气泡提醒的标题';
ALTER TABLE uk_consult_invite ADD tipusericon varchar(100) DEFAULT NULL COMMENT '访客端气泡提醒的图标';
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e74066464004b01646402379d068f', '活动状态分类', 'pub', 'com.dic.callout.activity', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-07-04 14:37:04', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);



ALTER TABLE uk_agentservice ADD invite tinyint DEFAULT 0 COMMENT '是否已邀请评价';
ALTER TABLE uk_agentservice ADD invitedate datetime DEFAULT NULL COMMENT '邀请评价状态';

ALTER TABLE uk_agentservice ADD servicekind varchar(32) DEFAULT NULL COMMENT '服务分类';


ALTER TABLE uk_sessionconfig ADD multisatisf tinyint DEFAULT 0 COMMENT '是否运行访客多次评价';
ALTER TABLE uk_sessionconfig ADD satisftext varchar(255) DEFAULT NULL COMMENT '显示邀请评价的提示文本';

ALTER TABLE uk_sessionconfig ADD aiicon varchar(255) DEFAULT NULL COMMENT 'AI机器人头像';

ALTER TABLE uk_xiaoe_config ADD aiicon varchar(255) DEFAULT NULL COMMENT 'AI机器人头像';
ALTER TABLE uk_consult_invite ADD aiicon varchar(255) DEFAULT NULL COMMENT 'AI机器人头像';




CREATE TABLE `uk_ekm_experts` (
  `id` varchar(32) NOT NULL,
  `userid` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `roleid` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `bustype` varchar(50) DEFAULT NULL COMMENT '业务类型（电销/回访/知识库专家）',
  `auditimes` int(11) DEFAULT '0' COMMENT '审核知识总条数',
  `auditpass` int(11) DEFAULT '0' COMMENT '审核知识通过总条数',
  `auditreject` int(11) DEFAULT '0' COMMENT '审核知识驳回总条数',
  `organ` varchar(50) DEFAULT NULL COMMENT '用户所属部门ID',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT 'EKM知识库专家-授权表';


CREATE TABLE `uk_ekm_knowbase` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `total` int(11) DEFAULT '0' COMMENT '知识库下的知识条数',
  `viewnum` int(11) DEFAULT '0' COMMENT '知识库下的知识被浏览次数',
  `collectnum` int(11) DEFAULT '0' COMMENT '知识库下的知识被收藏次数',
  `organ` varchar(32) DEFAULT NULL COMMENT '所属部门ID',
  `audit` int(11) DEFAULT '0' COMMENT '是否审核（0是/1否）',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT 'EKM知识库表';


CREATE TABLE `uk_ekm_knowledge_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `total` int(11) DEFAULT '0' COMMENT '该分类下的知识条数',
  `viewnum` int(11) DEFAULT '0' COMMENT '该分类下的知识被浏览次数',
  `collectnum` int(11) DEFAULT '0' COMMENT '该分类下的知识被收藏次数',
  `organ` varchar(32) DEFAULT NULL COMMENT '所属部门ID',
  `audit` int(11) DEFAULT '0' COMMENT '是否审核（0是/1否）',
  `auditer` varchar(32) DEFAULT '0' COMMENT '审核人',
  `parentid` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '所属知识库ID',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT '知识库-对应的知识分类树表';


CREATE TABLE `uk_ekm_knowledge` (
  `id` varchar(32) NOT NULL DEFAULT '0',
  `title` text COMMENT '知识标题',
  `summary` text COMMENT '摘要',
  `content` text COMMENT '知识内容',
  `tags` text COMMENT '知识标签',
  `keyword` text COMMENT '关键字',
  `dismenid` varchar(32) DEFAULT NULL COMMENT '所属维度ID（根级目录）',
  `dismentypeid` varchar(32) DEFAULT NULL COMMENT '所属维度分类ID（分支ID）',
  `organ` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `knowledgetypeid` varchar(32) DEFAULT NULL COMMENT '所属知识分类ID',
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '所属知识库ID',
  `pubstatus` varchar(32) DEFAULT NULL COMMENT '知识状态（新建 new/审核中 wait/发布成功 pass/被驳回 rejected /已下架 down）',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `knowledgetype` varchar(32) DEFAULT NULL COMMENT '知识类型（字典项）',
  `begintime` datetime DEFAULT NULL COMMENT '有效开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '有效结束时间',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  `auditor` varchar(32) DEFAULT '0' COMMENT '审核人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='EKM - 知识表';


CREATE TABLE `uk_ekm_dimension` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `organ` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `authorgan` varchar(32) DEFAULT NULL COMMENT '授权部门',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `total` int(11) DEFAULT '0' COMMENT '维度下的知识条数',
  `viewnum` int(11) DEFAULT '0' COMMENT '维度下的知识被浏览次数',
  `collectnum` int(11) DEFAULT '0' COMMENT '维度下的知识被收藏次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT 'EKM - 维度表';


CREATE TABLE `uk_ekm_dimension_type` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(32) NOT NULL COMMENT '维度名',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `organ` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `parent` varchar(32) DEFAULT '0' COMMENT '父级ID',
  `dimensionid` varchar(32) DEFAULT '0' COMMENT '维度ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT 'EKM - 维度分类表';

CREATE TABLE `uk_ekm_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '个人用户名',
  `userid` varchar(50) DEFAULT NULL COMMENT '个人用户ID',
  `topicnum` int(11) DEFAULT '0' COMMENT '个人创建知识总条数',
  `topicpubnum` int(11) DEFAULT '0' COMMENT '个人发布知识总条数',
  `commentsnum` int(11) DEFAULT '0' COMMENT '个人发出评论总条数',
  `auditnum` int(11) DEFAULT '0' COMMENT '个人审核知识总次数',
  `organ` varchar(32) DEFAULT NULL COMMENT '所属部门',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识库-个人贡献信息表';

CREATE TABLE `uk_ekm_comments` (
  `id` varchar(32) NOT NULL,
  `knowledgeid` varchar(32) DEFAULT NULL COMMENT '知识ID',
  `knowledgeower` varchar(32) DEFAULT NULL COMMENT '知识所属人ID',
  `dismenid` varchar(32) DEFAULT NULL COMMENT '所属维度ID（根级目录）',
  `dismentypeid` varchar(32) DEFAULT NULL  COMMENT '所属维度分类ID（分支ID）',
  `knowledgetypeid` varchar(32) DEFAULT NULL COMMENT '所属知识分类ID',
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '所属知识库ID',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `content` text  COMMENT '评论内容',
  `createtime` datetime DEFAULT NULL COMMENT '评论时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '评论人',
  `organ` varchar(32) DEFAULT NULL COMMENT '评论人所属部门',
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT 'EKM知识 - 评论表';

CREATE TABLE `uk_ekm_audit` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `knowid` varchar(32) NOT NULL COMMENT '知识ID',
  `knowtime` datetime DEFAULT NULL COMMENT '知识创建时间',
  `pubstatus` varchar(32) DEFAULT NULL COMMENT '审核状态（待审核 wait/通过 pass/驳回 rejected）',
  `auditor` varchar(32) DEFAULT NULL COMMENT '审核人ID',
  `auditime` datetime DEFAULT NULL COMMENT '审核时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人（知识所属人，提交审核人）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间（提交审核时间）',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `reject` text COMMENT '驳回原因',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `knowtitle` varchar(64) DEFAULT NULL COMMENT '知识标题',
  `auditorname` varchar(32) DEFAULT NULL COMMENT '审核人名称',
  `knowcreatername` varchar(32) DEFAULT NULL COMMENT '知识创建人名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识库-审核表';



CREATE TABLE `uk_ekm_access` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `knowledgeid` varchar(32) DEFAULT NULL COMMENT '知识ID',
  `knowledgeower` varchar(32) DEFAULT NULL COMMENT '知识所属人ID',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人（访客ID）',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间（访问时间）',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识库-访客记录，浏览表';

CREATE TABLE `uk_ekm_knowledge_times` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `knowledgeid` varchar(32) DEFAULT NULL COMMENT '知识ID',
  `viewtimes` int(11) DEFAULT '0' COMMENT '被浏览次数',
  `commentstimes` int(11) DEFAULT '0' COMMENT '被评论次数',
  `collectimes` int(11) DEFAULT '0' COMMENT '被收藏次数',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间（访问时间）',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识-字表（统计相关次数）';

INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`, `organ`) VALUES ('297e7406650cce9d01650ce68c37067e', 'EKM知识库知识类型', 'pub', 'com.dic.ekm.knowledge.type', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-08-06 09:42:40', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL, NULL);

CREATE TABLE `uk_ekm_knowledge_collect` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `knowledgeid` varchar(32) DEFAULT NULL COMMENT '知识ID',
  `knowledgeower` varchar(32) DEFAULT NULL COMMENT '知识作者',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `status` varchar(32) DEFAULT NULL COMMENT '收藏状态（true收藏/false取消收藏）',
  `creater` varchar(32) DEFAULT NULL COMMENT '收藏人ID',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间（收藏时间）',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识-字表（收藏表）';

CREATE TABLE `uk_act_callnames_his` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `ACTID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `BATID` varchar(32) DEFAULT NULL COMMENT '活动ID',
  `DATASTATUS` varchar(32) DEFAULT NULL COMMENT '数据状态',
  `CALLS` int(11) DEFAULT '0' COMMENT '拨打次数',
  `FAILDCALLS` int(11) DEFAULT '0' COMMENT '拨打失败次数',
  `invalid` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `failed` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `WORKSTATUS` varchar(32) DEFAULT NULL,
  `OPTIME` datetime DEFAULT NULL,
  `ORGAN` varchar(32) DEFAULT NULL,
  `BATNAME` varchar(100) DEFAULT NULL,
  `TASKNAME` varchar(100) DEFAULT NULL,
  `owneruser` varchar(100) DEFAULT NULL,
  `ownerdept` varchar(100) DEFAULT NULL,
  `dataid` varchar(100) DEFAULT NULL,
  `taskid` varchar(100) DEFAULT NULL,
  `filterid` varchar(100) DEFAULT NULL,
  `phonenumber` varchar(100) DEFAULT NULL,
  `leavenum` int(11) DEFAULT '0',
  `metaname` varchar(100) DEFAULT NULL,
  `distype` varchar(100) DEFAULT NULL,
  `previewtime` int(11) DEFAULT '0',
  `previewtimes` int(11) DEFAULT '0',
  `servicetype` text,
  `reservation` tinyint(4) DEFAULT '0',
  `memo` text,
  `firstcalltime` datetime DEFAULT NULL,
  `firstcallstatus` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



ALTER TABLE uk_tableproperties ADD privatefield tinyint DEFAULT 0 COMMENT '本地私有字段'; 
ALTER TABLE uk_consult_invite ADD hideagent tinyint DEFAULT 0 COMMENT '在机器人界面上隐藏人工坐席';



ALTER TABLE uk_xiaoe_config ADD transagent tinyint(4) DEFAULT 0 COMMENT "显示转人工坐席按钮";

ALTER TABLE uk_xiaoe_config ADD othertimeout int(4) DEFAULT 0 COMMENT "接口超时时长";
ALTER TABLE uk_xiaoe_config ADD othertrans tinyint(4) DEFAULT 0 COMMENT "接口超时自动转人工";

ALTER TABLE uk_callcenter_pbxhost ADD whitelist varchar(255) COMMENT '请求信息中必须包含信息，否则请求被拦截';
ALTER TABLE uk_tableproperties ADD privatefield tinyint DEFAULT 0 COMMENT '本地私有字段';

CREATE TABLE `uk_ekm_knowbase_role` (
  `id` varchar(32) NOT NULL,
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '知识库ID',
  `roleid` varchar(32) DEFAULT NULL COMMENT '角色id',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识库 - 角色授权表';

CREATE TABLE `uk_ekm_knowbase_organ` (
  `id` varchar(32) NOT NULL,
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '知识库ID',
  `organid` varchar(32) DEFAULT NULL COMMENT '部门id',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识库 - 部门授权表';

CREATE TABLE `uk_que_result` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `eventid` varchar(100) DEFAULT NULL COMMENT '通话记录ID',
	`processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
	`questionid` varchar(32) DEFAULT NULL COMMENT '问题ID',
	`processtime` int(11) DEFAULT '0' COMMENT '问答时长',
	`asktimes` int(11) DEFAULT '0' COMMENT '提问次数',
	`answertimes` int(11) DEFAULT '0' COMMENT '回答次数',
	`answertime` int(11) DEFAULT '0' COMMENT '回答时长（每个回答时间总和）',
	`errortimes` int(11) DEFAULT '0' COMMENT '回答错误次数',
	`timeouttimes` int(11) DEFAULT '0' COMMENT '回答超时次数',
	`retimes` int(11) DEFAULT '0' COMMENT '重复次数',
	`actid` varchar(32) DEFAULT NULL COMMENT '活动ID',
	`batchid` varchar(32) DEFAULT NULL COMMENT '批次ID',
	`filterid` varchar(32) DEFAULT NULL COMMENT '筛选表单ID',
	`formfilterid` varchar(32) DEFAULT NULL COMMENT '筛选记录ID',
	`nameid` varchar(32) DEFAULT NULL COMMENT '名单ID',
	`mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
	`sumscore` int(11) DEFAULT '0' COMMENT '问卷总评分',
	`createtime` datetime DEFAULT NULL COMMENT '创建时间',
	`endtime` datetime DEFAULT NULL COMMENT '结束时间',
	`creater` varchar(32) DEFAULT NULL COMMENT '创建人',
	`orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
	`organ` varchar(32) DEFAULT NULL COMMENT '部门',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT '问卷结果主表';

CREATE TABLE `uk_que_result_answer` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `resultid` varchar(100) DEFAULT NULL COMMENT '结果主表ID',
	`processid` varchar(32) DEFAULT NULL COMMENT '问卷ID',
	`questionid` varchar(32) DEFAULT NULL COMMENT '问题ID',
	`quetype` int(11) DEFAULT '0' COMMENT '问题类型（0选择题1问答题）',
	`answerid` varchar(32) DEFAULT NULL COMMENT '答案ID',
	`answertime` int(11) DEFAULT '0' COMMENT '回答时长（单个问题回答时长）',
	`answer` text COMMENT '问题答案（问答题）',
	`answerscore` int(11) DEFAULT '0' COMMENT '答案评分',
	`correct` varchar(32) DEFAULT NULL COMMENT '是否是正确答案（0正确1不正确）',
	`createtime` datetime DEFAULT NULL COMMENT '创建时间',
	`creater` varchar(32) DEFAULT NULL COMMENT '创建人',
	`orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
	`anstatus` varchar(32) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT '问卷结果字表';





ALTER TABLE uk_callcenter_event ADD busstype varchar(32) DEFAULT NULL COMMENT '业务类型';


ALTER TABLE uk_act_callnames ADD callresult varchar(255) DEFAULT NULL COMMENT '呼叫结果消息';
ALTER TABLE uk_act_callnames ADD callsuccess tinyint DEFAULT 0 COMMENT '是否呼叫成功';
ALTER TABLE uk_act_callnames ADD duration int DEFAULT 0 COMMENT '机器人呼叫通话时长';

CREATE TABLE `uk_ekm_knowledge_share` (
  `id` varchar(32) NOT NULL,
  `knowledgeid` varchar(32) DEFAULT NULL COMMENT '知识ID',
	`knowledgeower` varchar(32) DEFAULT NULL COMMENT '知识作者',
	`shareuser` varchar(32) DEFAULT NULL COMMENT '分享给用户',
	`organid` varchar(32) DEFAULT NULL COMMENT '部门id',
	`createtime` datetime DEFAULT NULL,
	`creater` varchar(32) DEFAULT NULL,
	`orgi` varchar(32) DEFAULT NULL,
	`datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识 - 分享表';

CREATE TABLE `uk_ekm_knowledge_auth` (
	`id` varchar(32) NOT NULL,
	`userid` varchar(32) DEFAULT NULL COMMENT '用户ID',
	`knowledgetypeid` varchar(32) DEFAULT NULL COMMENT '知识分类ID',
	`knowledgebaseid` varchar(32) DEFAULT NULL COMMENT '知识库ID',
	`organ` varchar(32) DEFAULT NULL COMMENT '所属部门',
	`auth` text COMMENT '按钮',
	`view` tinyint(4) DEFAULT '0' COMMENT '查看分类权限',
	`createtime` datetime DEFAULT NULL,
	`creater` varchar(32) DEFAULT NULL,
	`orgi` varchar(32) DEFAULT NULL,
	`datastatus` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='知识库 - 分类授权表';



ALTER TABLE uk_callcenter_event ADD metaname varchar(50) DEFAULT NULL COMMENT '名单数据表名';

CREATE TABLE `uk_ekm_knowledge_master` (
  `id` varchar(32) NOT NULL DEFAULT '0',
  `title` text COMMENT '知识标题',
  `summary` text COMMENT '摘要',
  `content` text COMMENT '知识内容',
  `tags` text COMMENT '知识标签',
  `keyword` text COMMENT '关键字',
  `dimenid` text COMMENT '所属维度ID（根级目录）',
  `dimentypeid` text COMMENT '所属维度分类ID（分支ID）',
  `organ` varchar(32) DEFAULT NULL COMMENT '部门ID',
  `knowledgetypeid` varchar(32) DEFAULT NULL COMMENT '所属知识分类ID',
  `knowbaseid` varchar(32) DEFAULT NULL COMMENT '所属知识库ID',
  `pubstatus` varchar(32) DEFAULT NULL COMMENT '知识状态（新建 new/审核中 wait/发布成功 pass/被驳回 rejected /已下架 down）',
  `datastatus` tinyint(4) DEFAULT NULL COMMENT '数据状态',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `knowledgetype` varchar(32) DEFAULT NULL COMMENT '知识类型（字典项）',
  `begintime` datetime DEFAULT NULL COMMENT '有效开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '有效结束时间',
  `createtime` datetime DEFAULT NULL,
  `creater` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  `auditor` varchar(32) DEFAULT '0' COMMENT '审核人ID',
  `nlpnr` text COMMENT '人名nr',
  `nlpns` text COMMENT '地名ns',
  `nlpnt` text COMMENT '机构名称nt',
  `nlpnz` text COMMENT '其他专有名词nz',
  `keyphrase` text COMMENT '关键短语',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='EKM - 知识 - 主表';

ALTER TABLE uk_callcenter_event ADD waste tinyint DEFAULT 0 COMMENT '是否作废名单';


ALTER TABLE uk_sessionconfig ADD servicedic varchar(32) DEFAULT NULL COMMENT '服务会话分类字典项';
ALTER TABLE uk_sessionconfig ADD servicekind tinyint DEFAULT 0 COMMENT '启用服务会话分类';

ALTER TABLE uk_systemconfig ADD savelog tinyint DEFAULT 0 COMMENT '启用日志存储';

CREATE TABLE `uk_sale_count` (
	`id` varchar(32) NOT NULL,
	`dataid` varchar(32) DEFAULT NULL COMMENT '坐席/部门/机器人ID',
	`type` varchar(32) DEFAULT NULL COMMENT '类型（坐席user/部门organ/机器人ai）',
	`namenum` int(11) DEFAULT '0' COMMENT '分配总数',
  `notcall` int(11) DEFAULT '0' COMMENT '未拨打',
  `callsuccess` int(11) DEFAULT '0' COMMENT '拨打成功',
  `callfaild` int(11) DEFAULT '0' COMMENT '拨打失败',
  `aptrue` int(11) DEFAULT '0' COMMENT '已预约',
  `apfalse` int(11) DEFAULT '0' COMMENT '未预约',
	`createtime` datetime DEFAULT NULL,
	`orgi` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='电销 - 坐席名单计数表';



ALTER TABLE uk_consult_invite ADD quickagent tinyint DEFAULT 0 COMMENT '是否显示快捷转人工';

ALTER TABLE uk_callcenter_event ADD callstatus tinyint DEFAULT 0 COMMENT '拨打状态';



ALTER TABLE uk_columnproperties ADD value varchar(32) DEFAULT NULL COMMENT '空值替换';



ALTER TABLE uk_chat_message ADD readstatus tinyint DEFAULT 0 COMMENT '是否已读';
ALTER TABLE uk_chat_message ADD useful tinyint DEFAULT 0 COMMENT '是否有用';

ALTER TABLE uk_callcenter_event ADD apstatus tinyint DEFAULT 0 COMMENT '是否预约名单拨打';


ALTER TABLE uk_systemconfig ADD loginlogowidth varchar(30) COMMENT '登陆页面Logo最大宽度';
ALTER TABLE uk_systemconfig ADD loginlogoheight varchar(30) COMMENT '登陆页面Logo最大高度';
ALTER TABLE uk_systemconfig ADD consolelogowidth varchar(30) COMMENT '后台页面Logo最大宽度';
ALTER TABLE uk_systemconfig ADD consolelogoheight varchar(30) COMMENT '后台页面Logo最大高度';

CREATE TABLE `uk_ekm_search` (
  `id` varchar(32) NOT NULL,
  `conditions` text COMMENT '查询内容',
  `times` int(11) DEFAULT '0' COMMENT '搜索次数',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `orgi` varchar(50) DEFAULT NULL,
	`type` VARCHAR(50) DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='搜索历史表';
  ALTER TABLE uk_sessionconfig ADD `quenetimeout` int(11) DEFAULT '600' COMMENT '允许访客排队的最大时长';
  ALTER TABLE uk_sessionconfig ADD `quenetimeoutmsg` varchar(255) DEFAULT NULL COMMENT '访客排队超市提示消息';
  ALTER TABLE `uk_callcenter_event` ADD  `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID';
  ALTER TABLE `uk_callcenter_event` ADD  `nameid` varchar(32) DEFAULT NULL COMMENT '数据ID';
    ALTER TABLE `uk_xiaoe_config` ADD  `aiid` varchar(32) DEFAULT NULL COMMENT '机器人ID';

	       
  CREATE TABLE `uk_work_session` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `USERID` varchar(50) DEFAULT NULL COMMENT '登录人ID',
  `AGENT` varchar(50) DEFAULT NULL COMMENT '坐席工号',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '坐席用户名（登录名）',
  `AGENTNO` varchar(50) DEFAULT NULL COMMENT '分机号（坐席登录的分机号码）',
  `NAME` varchar(50) DEFAULT NULL COMMENT '坐席姓名',
  `CODE` varchar(50) DEFAULT NULL COMMENT '坐席状态code（对应字典表里的CODE）',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '坐席当前状',
  `BUSY` tinyint(4) DEFAULT '0' COMMENT '坐席是否忙',
  `WORKSTATUS` varchar(50) DEFAULT NULL COMMENT '坐席工作状态',
  `ORGI` varchar(50) DEFAULT NULL COMMENT '租户ID',
  `AGENTSERVICEID` varchar(50) DEFAULT NULL COMMENT '会话ID',
  `SKILL` varchar(50) DEFAULT NULL COMMENT '接入的技能组ID',
  `SKILLNAME` varchar(50) DEFAULT NULL COMMENT '接入的技能组名称',
  `CREATETIME` datetime DEFAULT NULL COMMENT '记录创建时间',
  `ANI` varchar(50) DEFAULT NULL COMMENT '主叫号码',
  `CALLED` varchar(50) DEFAULT NULL COMMENT '被叫号码',
  `SOURCE` varchar(50) DEFAULT NULL COMMENT '来源',
  `SERVICEID` varchar(32) DEFAULT NULL COMMENT '服务记录ID',
  `SERVICESTATUS` varchar(50) DEFAULT NULL COMMENT '当前呼叫状态',
  `DISCALLER` varchar(50) DEFAULT NULL COMMENT '主叫分机号',
  `DISCALLED` varchar(50) DEFAULT NULL COMMENT '被叫分机号',
  `ORGAN` varchar(50) DEFAULT NULL COMMENT '所属组织机构ID',
  `BEGINTIME` datetime DEFAULT NULL COMMENT '状态开始时间',
  `ENDTIME` datetime DEFAULT NULL COMMENT '状态结束时间',
  `FIRSTSTATUS` tinyint(4) DEFAULT '0' COMMENT '当天首次时间',
  `DATESTR` varchar(20) DEFAULT NULL COMMENT '日期字符串',
  `DURATION` int(11) DEFAULT NULL COMMENT '通话时长',
  `IPADDR` varchar(100) DEFAULT NULL COMMENT '通话时长',
  `HOSTNAME` varchar(100) DEFAULT NULL COMMENT '通话时长',
  `ADMIN` tinyint(4) DEFAULT '0',
  `firsttime` tinyint(4) DEFAULT '0' COMMENT '是否首次就绪',
  `firsttimes` int(11) DEFAULT '0' COMMENT '首次就绪时长',
  `CLIENTID` varchar(100) DEFAULT NULL COMMENT '客户端ID',
  `SESSIONID` varchar(50) DEFAULT NULL COMMENT '会话ID',
  `WORKTYPE` varchar(32) DEFAULT NULL COMMENT '业务类型',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='坐席状态表';
CREATE TABLE `uk_act_formfilter_item` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `formfilterid` varchar(32) DEFAULT NULL COMMENT '筛选器ID',
  `field` varchar(32) DEFAULT NULL COMMENT '字段',
  `cond` varchar(32) DEFAULT NULL COMMENT '条件',
  `value` varchar(32) DEFAULT NULL COMMENT '取值',
  `contype` varchar(32) DEFAULT NULL COMMENT '条件类型',
  `itemtype` varchar(32) DEFAULT NULL COMMENT '类型',
  `comp` varchar(50) DEFAULT NULL COMMENT '逻辑条件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='筛选项';
CREATE TABLE `uk_act_formfilter` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '筛选表单名称',
  `CODE` varchar(50) DEFAULT NULL COMMENT '筛选表单代码',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` varchar(50) DEFAULT NULL COMMENT '创建人名称',
  `STATUS` varchar(50) DEFAULT NULL COMMENT '状态',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `FILTERTYPE` varchar(32) DEFAULT NULL COMMENT '筛选类型（批次筛选/元数据筛选）',
  `BATID` varchar(32) DEFAULT NULL COMMENT '筛选表单使用的批次ID',
  `TABLEID` varchar(32) DEFAULT NULL COMMENT '筛选表单使用元数据ID',
  `DATASTATUS` tinyint(4) DEFAULT '0' COMMENT '数据状态',
  `INX` int(11) DEFAULT '0' COMMENT '分类排序序号',
  `ORGAN` varchar(32) DEFAULT NULL COMMENT '部门',
  `DESCRIPTION` text COMMENT '备注信息',
  `execnum` int(11) DEFAULT '0' COMMENT '导入次数',
  `filternum` int(11) DEFAULT '0' COMMENT '筛选次数',
  `conditional` int(11) DEFAULT '0' COMMENT '条件个数',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='筛选表单';

CREATE TABLE `uk_act_task` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='电销任务表';
CREATE TABLE `uk_sales_status` (
  `id` varchar(32) NOT NULL COMMENT '数据ID',
  `name` varchar(255) DEFAULT NULL COMMENT '状态名',
  `code` varchar(255) DEFAULT NULL COMMENT '状态代码',
  `cate` varchar(32) DEFAULT NULL COMMENT '状态分类ID',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `memo` varchar(32) DEFAULT NULL COMMENT '备注',
  `activityid` varchar(32) DEFAULT NULL COMMENT '活动ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='电销状态';



CREATE TABLE `uk_act_filter_his` (
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
INSERT INTO `uk_sysdic` (`ID`, `NAME`, `TITLE`, `CODE`, `ORGI`, `CTYPE`, `PARENTID`, `DESCRIPTION`, `MEMO`, `ICONSTR`, `ICONSKIN`, `CATETYPE`, `CREATER`, `CREATETIME`, `UPDATETIME`, `HASCHILD`, `SORTINDEX`, `DICID`, `DEFAULTVALUE`, `DISCODE`, `URL`, `MODULE`, `MLEVEL`, `RULES`, `MENUTYPE`) VALUES ('297e74066464004b01646402379d068f', '活动状态分类', 'pub', 'com.dic.callout.activity', NULL, 'data', '0', '', NULL, NULL, NULL, NULL, '4028cac3614cd2f901614cf8be1f0324', '2018-07-04 14:37:04', NULL, '1', '0', NULL, '0', '0', NULL, NULL, NULL, NULL, NULL);
ALTER TABLE uk_jobdetail ADD `city` varchar(32) DEFAULT NULL COMMENT '线路所在城市';
ALTER TABLE uk_jobdetail ADD `prefix` tinyint(4) DEFAULT '0' COMMENT '线路资源拨号前缀';
ALTER TABLE uk_jobdetail ADD  `reportid` varchar(32) DEFAULT NULL COMMENT '数据表ID';
ALTER TABLE uk_jobdetail ADD `siptrunk` varchar(32) DEFAULT NULL COMMENT '线路信息';
ALTER TABLE uk_jobdetail ADD `province` varchar(32) DEFAULT NULL COMMENT '线路所在省份';
ALTER TABLE `uk_sales_product_type` ADD `parentid` varchar(32) DEFAULT NULL COMMENT '上级产品分类';
ALTER TABLE `uk_act_config` ADD `dataid` varchar(32) DEFAULT NULL COMMENT '数据ID';
ALTER TABLE `uk_act_config` ADD `previewautocallout` tinyint(4) DEFAULT '0' COMMENT '启用预览倒计时';
ALTER TABLE `uk_act_config` ADD `defaultvalue` varchar(100) DEFAULT NULL COMMENT '默认值';
ALTER TABLE `uk_act_config` ADD `strategy` varchar(100) DEFAULT NULL COMMENT '策略';
ALTER TABLE `uk_act_config` ADD `type` varchar(100) DEFAULT NULL COMMENT '类型';
ALTER TABLE `uk_act_config` ADD `updatetime` datetime DEFAULT NULL COMMENT '更新时间';

  


 


















