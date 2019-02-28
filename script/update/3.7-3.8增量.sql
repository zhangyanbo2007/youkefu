CREATE TABLE `uk_datadic` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(32) DEFAULT NULL,
  `TITLE` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `PARENTID` varchar(32) DEFAULT NULL,
  `TYPE` varchar(32) DEFAULT NULL,
  `MEMO` varchar(255) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `CREATETIME` timestamp NULL DEFAULT NULL,
  `UPDATETIME` timestamp NULL DEFAULT NULL,
  `CREATER` varchar(255) DEFAULT NULL,
  `PUBLISHEDTYPE` varchar(32) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `TABTYPE` varchar(32) DEFAULT NULL,
  `DSTYPE` varchar(32) DEFAULT NULL,
  `DSTEMPLET` varchar(255) DEFAULT NULL,
  `SORTINDEX` int(11) DEFAULT NULL,
  `DICTYPE` varchar(32) DEFAULT NULL,
  `ICONCLASS` varchar(100) DEFAULT NULL,
  `CSSSTYLE` varchar(255) DEFAULT NULL,
  `AUTHCODE` varchar(100) DEFAULT NULL,
  `DEFAULTMENU` tinyint(4) DEFAULT NULL,
  `DATAID` varchar(32) DEFAULT NULL,
  `DICICON` varchar(32) DEFAULT NULL,
  `CURICON` varchar(32) DEFAULT NULL,
  `BGCOLOR` varchar(32) DEFAULT NULL,
  `CURBGCOLOR` varchar(32) DEFAULT NULL,
  `MENUPOS` varchar(32) DEFAULT NULL,
  `DISTITLE` varchar(100) DEFAULT NULL,
  `NAVMENU` tinyint(4) DEFAULT '0',
  `QUICKMENU` tinyint(4) DEFAULT '0',
  `PROJECTID` varchar(32) DEFAULT NULL,
  `SPSEARCH` tinyint(4) DEFAULT NULL,
  UNIQUE KEY `SQL121227155530400` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE uk_templet ADD layoutcols int default 0;

ALTER TABLE uk_agentstatus ADD workstatus varchar(50);
ALTER TABLE uk_system_message ADD moreparam text;
ALTER TABLE uk_templet ADD datatype varchar(32) default null;
ALTER TABLE uk_templet ADD charttype varchar(32) default null;

alter table uk_system_message modify column smstype varchar(32);


ALTER TABLE uk_sessionconfig ADD quality tinyint default 0;
ALTER TABLE uk_sessionconfig ADD qualityscore varchar(20);


/*
质检相关
*/
ALTER TABLE uk_agentservice ADD qualitystatus varchar(20);
ALTER TABLE uk_agentservice ADD qualitydisorgan varchar(32);
ALTER TABLE uk_agentservice ADD qualitydisuser varchar(32);

ALTER TABLE uk_agentservice ADD qualityorgan varchar(32);
ALTER TABLE uk_agentservice ADD qualityuser varchar(32);

ALTER TABLE uk_agentservice ADD qualityscore int DEFAULT 0;
ALTER TABLE uk_agentservice ADD qualitytime datetime;
ALTER TABLE uk_agentservice ADD qualitytype varchar(20);

ALTER TABLE uk_agentservice ADD solvestatus varchar(20);

ALTER TABLE uk_agentservice ADD leavemsg tinyint DEFAULT 0;

ALTER TABLE uk_agentservice ADD leavemsgstatus varchar(20) DEFAULT "notprocess";

ALTER TABLE uk_agentservice ADD initiator varchar(32);

ALTER TABLE uk_agentservice ADD agenttimeout int default 0;
ALTER TABLE uk_agentservice ADD agenttimeouttimes int default 0;
ALTER TABLE uk_agentservice ADD servicetimeout tinyint default 0;
ALTER TABLE uk_agentservice ADD agentservicetimeout int default 0;

ALTER TABLE uk_agentservice ADD agent varchar(32);
ALTER TABLE uk_agentservice ADD skill varchar(32);


ALTER TABLE uk_agentuser ADD agenttimeout int default 0;
ALTER TABLE uk_agentuser ADD agenttimeouttimes int default 0;
ALTER TABLE uk_agentuser ADD servicetimeout tinyint default 0;
ALTER TABLE uk_agentuser ADD agentservicetimeout int default 0;

ALTER TABLE uk_sessionconfig ADD servicetimeoutlimit tinyint default 0;
ALTER TABLE uk_sessionconfig ADD servicetimeout int default 0;

ALTER TABLE uk_sessionconfig ADD servicetimeoutmsg varchar(50) default 0;

ALTER TABLE uk_agentservice ADD agentfrewords int default 0;
ALTER TABLE uk_agentservice ADD servicefrewords int default 0;

ALTER TABLE uk_agentuser ADD agentfrewords int default 0;
ALTER TABLE uk_agentuser ADD servicefrewords int default 0;

ALTER TABLE uk_chat_message ADD datastatus tinyint default 0;

ALTER TABLE uk_user ADD maxuser int default 0;
ALTER TABLE uk_user ADD ordertype varchar(20);


/*
Navicat MySQL Data Transfer

Source Server         : ljc
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : 333

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-03-29 17:18:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for uk_columnproperties
-- ----------------------------
CREATE TABLE `uk_columnproperties` (
  `id` varchar(32) NOT NULL,
  `format` varchar(255) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `width` varchar(255) DEFAULT NULL,
  `suffix` varchar(255) DEFAULT NULL,
  `font` varchar(255) DEFAULT NULL,
  `colname` varchar(255) DEFAULT NULL,
  `border` varchar(255) DEFAULT NULL,
  `decimalCount` varchar(255) DEFAULT NULL,
  `sepsymbol` varchar(255) DEFAULT NULL,
  `alignment` varchar(255) DEFAULT NULL,
  `fontStyle` varchar(255) DEFAULT NULL,
  `fontColor` varchar(255) DEFAULT NULL,
  `paramName` varchar(255) DEFAULT NULL,
  `orgi` varchar(255) DEFAULT NULL,
  `dataid` varchar(255) DEFAULT NULL,
  `modelid` varchar(255) DEFAULT NULL,
  `dataname` varchar(255) DEFAULT NULL,
  `cur` varchar(255) DEFAULT NULL,
  `hyp` varchar(255) DEFAULT NULL,
  `timeFormat` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `SORTINDEX` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_cube
-- ----------------------------
CREATE TABLE `uk_cube` (
  `ID` varchar(255) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DB` varchar(32) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `MPOSLEFT` varchar(32) DEFAULT NULL,
  `MPOSTOP` varchar(32) DEFAULT NULL,
  `TYPEID` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `DSTYPE` varchar(255) DEFAULT NULL,
  `MODELTYPE` varchar(32) DEFAULT NULL,
  `createdata` varchar(32) DEFAULT NULL,
  `startindex` int(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `dataid` varchar(32) DEFAULT NULL,
  `dataflag` varchar(255) DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  `UPDATETIME` timestamp NULL DEFAULT NULL,
  `CUBEFILE` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_cube_type
-- ----------------------------
CREATE TABLE `uk_cube_type` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '维度名称',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户id',
  `parentid` varchar(32) DEFAULT NULL COMMENT '模型分类上级ID',
  `inx` int(11) DEFAULT NULL COMMENT '分类排序序号',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `description` varchar(255) DEFAULT NULL COMMENT '分类描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_cubelevel
-- ----------------------------
CREATE TABLE `uk_cubelevel` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `COLUMNAME` varchar(255) DEFAULT NULL,
  `UNIQUEMEMBERS` smallint(6) DEFAULT NULL,
  `TYPE` varchar(32) DEFAULT NULL,
  `LEVELTYPE` varchar(32) DEFAULT NULL,
  `TABLENAME` varchar(255) DEFAULT NULL,
  `CUBEID` varchar(32) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `SORTINDEX` int(11) DEFAULT NULL,
  `PARAMETERS` longtext,
  `ATTRIBUE` longtext,
  `DIMID` varchar(32) DEFAULT NULL,
  `PERMISSIONS` smallint(6) DEFAULT NULL,
  `TABLEPROPERTY` varchar(32) DEFAULT NULL,
  `FORMATSTR` varchar(255) DEFAULT NULL,
  `description` text,
  `creater` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_cubemeasure
-- ----------------------------
CREATE TABLE `uk_cubemeasure` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `COLUMNAME` varchar(255) DEFAULT NULL,
  `UNIQUEMEMBERS` smallint(6) DEFAULT NULL,
  `TYPE` varchar(32) DEFAULT NULL,
  `LEVELTYPE` varchar(32) DEFAULT NULL,
  `TABLENAME` varchar(255) DEFAULT NULL,
  `CUBEID` varchar(32) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `SORTINDEX` int(11) DEFAULT NULL,
  `PARAMETERS` longtext,
  `ATTRIBUE` longtext,
  `MID` varchar(32) DEFAULT NULL,
  `AGGREGATOR` varchar(32) DEFAULT NULL,
  `FORMATSTRING` varchar(255) DEFAULT NULL,
  `CALCULATEDMEMBER` smallint(6) DEFAULT NULL,
  `MODELTYPE` varchar(32) DEFAULT NULL,
  `MEASURE` varchar(32) DEFAULT NULL,
  `description` text,
  `creater` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_cubemetadata
-- ----------------------------
CREATE TABLE `uk_cubemetadata` (
  `ID` varchar(32) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `TB` varchar(32) DEFAULT NULL,
  `ORGI` varchar(255) DEFAULT NULL,
  `CUBEID` varchar(32) DEFAULT NULL,
  `POSTOP` varchar(32) DEFAULT NULL,
  `POSLEFT` varchar(32) DEFAULT NULL,
  `MTYPE` varchar(5) DEFAULT NULL,
  `NAMEALIAS` varchar(255) DEFAULT NULL,
  `PARAMETERS` varchar(255) DEFAULT NULL,
  `ATTRIBUE` longtext,
  `creater` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_dimension
-- ----------------------------
CREATE TABLE `uk_dimension` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CODE` varchar(255) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CUBEID` varchar(32) DEFAULT NULL,
  `ORGI` varchar(255) DEFAULT NULL,
  `TYPE` varchar(32) DEFAULT NULL,
  `SORTINDEX` int(11) DEFAULT NULL,
  `PARAMETERS` longtext,
  `ATTRIBUE` longtext,
  `POSLEFT` varchar(32) DEFAULT NULL,
  `POSTOP` varchar(32) DEFAULT NULL,
  `FORMATSTR` varchar(32) DEFAULT NULL,
  `MODELTYPE` varchar(32) DEFAULT NULL,
  `DIM` varchar(32) DEFAULT NULL,
  `ALLMEMBERNAME` varchar(32) DEFAULT NULL,
  `FKFIELD` varchar(255) DEFAULT NULL,
  `FKTABLE` varchar(255) DEFAULT NULL,
  `FKTABLEID` varchar(255) DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_drilldown
-- ----------------------------
CREATE TABLE `uk_drilldown` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `orgi` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `dataid` varchar(255) DEFAULT NULL,
  `dataname` varchar(255) DEFAULT NULL,
  `tdstyle` varchar(255) DEFAULT NULL,
  `reportid` varchar(255) DEFAULT NULL,
  `modelid` varchar(255) DEFAULT NULL,
  `paramname` varchar(255) DEFAULT NULL,
  `paramtype` varchar(255) DEFAULT NULL,
  `paramurl` varchar(255) DEFAULT NULL,
  `paramtarget` varchar(255) DEFAULT NULL,
  `paramreport` varchar(255) DEFAULT NULL,
  `paramvalue` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_publishedcube
-- ----------------------------
CREATE TABLE `uk_publishedcube` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `DB` varchar(32) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `MPOSLEFT` varchar(32) DEFAULT NULL,
  `MPOSTOP` varchar(32) DEFAULT NULL,
  `TYPEID` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `DSTYPE` varchar(255) DEFAULT NULL,
  `MODELTYPE` varchar(32) DEFAULT NULL,
  `createdata` varchar(32) DEFAULT NULL,
  `startindex` int(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `dataid` varchar(32) DEFAULT NULL,
  `dataflag` varchar(255) DEFAULT NULL,
  `DATAVERSION` int(11) DEFAULT NULL,
  `CREATER` varchar(255) DEFAULT NULL,
  `USERID` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `CUBECONTENT` longtext,
  `DBID` varchar(32) DEFAULT NULL,
  `DICLOCATION` varchar(255) DEFAULT NULL,
  `USEREMAIL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_publishedreport
-- ----------------------------
CREATE TABLE `uk_publishedreport` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `CREATETIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ORGI` varchar(32) DEFAULT NULL,
  `DICID` varchar(32) DEFAULT NULL,
  `CODE` varchar(32) DEFAULT NULL,
  `reporttype` varchar(255) DEFAULT NULL,
  `startindex` int(11) DEFAULT NULL,
  `startdate` datetime DEFAULT NULL,
  `dataid` varchar(32) DEFAULT NULL,
  `dataflag` varchar(255) DEFAULT NULL,
  `DATAVERSION` int(11) DEFAULT NULL,
  `CREATER` varchar(255) DEFAULT NULL,
  `REPORTCONTENT` longtext,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_report
-- ----------------------------
CREATE TABLE `uk_report` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `REPORTTYPE` varchar(32) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `OBJECTCOUNT` int(11) DEFAULT NULL,
  `DICID` varchar(32) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `DESCRIPTION` longtext,
  `HTML` longtext,
  `REPORTPACKAGE` varchar(255) DEFAULT NULL,
  `USEACL` varchar(32) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `rolename` text,
  `userid` text,
  `blacklist` text,
  `REPORTCONTENT` longtext,
  `reportmodel` varchar(32) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  `creater` varchar(255) DEFAULT NULL,
  `reportversion` int(11) DEFAULT NULL,
  `publishedtype` varchar(32) DEFAULT NULL,
  `tabtype` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(32) DEFAULT NULL,
  `USEREMAIL` varchar(255) DEFAULT NULL,
  `CACHE` smallint(6) DEFAULT NULL,
  `EXTPARAM` varchar(255) DEFAULT NULL,
  `TARGETREPORT` varchar(32) DEFAULT NULL,
  `DATASTATUS` tinyint(4) DEFAULT NULL,
  `CODE` varchar(100) DEFAULT NULL,
  `SOURCE` varchar(50) DEFAULT NULL,
  `VIEWTYPE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_reportfilter
-- ----------------------------
CREATE TABLE `uk_reportfilter` (
  `id` varchar(32) NOT NULL DEFAULT '',
  `dataid` varchar(32) DEFAULT NULL,
  `dataname` varchar(255) DEFAULT NULL,
  `modelid` varchar(32) DEFAULT NULL,
  `reportid` varchar(32) DEFAULT NULL,
  `contype` varchar(32) DEFAULT NULL,
  `filtertype` varchar(32) DEFAULT NULL,
  `formatstr` varchar(255) DEFAULT NULL,
  `convalue` varchar(255) DEFAULT NULL,
  `userdefvalue` text,
  `valuefiltertype` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(32) DEFAULT NULL,
  `orgi` varchar(32) DEFAULT NULL,
  `content` text,
  `valuestr` varchar(255) DEFAULT NULL,
  `filterprefix` varchar(255) DEFAULT NULL,
  `filtersuffix` varchar(255) DEFAULT NULL,
  `modeltype` varchar(32) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `funtype` varchar(32) DEFAULT NULL,
  `measureid` varchar(32) DEFAULT NULL,
  `valuecompare` varchar(32) DEFAULT NULL,
  `defaultvalue` text,
  `comparetype` varchar(32) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `cubeid` varchar(32) DEFAULT NULL,
  `mustvalue` varchar(32) DEFAULT NULL,
  `groupids` text,
  `defaultvaluerule` text,
  `dimid` varchar(32) DEFAULT NULL,
  `endvalue` text,
  `filtertemplet` varchar(255) DEFAULT NULL,
  `noformatvalue` text,
  `startvalue` varchar(255) DEFAULT NULL,
  `sortindex` int(11) DEFAULT NULL,
  `cascadeid` varchar(32) DEFAULT NULL,
  `tableproperty` varchar(32) DEFAULT NULL,
  `tableid` varchar(32) DEFAULT NULL,
  `fieldid` varchar(32) DEFAULT NULL,
  `fktableid` varchar(32) DEFAULT NULL,
  `filterfieldid` varchar(32) DEFAULT NULL,
  `isdic` tinyint(4) DEFAULT NULL,
  `diccode` varchar(255) DEFAULT NULL,
  `keyfield` varchar(32) DEFAULT NULL,
  `valuefield` varchar(32) DEFAULT NULL,
  `fkfieldid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for uk_reportmodel
-- ----------------------------
CREATE TABLE `uk_reportmodel` (
  `id` varchar(50) NOT NULL,
  `posx` varchar(50) DEFAULT NULL,
  `posy` varchar(50) DEFAULT NULL,
  `poswidth` varchar(50) DEFAULT NULL,
  `posheight` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `reportid` varchar(50) DEFAULT NULL,
  `modeltype` varchar(50) DEFAULT NULL,
  `sortindex` int(11) DEFAULT NULL,
  `stylestr` varchar(50) DEFAULT NULL,
  `labeltext` varchar(50) DEFAULT NULL,
  `cssclassname` varchar(50) DEFAULT NULL,
  `mposleft` varchar(50) DEFAULT NULL,
  `mpostop` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `exchangerw` tinyint(4) DEFAULT '0',
  `publishedcubeid` varchar(50) DEFAULT NULL,
  `rowdimension` text,
  `coldimension` text,
  `measure` varchar(50) DEFAULT NULL,
  `dstype` varchar(50) DEFAULT NULL,
  `dbtype` varchar(50) DEFAULT NULL,
  `orgi` varchar(50) DEFAULT NULL,
  `objectid` varchar(50) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `filterstr` varchar(50) DEFAULT NULL,
  `sortstr` varchar(50) DEFAULT NULL,
  `viewtype` varchar(50) DEFAULT NULL,
  `chartemplet` varchar(50) DEFAULT NULL,
  `chartype` varchar(50) DEFAULT NULL,
  `chartdatatype` varchar(50) DEFAULT NULL,
  `chart3d` varchar(50) DEFAULT NULL,
  `xtitle` varchar(50) DEFAULT NULL,
  `ytitle` varchar(50) DEFAULT NULL,
  `charttitle` varchar(50) DEFAULT NULL,
  `displayborder` varchar(50) DEFAULT NULL,
  `bordercolor` varchar(50) DEFAULT NULL,
  `displaydesc` varchar(50) DEFAULT NULL,
  `formdisplay` varchar(50) DEFAULT NULL,
  `labelstyle` varchar(50) DEFAULT NULL,
  `formname` varchar(50) DEFAULT NULL,
  `defaultvalue` varchar(50) DEFAULT NULL,
  `querytext` varchar(50) DEFAULT NULL,
  `tempquey` varchar(50) DEFAULT NULL,
  `displaytitle` tinyint(4) DEFAULT '0',
  `clearzero` tinyint(4) DEFAULT '0',
  `titlestr` varchar(50) DEFAULT NULL,
  `width` varchar(50) DEFAULT NULL,
  `height` varchar(50) DEFAULT NULL,
  `widthunit` varchar(50) DEFAULT NULL,
  `heightunit` varchar(50) DEFAULT NULL,
  `defheight` varchar(50) DEFAULT NULL,
  `defwidth` varchar(50) DEFAULT NULL,
  `neckwidth` varchar(50) DEFAULT NULL,
  `neckheight` varchar(50) DEFAULT NULL,
  `extparam` varchar(50) DEFAULT NULL,
  `marginright` varchar(50) DEFAULT NULL,
  `colorstr` varchar(50) DEFAULT NULL,
  `start` varchar(50) DEFAULT NULL,
  `end` varchar(50) DEFAULT NULL,
  `rowformatstr` varchar(50) DEFAULT NULL,
  `colformatstr` varchar(50) DEFAULT NULL,
  `publishtype` varchar(50) DEFAULT NULL,
  `editview` varchar(50) DEFAULT NULL,
  `expandbtm` tinyint(4) DEFAULT '0',
  `expandrgt` tinyint(4) DEFAULT '0',
  `curtab` varchar(50) DEFAULT NULL,
  `hiddencolstr` varchar(50) DEFAULT NULL,
  `eventstr` varchar(50) DEFAULT NULL,
  `dsmodel` varchar(50) DEFAULT NULL,
  `html` text,
  `sqldialect` varchar(255) DEFAULT NULL,
  `pagesize` int(11) DEFAULT NULL,
  `isloadfulldata` varchar(50) DEFAULT NULL,
  `isexport` tinyint(4) DEFAULT '0',
  `selectdata` tinyint(4) DEFAULT '0',
  `exporttitle` varchar(50) DEFAULT NULL,
  `colsize` int(11) DEFAULT NULL,
  `sorttype` varchar(50) DEFAULT NULL,
  `sortname` varchar(50) DEFAULT NULL,
  `mid` varchar(32) DEFAULT NULL,
  `parentid` varchar(32) DEFAULT NULL,
  `templetid` varchar(32) DEFAULT NULL,
  `colspan` int(11) DEFAULT NULL,
  `colindex` int(11) DEFAULT NULL,
  `chartcontent` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




ALTER TABLE uk_agentservice ADD agenttimeout int default 0;
ALTER TABLE uk_agentservice ADD agenttimeouttimes int default 0;
ALTER TABLE uk_agentservice ADD servicetimeout tinyint default 0;
ALTER TABLE uk_agentservice ADD agentservicetimeout int default 0;


ALTER TABLE uk_agentuser ADD agenttimeout int default 0;
ALTER TABLE uk_agentuser ADD agenttimeouttimes int default 0;
ALTER TABLE uk_agentuser ADD servicetimeout tinyint default 0;
ALTER TABLE uk_agentuser ADD agentservicetimeout int default 0;

ALTER TABLE uk_sessionconfig ADD servicetimeoutlimit tinyint default 0;
ALTER TABLE uk_sessionconfig ADD servicetimeout int default 0;

ALTER TABLE uk_sessionconfig ADD servicetimeoutmsg varchar(50) default 0;

ALTER TABLE uk_agentservice ADD agentfrewords int default 0;
ALTER TABLE uk_agentservice ADD servicefrewords int default 0;

ALTER TABLE uk_agentuser ADD agentfrewords int default 0;
ALTER TABLE uk_agentuser ADD servicefrewords int default 0;

ALTER TABLE uk_agentuser ADD satisfaction tinyint default 0;
ALTER TABLE uk_agentuser ADD satistime datetime default 0;
ALTER TABLE uk_agentuser ADD satislevel varchar(50) default 0;
ALTER TABLE uk_agentuser ADD satiscomment varchar(255) default 0;

ALTER TABLE uk_xiaoe_topic ADD  aiid varchar(32)  COMMENT '机器人ID';
ALTER TABLE uk_xiaoe_topic ADD visualangle varchar(20) default 0;
ALTER TABLE uk_xiaoe_topic ADD knowpoint varchar(50) default 0;
ALTER TABLE uk_xiaoe_topic ADD knowpoints varchar(20) default 0;
ALTER TABLE uk_xiaoe_topic ADD knowllimit varchar(20) default 0;
ALTER TABLE uk_xiaoe_topic CHANGE views sviews varchar(32);


CREATE TABLE `uk_log_request` (
  `id` varchar(32) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `parameters` longtext,
  `throwable` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usermail` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `orgi` varchar(255) DEFAULT NULL,
  `error` text,
  `classname` varchar(255) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `detailtype` varchar(255) DEFAULT NULL,
  `url` text,
  `reportdic` varchar(255) DEFAULT NULL,
  `reportname` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `hostname` varchar(255) DEFAULT NULL,
  `statues` varchar(255) DEFAULT NULL,
  `methodname` text,
  `linenumber` varchar(255) DEFAULT NULL,
  `querytime` int(255) DEFAULT NULL,
  `optext` varchar(255) DEFAULT NULL,
  `field6` varchar(255) DEFAULT NULL,
  `field7` varchar(255) DEFAULT NULL,
  `field8` varchar(255) DEFAULT NULL,
  `flowid` varchar(32) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `funtype` varchar(32) DEFAULT NULL,
  `fundesc` varchar(255) DEFAULT NULL,
  `triggerwarning` varchar(32) DEFAULT NULL,
  `triggertime` varchar(32) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `uk_quality` (
  `ID` varchar(32) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATER` varchar(32) DEFAULT NULL,
  `UPDATETIME` datetime DEFAULT NULL,
  `ORGI` varchar(32) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '上级ID',
  `STARTDATE` datetime DEFAULT NULL COMMENT '有效期开始时间',
  `ENDDATE` datetime DEFAULT NULL COMMENT '有效期结束时间',
  `ENABLE` tinyint(4) DEFAULT NULL COMMENT '分类状态',
  `SCORE` int(11) DEFAULT '0' COMMENT '分值',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `QUALITYTYPE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



CREATE TABLE `uk_work_monitor` (
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
  `EVENTID` varchar(50) DEFAULT NULL,
  `WORKTYPE` varchar(32) DEFAULT NULL,
  `CALLENDTIME` datetime DEFAULT NULL,
  `CALLSTARTTIME` datetime DEFAULT NULL,
  `DIRECTION` varchar(50) DEFAULT NULL,
  `EXTNO` varchar(32) DEFAULT NULL,
  `ADMIN` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='坐席状态表';


CREATE TABLE `uk_webim_monitor` (
  `ID` varchar(50) NOT NULL COMMENT 'ID',
  `ORGI` varchar(50) DEFAULT NULL COMMENT '租户ID',
  `CREATETIME` datetime DEFAULT NULL COMMENT '记录创建时间',
  `AGENTS` int(11) DEFAULT NULL COMMENT '在线坐席数量',
  `USERS` int(11) DEFAULT NULL COMMENT '咨询中访客数量',
  `INQUENE` int(11) DEFAULT NULL COMMENT '排队访客数量',
  `BUSY` int(11) DEFAULT NULL COMMENT '示忙坐席数量',
  `TYPE` varchar(32) DEFAULT NULL,
  `DATESTR` varchar(32) DEFAULT NULL,
  `HOURSTR` varchar(32) DEFAULT NULL,
  `DATEHOURSTR` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='坐席状态表';



ALTER TABLE uk_work_monitor ADD firsttime tinyint default 0  COMMENT '是否首次就绪';
ALTER TABLE uk_work_monitor ADD firsttimes int default 0 COMMENT '首次就绪时长';
ALTER TABLE uk_chat_message ADD topic tinyint default 0 COMMENT '是否命中知识库';
ALTER TABLE uk_chat_message ADD topicid varchar(32) COMMENT '命中知识库ID';
ALTER TABLE uk_chat_message ADD topicatid varchar(32) COMMENT '命中知识库分类ID';
ALTER TABLE uk_agentservice ADD foragent tinyint default 0 COMMENT '直接转人工';
ALTER TABLE uk_chat_message ADD aichat tinyint default 0 COMMENT '是否在和AI对话';


ALTER TABLE uk_sessionconfig ADD quene tinyint default 0 COMMENT '坐席姓名';
ALTER TABLE uk_sessionconfig ADD servicename varchar(50) COMMENT '无坐席的时候回复昵称';



ALTER TABLE uk_chat_message ADD aiid varchar(32) default 0 COMMENT '机器人ID';


CREATE TABLE `uk_ai_snsaccount` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `aiid` varchar(32) DEFAULT NULL COMMENT '用户ID',
  `snsid` varchar(32) DEFAULT NULL COMMENT '角色ID',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;




ALTER TABLE uk_agentservice ADD endby varchar(20)  COMMENT '终止方';
ALTER TABLE uk_agentservice ADD aiid varchar(32)  COMMENT 'AIID';
ALTER TABLE uk_agentservice ADD aiservice tinyint default 0 COMMENT '是否AI服务';
ALTER TABLE uk_agentservice ADD foragent tinyint default 0 COMMENT '直接转人工';

ALTER TABLE uk_consult_invite ADD aiid varchar(32) COMMENT '默认的AI';
ALTER TABLE uk_consult_invite ADD maxwordsnum int DEFAULT 300 COMMENT '访客端允许输入的最大字数';


ALTER TABLE uk_callcenter_event CHANGE current scurrent tinyint;
ALTER TABLE uk_callcenter_event CHANGE record srecord tinyint;

ALTER TABLE uk_callcenter_extention CHANGE record srecord tinyint;

ALTER TABLE uk_fans CHANGE user suser varchar(32);

ALTER TABLE uk_kbs_topic CHANGE views sviews int;

ALTER TABLE uk_reportmodel CHANGE start sstart varchar(50);
ALTER TABLE uk_reportmodel CHANGE end send varchar(50);


ALTER TABLE uk_workorders CHANGE views sviews int;

ALTER TABLE uk_xiaoe_scene CHANGE views sviews int;

ALTER TABLE uk_callcenter_pbxhost ADD enableai tinyint DEFAULT 0  COMMENT '启用AI机器人';
ALTER TABLE uk_callcenter_pbxhost ADD aiid varchar(32) COMMENT 'AI机器人';
ALTER TABLE uk_callcenter_pbxhost ADD sceneid varchar(32) COMMENT '启用场景配置';


DROP TABLE uk_agentuser ;
CREATE TABLE `uk_agentuser` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '主键ID',
  `username` varchar(100) DEFAULT '' COMMENT '用户名',
  `agentno` varchar(100) DEFAULT '' COMMENT '坐席ID',
  `userid` varchar(100) DEFAULT '' COMMENT '用户ID',
  `channel` varchar(100) DEFAULT '' COMMENT '渠道',
  `logindate` datetime DEFAULT NULL COMMENT '登录时间',
  `source` varchar(100) DEFAULT '' COMMENT '来源',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `nickname` varchar(100) DEFAULT '' COMMENT '昵称',
  `city` varchar(100) DEFAULT '' COMMENT '城市',
  `province` varchar(100) DEFAULT '' COMMENT '省份',
  `country` varchar(100) DEFAULT '' COMMENT '国家',
  `headImgUrl` varchar(255) DEFAULT '' COMMENT '头像URL',
  `waittingtime` int(11) DEFAULT '0' COMMENT '等待时长',
  `tokenum` int(11) DEFAULT '0' COMMENT '接入次数',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `status` varchar(100) DEFAULT '' COMMENT '状态',
  `appid` varchar(100) DEFAULT '' COMMENT 'SNSID',
  `sessiontype` varchar(100) DEFAULT '' COMMENT '会话类型',
  `contextid` varchar(100) DEFAULT '' COMMENT '会话ID',
  `agentserviceid` varchar(100) DEFAULT '' COMMENT '服务记录ID',
  `orgi` varchar(100) DEFAULT '' COMMENT '租户ID',
  `snsuser` varchar(100) DEFAULT '' COMMENT 'SNS用户ID',
  `lastmessage` datetime DEFAULT NULL COMMENT '最后一条消息时间',
  `waittingtimestart` datetime DEFAULT NULL COMMENT '进入队列时间',
  `lastgetmessage` datetime DEFAULT NULL COMMENT '最后一条消息时间',
  `lastmsg` varchar(100) DEFAULT '' COMMENT '最后一条消息',
  `agentskill` varchar(100) DEFAULT '' COMMENT '技能组',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creater` varchar(255) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '修改人',
  `assignedto` varchar(255) DEFAULT NULL COMMENT '分配目标用户',
  `wfstatus` varchar(255) DEFAULT NULL COMMENT '流程状态',
  `shares` varchar(255) DEFAULT NULL COMMENT '分享给',
  `owner` varchar(255) DEFAULT NULL COMMENT '所属人',
  `datadept` varchar(255) DEFAULT NULL COMMENT '创建人部门',
  `intime` int(32) DEFAULT NULL COMMENT '接入时间',
  `batid` varchar(32) DEFAULT NULL COMMENT '批次ID',
  `ipaddr` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `osname` varchar(100) DEFAULT NULL COMMENT '操作系统名称',
  `browser` varchar(100) DEFAULT NULL COMMENT '浏览器',
  `sessiontimes` int(20) DEFAULT NULL COMMENT '会话时长',
  `servicetime` datetime DEFAULT NULL COMMENT '服务次数',
  `region` varchar(255) DEFAULT NULL COMMENT '地区',
  `agentservice` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `warnings` varchar(11) DEFAULT NULL COMMENT '提醒次数',
  `warningtime` datetime DEFAULT NULL COMMENT '提醒时间',
  `reptime` datetime DEFAULT NULL COMMENT '坐席最后一次回复时间',
  `reptimes` varchar(10) DEFAULT NULL COMMENT '坐席回复次数',
  `skill` varchar(32) DEFAULT NULL COMMENT '技能组',
  `agent` varchar(32) DEFAULT NULL COMMENT '坐席ID',
  `name` varchar(100) DEFAULT NULL COMMENT '用户录入的姓名',
  `phone` varchar(100) DEFAULT NULL COMMENT '访客录入的电话',
  `email` varchar(100) DEFAULT NULL COMMENT '访客录入的邮件',
  `resion` varchar(255) DEFAULT NULL COMMENT '访客录入的来访原因',
  `agentreplyinterval` int(11) DEFAULT '0',
  `agentreplytime` int(11) DEFAULT '0',
  `agentreplys` int(11) DEFAULT '0',
  `userasks` int(11) DEFAULT '0',
  `avgreplyinterval` int(11) DEFAULT '0',
  `avgreplytime` int(11) DEFAULT '0',
  `sessionid` varchar(32) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `traceid` varchar(32) DEFAULT NULL,
  `agenttimeout` int(11) DEFAULT '0',
  `agenttimeouttimes` int(11) DEFAULT '0',
  `servicetimeout` tinyint(4) DEFAULT '0',
  `agentservicetimeout` int(11) DEFAULT '0',
  `agentfrewords` int(11) DEFAULT '0',
  `servicefrewords` int(11) DEFAULT '0',
  `topflag` tinyint(4) DEFAULT NULL COMMENT '是否置顶',
  `toptimes` int(20) DEFAULT NULL COMMENT '置顶时长',
  `toptime` datetime DEFAULT NULL COMMENT '置顶时间',
  `firstreplytime` int(20) DEFAULT '0' COMMENT '首次响应时间',
  `agentusername` varchar(32) DEFAULT NULL COMMENT '坐席姓名',
  `alarm` int(10) DEFAULT '0',
  `initiator` varchar(32) DEFAULT NULL COMMENT '会话发起方',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `agentuser_userid` (`userid`) USING BTREE,
  KEY `agentuser_orgi` (`orgi`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;



ALTER TABLE uk_xiaoe_config ADD welcomemsg text  COMMENT '机器人欢迎语';
ALTER TABLE uk_xiaoe_config ADD waitmsg text  COMMENT '机器人等待提示语';

ALTER TABLE uk_callcenter_pbxhost ADD welcomemsg text  COMMENT '机器人欢迎语';
ALTER TABLE uk_callcenter_pbxhost ADD waitmsg text  COMMENT '机器人等待提示语';
ALTER TABLE uk_callcenter_pbxhost ADD tipmessage text  COMMENT '机器人提示客户说话';
ALTER TABLE uk_callcenter_pbxhost ADD afterprocess tinyint DEFAULT 0 COMMENT '坐席通话后启用后处理功能';


ALTER TABLE uk_callcenter_pbxhost ADD asrrecordpath varchar(255)  COMMENT 'ASR结果路径';
ALTER TABLE uk_callcenter_pbxhost ADD ttsrecordpath varchar(255)  COMMENT 'ASR结果路径';

ALTER TABLE uk_callcenter_extention ADD mediapath varchar(255)  COMMENT '播报工号语音文件';

ALTER TABLE uk_callcenter_extention ADD afterprocess tinyint DEFAULT 0 COMMENT '坐席通话后启用后处理功能';

CREATE TABLE `uk_ai` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '维度名称',
  `createtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `creater` varchar(32) DEFAULT NULL COMMENT '创建人',
  `orgi` varchar(32) DEFAULT NULL COMMENT '租户id',
  `inx` int(11) DEFAULT NULL COMMENT '分类排序序号',
  `updatetime` datetime DEFAULT NULL COMMENT '更新时间',
  `description` varchar(255) DEFAULT NULL COMMENT '分类描述',
  `code` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
