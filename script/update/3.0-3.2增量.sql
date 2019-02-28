
ALTER TABLE `uk_xiaoe_config` ADD askqs TINYINT ;
ALTER TABLE `uk_xiaoe_config` ADD `asktipmsg` VARCHAR(255) ;

ALTER TABLE `uk_xiaoe_config` ADD `resolved` VARCHAR(100) ;
ALTER TABLE `uk_xiaoe_config` ADD `unresolved` VARCHAR(100) ;
ALTER TABLE `uk_xiaoe_config` ADD `redirectagent` TINYINT ;
ALTER TABLE `uk_xiaoe_config` ADD `redirecturl` VARCHAR(255) ;
ALTER TABLE `uk_xiaoe_config` ADD `asktimes` INT;
ALTER TABLE `uk_xiaoe_config` ADD selectskill INT;
ALTER TABLE `uk_xiaoe_config` ADD selectskillmsg VARCHAR(255) ;



UPDATE `uk_xiaoe_config` SET askqs = FALSE , `redirectagent` = FALSE , `asktimes` = 120 , selectskill = FALSE;


ALTER TABLE uk_consult_invite ADD consult_skill_agent TINYINT ;
UPDATE uk_consult_invite SET consult_skill_agent = TRUE ;

ALTER TABLE uk_chat_message ADD filename VARCHAR(255);
ALTER TABLE uk_chat_message ADD attachmentid VARCHAR(32);
ALTER TABLE uk_chat_message ADD lastagentmsgtime DATETIME;
ALTER TABLE uk_chat_message ADD agentreplytime INT;
ALTER TABLE uk_chat_message ADD filesize INT;

ALTER TABLE uk_chat_message ADD lastmsgtime DATETIME;
ALTER TABLE uk_chat_message ADD agentreplyinterval INT;



UPDATE uk_chat_message SET filesize = 0 , agentreplytime = 0 , agentreplyinterval = 0;


ALTER TABLE uk_agentservice ADD satisfaction TINYINT ;
ALTER TABLE uk_agentservice ADD satistime DATETIME ;
ALTER TABLE uk_agentservice ADD satislevel VARCHAR(50);
ALTER TABLE uk_agentservice ADD satiscomment VARCHAR(255);



ALTER TABLE uk_agentservice ADD trans TINYINT ;
ALTER TABLE uk_agentservice ADD transtime DATETIME ;
ALTER TABLE uk_agentservice ADD transmemo TEXT;


UPDATE uk_agentservice SET satisfaction = FALSE , trans = FALSE;


ALTER TABLE uk_agentuser ADD agentreplyinterval INT DEFAULT 0;
ALTER TABLE uk_agentuser ADD agentreplytime INT DEFAULT 0;
ALTER TABLE uk_agentuser ADD agentreplys INT DEFAULT 0;
ALTER TABLE uk_agentuser ADD userasks INT DEFAULT 0;
ALTER TABLE uk_agentuser ADD avgreplyinterval INT DEFAULT 0;
ALTER TABLE uk_agentuser ADD avgreplytime INT DEFAULT 0;
UPDATE uk_agentuser SET agentreplyinterval = 0 , agentreplytime = 0  , avgreplytime = 0  , avgreplyinterval = 0 , agentreplys = 0 , userasks = 0;

ALTER TABLE uk_agentservice ADD agentreplyinterval INT DEFAULT 0;
ALTER TABLE uk_agentservice ADD agentreplytime INT DEFAULT 0;
ALTER TABLE uk_agentservice ADD avgreplyinterval INT DEFAULT 0;
ALTER TABLE uk_agentservice ADD avgreplytime INT DEFAULT 0;

ALTER TABLE uk_agentservice ADD agentreplys INT DEFAULT 0;
ALTER TABLE uk_agentservice ADD userasks INT DEFAULT 0;

UPDATE uk_agentservice SET agentreplyinterval = 0 , agentreplytime = 0  , avgreplytime = 0  , avgreplyinterval = 0 , userasks = 0 , agentreplys = 0 ;


ALTER TABLE uk_sysdic ADD menutype VARCHAR(32);

ALTER TABLE uk_sysdic ADD URL  VARCHAR(255) DEFAULT NULL COMMENT '系统权限资源的URL';

ALTER TABLE uk_sysdic ADD MODULE VARCHAR(32) DEFAULT NULL COMMENT '权限资源所属模块';

ALTER TABLE uk_sysdic ADD MLEVEL VARCHAR(32) DEFAULT NULL COMMENT '菜单级别（一级/二级）';

ALTER TABLE uk_sysdic ADD RULES VARCHAR(100) DEFAULT NULL;



ALTER TABLE uk_systemconfig ADD auth TINYINT(4);

ALTER TABLE uk_systemconfig ADD callout TINYINT(4);

UPDATE uk_systemconfig SET callout = FALSE ;

UPDATE uk_systemconfig SET auth = FALSE ;

ALTER TABLE uk_user ADD superuser TINYINT(4) DEFAULT NULL COMMENT '是否超级管理员';

UPDATE uk_user SET superuser = TRUE ;


DROP TABLE IF EXISTS `uk_quick_type`;
CREATE TABLE `uk_quick_type` (
  `ID` VARCHAR(32) NOT NULL,
  `NAME` VARCHAR(50) DEFAULT NULL,
  `CODE` VARCHAR(50) DEFAULT NULL,
  `CREATETIME` DATETIME DEFAULT NULL,
  `CREATER` VARCHAR(32) DEFAULT NULL,
  `UPDATETIME` DATETIME DEFAULT NULL,
  `ORGI` VARCHAR(32) DEFAULT NULL,
  `USERNAME` VARCHAR(50) DEFAULT NULL,
  `PARENTID` VARCHAR(32) DEFAULT NULL COMMENT '知识库分类上级ID',
  `INX` INT(11) DEFAULT NULL COMMENT '分类排序序号',
  `STARTDATE` DATETIME DEFAULT NULL COMMENT '有效期开始时间',
  `ENDDATE` DATETIME DEFAULT NULL COMMENT '有效期结束时间',
  `ENABLE` TINYINT(4) DEFAULT NULL COMMENT '分类状态',
  `DESCRIPTION` VARCHAR(255) DEFAULT NULL COMMENT '分类描述',
  `QUICKTYPE` VARCHAR(32) DEFAULT NULL COMMENT '类型（公共/个人）',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


ALTER TABLE uk_consult_invite ADD recordhis TINYINT(4) DEFAULT NULL COMMENT '是否记录访问轨迹';

ALTER TABLE uk_consult_invite ADD traceuser TINYINT(4) DEFAULT NULL ;

DROP TABLE IF EXISTS `uk_callcenter_pbxhost`;

CREATE TABLE `uk_callcenter_pbxhost` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `creater` VARCHAR(32) DEFAULT NULL COMMENT '创建人',
  `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `updatetime` DATETIME DEFAULT NULL COMMENT '更新时间',
  `name` VARCHAR(100) DEFAULT NULL COMMENT '名称',
  `orgi` VARCHAR(100) DEFAULT NULL COMMENT '租户ID',
  `hostname` VARCHAR(100) DEFAULT NULL COMMENT '主机名',
  `port` INT(11) DEFAULT NULL COMMENT '端口',
  `password` VARCHAR(100) DEFAULT NULL COMMENT '密码',
  `ipaddr` VARCHAR(32) DEFAULT NULL COMMENT 'IP地址',
  `callbacknumber` VARCHAR(50) DEFAULT NULL COMMENT '回呼号码',
  `autoanswer` TINYINT(4) DEFAULT NULL COMMENT '启用自动接听',
  `callcenter` TINYINT(4) DEFAULT NULL COMMENT '启用呼叫中心功能',
  `recordpath` VARCHAR(100) DEFAULT NULL COMMENT '录音文件位置',
  `ivrpath` VARCHAR(100) DEFAULT NULL COMMENT 'IVR文件位置',
  `fspath` VARCHAR(100) DEFAULT NULL COMMENT 'FS安装路径',
  `device` VARCHAR(50) DEFAULT NULL COMMENT '语音设备类型',
  `callbacktype` VARCHAR(32) DEFAULT NULL COMMENT '回呼送号号码',
  `sipautoanswer` TINYINT(4) DEFAULT NULL COMMENT 'SIP自动应答',
  `abscodec` VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `uk_role_auth`;
CREATE TABLE `uk_role_auth` (
  `ID` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `NAME` VARCHAR(50) DEFAULT NULL COMMENT '名称',
  `CODE` VARCHAR(50) DEFAULT NULL COMMENT '代码',
  `CREATETIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  `CREATER` VARCHAR(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` DATETIME DEFAULT NULL COMMENT '更新时间',
  `ORGI` VARCHAR(32) DEFAULT NULL COMMENT '租户ID',
  `USERNAME` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
  `ROLEID` VARCHAR(32) DEFAULT NULL COMMENT '角色ID',
  `DICID` VARCHAR(32) DEFAULT NULL COMMENT '权限ID',
  `DICVALUE` VARCHAR(30) DEFAULT NULL COMMENT '权限代码',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `uk_organrole`;
CREATE TABLE `uk_organrole` (
  `id` VARCHAR(32) NOT NULL COMMENT '主键ID',
  `organ_id` VARCHAR(32) DEFAULT NULL COMMENT '机构ID',
  `role_id` VARCHAR(32) DEFAULT NULL COMMENT '角色ID',
  `creater` VARCHAR(32) DEFAULT NULL COMMENT '创建人',
  `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `orgi` VARCHAR(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


ALTER TABLE uk_blacklist ADD controltime INT(11) DEFAULT '1' ;

ALTER TABLE uk_blacklist ADD agentuser VARCHAR(255) DEFAULT NULL ;

ALTER TABLE uk_blacklist ADD endtime DATETIME DEFAULT NULL;


ALTER TABLE uk_sessionconfig ADD noagentmsg VARCHAR(255) DEFAULT NULL ;

ALTER TABLE uk_sessionconfig ADD agentbusymsg VARCHAR(255) DEFAULT NULL ;

ALTER TABLE uk_sessionconfig ADD successmsg VARCHAR(255) DEFAULT NULL ;

ALTER TABLE uk_sessionconfig ADD finessmsg VARCHAR(255) DEFAULT NULL ;

ALTER TABLE uk_agentservice ADD agentuserid VARCHAR(32) DEFAULT NULL ;


ALTER TABLE uk_servicesummary ADD PROCESS TINYINT(4) DEFAULT NULL;

ALTER TABLE uk_servicesummary ADD updateuser VARCHAR(32) DEFAULT NULL;

ALTER TABLE uk_servicesummary ADD updatetime DATETIME DEFAULT NULL;

ALTER TABLE uk_servicesummary ADD processmemo TEXT ;






