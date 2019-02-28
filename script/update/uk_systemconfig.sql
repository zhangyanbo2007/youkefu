/*
Navicat MySQL Data Transfer

Source Server         : UCKeFu
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : uckefu

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-08-14 15:51:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `uk_systemconfig`
-- ----------------------------
DROP TABLE IF EXISTS `uk_systemconfig`;
CREATE TABLE `uk_systemconfig` (
  `ID` varchar(32) NOT NULL COMMENT '主键ID',
  `NAME` varchar(100) DEFAULT NULL COMMENT '名称',
  `TITLE` varchar(100) DEFAULT NULL COMMENT '标题',
  `CODE` varchar(100) DEFAULT NULL COMMENT '编码',
  `ORGI` varchar(32) DEFAULT NULL COMMENT '租户ID',
  `CTYPE` varchar(32) DEFAULT NULL COMMENT '类型',
  `PARENTID` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `DESCRIPTION` varchar(255) DEFAULT NULL COMMENT '描述',
  `MEMO` varchar(32) DEFAULT NULL,
  `ICONSTR` varchar(255) DEFAULT NULL COMMENT '自定义样式',
  `ICONSKIN` varchar(255) DEFAULT NULL COMMENT '自定义样式',
  `CATETYPE` varchar(32) DEFAULT NULL COMMENT '分类',
  `CREATER` varchar(32) DEFAULT NULL COMMENT '创建人',
  `CREATETIME` datetime DEFAULT NULL COMMENT '创建时间',
  `UPDATETIME` datetime DEFAULT NULL COMMENT '更新时间',
  `HASCHILD` tinyint(4) DEFAULT NULL COMMENT '是否有下级',
  `SORTINDEX` int(11) DEFAULT NULL COMMENT '排序',
  `DICID` varchar(32) DEFAULT NULL COMMENT '目录ID',
  `DEFAULTVALUE` tinyint(4) DEFAULT NULL COMMENT '默认值',
  `THEME` varchar(50) DEFAULT NULL COMMENT '皮肤',
  `LOGLEVEL` varchar(32) DEFAULT NULL COMMENT '日志级别',
  `ENABLESSL` tinyint(4) DEFAULT NULL COMMENT '启用SSL',
  `JKSFILE` varchar(255) DEFAULT NULL COMMENT 'JKS文件路径',
  `JKSPASSWORD` varchar(255) DEFAULT NULL COMMENT 'JKS密码',
  `MAPKEY` varchar(255) DEFAULT NULL COMMENT '百度地图授权编码',
  `workorders` tinyint(4) DEFAULT NULL COMMENT '启用工单三栏布局',
  `callcenter` tinyint(4) DEFAULT NULL COMMENT '启用呼叫中心',
  `cc_extention` varchar(32) DEFAULT NULL COMMENT '分机',
  `cc_quene` varchar(32) DEFAULT NULL COMMENT '技能组队列',
  `cc_router` varchar(32) DEFAULT NULL COMMENT '路由策略',
  `cc_ivr` varchar(32) DEFAULT NULL COMMENT 'IVR模板',
  `cc_acl` varchar(32) DEFAULT NULL COMMENT '访问列表模板',
  `cc_siptrunk` varchar(32) DEFAULT NULL COMMENT 'SIP配置模板',
  `cc_callcenter` varchar(32) DEFAULT NULL COMMENT '呼叫中心配置',
  `CALLOUT` tinyint(4) DEFAULT NULL COMMENT '是否允许点击号码外呼',
  `AUTH` tinyint(4) DEFAULT NULL COMMENT '启用权限控制',
  UNIQUE KEY `SQL121227155532210` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uk_systemconfig
-- ----------------------------
INSERT INTO `uk_systemconfig` VALUES ('402883965c12006b015c12013b990002', null, null, null, 'ukewo', null, null, null, null, null, null, null, '297e8c7b455798280145579c73e501c1', '2017-05-17 00:04:49', null, null, null, null, null, '02', 'info', '0', 'catalina.out', '123456', '', '0', '1', '297e63f05d1fac44015d1fadd35f0004', '297e63f05d1fac44015d1fad1b9f0002', '297e63f05d1fac44015d1fae11810005', '297e63f05d1de125015d1df495710002', '297e63f05d1fac44015d1facc9a10001', '297e63f05d1de125015d1df495780003', '297e63f05d1de125015d1df495780003', '1', '0');
