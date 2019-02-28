CREATE TABLE `uk_tenant` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `datasourceid` varchar(32) DEFAULT NULL,
  `tenantname` varchar(255) DEFAULT NULL,
  `tenantcode` varchar(255) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `lastmenutime` timestamp NULL DEFAULT NULL,
  `lastbasetime` timestamp NULL DEFAULT NULL,
  `tenantlogo` varchar(255) DEFAULT NULL,
  `tenantvalid` varchar(10) DEFAULT NULL,
  `genpasstype` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `adminuser` varchar(255) DEFAULT NULL,
  `initdb` tinyint(4) DEFAULT '0',
  `inites` tinyint(4) DEFAULT NULL COMMENT '0',
  `inited` tinyint(4) DEFAULT '0',
  `systemtenant` tinyint(4) DEFAULT '0',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sign` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `uk_system_message` (
  `ID` varchar(32) NOT NULL,
  `MSGTYPE` varchar(20) DEFAULT NULL,
  `SMTPSERVER` varchar(255) DEFAULT NULL,
  `SMTPUSER` varchar(255) DEFAULT NULL,
  `SMTPPASSWORD` varchar(255) DEFAULT NULL,
  `MAILFROM` varchar(255) DEFAULT NULL,
  `SECLEV` varchar(50) DEFAULT NULL,
  `SSLPORT` varchar(50) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `URL` varchar(255) DEFAULT NULL,
  `SMSTYPE` varchar(20) DEFAULT NULL,
  `APPKEY` varchar(200) DEFAULT NULL,
  `APPSEC` varchar(200) DEFAULT NULL,
  `SIGN` varchar(50) DEFAULT NULL,
  `TPCODE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




ALTER TABLE uk_systemconfig ADD enablemail tinyint DEFAULT 0;
ALTER TABLE uk_systemconfig ADD enablesms tinyint DEFAULT 0;

ALTER TABLE uk_systemconfig ADD emailid varchar(32);
ALTER TABLE uk_systemconfig ADD emailworkordertp varchar(32);

ALTER TABLE uk_systemconfig ADD smsid varchar(32);
ALTER TABLE uk_systemconfig ADD smsworkordertp varchar(32);


ALTER TABLE uk_systemconfig ADD mailcreatetp varchar(32);
ALTER TABLE uk_systemconfig ADD mailupdatetp varchar(32);
ALTER TABLE uk_systemconfig ADD mailprocesstp varchar(32);
ALTER TABLE uk_systemconfig ADD emailtocreater tinyint DEFAULT 0;
ALTER TABLE uk_systemconfig ADD emailtocreatertp varchar(32);


ALTER TABLE uk_systemconfig ADD smscreatetp varchar(32);
ALTER TABLE uk_systemconfig ADD smsupdatetp varchar(32);
ALTER TABLE uk_systemconfig ADD smsprocesstp varchar(32);
ALTER TABLE uk_systemconfig ADD smstocreater tinyint DEFAULT 0;
ALTER TABLE uk_systemconfig ADD smstocreatertp varchar(32);



ALTER TABLE uk_systemconfig ADD enabletneant tinyint DEFAULT 0;
ALTER TABLE uk_systemconfig ADD tenantshare tinyint DEFAULT 0;
ALTER TABLE uk_systemconfig ADD namealias varchar(100);


ALTER TABLE uk_systemconfig ADD tenantconsole tinyint DEFAULT 0;

ALTER TABLE uk_systemconfig ADD emailshowrecipient tinyint DEFAULT 0;

ALTER TABLE uk_templet ADD templettitle varchar(500);


ALTER TABLE uk_organrole ADD dicid varchar(32);
ALTER TABLE uk_organrole ADD dicvalue varchar(50);


-- ----------------------------
-- Table structure for `uk_orgi_skill_rel`
-- ----------------------------
DROP TABLE IF EXISTS `uk_orgi_skill_rel`;
CREATE TABLE `uk_orgi_skill_rel` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `SKILLID` varchar(50) DEFAULT NULL COMMENT '技能组织ID',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uk_orgi_skill_rel
-- ----------------------------


ALTER TABLE uk_user ADD orgid varchar(32);
ALTER TABLE uk_organ ADD orgid varchar(32);
ALTER TABLE uk_role ADD orgid varchar(32);
ALTER TABLE uk_tenant ADD orgid varchar(32);
ALTER TABLE uk_systemconfig ADD enableregorgi tinyint DEFAULT 0;

CREATE TABLE `uk_organization` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '机构ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `orgtype` varchar(255) DEFAULT NULL COMMENT '类型',
  `orgscale` varchar(255) DEFAULT NULL COMMENT '规模',
  `orgindustry` varchar(255) DEFAULT NULL COMMENT '行业',
  `code` varchar(255) DEFAULT NULL COMMENT '代码',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `logo` varchar(200) DEFAULT NULL COMMENT 'LOGO URL',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;


/* 类似问题关联表*/
DROP TABLE IF EXISTS `uk_xiaoe_topic_item`;
CREATE TABLE `uk_xiaoe_topic_item` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT 'ID',
  `topicid` varchar(255) DEFAULT NULL COMMENT '知识id',
  `title` varchar(255) DEFAULT NULL COMMENT '问题',
  `orgi` varchar(255) DEFAULT NULL COMMENT '产品id',
  `creater` varchar(255) DEFAULT NULL COMMENT '创建人',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

alter table uk_contacts change  column birthday cusbirthday varchar(50);
update uk_tableproperties set fieldname='cusbirthday' where name ='出生日期' and tablename='uk_contacts'