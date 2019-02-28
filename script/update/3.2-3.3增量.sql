CREATE TABLE `uk_area_type` (
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
  `ENABLE` tinyint(4) DEFAULT NULL COMMENT '分类状态',
  `AREA` text COMMENT '分类描述',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



ALTER TABLE uk_organ ADD area text;
ALTER TABLE uk_xiaoe_scene_type ADD area text;
ALTER TABLE uk_xiaoe_kbs_type ADD area text;


ALTER TABLE uk_consult_invite ADD onlyareaskill tinyint DEFAULT 0;
ALTER TABLE uk_consult_invite ADD areaskilltipmsg text;

ALTER TABLE uk_agentuser ADD sessionid varchar(32);

ALTER TABLE uk_agentservice ADD sessionid varchar(32);

ALTER TABLE uk_inviterecord ADD title varchar(255);
ALTER TABLE uk_inviterecord ADD url varchar(255);
ALTER TABLE uk_inviterecord ADD traceid varchar(32);

ALTER TABLE uk_agentuser ADD title varchar(255);
ALTER TABLE uk_agentuser ADD url varchar(255);
ALTER TABLE uk_agentuser ADD traceid varchar(32);

ALTER TABLE uk_chat_message ADD sessionid varchar(50);

ALTER TABLE uk_agentstatus ADD busy tinyint DEFAULT 0;


ALTER TABLE uk_userevent ADD referer VARCHAR(255);



CREATE TABLE `uk_ad_position` (
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
  `ENABLE` tinyint(4) DEFAULT NULL COMMENT '分类状态',
  `AREA` text COMMENT '分类描述',
  `IMGURL` varchar(255) DEFAULT NULL,
  `TIPTEXT` varchar(100) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `WEIGHT` int(11) DEFAULT NULL,
  `ADTYPE` varchar(50) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `ADPOS` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


