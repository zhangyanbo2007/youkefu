CREATE TABLE `uk_quick_type` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '知识库分类上级ID',
  `INX` int(11) DEFAULT NULL COMMENT '分类排序序号',
  `STARTDATE` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `ENDDATE` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `ENABLE` tinyint(4) DEFAULT NULL COMMENT '分类状态',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `QUICKTYPE` varchar(32) DEFAULT NULL COMMENT '类型（公共/个人）',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE `uk_xiaoe_config` ADD askqs tinyint ;
ALTER TABLE `uk_xiaoe_config` ADD `asktipmsg` varchar(255) ;

ALTER TABLE `uk_xiaoe_config` ADD `resolved` varchar(100) ;
ALTER TABLE `uk_xiaoe_config` ADD `unresolved` varchar(100) ;
ALTER TABLE `uk_xiaoe_config` ADD `redirectagent` tinyint ;
ALTER TABLE `uk_xiaoe_config` ADD `redirecturl` varchar(255) ;
ALTER TABLE `uk_xiaoe_config` ADD `asktimes` int;
ALTER TABLE `uk_xiaoe_config` ADD selectskill int;
ALTER TABLE `uk_xiaoe_config` ADD selectskillmsg varchar(255) ;



update `uk_xiaoe_config` set askqs = false , `redirectagent` = false , `asktimes` = 120 , selectskill = false;


ALTER TABLE uk_consult_invite ADD consult_skill_agent tinyint ;
update uk_consult_invite set consult_skill_agent = true ;

ALTER TABLE uk_chat_message ADD filename varchar(255);
ALTER TABLE uk_chat_message ADD attachmentid varchar(32);
ALTER TABLE uk_chat_message ADD lastagentmsgtime datetime;
ALTER TABLE uk_chat_message ADD agentreplytime int;
ALTER TABLE uk_chat_message ADD filesize int;

ALTER TABLE uk_chat_message ADD lastmsgtime datetime;
ALTER TABLE uk_chat_message ADD agentreplyinterval int;



update uk_chat_message set filesize = 0 , agentreplytime = 0 , agentreplyinterval = 0;


ALTER TABLE uk_agentservice ADD satisfaction tinyint ;
ALTER TABLE uk_agentservice ADD satistime datetime ;
ALTER TABLE uk_agentservice ADD satislevel varchar(50);
ALTER TABLE uk_agentservice ADD satiscomment varchar(255);



ALTER TABLE uk_agentservice ADD trans tinyint ;
ALTER TABLE uk_agentservice ADD transtime datetime ;
ALTER TABLE uk_agentservice ADD transmemo text;
ALTER TABLE uk_agentservice ADD agentuserid varchar(32);


update uk_agentservice set satisfaction = false , trans = false;


ALTER TABLE uk_agentuser ADD agentreplyinterval int DEFAULT 0;
ALTER TABLE uk_agentuser ADD agentreplytime int DEFAULT 0;
ALTER TABLE uk_agentuser ADD agentreplys int DEFAULT 0;
ALTER TABLE uk_agentuser ADD userasks int DEFAULT 0;
ALTER TABLE uk_agentuser ADD avgreplyinterval int DEFAULT 0;
ALTER TABLE uk_agentuser ADD avgreplytime int DEFAULT 0;
update uk_agentuser set agentreplyinterval = 0 , agentreplytime = 0  , avgreplytime = 0  , avgreplyinterval = 0 , agentreplys = 0 , userasks = 0;

ALTER TABLE uk_agentservice ADD agentreplyinterval int DEFAULT 0;
ALTER TABLE uk_agentservice ADD agentreplytime int DEFAULT 0;
ALTER TABLE uk_agentservice ADD avgreplyinterval int DEFAULT 0;
ALTER TABLE uk_agentservice ADD avgreplytime int DEFAULT 0;

ALTER TABLE uk_agentservice ADD agentreplys int DEFAULT 0;
ALTER TABLE uk_agentservice ADD userasks int DEFAULT 0;

update uk_agentservice set agentreplyinterval = 0 , agentreplytime = 0  , avgreplytime = 0  , avgreplyinterval = 0 , userasks = 0 , agentreplys = 0 ;



ALTER TABLE uk_blacklist ADD controltime int DEFAULT 1;
ALTER TABLE uk_blacklist ADD endtime datetime ;
ALTER TABLE uk_blacklist ADD agentuser varchar(255);


ALTER TABLE uk_sessionconfig ADD noagentmsg varchar(255);
ALTER TABLE uk_sessionconfig ADD agentbusymsg varchar(255);

ALTER TABLE uk_sessionconfig ADD successmsg varchar(255);
ALTER TABLE uk_sessionconfig ADD finessmsg varchar(255);