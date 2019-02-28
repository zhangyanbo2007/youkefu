 
DROP TABLE  uk_act_batch; 
CREATE TABLE uk_act_batch ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 STATUS varchar2(50) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 ACTID varchar2(32) DEFAULT NULL , 
 INX number(11) DEFAULT '0' , 
 NAMENUM number(11) DEFAULT '0' , 
 VALIDNUM number(11) DEFAULT '0' , 
 INVALIDNUM number(11) DEFAULT '0' , 
 ASSIGNED number(11) DEFAULT '0' , 
 NOTASSIGNED number(11) DEFAULT '0' , 
 ENABLE number(4) DEFAULT '0' , 
 DATASTATUS number(4) DEFAULT '0' , 
 AREA clob , 
 imptype varchar2(50) DEFAULT NULL , 
 batchtype varchar2(32) DEFAULT NULL , 
 ORGAN varchar2(32) DEFAULT NULL , 
 impurl clob , 
 filetype varchar2(50) DEFAULT NULL , 
 dbtype varchar2(50) DEFAULT NULL , 
 jdbcurl clob , 
 driverclazz varchar2(255) DEFAULT NULL , 
 password varchar2(255) DEFAULT NULL , 
 DESCRIPTION clob , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_act_batch 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_ad_position 
-- ---------------------------- 
DROP TABLE  uk_ad_position; 
CREATE TABLE uk_ad_position ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 INX number(11) DEFAULT NULL , 
 ENABLE number(4) DEFAULT NULL , 
 AREA clob , 
 IMGURL varchar2(255) DEFAULT NULL , 
 TIPTEXT varchar2(100) DEFAULT NULL , 
 URL varchar2(255) DEFAULT NULL , 
 CONTENT clob , 
 WEIGHT number(11) DEFAULT NULL , 
 ADTYPE varchar2(50) DEFAULT NULL , 
 STATUS varchar2(32) DEFAULT NULL , 
 ADPOS varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_ad_position 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_agentservice 
-- ---------------------------- 
DROP TABLE  uk_agentservice; 
CREATE TABLE uk_agentservice ( 
 id varchar2(32) NOT NULL,
 username varchar2(100) DEFAULT '' , 
 agentno varchar2(100) DEFAULT '' , 
 userid varchar2(100) DEFAULT '' , 
 channel varchar2(100) DEFAULT '' , 
 logindate date DEFAULT NULL , 
 source varchar2(100) DEFAULT '' , 
 endtime date DEFAULT NULL , 
 nickname varchar2(100) DEFAULT '' , 
 city varchar2(100) DEFAULT '' , 
 province varchar2(100) DEFAULT '' , 
 country varchar2(100) DEFAULT '' , 
 headImgUrl varchar2(255) DEFAULT '' , 
 waittingtime number(11) DEFAULT '0' , 
 tokenum number(11) DEFAULT '0' , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 status varchar2(100) DEFAULT '' , 
 appid varchar2(100) DEFAULT '' , 
 sessiontype varchar2(100) DEFAULT '' , 
 contextid varchar2(100) DEFAULT '' , 
 agentserviceid varchar2(100) DEFAULT '' , 
 orgi varchar2(100) DEFAULT '' , 
 snsuser varchar2(100) DEFAULT '' , 
 lastmessage date DEFAULT NULL , 
 waittingtimestart date DEFAULT NULL , 
 lastgetmessage date DEFAULT NULL , 
 lastmsg varchar2(100) DEFAULT '' , 
 agentskill varchar2(100) DEFAULT '' , 
 create_time date DEFAULT NULL , 
 creater varchar2(255) DEFAULT NULL , 
 update_time date DEFAULT NULL , 
 update_user varchar2(255) DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 intime number(32) DEFAULT NULL , 
 batid varchar2(32) DEFAULT NULL , 
 ipaddr varchar2(50) DEFAULT NULL , 
 osname varchar2(100) DEFAULT NULL , 
 browser varchar2(100) DEFAULT NULL , 
 sessiontimes number(20) DEFAULT NULL , 
 servicetime date DEFAULT NULL , 
 region varchar2(255) DEFAULT NULL , 
 agentusername varchar2(32) DEFAULT NULL , 
 times number(10) DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 contactsid varchar2(32) DEFAULT NULL , 
 createdate varchar2(32) DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 email varchar2(100) DEFAULT NULL , 
 phone varchar2(100) DEFAULT NULL , 
 resion varchar2(255) DEFAULT NULL , 
 satisfaction number(4) DEFAULT NULL , 
 satistime date DEFAULT NULL , 
 satislevel varchar2(50) DEFAULT NULL , 
 satiscomment varchar2(255) DEFAULT NULL , 
 trans number(4) DEFAULT NULL , 
 transtime date DEFAULT NULL , 
 transmemo clob , 
 agentreplyinterval number(11) DEFAULT NULL , 
 agentreplytime number(11) DEFAULT NULL , 
 avgreplyinterval number(11) DEFAULT NULL , 
 avgreplytime number(11) DEFAULT NULL , 
 agentreplys number(11) DEFAULT NULL , 
 userasks number(11) DEFAULT NULL , 
 agentuserid varchar2(32) DEFAULT NULL , 
 sessionid varchar2(32) DEFAULT NULL , 
 qualitystatus varchar2(20) DEFAULT NULL , 
 qualitydisorgan varchar2(32) DEFAULT NULL , 
 qualitydisuser varchar2(32) DEFAULT NULL , 
 qualityorgan varchar2(32) DEFAULT NULL , 
 qualityuser varchar2(32) DEFAULT NULL , 
 qualitytime date DEFAULT NULL , 
 qualitytype varchar2(20) DEFAULT NULL , 
 qualityscore number(11) DEFAULT '0' , 
 solvestatus varchar2(20) DEFAULT NULL , 
 leavemsg number(4) DEFAULT '0' , 
 initiator varchar2(32) DEFAULT NULL , 
 agenttimeout number(11) DEFAULT '0' , 
 agenttimeouttimes number(11) DEFAULT '0' , 
 servicetimeout number(4) DEFAULT '0' , 
 agentservicetimeout number(11) DEFAULT '0' , 
 agentfrewords number(11) DEFAULT '0' , 
 servicefrewords number(11) DEFAULT '0' , 
 leavemsgstatus varchar2(20) DEFAULT 'notprocess' , 
 agent varchar2(32) DEFAULT NULL , 
 skill varchar2(32) DEFAULT NULL , 
 endby varchar2(20) DEFAULT NULL , 
 aiid varchar2(32) DEFAULT NULL , 
 aiservice number(4) DEFAULT '0' , 
 foragent number(4) DEFAULT '0' , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_agentservice 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_agentstatus 
-- ---------------------------- 
DROP TABLE  uk_agentstatus; 
CREATE TABLE uk_agentstatus ( 
 id varchar2(32) NOT NULL,
 agentno varchar2(100) DEFAULT '' , 
 logindate date DEFAULT NULL , 
 status varchar2(100) DEFAULT '' , 
 orgi varchar2(100) DEFAULT '' , 
 agentserviceid varchar2(100) DEFAULT '' , 
 serusernum number(11) DEFAULT '0' , 
 skill varchar2(100) DEFAULT '' , 
 skillname varchar2(100) DEFAULT '' , 
 users number(11) DEFAULT '0' , 
 maxusers number(11) DEFAULT '0' , 
 username varchar2(100) DEFAULT '' , 
 name varchar2(100) DEFAULT '' , 
 updatetime date DEFAULT NULL , 
 userid varchar2(100) DEFAULT '' , 
 createtime date DEFAULT NULL , 
 creater varchar2(255) DEFAULT NULL , 
 update_time date DEFAULT NULL , 
 update_user varchar2(255) DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 batid varchar2(32) DEFAULT NULL , 
 pulluser number(4) DEFAULT NULL , 
 busy number(4) DEFAULT '0' , 
 workstatus varchar2(50) DEFAULT NULL  
); 
 
-- ---------------------------- 
-- Records of uk_agentstatus 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_agentuser 
-- ---------------------------- 
DROP TABLE  uk_agentuser; 
CREATE TABLE uk_agentuser ( 
 id varchar2(32) NOT NULL,
 username varchar2(100) DEFAULT '' , 
 agentno varchar2(100) DEFAULT '' , 
 userid varchar2(100) DEFAULT '' , 
 channel varchar2(100) DEFAULT '' , 
 logindate date DEFAULT NULL , 
 source varchar2(100) DEFAULT '' , 
 endtime date DEFAULT NULL , 
 nickname varchar2(100) DEFAULT '' , 
 city varchar2(100) DEFAULT '' , 
 province varchar2(100) DEFAULT '' , 
 country varchar2(100) DEFAULT '' , 
 headImgUrl varchar2(255) DEFAULT '' , 
 waittingtime number(11) DEFAULT '0' , 
 tokenum number(11) DEFAULT '0' , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 status varchar2(100) DEFAULT '' , 
 appid varchar2(100) DEFAULT '' , 
 sessiontype varchar2(100) DEFAULT '' , 
 contextid varchar2(100) DEFAULT '' , 
 agentserviceid varchar2(100) DEFAULT '' , 
 orgi varchar2(100) DEFAULT '' , 
 snsuser varchar2(100) DEFAULT '' , 
 lastmessage date DEFAULT NULL , 
 waittingtimestart date DEFAULT NULL , 
 lastgetmessage date DEFAULT NULL , 
 lastmsg varchar2(100) DEFAULT '' , 
 agentskill varchar2(100) DEFAULT '' , 
 create_time date DEFAULT NULL , 
 creater varchar2(255) DEFAULT NULL , 
 update_time date DEFAULT NULL , 
 update_user varchar2(255) DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 intime number(32) DEFAULT NULL , 
 batid varchar2(32) DEFAULT NULL , 
 ipaddr varchar2(50) DEFAULT NULL , 
 osname varchar2(100) DEFAULT NULL , 
 browser varchar2(100) DEFAULT NULL , 
 sessiontimes number(20) DEFAULT NULL , 
 servicetime date DEFAULT NULL , 
 region varchar2(255) DEFAULT NULL , 
 agentservice varchar2(32) DEFAULT NULL , 
 warnings varchar2(11) DEFAULT NULL , 
 warningtime date DEFAULT NULL , 
 reptime date DEFAULT NULL , 
 reptimes varchar2(10) DEFAULT NULL , 
 skill varchar2(32) DEFAULT NULL , 
 agent varchar2(32) DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 phone varchar2(100) DEFAULT NULL , 
 email varchar2(100) DEFAULT NULL , 
 resion varchar2(255) DEFAULT NULL , 
 agentreplyinterval number(11) DEFAULT '0' , 
 agentreplytime number(11) DEFAULT '0' , 
 agentreplys number(11) DEFAULT '0' , 
 userasks number(11) DEFAULT '0' , 
 avgreplyinterval number(11) DEFAULT '0' , 
 avgreplytime number(11) DEFAULT '0' , 
 sessionid varchar2(32) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 url varchar2(255) DEFAULT NULL , 
 traceid varchar2(32) DEFAULT NULL , 
 agenttimeout number(11) DEFAULT '0' , 
 agenttimeouttimes number(11) DEFAULT '0' , 
 servicetimeout number(4) DEFAULT '0' , 
 agentservicetimeout number(11) DEFAULT '0' , 
 agentfrewords number(11) DEFAULT '0' , 
 servicefrewords number(11) DEFAULT '0' , 
 topflag number(4) DEFAULT NULL , 
 toptimes number(20) DEFAULT NULL , 
 toptime date DEFAULT NULL , 
 firstreplytime number(20) DEFAULT '0' , 
 agentusername varchar2(32) DEFAULT NULL , 
 alarm number(10) DEFAULT '0' , 
 initiator varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_agentuser 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_agentuser_contacts 
-- ---------------------------- 
DROP TABLE  uk_agentuser_contacts; 
CREATE TABLE uk_agentuser_contacts ( 
 id varchar2(32) NOT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 appid varchar2(32) DEFAULT NULL , 
 channel varchar2(32) DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 contactsid varchar2(32) DEFAULT NULL , 
 username varchar2(50) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_agentuser_contacts 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_ai 
-- ---------------------------- 
DROP TABLE  uk_ai; 
CREATE TABLE uk_ai ( 
 id varchar2(32) NOT NULL , 
 name varchar2(255) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 inx number(11) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 description varchar2(255) DEFAULT NULL , 
 code varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_ai 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_ai_snsaccount 
-- ---------------------------- 
DROP TABLE  uk_ai_snsaccount; 
CREATE TABLE uk_ai_snsaccount ( 
 id varchar2(32) NOT NULL , 
 aiid varchar2(32) DEFAULT NULL , 
 snsid varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_ai_snsaccount 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_area_type 
-- ---------------------------- 
DROP TABLE  uk_area_type; 
CREATE TABLE uk_area_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 INX number(11) DEFAULT NULL , 
 ENABLE number(4) DEFAULT NULL , 
 AREA clob , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_area_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_attachment_file 
-- ---------------------------- 
DROP TABLE  uk_attachment_file; 
CREATE TABLE uk_attachment_file ( 
 id varchar2(32) NOT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 datastatus number(4) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 url varchar2(255)  , 
 updatetime date DEFAULT NULL , 
 filelength number(11) DEFAULT NULL , 
 filetype varchar2(255) DEFAULT NULL , 
 image number(4) DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 model varchar2(32) DEFAULT NULL , 
 fileid varchar2(32) DEFAULT NULL , 
 modelid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_attachment_file 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_blacklist 
-- ---------------------------- 
DROP TABLE  uk_blacklist; 
CREATE TABLE uk_blacklist ( 
 id varchar2(32) NOT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 contactid varchar2(32) DEFAULT NULL , 
 sessionid varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 channel varchar2(20) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 agentid varchar2(32) DEFAULT NULL , 
 phone varchar2(20) DEFAULT NULL , 
 openid varchar2(100) DEFAULT NULL , 
 description clob , 
 agentserviceid varchar2(32) DEFAULT NULL , 
 times number(11) DEFAULT NULL , 
 chattime number(11) DEFAULT NULL , 
 controltime number(11) DEFAULT '1', 
 endtime date DEFAULT NULL , 
 agentuser varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_blacklist 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_bpm_process 
-- ---------------------------- 
DROP TABLE  uk_bpm_process; 
CREATE TABLE uk_bpm_process ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 CONTENT clob , 
 STATUS varchar2(20) DEFAULT NULL , 
 TITLE varchar2(50) DEFAULT NULL , 
 PUBLISHED number(4) DEFAULT NULL , 
 PROCESSID varchar2(50) DEFAULT NULL , 
 PROCESSTYPE varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_bpm_process 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_acl 
-- ---------------------------- 
DROP TABLE  uk_callcenter_acl; 
CREATE TABLE uk_callcenter_acl ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 strategy clob , 
 defaultvalue varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_acl 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_event 
-- ---------------------------- 
DROP TABLE  uk_callcenter_event; 
CREATE TABLE uk_callcenter_event ( 
 ID varchar2(100) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 SOURCE varchar2(50) DEFAULT NULL , 
 ANSWER varchar2(50) DEFAULT NULL , 
 scurrent number(4) DEFAULT NULL , 
 INIT number(4) DEFAULT NULL , 
 CALLER varchar2(50) DEFAULT NULL , 
 CALLING varchar2(50) DEFAULT NULL , 
 CALLED varchar2(50) DEFAULT NULL , 
 AGENTYPE varchar2(50) DEFAULT NULL , 
 QUENE varchar2(50) DEFAULT NULL , 
 ANI varchar2(50) DEFAULT NULL , 
 TOUSER varchar2(50) DEFAULT NULL , 
 DIRECTION varchar2(50) DEFAULT NULL , 
 STATE varchar2(50) DEFAULT NULL , 
 AGENT varchar2(50) DEFAULT NULL , 
 ACTION varchar2(50) DEFAULT NULL , 
 HOST varchar2(50) DEFAULT NULL , 
 IPADDR varchar2(50) DEFAULT NULL , 
 LOCALDATETIME varchar2(50) DEFAULT NULL , 
 STATUS varchar2(50) DEFAULT NULL , 
 TIME number(11) DEFAULT NULL , 
 STARTTIME date DEFAULT NULL , 
 ENDTIME date DEFAULT NULL , 
 DURATION number(11) DEFAULT NULL , 
 INSIDE number(4) DEFAULT NULL , 
 MISSCALL number(4) DEFAULT NULL , 
 srecord number(4) DEFAULT NULL , 
 RECORDTIME number(11) DEFAULT NULL , 
 STARTRECORD date DEFAULT NULL , 
 ENDRECORD date DEFAULT NULL , 
 ANSWERTIME date DEFAULT NULL , 
 RINGDURATION number(11) DEFAULT NULL , 
 SERVICESUMMARY number(4) DEFAULT NULL , 
 SERVICEID varchar2(32) DEFAULT NULL , 
 RECORDFILE varchar2(255) DEFAULT NULL , 
 CALLBACK number(4) DEFAULT NULL , 
 CCQUENE varchar2(50) DEFAULT NULL , 
 SERVICESTATUS varchar2(20) DEFAULT NULL , 
 CHANNELSTATUS varchar2(50) DEFAULT NULL , 
 COUNTRY varchar2(50) DEFAULT NULL , 
 PROVINCE varchar2(50) DEFAULT NULL , 
 CITY varchar2(50) DEFAULT NULL , 
 ISP varchar2(50) DEFAULT NULL , 
 VOICECALLED varchar2(50) DEFAULT NULL , 
 CONTACTSID varchar2(32) DEFAULT NULL , 
 EXTENTION varchar2(32) DEFAULT NULL , 
 HOSTID varchar2(32) DEFAULT NULL , 
 CALLTYPE varchar2(20) DEFAULT NULL , 
 CALLDIR varchar2(30) DEFAULT NULL , 
 OTHERDIR varchar2(30) DEFAULT NULL , 
 OTHERLEGDEST varchar2(50) DEFAULT NULL , 
 BRIDGEID varchar2(100) DEFAULT NULL , 
 BRIDGE number(4) DEFAULT NULL , 
 RECORDFILENAME varchar2(100) DEFAULT NULL , 
 DISCALLER varchar2(50) DEFAULT NULL , 
 DISCALLED varchar2(50) DEFAULT NULL , 
 SATISF number(4) DEFAULT '0' , 
 SATISFACTION varchar2(32) DEFAULT NULL , 
 SATISFDATE date DEFAULT NULL , 
 datestr varchar2(32) DEFAULT NULL,
 hourstr varchar2(32) DEFAULT NULL,
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_event 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_extention 
-- ---------------------------- 
DROP TABLE  uk_callcenter_extention; 
CREATE TABLE uk_callcenter_extention ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 extention varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(100) DEFAULT NULL , 
 agentno varchar2(50) DEFAULT NULL , 
 password varchar2(100) DEFAULT NULL , 
 callout number(4) DEFAULT NULL , 
 playnum number(4) DEFAULT NULL , 
 srecord number(4) DEFAULT NULL , 
 extype varchar2(50) DEFAULT NULL , 
 description varchar2(255) DEFAULT NULL , 
 subtype varchar2(50) DEFAULT NULL , 
 mediapath varchar2(255) DEFAULT NULL , 
 afterprocess number(4) DEFAULT '0' , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_extention 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_ivr 
-- ---------------------------- 
DROP TABLE  uk_callcenter_ivr; 
CREATE TABLE uk_callcenter_ivr ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 greetlong varchar2(100) DEFAULT NULL , 
 greetshort varchar2(100) DEFAULT NULL , 
 invalidsound varchar2(100) DEFAULT NULL , 
 exitsound varchar2(100) DEFAULT NULL , 
 confirmmacro varchar2(50) DEFAULT NULL , 
 confirmkey varchar2(50) DEFAULT NULL , 
 ttsengine varchar2(20) DEFAULT NULL , 
 ttsvoice varchar2(50) DEFAULT NULL , 
 confirmattempts varchar2(50) DEFAULT NULL , 
 timeout number(11) DEFAULT NULL , 
 interdigittimeout number(11) DEFAULT NULL , 
 maxfailures number(11) DEFAULT NULL , 
 maxtimeouts number(11) DEFAULT NULL , 
 digitlen number(11) DEFAULT NULL , 
 menucontent clob , 
 action varchar2(50) DEFAULT NULL , 
 digits varchar2(50) DEFAULT NULL , 
 param varchar2(255) DEFAULT NULL , 
 parentid varchar2(32) DEFAULT NULL , 
 extentionid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_ivr 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_media 
-- ---------------------------- 
DROP TABLE  uk_callcenter_media; 
CREATE TABLE uk_callcenter_media ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 filename varchar2(255) DEFAULT NULL , 
 content varchar2(50) DEFAULT NULL , 
 filelength number(11) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_media 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_pbxhost 
-- ---------------------------- 
DROP TABLE  uk_callcenter_pbxhost; 
CREATE TABLE uk_callcenter_pbxhost ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostname varchar2(100) DEFAULT NULL , 
 port number(11) DEFAULT NULL , 
 password varchar2(100) DEFAULT NULL , 
 ipaddr varchar2(32) DEFAULT NULL , 
 callbacknumber varchar2(50) DEFAULT NULL , 
 autoanswer number(4) DEFAULT NULL , 
 callcenter number(4) DEFAULT NULL , 
 recordpath varchar2(100) DEFAULT NULL , 
 ivrpath varchar2(100) DEFAULT NULL , 
 fspath varchar2(100) DEFAULT NULL , 
 device varchar2(50) DEFAULT NULL , 
 callbacktype varchar2(32) DEFAULT NULL , 
 sipautoanswer number(4) DEFAULT NULL , 
 abscodec varchar2(50) DEFAULT NULL , 
 enableai number(4) DEFAULT NULL , 
 aiid varchar2(32) DEFAULT NULL , 
 sceneid varchar2(32) DEFAULT NULL , 
 welcomemsg clob , 
 waitmsg clob , 
 tipmessage clob , 
 asrrecordpath varchar2(255) DEFAULT NULL , 
 ttsrecordpath varchar2(255) DEFAULT NULL , 
 afterprocess number(4) DEFAULT '0' , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_pbxhost 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_router 
-- ---------------------------- 
DROP TABLE  uk_callcenter_router; 
CREATE TABLE uk_callcenter_router ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 regex varchar2(255) DEFAULT NULL , 
 allow number(4) DEFAULT NULL , 
 falsebreak number(4) DEFAULT NULL , 
 routerinx number(11) DEFAULT NULL , 
 routercontent clob , 
 field varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_router 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_siptrunk 
-- ---------------------------- 
DROP TABLE  uk_callcenter_siptrunk; 
CREATE TABLE uk_callcenter_siptrunk ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 sipcontent clob , 
 sipserver varchar2(50) DEFAULT NULL , 
 extention varchar2(50) DEFAULT NULL , 
 outnumber varchar2(50) DEFAULT NULL , 
 prefix varchar2(50) DEFAULT NULL , 
 port number(11) DEFAULT NULL , 
 exptime number(11) DEFAULT NULL , 
 retry number(11) DEFAULT NULL , 
 register number(4) DEFAULT NULL , 
 fromuser number(4) DEFAULT NULL , 
 transprotocol number(4) DEFAULT NULL , 
 username varchar2(50) DEFAULT NULL , 
 authuser varchar2(50) DEFAULT NULL , 
 password varchar2(50) DEFAULT NULL , 
 protocol varchar2(50) DEFAULT NULL , 
 heartbeat number(11) DEFAULT NULL , 
 dtmf varchar2(20) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_siptrunk 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_skill 
-- ---------------------------- 
DROP TABLE  uk_callcenter_skill; 
CREATE TABLE uk_callcenter_skill ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 skill varchar2(50) DEFAULT NULL , 
 password varchar2(100) DEFAULT NULL , 
 quene varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_skill 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_callcenter_skillext 
-- ---------------------------- 
DROP TABLE  uk_callcenter_skillext; 
CREATE TABLE uk_callcenter_skillext ( 
 id varchar2(32) NOT NULL, 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 skillid varchar2(32) DEFAULT NULL , 
 extention varchar2(32) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_callcenter_skillext 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_chat_message 
-- ---------------------------- 
DROP TABLE  uk_chat_message; 
CREATE TABLE uk_chat_message ( 
 type varchar2(100) DEFAULT '' , 
 id varchar2(32) NOT NULL,
 calltype varchar2(32) DEFAULT NULL , 
 contextid varchar2(50) DEFAULT NULL , 
 usession varchar2(100) DEFAULT NULL , 
 touser varchar2(50) DEFAULT NULL , 
 channel varchar2(32) DEFAULT NULL , 
 tousername varchar2(100) DEFAULT NULL , 
 appid varchar2(50) DEFAULT NULL , 
 userid varchar2(100) DEFAULT '' , 
 nickname varchar2(100) DEFAULT '' , 
 message clob , 
 msgtype varchar2(100) DEFAULT '' , 
 orgi varchar2(100) DEFAULT '' , 
 msgid varchar2(100) DEFAULT '' , 
 expmsg varchar2(100) DEFAULT '' , 
 name varchar2(255) DEFAULT NULL , 
 createtime varchar2(50) DEFAULT NULL , 
 creater varchar2(255) DEFAULT NULL , 
 updatetime number(20) DEFAULT NULL , 
 update_user varchar2(255) DEFAULT NULL , 
 username varchar2(255) DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 batid varchar2(32) DEFAULT NULL , 
 model varchar2(32) DEFAULT '' , 
 chatype varchar2(32) DEFAULT NULL , 
 agentserviceid varchar2(32) DEFAULT NULL , 
 mediaid varchar2(255) DEFAULT NULL , 
 locx varchar2(20) DEFAULT NULL , 
 locy varchar2(20) DEFAULT NULL , 
 duration varchar2(30) DEFAULT NULL , 
 scale varchar2(10) DEFAULT NULL , 
 filename varchar2(255) DEFAULT NULL , 
 filesize number(11) DEFAULT NULL , 
 attachmentid varchar2(32) DEFAULT NULL , 
 lastagentmsgtime date DEFAULT NULL , 
 agentreplytime number(11) DEFAULT NULL , 
 lastmsgtime date DEFAULT NULL , 
 agentreplyinterval number(11) DEFAULT NULL , 
 sessionid varchar2(50) DEFAULT NULL , 
 cooperation number(4) DEFAULT NULL , 
 datastatus number(4) DEFAULT '0' , 
 aiid varchar2(32) DEFAULT '0' , 
 topic number(4) DEFAULT '0' , 
 topicid varchar2(32) DEFAULT NULL , 
 topicatid varchar2(32) DEFAULT NULL , 
 aichat number(4) DEFAULT '0' , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_chat_message 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_columnproperties 
-- ---------------------------- 
DROP TABLE  uk_columnproperties; 
CREATE TABLE uk_columnproperties ( 
 id varchar2(32) NOT NULL , 
 format varchar2(255) DEFAULT NULL , 
 prefix varchar2(255) DEFAULT NULL , 
 width varchar2(255) DEFAULT NULL , 
 suffix varchar2(255) DEFAULT NULL , 
 font varchar2(255) DEFAULT NULL , 
 colname varchar2(255) DEFAULT NULL , 
 border varchar2(255) DEFAULT NULL , 
 decimalCount varchar2(255) DEFAULT NULL , 
 sepsymbol varchar2(255) DEFAULT NULL , 
 alignment varchar2(255) DEFAULT NULL , 
 fontStyle varchar2(255) DEFAULT NULL , 
 fontColor varchar2(255) DEFAULT NULL , 
 paramName varchar2(255) DEFAULT NULL , 
 orgi varchar2(255) DEFAULT NULL , 
 dataid varchar2(255) DEFAULT NULL , 
 modelid varchar2(255) DEFAULT NULL , 
 dataname varchar2(255) DEFAULT NULL , 
 cur varchar2(255) DEFAULT NULL , 
 hyp varchar2(255) DEFAULT NULL , 
 timeFormat varchar2(255) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_columnproperties 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_consult_invite 
-- ---------------------------- 
DROP TABLE  uk_consult_invite; 
CREATE TABLE uk_consult_invite ( 
 id varchar2(32) NOT NULL , 
 impid varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 owner varchar2(32) DEFAULT NULL , 
 processid varchar2(32) DEFAULT NULL , 
 shares varchar2(32) DEFAULT NULL , 
 update_time date DEFAULT NULL , 
 update_user varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 wfstatus varchar2(32) DEFAULT NULL , 
 consult_invite_model varchar2(32) DEFAULT NULL , 
 consult_invite_content varchar2(255) DEFAULT NULL , 
 consult_invite_position varchar2(32) DEFAULT NULL , 
 consult_invite_color varchar2(32) DEFAULT NULL , 
 consult_invite_right number(11) DEFAULT NULL , 
 consult_invite_left number(11) DEFAULT NULL , 
 consult_invite_bottom number(11) DEFAULT NULL , 
 consult_invite_top number(11) DEFAULT NULL , 
 create_time date DEFAULT NULL , 
 name varchar2(50) DEFAULT NULL , 
 consult_invite_width number(32) DEFAULT NULL , 
 consult_invite_poptype varchar2(32) DEFAULT NULL , 
 consult_invite_fontsize number(32) DEFAULT NULL , 
 consult_invite_fontstyle varchar2(32) DEFAULT NULL , 
 consult_invite_fontcolor varchar2(32) DEFAULT NULL , 
 consult_invite_interval number(32) DEFAULT NULL , 
 consult_invite_repeat varchar2(32) DEFAULT NULL , 
 consult_invite_hight number(32) DEFAULT NULL , 
 snsaccountid varchar2(56) DEFAULT NULL , 
 consult_vsitorbtn_position varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_content varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_right varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_left varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_top varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_color varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_model varchar2(32) DEFAULT NULL , 
 consult_vsitorbtn_bottom varchar2(32) DEFAULT NULL , 
 consult_invite_backimg varchar2(32) DEFAULT NULL , 
 datadept varchar2(32) DEFAULT NULL , 
 agent_online varchar2(32) DEFAULT NULL , 
 consult_dialog_color varchar2(32) DEFAULT NULL , 
 consult_dialog_logo varchar2(100) DEFAULT NULL , 
 consult_dialog_headimg varchar2(100) DEFAULT NULL , 
 consult_vsitorbtn_display number(11) DEFAULT NULL , 
 dialog_name varchar2(100) DEFAULT NULL , 
 dialog_address varchar2(100) DEFAULT NULL , 
 dialog_phone varchar2(32) DEFAULT NULL , 
 dialog_mail varchar2(100) DEFAULT NULL , 
 dialog_introduction clob , 
 dialog_message clob , 
 dialog_ad varchar2(100) DEFAULT NULL , 
 consult_invite_enable number(4) DEFAULT NULL , 
 consult_invite_accept varchar2(50) DEFAULT NULL , 
 consult_invite_later varchar2(50) DEFAULT NULL , 
 consult_invite_delay number(11) DEFAULT NULL , 
 consult_invite_bg varchar2(100) DEFAULT NULL , 
 leavemessage number(4) DEFAULT NULL , 
 lvmname number(4) DEFAULT NULL , 
 lvmphone number(4) DEFAULT NULL , 
 lvmemail number(4) DEFAULT NULL , 
 lvmaddress number(4) DEFAULT NULL , 
 lvmqq number(4) DEFAULT '0' , 
 lvmcontent number(4) DEFAULT NULL , 
 workinghours clob , 
 lvmopentype varchar2(32) DEFAULT NULL , 
 skill number(4) DEFAULT '0' , 
 notinwhmsg clob , 
 consult_skill_logo varchar2(100) DEFAULT NULL , 
 consult_skill_title varchar2(50) DEFAULT NULL , 
 consult_skill_img varchar2(100) DEFAULT NULL , 
 consult_skill_msg clob , 
 consult_skill_numbers number(11) DEFAULT NULL , 
 consult_skill_maxagent number(11) DEFAULT NULL , 
 consult_skill_bottomtitle varchar2(50) DEFAULT NULL , 
 consult_skill_agent number(4) DEFAULT NULL , 
 ai number(4) DEFAULT '0' , 
 aifirst number(4) DEFAULT '0' , 
 aisearch number(4) DEFAULT '0' , 
 aimsg clob , 
 aisuccesstip varchar2(100) DEFAULT NULL , 
 ainame varchar2(50) DEFAULT NULL , 
 consult_info number(4) DEFAULT NULL , 
 consult_info_name number(4) DEFAULT NULL , 
 consult_info_email number(4) DEFAULT NULL , 
 consult_info_phone number(4) DEFAULT NULL , 
 consult_info_resion number(4) DEFAULT NULL , 
 consult_info_message clob , 
 consult_info_cookies number(4) DEFAULT NULL , 
 recordhis number(4) DEFAULT NULL , 
 traceuser number(4) DEFAULT NULL , 
 onlyareaskill number(4) DEFAULT '0' , 
 uk_consult_invite clob , 
 areaskilltipmsg clob , 
 aiid varchar2(32) DEFAULT NULL , 
 maxwordsnum number(11) DEFAULT '300' ,
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_consult_invite 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_contacts 
-- ---------------------------- 
DROP TABLE  uk_contacts; 
CREATE TABLE uk_contacts ( 
 id varchar2(32) NOT NULL , 
 gender varchar2(60) DEFAULT NULL , 
 cusbirthday varchar2(50) DEFAULT NULL , 
 ctype varchar2(60) DEFAULT NULL , 
 ckind varchar2(60) DEFAULT NULL , 
 clevel varchar2(60) DEFAULT NULL , 
 ccode varchar2(60) DEFAULT NULL , 
 nickname varchar2(64) DEFAULT NULL , 
 sarea varchar2(60) DEFAULT NULL , 
 csource varchar2(64) DEFAULT NULL , 
 language varchar2(40) DEFAULT NULL , 
 marriage varchar2(60) DEFAULT NULL , 
 education varchar2(60) DEFAULT NULL , 
 identifytype varchar2(60) DEFAULT NULL , 
 identifynumber varchar2(40) DEFAULT NULL , 
 website varchar2(255) DEFAULT NULL , 
 email varchar2(128) DEFAULT NULL , 
 emailalt varchar2(128) DEFAULT NULL , 
 mobile varchar2(40) DEFAULT NULL , 
 mobilealt varchar2(40) DEFAULT NULL , 
 phone varchar2(40) DEFAULT NULL , 
 extension varchar2(40) DEFAULT NULL , 
 phonealt varchar2(40) DEFAULT NULL , 
 extensionalt varchar2(40) DEFAULT NULL , 
 familyphone varchar2(40) DEFAULT NULL , 
 familyphonealt varchar2(40) DEFAULT NULL , 
 fax varchar2(40) DEFAULT NULL , 
 faxalt varchar2(40) DEFAULT NULL , 
 country varchar2(60) DEFAULT NULL , 
 province varchar2(60) DEFAULT NULL , 
 city varchar2(60) DEFAULT NULL , 
 address clob , 
 postcode varchar2(40) DEFAULT NULL , 
 enterpriseid varchar2(60) DEFAULT NULL , 
 company clob , 
 department varchar2(40) DEFAULT NULL , 
 duty varchar2(40) DEFAULT NULL , 
 deptpr varchar2(40) DEFAULT NULL , 
 validstatus varchar2(50) DEFAULT NULL , 
 weixin varchar2(60) DEFAULT NULL , 
 weixinname varchar2(60) DEFAULT NULL , 
 weixinid varchar2(255) DEFAULT NULL , 
 weibo varchar2(255) DEFAULT NULL , 
 weiboid varchar2(255) DEFAULT NULL , 
 qqcode varchar2(32) DEFAULT NULL , 
 touchtime date DEFAULT NULL , 
 datastatus number(10) DEFAULT NULL , 
 processid varchar2(60) DEFAULT NULL , 
 creater varchar2(60) DEFAULT NULL , 
 username varchar2(60) DEFAULT NULL , 
 updateuser varchar2(60) DEFAULT NULL , 
 memo varchar2(255) DEFAULT NULL , 
 updateusername varchar2(60) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 orgi varchar2(60) DEFAULT NULL , 
 compper varchar2(255) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 name varchar2(255) DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 entcusid varchar2(32) DEFAULT NULL , 
 pinyin varchar2(10) DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_contacts 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_cube 
-- ---------------------------- 
DROP TABLE  uk_cube; 
CREATE TABLE uk_cube ( 
 ID varchar2(255) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 DB varchar2(32) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 MPOSLEFT varchar2(32) DEFAULT NULL , 
 MPOSTOP varchar2(32) DEFAULT NULL , 
 TYPEID varchar2(32) DEFAULT NULL , 
 CODE varchar2(32) DEFAULT NULL , 
 DSTYPE varchar2(255) DEFAULT NULL , 
 MODELTYPE varchar2(32) DEFAULT NULL , 
 createdata varchar2(32) DEFAULT NULL , 
 startindex number(11) DEFAULT NULL , 
 startdate date DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 dataflag varchar2(255) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 CUBEFILE clob , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_cube 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_cubelevel 
-- ---------------------------- 
DROP TABLE  uk_cubelevel; 
CREATE TABLE uk_cubelevel ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CODE varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 COLUMNAME varchar2(255) DEFAULT NULL , 
 UNIQUEMEMBERS NUMBER(6) DEFAULT NULL , 
 TYPE varchar2(32) DEFAULT NULL , 
 LEVELTYPE varchar2(32) DEFAULT NULL , 
 TABLENAME varchar2(255) DEFAULT NULL , 
 CUBEID varchar2(32) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 PARAMETERS clob , 
 ATTRIBUE clob , 
 DIMID varchar2(32) DEFAULT NULL , 
 PERMISSIONS NUMBER(6) DEFAULT NULL , 
 TABLEPROPERTY varchar2(32) DEFAULT NULL , 
 FORMATSTR varchar2(255) DEFAULT NULL , 
 description clob , 
 creater varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_cubelevel 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_cubemeasure 
-- ---------------------------- 
DROP TABLE  uk_cubemeasure; 
CREATE TABLE uk_cubemeasure ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CODE varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 COLUMNAME varchar2(255) DEFAULT NULL , 
 UNIQUEMEMBERS NUMBER(6) DEFAULT NULL , 
 TYPE varchar2(32) DEFAULT NULL , 
 LEVELTYPE varchar2(32) DEFAULT NULL , 
 TABLENAME varchar2(255) DEFAULT NULL , 
 CUBEID varchar2(32) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 PARAMETERS clob , 
 ATTRIBUE clob , 
 MID varchar2(32) DEFAULT NULL , 
 AGGREGATOR varchar2(32) DEFAULT NULL , 
 FORMATSTRING varchar2(255) DEFAULT NULL , 
 CALCULATEDMEMBER NUMBER(6) DEFAULT NULL , 
 MODELTYPE varchar2(32) DEFAULT NULL , 
 MEASURE varchar2(32) DEFAULT NULL , 
 description clob , 
 creater varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_cubemeasure 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_cubemetadata 
-- ---------------------------- 
DROP TABLE  uk_cubemetadata; 
CREATE TABLE uk_cubemetadata ( 
 ID varchar2(32) NOT NULL , 
 TITLE varchar2(255) DEFAULT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CODE varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 TB varchar2(32) DEFAULT NULL , 
 ORGI varchar2(255) DEFAULT NULL , 
 CUBEID varchar2(32) DEFAULT NULL , 
 POSTOP varchar2(32) DEFAULT NULL , 
 POSLEFT varchar2(32) DEFAULT NULL , 
 MTYPE varchar2(5) DEFAULT NULL , 
 NAMEALIAS varchar2(255) DEFAULT NULL , 
 PARAMETERS varchar2(255) DEFAULT NULL , 
 ATTRIBUE clob , 
 creater varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_cubemetadata 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_cube_type 
-- ---------------------------- 
DROP TABLE  uk_cube_type; 
CREATE TABLE uk_cube_type ( 
 id varchar2(32) NOT NULL , 
 name varchar2(255) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 parentid varchar2(32) DEFAULT NULL , 
 inx number(11) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 description varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_cube_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_datadic 
-- ---------------------------- 
DROP TABLE  uk_datadic; 
CREATE TABLE uk_datadic ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(32) DEFAULT NULL , 
 TITLE varchar2(32) DEFAULT NULL , 
 CODE varchar2(32) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 TYPE varchar2(32) DEFAULT NULL , 
 MEMO varchar2(255) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 STATUS varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 CREATER varchar2(255) DEFAULT NULL , 
 PUBLISHEDTYPE varchar2(32) DEFAULT NULL , 
 DESCRIPTION varchar2(255) DEFAULT NULL , 
 TABTYPE varchar2(32) DEFAULT NULL , 
 DSTYPE varchar2(32) DEFAULT NULL , 
 DSTEMPLET varchar2(255) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 DICTYPE varchar2(32) DEFAULT NULL , 
 ICONCLASS varchar2(100) DEFAULT NULL , 
 CSSSTYLE varchar2(255) DEFAULT NULL , 
 AUTHCODE varchar2(100) DEFAULT NULL , 
 DEFAULTMENU number(4) DEFAULT NULL , 
 DATAID varchar2(32) DEFAULT NULL , 
 DICICON varchar2(32) DEFAULT NULL , 
 CURICON varchar2(32) DEFAULT NULL , 
 BGCOLOR varchar2(32) DEFAULT NULL , 
 CURBGCOLOR varchar2(32) DEFAULT NULL , 
 MENUPOS varchar2(32) DEFAULT NULL , 
 DISTITLE varchar2(100) DEFAULT NULL , 
 NAVMENU number(4) DEFAULT '0' , 
 QUICKMENU number(4) DEFAULT '0' , 
 PROJECTID varchar2(32) DEFAULT NULL , 
 SPSEARCH number(4) DEFAULT NULL  
); 
 
-- ---------------------------- 
-- Records of uk_datadic 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_dataevent 
-- ---------------------------- 
DROP TABLE  uk_dataevent; 
CREATE TABLE uk_dataevent ( 
 id varchar2(32) NOT NULL , 
 name varchar2(50) DEFAULT NULL , 
 tpid varchar2(32) DEFAULT NULL , 
 propertity varchar2(50) DEFAULT NULL , 
 field varchar2(50) DEFAULT NULL , 
 newvalue varchar2(255) DEFAULT NULL , 
 oldvalue varchar2(255) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 modifyid varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 eventtype varchar2(32) DEFAULT NULL , 
 content varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_dataevent 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_dimension 
-- ---------------------------- 
DROP TABLE  uk_dimension; 
CREATE TABLE uk_dimension ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CODE varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CUBEID varchar2(32) DEFAULT NULL , 
 ORGI varchar2(255) DEFAULT NULL , 
 TYPE varchar2(32) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 PARAMETERS clob , 
 ATTRIBUE clob , 
 POSLEFT varchar2(32) DEFAULT NULL , 
 POSTOP varchar2(32) DEFAULT NULL , 
 FORMATSTR varchar2(32) DEFAULT NULL , 
 MODELTYPE varchar2(32) DEFAULT NULL , 
 DIM varchar2(32) DEFAULT NULL , 
 ALLMEMBERNAME varchar2(32) DEFAULT NULL , 
 FKFIELD varchar2(255) DEFAULT NULL , 
 FKTABLE varchar2(255) DEFAULT NULL , 
 FKTABLEID varchar2(255) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_dimension 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_drilldown 
-- ---------------------------- 
DROP TABLE  uk_drilldown; 
CREATE TABLE uk_drilldown ( 
 id varchar2(32) NOT NULL , 
 name varchar2(255) DEFAULT NULL , 
 memo varchar2(255) DEFAULT NULL , 
 orgi varchar2(255) DEFAULT NULL , 
 code varchar2(255) DEFAULT NULL , 
 dataid varchar2(255) DEFAULT NULL , 
 dataname varchar2(255) DEFAULT NULL , 
 tdstyle varchar2(255) DEFAULT NULL , 
 reportid varchar2(255) DEFAULT NULL , 
 modelid varchar2(255) DEFAULT NULL , 
 paramname varchar2(255) DEFAULT NULL , 
 paramtype varchar2(255) DEFAULT NULL , 
 paramurl varchar2(255) DEFAULT NULL , 
 paramtarget varchar2(255) DEFAULT NULL , 
 paramreport varchar2(255) DEFAULT NULL , 
 paramvalue varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_drilldown 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_entcustomer 
-- ---------------------------- 
DROP TABLE  uk_entcustomer; 
CREATE TABLE uk_entcustomer ( 
 id varchar2(32) NOT NULL , 
 name varchar2(255) DEFAULT NULL , 
 etype varchar2(60) DEFAULT NULL , 
 ekind varchar2(60) DEFAULT NULL , 
 elevel varchar2(60) DEFAULT NULL , 
 ecode varchar2(60) DEFAULT NULL , 
 nickname varchar2(64) DEFAULT NULL , 
 esource varchar2(64) DEFAULT NULL , 
 origincode varchar2(60) DEFAULT NULL , 
 corporation varchar2(60) DEFAULT NULL , 
 leadername varchar2(40) DEFAULT NULL , 
 leadermobile varchar2(40) DEFAULT NULL , 
 leadermobile2 varchar2(40) DEFAULT NULL , 
 leaderphone varchar2(40) DEFAULT NULL , 
 leaderemail varchar2(60) DEFAULT NULL , 
 website varchar2(255) DEFAULT NULL , 
 email varchar2(128) DEFAULT NULL , 
 emailalt varchar2(128) DEFAULT NULL , 
 phone varchar2(40) DEFAULT NULL , 
 phonealt varchar2(40) DEFAULT NULL , 
 fax varchar2(40) DEFAULT NULL , 
 faxalt varchar2(40) DEFAULT NULL , 
 country varchar2(60) DEFAULT NULL , 
 province varchar2(60) DEFAULT NULL , 
 city varchar2(60) DEFAULT NULL , 
 sarea varchar2(60) DEFAULT NULL , 
 address varchar2(255) DEFAULT NULL , 
 postcode varchar2(40) DEFAULT NULL , 
 businessscope clob , 
 capital varchar2(40) DEFAULT NULL , 
 stockcode varchar2(40) DEFAULT NULL , 
 bankaccount varchar2(40) DEFAULT NULL , 
 registeredaddress clob , 
 esize varchar2(60) DEFAULT NULL , 
 industry varchar2(60) DEFAULT NULL , 
 validstatus varchar2(50) DEFAULT NULL , 
 weixin varchar2(60) DEFAULT NULL , 
 weibo varchar2(255) DEFAULT NULL , 
 touchtime date DEFAULT NULL , 
 dzip varchar2(32) DEFAULT NULL , 
 daddress varchar2(255) DEFAULT NULL , 
 darea varchar2(60) DEFAULT NULL , 
 dcity varchar2(60) DEFAULT NULL , 
 dprovince varchar2(60) DEFAULT NULL , 
 datastatus varchar2(2) DEFAULT NULL , 
 processid varchar2(60) DEFAULT NULL , 
 description clob , 
 creater varchar2(60) DEFAULT NULL , 
 username varchar2(60) DEFAULT NULL ,
 updateuser varchar2(60) DEFAULT NULL , 
 updateusername varchar2(60) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 orgi varchar2(60) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 batid varchar2(32) DEFAULT NULL , 
 maturity varchar2(32) DEFAULT NULL , 
 entcusid varchar2(32) DEFAULT NULL , 
 pinyin varchar2(10) DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
 );
 
 
-- ---------------------------- 
-- Records of uk_entcustomer 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_fans 
-- ---------------------------- 
DROP TABLE  uk_fans; 
CREATE TABLE uk_fans ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 suser varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_fans 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_favorites 
-- ---------------------------- 
DROP TABLE  uk_favorites; 
CREATE TABLE uk_favorites ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 ORDERID varchar2(32) DEFAULT NULL , 
 TITLE varchar2(255) DEFAULT NULL , 
 MODEL varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_favorites 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_fs_event_socket 
-- ---------------------------- 
DROP TABLE  uk_fs_event_socket; 
CREATE TABLE uk_fs_event_socket ( 
 id number(11) NOT NULL , 
 hostname varchar2(50) NOT NULL , 
 nat_map varchar2(20) DEFAULT 'false' ,
 listen_ip varchar2(50) DEFAULT '0.0.0.0' ,
 listen_port number(11) DEFAULT '8021' ,
 password varchar2(50) DEFAULT 'ClueCon' ,
 apply_inbound_acl varchar2(50) DEFAULT 'lan' ,
 stop_on_bind_error varchar2(50) DEFAULT 'true' ,
 addtime varchar2(20) DEFAULT NULL , 
 updatetime varchar2(20) DEFAULT NULL , 
 connected number(11) DEFAULT NULL , 
 connected_result varchar2(50) DEFAULT NULL , 
 show_calls number(11) DEFAULT NULL , 
 enable number(11) DEFAULT '1', 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_fs_event_socket 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_generation 
-- ---------------------------- 
DROP TABLE  uk_generation; 
CREATE TABLE uk_generation ( 
 id varchar2(32) NOT NULL , 
 model varchar2(32) DEFAULT NULL , 
 startinx number(11) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_generation 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_historyreport 
-- ---------------------------- 
DROP TABLE  uk_historyreport; 
CREATE TABLE uk_historyreport ( 
 ID varchar2(32) NOT NULL , 
 BYTES number(11) NOT NULL , 
 THREADS number(11) NOT NULL , 
 TYPE varchar2(255) DEFAULT NULL , 
 STATUS varchar2(255) DEFAULT NULL , 
 ERRORMSG varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 STARTTIME date DEFAULT NULL , 
 ENDTIME date DEFAULT NULL , 
 AMOUNT varchar2(255) DEFAULT NULL , 
 PAGES number(11) NOT NULL , 
 ERRORS number(11) NOT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 TABLEDIRID varchar2(32) DEFAULT NULL , 
 TABLEID varchar2(32) DEFAULT NULL , 
 TOTAL number(11) DEFAULT NULL , 
 USERID varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT '' , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_historyreport 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_imgroup 
-- ---------------------------- 
DROP TABLE  uk_imgroup; 
CREATE TABLE uk_imgroup ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 tipmessage clob , 
 descript clob , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_imgroup 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_imgroup_user 
-- ---------------------------- 
DROP TABLE  uk_imgroup_user; 
CREATE TABLE uk_imgroup_user ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 user_id varchar2(32) DEFAULT NULL , 
 imgroup_id varchar2(32) DEFAULT NULL , 
 admin number(4) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_imgroup_user 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_instruction 
-- ---------------------------- 
DROP TABLE  uk_instruction; 
CREATE TABLE uk_instruction ( 
 id varchar2(96) NOT NULL , 
 name varchar2(96) DEFAULT NULL , 
 code varchar2(96) DEFAULT NULL , 
 plugin varchar2(96) DEFAULT NULL , 
 memo clob , 
 status varchar2(96) DEFAULT NULL , 
 orgi varchar2(96) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 userid varchar2(96) DEFAULT NULL , 
 type varchar2(96) DEFAULT NULL , 
 parent varchar2(96) DEFAULT NULL , 
 username varchar2(96) DEFAULT NULL , 
 scope varchar2(15) DEFAULT NULL , 
 tipdefault NUMBER(6) DEFAULT NULL , 
 matcherule varchar2(96) DEFAULT NULL , 
 userbind NUMBER(6) DEFAULT NULL , 
 interfacetype varchar2(96) DEFAULT NULL , 
 adapter varchar2(96) DEFAULT NULL , 
 interfaceurl clob , 
 interfaceparam clob , 
 messagetype varchar2(96) DEFAULT NULL , 
 keyword varchar2(100) DEFAULT NULL , 
 eventype varchar2(32) DEFAULT NULL , 
 snsid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_instruction 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_inviterecord 
-- ---------------------------- 
DROP TABLE  uk_inviterecord; 
CREATE TABLE uk_inviterecord ( 
 id varchar2(32) NOT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 agentno varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 result varchar2(10) DEFAULT NULL , 
 responsetime number(11) DEFAULT NULL , 
 appid varchar2(32) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 url varchar2(255) DEFAULT NULL , 
 traceid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_inviterecord 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_kbs_expert 
-- ---------------------------- 
DROP TABLE  uk_kbs_expert; 
CREATE TABLE uk_kbs_expert ( 
 id varchar2(32) NOT NULL , 
 user_id varchar2(32) DEFAULT NULL , 
 kbstype varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_kbs_expert 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_kbs_topic 
-- ---------------------------- 
DROP TABLE  uk_kbs_topic; 
CREATE TABLE uk_kbs_topic ( 
 id varchar2(32) NOT NULL , 
 sessionid varchar2(32) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 content clob , 
 keyword clob , 
 summary clob , 
 anonymous number(4) DEFAULT NULL , 
 begintime date DEFAULT NULL , 
 endtime date DEFAULT NULL , 
 top number(4) DEFAULT NULL , 
 essence number(4) DEFAULT NULL , 
 accept number(4) DEFAULT NULL , 
 finish number(4) DEFAULT NULL , 
 answers number(11) DEFAULT NULL , 
 sviews number(11) DEFAULT NULL , 
 followers number(11) DEFAULT NULL , 
 collections number(11) DEFAULT NULL , 
 COMMENTs number(11) DEFAULT NULL , 
 mobile number(4) DEFAULT NULL , 
 status varchar2(32) DEFAULT NULL , 
 tptype varchar2(32) DEFAULT NULL , 
 cate varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 memo varchar2(32) DEFAULT NULL , 
 price number(11) DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 sms varchar2(255) DEFAULT NULL , 
 tts varchar2(255) DEFAULT NULL , 
 email clob , 
 weixin clob , 
 tags clob , 
 attachment clob , 
 approval number(4) DEFAULT NULL  
); 
 
-- ---------------------------- 
-- Records of uk_kbs_topic 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_kbs_type 
-- ---------------------------- 
DROP TABLE  uk_kbs_type; 
CREATE TABLE uk_kbs_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 APPROVAL number(4) DEFAULT NULL , 
 BPMID varchar2(32) DEFAULT NULL , 
 PC varchar2(32) DEFAULT NULL , 
 INX number(11) DEFAULT NULL , 
 STARTDATE date DEFAULT NULL , 
 ENDDATE date DEFAULT NULL , 
 ENABLE number(4) DEFAULT NULL , 
 DESCRIPTION varchar2(255) DEFAULT NULL , 
 BPM number(4) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_kbs_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_leavemsg 
-- ---------------------------- 
DROP TABLE  uk_leavemsg; 
CREATE TABLE uk_leavemsg ( 
 id varchar2(32) NOT NULL , 
 name varchar2(50) DEFAULT NULL , 
 mobile varchar2(32) DEFAULT NULL , 
 email varchar2(100) DEFAULT NULL , 
 address varchar2(255) DEFAULT NULL , 
 qq varchar2(30) DEFAULT NULL , 
 content varchar2(255) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 msgstatus varchar2(20) DEFAULT NULL , 
 contactsid varchar2(32) DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_leavemsg 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_log 
-- ---------------------------- 
DROP TABLE  uk_log; 
CREATE TABLE uk_log ( 
 id varchar2(32) NOT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 flowid varchar2(32) DEFAULT NULL , 
 logtype varchar2(32) DEFAULT NULL , 
 createdate date DEFAULT NULL , 
 msg clob , 
 LEVELS varchar2(32) DEFAULT NULL , 
 thread varchar2(255) DEFAULT NULL , 
 clazz varchar2(255) DEFAULT NULL , 
 FILES varchar2(255) DEFAULT NULL , 
 linenumber varchar2(32) DEFAULT NULL , 
 method varchar2(255) DEFAULT NULL , 
 startid varchar2(32) DEFAULT NULL , 
 errorinfo clob , 
 triggerwarning varchar2(32) DEFAULT NULL , 
 triggertime varchar2(32) DEFAULT NULL , 
 triggertimes number(11) DEFAULT NULL , 
 name varchar2(32) DEFAULT NULL , 
 code varchar2(32) DEFAULT NULL , 
 memo clob DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 logtime varchar2(32) DEFAULT NULL , 
 ipaddr varchar2(255) DEFAULT NULL , 
 port varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_log 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_log_request 
-- ---------------------------- 
DROP TABLE  uk_log_request; 
CREATE TABLE uk_log_request ( 
 id varchar2(32) NOT NULL , 
 type varchar2(255) DEFAULT NULL , 
 parameters clob , 
 throwable varchar2(255) DEFAULT NULL , 
 username varchar2(255) DEFAULT NULL , 
 usermail varchar2(255) DEFAULT NULL , 
 filename varchar2(255) DEFAULT NULL , 
 orgi varchar2(255) DEFAULT NULL , 
 error clob , 
 classname varchar2(255) DEFAULT NULL , 
 starttime date DEFAULT NULL , 
 endtime date DEFAULT NULL , 
 detailtype varchar2(255) DEFAULT NULL , 
 url clob , 
 reportdic varchar2(255) DEFAULT NULL , 
 reportname varchar2(255) DEFAULT NULL , 
 ip varchar2(255) DEFAULT NULL , 
 hostname varchar2(255) DEFAULT NULL , 
 statues varchar2(255) DEFAULT NULL , 
 methodname clob , 
 linenumber varchar2(255) DEFAULT NULL , 
 querytime number(11) DEFAULT NULL , 
 optext varchar2(255) DEFAULT NULL , 
 field6 varchar2(255) DEFAULT NULL , 
 field7 varchar2(255) DEFAULT NULL , 
 field8 varchar2(255) DEFAULT NULL , 
 flowid varchar2(32) DEFAULT NULL , 
 userid varchar2(255) DEFAULT NULL , 
 name varchar2(32) DEFAULT NULL , 
 funtype varchar2(32) DEFAULT NULL , 
 fundesc varchar2(255) DEFAULT NULL , 
 triggerwarning varchar2(32) DEFAULT NULL , 
 triggertime varchar2(32) DEFAULT NULL , 
 createdate date DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_log_request 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_message 
-- ---------------------------- 
DROP TABLE  uk_message; 
CREATE TABLE uk_message ( 
 id varchar2(32) NOT NULL , 
 createtime date DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 content clob , 
 status varchar2(10) DEFAULT NULL , 
 fromuser varchar2(32) DEFAULT NULL , 
 touser varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_message 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_onlineuser 
-- ---------------------------- 
DROP TABLE  uk_onlineuser; 
CREATE TABLE uk_onlineuser ( 
 assignedto varchar2(255) NOT NULL,
 creater varchar2(255) DEFAULT NULL , 
 datastatus varchar2(255) DEFAULT NULL , 
 id varchar2(32) NOT NULL , 
 impid varchar2(255) DEFAULT NULL , 
 ipcode varchar2(255) DEFAULT NULL , 
 orgi varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 processid varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 updateuser varchar2(255) DEFAULT NULL , 
 username varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 resolution varchar2(255) DEFAULT NULL , 
 opersystem varchar2(100) DEFAULT NULL , 
 ip varchar2(50) DEFAULT NULL , 
 hostname varchar2(32) DEFAULT NULL , 
 browser varchar2(32) DEFAULT NULL , 
 status varchar2(11) DEFAULT NULL , 
 userid varchar2(52) DEFAULT NULL , 
 logintime date DEFAULT NULL , 
 sessionid varchar2(100) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 usertype varchar2(52) DEFAULT NULL , 
 optype varchar2(52) DEFAULT NULL , 
 mobile varchar2(10) DEFAULT NULL , 
 olduser varchar2(10) DEFAULT NULL , 
 country varchar2(50) DEFAULT NULL , 
 region varchar2(200) DEFAULT NULL , 
 city varchar2(50) DEFAULT NULL , 
 isp varchar2(100) DEFAULT NULL , 
 province varchar2(50) DEFAULT NULL , 
 betweentime number(11) DEFAULT NULL , 
 datestr varchar2(20) DEFAULT NULL , 
 keyword varchar2(100) DEFAULT NULL , 
 source varchar2(50) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 url varchar2(255) DEFAULT NULL , 
 useragent clob , 
 invitetimes number(11) DEFAULT NULL , 
 invitestatus varchar2(10) DEFAULT NULL , 
 refusetimes number(11) DEFAULT NULL , 
 channel varchar2(32) DEFAULT NULL , 
 appid varchar2(32) DEFAULT NULL , 
 contactsid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_onlineuser 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_onlineuser_his 
-- ---------------------------- 
DROP TABLE  uk_onlineuser_his; 
CREATE TABLE uk_onlineuser_his ( 
 assignedto varchar2(255) NOT NULL,
 creater varchar2(255) DEFAULT NULL , 
 datastatus varchar2(255) DEFAULT NULL , 
 id varchar2(32) NOT NULL , 
 impid varchar2(255) DEFAULT NULL , 
 ipcode varchar2(255) DEFAULT NULL , 
 orgi varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 processid varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 updateuser varchar2(255) DEFAULT NULL , 
 username varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 resolution varchar2(255) DEFAULT NULL , 
 opersystem varchar2(100) DEFAULT NULL , 
 ip varchar2(50) DEFAULT NULL , 
 hostname varchar2(32) DEFAULT NULL , 
 browser varchar2(32) DEFAULT NULL , 
 status varchar2(11) DEFAULT NULL , 
 userid varchar2(52) DEFAULT NULL , 
 logintime date DEFAULT NULL , 
 sessionid varchar2(100) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 usertype varchar2(52) DEFAULT NULL , 
 optype varchar2(52) DEFAULT NULL , 
 mobile varchar2(10) DEFAULT NULL , 
 olduser varchar2(10) DEFAULT NULL , 
 country varchar2(50) DEFAULT NULL , 
 region varchar2(200) DEFAULT NULL , 
 city varchar2(50) DEFAULT NULL , 
 isp varchar2(100) DEFAULT NULL , 
 province varchar2(50) DEFAULT NULL , 
 betweentime number(11) DEFAULT NULL , 
 datestr varchar2(20) DEFAULT NULL , 
 keyword varchar2(100) DEFAULT NULL , 
 source varchar2(50) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 url varchar2(255) DEFAULT NULL , 
 useragent clob , 
 invitetimes number(11) DEFAULT NULL , 
 invitestatus varchar2(10) DEFAULT NULL , 
 refusetimes number(11) DEFAULT NULL , 
 channel varchar2(32) DEFAULT NULL , 
 appid varchar2(32) DEFAULT NULL , 
 contactsid varchar2(32) DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_onlineuser_his 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_orders 
-- ---------------------------- 
DROP TABLE  uk_orders ;
CREATE TABLE uk_orders (
 ID varchar2(32) NOT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 DATAID varchar2(32) DEFAULT NULL , 
 CONTENT clob , 
 UPDATETIME date DEFAULT NULL , 
 OPTIMAL number(4) DEFAULT NULL , 
 PRIREP number(4) DEFAULT NULL , 
 UP number(11) DEFAULT NULL , 
 COMMENTS number(11) DEFAULT NULL , 
 ADMIN number(4) DEFAULT NULL , 
 DATASTATUS number(4) DEFAULT NULL , 
 ORGI varchar2(50) DEFAULT NULL , 
 CATE varchar2(32) DEFAULT NULL , 
 OPTYPE varchar2(32) DEFAULT NULL , 
 IPCODE varchar2(100) DEFAULT NULL , 
 COUNTRY varchar2(100) DEFAULT NULL , 
 PROVINCE varchar2(100) DEFAULT NULL , 
 CITY varchar2(100) DEFAULT NULL , 
 ISP varchar2(100) DEFAULT NULL , 
 REGION varchar2(100) DEFAULT NULL , 
 ROWCOUNT number(11) DEFAULT NULL , 
 KEY varchar2(100) DEFAULT NULL , 
 APPROVAL varchar2(20) DEFAULT NULL , 
 RETBACK varchar2(20) DEFAULT NULL , 
 ACCDEPT varchar2(32) DEFAULT NULL , 
 ACCUSER varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_orders 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_organ 
-- ---------------------------- 
DROP TABLE  uk_organ; 
CREATE TABLE uk_organ ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 ORGID varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENT varchar2(32) DEFAULT NULL , 
 SKILL number(4) DEFAULT '0' , 
 area clob , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_organ 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_organization 
-- ---------------------------- 
DROP TABLE  uk_organization; 
CREATE TABLE uk_organization ( 
 id varchar2(32) NOT NULL,
 name varchar2(255) DEFAULT NULL , 
 orgtype varchar2(255) DEFAULT NULL , 
 orgscale varchar2(255) DEFAULT NULL , 
 orgindustry varchar2(255) DEFAULT NULL , 
 code varchar2(255) DEFAULT NULL , 
 memo varchar2(200) DEFAULT NULL , 
 logo varchar2(200) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_organization 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_organrole 
-- ---------------------------- 
DROP TABLE  uk_organrole; 
CREATE TABLE uk_organrole ( 
 id varchar2(32) NOT NULL , 
 organ_id varchar2(32) DEFAULT NULL , 
 role_id varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 dicid varchar2(32) DEFAULT NULL , 
 dicvalue varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_organrole 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_orgi_skill_rel 
-- ---------------------------- 
DROP TABLE  uk_orgi_skill_rel; 
CREATE TABLE uk_orgi_skill_rel ( 
 ID varchar2(32) NOT NULL , 
 SKILLID varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_orgi_skill_rel 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_propertiesevent 
-- ---------------------------- 
DROP TABLE  uk_propertiesevent; 
CREATE TABLE uk_propertiesevent ( 
 id varchar2(32) NOT NULL , 
 name varchar2(50) DEFAULT NULL , 
 tpid varchar2(32) DEFAULT NULL , 
 propertity varchar2(50) DEFAULT NULL , 
 field varchar2(50) DEFAULT NULL , 
 newvalue varchar2(255) DEFAULT NULL , 
 oldvalue varchar2(255) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 modifyid varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 textvalue varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_propertiesevent 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_publishedcube 
-- ---------------------------- 
DROP TABLE  uk_publishedcube; 
CREATE TABLE uk_publishedcube ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 DB varchar2(32) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 MPOSLEFT varchar2(32) DEFAULT NULL , 
 MPOSTOP varchar2(32) DEFAULT NULL , 
 TYPEID varchar2(32) DEFAULT NULL , 
 CODE varchar2(32) DEFAULT NULL , 
 DSTYPE varchar2(255) DEFAULT NULL , 
 MODELTYPE varchar2(32) DEFAULT NULL , 
 createdata varchar2(32) DEFAULT NULL , 
 startindex number(11) DEFAULT NULL , 
 startdate date DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 dataflag varchar2(255) DEFAULT NULL , 
 DATAVERSION number(11) DEFAULT NULL , 
 CREATER varchar2(255) DEFAULT NULL , 
 USERID varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(255) DEFAULT NULL , 
 CUBECONTENT clob , 
 DBID varchar2(32) DEFAULT NULL , 
 DICLOCATION varchar2(255) DEFAULT NULL , 
 USEREMAIL varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_publishedcube 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_publishedreport 
-- ---------------------------- 
DROP TABLE  uk_publishedreport; 
CREATE TABLE uk_publishedreport ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 DICID varchar2(32) DEFAULT NULL , 
 CODE varchar2(32) DEFAULT NULL , 
 reporttype varchar2(255) DEFAULT NULL , 
 startindex number(11) DEFAULT NULL , 
 startdate date DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 dataflag varchar2(255) DEFAULT NULL , 
 DATAVERSION number(11) DEFAULT NULL , 
 CREATER varchar2(255) DEFAULT NULL , 
 REPORTCONTENT clob , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_publishedreport 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_quality 
-- ---------------------------- 
DROP TABLE  uk_quality; 
CREATE TABLE uk_quality ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 STARTDATE date DEFAULT NULL , 
 ENDDATE date DEFAULT NULL , 
 ENABLE number(4) DEFAULT NULL , 
 SCORE number(11) DEFAULT '0' , 
 DESCRIPTION varchar2(255) DEFAULT NULL , 
 QUALITYTYPE varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_quality 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_quickreply 
-- ---------------------------- 
DROP TABLE  uk_quickreply; 
CREATE TABLE uk_quickreply ( 
 id varchar2(32) NOT NULL,
 title varchar2(255) DEFAULT NULL , 
 content clob , 
 type varchar2(10) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 cate varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_quickreply 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_quick_type 
-- ---------------------------- 
DROP TABLE  uk_quick_type; 
CREATE TABLE uk_quick_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 INX number(11) DEFAULT NULL , 
 STARTDATE date DEFAULT NULL , 
 ENDDATE date DEFAULT NULL , 
 ENABLE number(4) DEFAULT NULL , 
 DESCRIPTION varchar2(255) DEFAULT NULL , 
 QUICKTYPE varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_quick_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_recentuser 
-- ---------------------------- 
DROP TABLE  uk_recentuser; 
CREATE TABLE uk_recentuser ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 user_id varchar2(32) DEFAULT NULL , 
 lastmsg varchar2(100) DEFAULT NULL , 
 newmsg number(11) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_recentuser 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_report 
-- ---------------------------- 
DROP TABLE  uk_report; 
CREATE TABLE uk_report ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 REPORTTYPE varchar2(32) DEFAULT NULL , 
 TITLE varchar2(255) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 OBJECTCOUNT number(11) DEFAULT NULL , 
 DICID varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 DESCRIPTION clob , 
 HTML clob , 
 REPORTPACKAGE varchar2(255) DEFAULT NULL , 
 USEACL varchar2(32) DEFAULT NULL , 
 status varchar2(32) DEFAULT NULL , 
 rolename clob , 
 userid clob , 
 blacklist clob , 
 REPORTCONTENT clob , 
 reportmodel varchar2(32) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 creater varchar2(255) DEFAULT NULL , 
 reportversion number(11) DEFAULT NULL , 
 publishedtype varchar2(32) DEFAULT NULL , 
 tabtype varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(32) DEFAULT NULL , 
 USEREMAIL varchar2(255) DEFAULT NULL , 
 CACHE NUMBER(6) DEFAULT NULL , 
 EXTPARAM varchar2(255) DEFAULT NULL , 
 TARGETREPORT varchar2(32) DEFAULT NULL , 
 DATASTATUS number(4) DEFAULT NULL , 
 CODE varchar2(100) DEFAULT NULL , 
 SOURCE varchar2(50) DEFAULT NULL , 
 VIEWTYPE varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_report 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_reportfilter 
-- ---------------------------- 
DROP TABLE  uk_reportfilter; 
CREATE TABLE uk_reportfilter ( 
 id varchar2(32) NOT NULL,
 dataid varchar2(32) DEFAULT NULL , 
 dataname varchar2(255) DEFAULT NULL , 
 modelid varchar2(32) DEFAULT NULL , 
 reportid varchar2(32) DEFAULT NULL , 
 contype varchar2(32) DEFAULT NULL , 
 filtertype varchar2(32) DEFAULT NULL , 
 formatstr varchar2(255) DEFAULT NULL , 
 convalue varchar2(255) DEFAULT NULL , 
 userdefvalue clob , 
 valuefiltertype varchar2(255) DEFAULT NULL , 
 name varchar2(255) DEFAULT NULL , 
 code varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 content clob , 
 valuestr varchar2(255) DEFAULT NULL , 
 filterprefix varchar2(255) DEFAULT NULL , 
 filtersuffix varchar2(255) DEFAULT NULL , 
 modeltype varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 funtype varchar2(32) DEFAULT NULL , 
 measureid varchar2(32) DEFAULT NULL , 
 valuecompare varchar2(32) DEFAULT NULL , 
 defaultvalue clob , 
 comparetype varchar2(32) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 cubeid varchar2(32) DEFAULT NULL , 
 mustvalue varchar2(32) DEFAULT NULL , 
 groupids clob , 
 defaultvaluerule clob , 
 dimid varchar2(32) DEFAULT NULL , 
 endvalue clob , 
 filtertemplet varchar2(255) DEFAULT NULL , 
 noformatvalue clob , 
 startvalue varchar2(255) DEFAULT NULL , 
 sortindex number(11) DEFAULT NULL , 
 cascadeid varchar2(32) DEFAULT NULL , 
 tableproperty varchar2(32) DEFAULT NULL , 
 tableid varchar2(32) DEFAULT NULL , 
 fieldid varchar2(32) DEFAULT NULL , 
 fktableid varchar2(32) DEFAULT NULL , 
 filterfieldid varchar2(32) DEFAULT NULL , 
 isdic number(4) DEFAULT NULL , 
 diccode varchar2(255) DEFAULT NULL , 
 keyfield varchar2(32) DEFAULT NULL , 
 valuefield varchar2(32) DEFAULT NULL , 
 fkfieldid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_reportfilter 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_reportmodel 
-- ---------------------------- 
DROP TABLE  uk_reportmodel; 
CREATE TABLE uk_reportmodel ( 
 id varchar2(50) NOT NULL , 
 posx varchar2(50) DEFAULT NULL , 
 posy varchar2(50) DEFAULT NULL , 
 poswidth varchar2(50) DEFAULT NULL , 
 posheight varchar2(50) DEFAULT NULL , 
 name varchar2(50) DEFAULT NULL , 
 code varchar2(50) DEFAULT NULL , 
 reportid varchar2(50) DEFAULT NULL , 
 modeltype varchar2(50) DEFAULT NULL , 
 sortindex number(11) DEFAULT NULL , 
 stylestr varchar2(50) DEFAULT NULL , 
 labeltext varchar2(50) DEFAULT NULL , 
 cssclassname varchar2(50) DEFAULT NULL , 
 mposleft varchar2(50) DEFAULT NULL , 
 mpostop varchar2(50) DEFAULT NULL , 
 title varchar2(50) DEFAULT NULL , 
 exchangerw number(4) DEFAULT '0' , 
 publishedcubeid varchar2(50) DEFAULT NULL , 
 rowdimension clob , 
 coldimension clob , 
 measure varchar2(50) DEFAULT NULL , 
 dstype varchar2(50) DEFAULT NULL , 
 dbtype varchar2(50) DEFAULT NULL , 
 orgi varchar2(50) DEFAULT NULL , 
 objectid varchar2(50) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 filterstr varchar2(50) DEFAULT NULL , 
 sortstr varchar2(50) DEFAULT NULL , 
 viewtype varchar2(50) DEFAULT NULL , 
 chartemplet varchar2(50) DEFAULT NULL , 
 chartype varchar2(50) DEFAULT NULL , 
 chartdatatype varchar2(50) DEFAULT NULL , 
 chart3d varchar2(50) DEFAULT NULL , 
 xtitle varchar2(50) DEFAULT NULL , 
 ytitle varchar2(50) DEFAULT NULL , 
 charttitle varchar2(50) DEFAULT NULL , 
 displayborder varchar2(50) DEFAULT NULL , 
 bordercolor varchar2(50) DEFAULT NULL , 
 displaydesc varchar2(50) DEFAULT NULL , 
 formdisplay varchar2(50) DEFAULT NULL , 
 labelstyle varchar2(50) DEFAULT NULL , 
 formname varchar2(50) DEFAULT NULL , 
 defaultvalue varchar2(50) DEFAULT NULL , 
 querytext varchar2(50) DEFAULT NULL , 
 tempquey varchar2(50) DEFAULT NULL , 
 displaytitle number(4) DEFAULT '0' , 
 clearzero number(4) DEFAULT '0' , 
 titlestr varchar2(50) DEFAULT NULL , 
 width varchar2(50) DEFAULT NULL , 
 height varchar2(50) DEFAULT NULL , 
 widthunit varchar2(50) DEFAULT NULL , 
 heightunit varchar2(50) DEFAULT NULL , 
 defheight varchar2(50) DEFAULT NULL , 
 defwidth varchar2(50) DEFAULT NULL , 
 neckwidth varchar2(50) DEFAULT NULL , 
 neckheight varchar2(50) DEFAULT NULL , 
 extparam varchar2(50) DEFAULT NULL , 
 marginright varchar2(50) DEFAULT NULL , 
 colorstr varchar2(50) DEFAULT NULL , 
 sstart varchar2(50) DEFAULT NULL , 
 send varchar2(50) DEFAULT NULL , 
 rowformatstr varchar2(50) DEFAULT NULL , 
 colformatstr varchar2(50) DEFAULT NULL , 
 publishtype varchar2(50) DEFAULT NULL , 
 editview varchar2(50) DEFAULT NULL , 
 expandbtm number(4) DEFAULT '0' , 
 expandrgt number(4) DEFAULT '0' , 
 curtab varchar2(50) DEFAULT NULL , 
 hiddencolstr varchar2(50) DEFAULT NULL , 
 eventstr varchar2(50) DEFAULT NULL , 
 dsmodel varchar2(50) DEFAULT NULL , 
 html clob , 
 sqldialect varchar2(255) DEFAULT NULL , 
 pagesize number(11) DEFAULT NULL , 
 isloadfulldata varchar2(50) DEFAULT NULL , 
 isexport number(4) DEFAULT '0' , 
 selectdata number(4) DEFAULT '0' , 
 exporttitle varchar2(50) DEFAULT NULL , 
 colsize number(11) DEFAULT NULL , 
 sorttype varchar2(50) DEFAULT NULL , 
 sortname varchar2(50) DEFAULT NULL , 
 mid varchar2(32) DEFAULT NULL , 
 parentid varchar2(32) DEFAULT NULL , 
 templetid varchar2(32) DEFAULT NULL , 
 colspan number(11) DEFAULT NULL , 
 colindex number(11) DEFAULT NULL , 
 chartcontent clob , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_reportmodel 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_role 
-- ---------------------------- 
DROP TABLE  uk_role; 
CREATE TABLE uk_role ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 ORGID varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_role 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_role_auth 
-- ---------------------------- 
DROP TABLE  uk_role_auth; 
CREATE TABLE uk_role_auth ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 ROLEID varchar2(32) DEFAULT NULL , 
 DICID varchar2(32) DEFAULT NULL , 
 DICVALUE varchar2(30) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_role_auth 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_sales_product 
-- ---------------------------- 
DROP TABLE  uk_sales_product; 
CREATE TABLE uk_sales_product ( 
 id varchar2(32) NOT NULL , 
 title varchar2(255) DEFAULT NULL , 
 content clob , 
 keyword varchar2(100) DEFAULT NULL , 
 summary varchar2(255) DEFAULT NULL , 
 status varchar2(32) DEFAULT NULL , 
 tptype varchar2(32) DEFAULT NULL , 
 cate varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 memo varchar2(32) DEFAULT NULL , 
 price number(11) DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_sales_product 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_sales_product_type 
-- ---------------------------- 
DROP TABLE  uk_sales_product_type; 
CREATE TABLE uk_sales_product_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_sales_product_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_secret 
-- ---------------------------- 
DROP TABLE  uk_secret; 
CREATE TABLE uk_secret ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime varchar2(32) DEFAULT NULL , 
 password varchar2(100) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 model varchar2(32) DEFAULT NULL , 
 enable number(4) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_secret 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_servicesummary 
-- ---------------------------- 
DROP TABLE  uk_servicesummary; 
CREATE TABLE uk_servicesummary ( 
 id varchar2(32) NOT NULL , 
 agentusername varchar2(100) DEFAULT NULL , 
 agentno varchar2(32) DEFAULT NULL , 
 status varchar2(32) DEFAULT NULL , 
 times number(11) DEFAULT NULL , 
 servicetime date DEFAULT NULL , 
 orgi varchar2(50) DEFAULT NULL , 
 username varchar2(100) DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 channel varchar2(32) DEFAULT NULL , 
 logindate date DEFAULT NULL , 
 servicetype clob , 
 reservation number(4) DEFAULT NULL , 
 reservtype varchar2(32) DEFAULT NULL , 
 reservtime date DEFAULT NULL , 
 email varchar2(100) DEFAULT NULL , 
 phonenumber varchar2(32) DEFAULT NULL , 
 summary clob , 
 agentserviceid varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 statuseventid varchar2(50) DEFAULT NULL , 
 contactsid varchar2(50) DEFAULT NULL , 
 ani varchar2(50) DEFAULT NULL , 
 caller varchar2(50) DEFAULT NULL , 
 called varchar2(50) DEFAULT NULL , 
 agent varchar2(50) DEFAULT NULL , 
 process number(4) DEFAULT NULL , 
 updateuser varchar2(32) DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 processmemo clob , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_servicesummary 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_sessionconfig 
-- ---------------------------- 
DROP TABLE  uk_sessionconfig; 
CREATE TABLE uk_sessionconfig ( 
 id varchar2(32) NOT NULL,
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 name varchar2(32) DEFAULT NULL , 
 sessionmsg varchar2(255) DEFAULT NULL , 
 distribution varchar2(32) DEFAULT NULL , 
 timeoutmsg varchar2(255) DEFAULT NULL , 
 retimeoutmsg varchar2(255) DEFAULT NULL , 
 satisfaction number(4) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 lastagent number(4) DEFAULT NULL , 
 sessiontimeout number(4) DEFAULT NULL , 
 resessiontimeout number(4) DEFAULT NULL , 
 timeout number(11) DEFAULT NULL , 
 retimeout number(11) DEFAULT NULL , 
 agenttimeout number(11) DEFAULT NULL , 
 agentreplaytimeout number(4) DEFAULT NULL , 
 agenttimeoutmsg varchar2(255) DEFAULT NULL , 
 maxuser number(11) DEFAULT NULL , 
 initmaxuser number(11) DEFAULT NULL , 
 workinghours clob , 
 notinwhmsg clob , 
 hourcheck number(4) DEFAULT NULL , 
 noagentmsg varchar2(255) DEFAULT NULL , 
 agentbusymsg varchar2(255) DEFAULT NULL , 
 successmsg varchar2(255) DEFAULT NULL , 
 finessmsg varchar2(255) DEFAULT NULL , 
 quality number(4) DEFAULT '0' , 
 qualityscore varchar2(20) DEFAULT NULL , 
 servicetimeoutlimit number(4) DEFAULT '0' , 
 servicetimeout number(11) DEFAULT '0' , 
 servicetimeoutmsg varchar2(50) DEFAULT '0' , 
 quenetimeout number(11) DEFAULT '600' ,
 quenetimeoutmsg varchar2(255) DEFAULT NULL , 
 quene number(4) DEFAULT '0' , 
 servicename varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_sessionconfig 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_skill 
-- ---------------------------- 
DROP TABLE  uk_skill; 
CREATE TABLE uk_skill ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_skill 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_snsaccount 
-- ---------------------------- 
DROP TABLE  uk_snsaccount; 
CREATE TABLE uk_snsaccount ( 
 authorizeURL varchar2(255) DEFAULT NULL , 
 accessTokenURL varchar2(255) DEFAULT NULL , 
 baseURL varchar2(255) DEFAULT NULL , 
 redirectURI varchar2(255) DEFAULT NULL , 
 clientSERCRET varchar2(192) DEFAULT NULL , 
 clientID varchar2(96) DEFAULT NULL , 
 ID varchar2(96) NOT NULL , 
 states varchar2(32) DEFAULT NULL , 
 region varchar2(32) DEFAULT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 CODE varchar2(255) DEFAULT NULL , 
 USERNAME varchar2(255) DEFAULT NULL , 
 APIPOINT varchar2(255) DEFAULT NULL , 
 PASSWORD varchar2(255) DEFAULT NULL , 
 SNSTYPE varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 ACCOUNT varchar2(255) DEFAULT NULL , 
 ALLOWREMOTE varchar2(255) DEFAULT NULL , 
 EMAIL varchar2(255) DEFAULT NULL , 
 USERNO varchar2(255) DEFAULT NULL , 
 TOKEN varchar2(255) DEFAULT NULL , 
 APPKEY varchar2(255) DEFAULT NULL , 
 SECRET varchar2(255) DEFAULT NULL , 
 AESKEY varchar2(255) DEFAULT NULL , 
 APPTOKEN varchar2(255) DEFAULT NULL , 
 SESSIONKEY varchar2(255) DEFAULT NULL , 
 MOREPARAM varchar2(255) DEFAULT NULL , 
 ORGI varchar2(255) DEFAULT NULL , 
 DEFAULTACCOUNT NUMBER(6) DEFAULT NULL , 
 lastatupdate varchar2(96) DEFAULT NULL , 
 lastprimsgupdate varchar2(96) DEFAULT NULL , 
 ACCTYPE varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 create_time date DEFAULT NULL , 
 update_username varchar2(255) DEFAULT NULL , 
 update_time date DEFAULT NULL , 
 update_user varchar2(255) DEFAULT NULL , 
 shares varchar2(255) DEFAULT NULL , 
 owner varchar2(255) DEFAULT NULL , 
 assignedto varchar2(255) DEFAULT NULL , 
 wfstatus varchar2(255) DEFAULT NULL , 
 datadept varchar2(255) DEFAULT NULL , 
 batid varchar2(32) DEFAULT NULL , 
 alias varchar2(50) DEFAULT NULL , 
 authaccesstoken varchar2(255) DEFAULT NULL , 
 expirestime number(11) DEFAULT NULL , 
 headimg varchar2(255) DEFAULT NULL , 
 oepnscan varchar2(100) DEFAULT NULL , 
 opencard varchar2(100) DEFAULT NULL , 
 openstore varchar2(100) DEFAULT NULL , 
 openpay varchar2(100) DEFAULT NULL , 
 openshake varchar2(100) DEFAULT NULL , 
 qrcode varchar2(100) DEFAULT NULL , 
 refreshtoken varchar2(255) DEFAULT NULL , 
 verify varchar2(255) DEFAULT NULL , 
 snsid varchar2(32) DEFAULT NULL , 
 agent number(4) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_snsaccount 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_sysdic 
-- ---------------------------- 
DROP TABLE  uk_sysdic; 
CREATE TABLE uk_sysdic ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(100) DEFAULT NULL , 
 TITLE varchar2(100) DEFAULT NULL , 
 CODE varchar2(100) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 CTYPE varchar2(32) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 DESCRIPTION varchar2(255) DEFAULT NULL , 
 MEMO varchar2(32) DEFAULT NULL , 
 ICONSTR varchar2(255) DEFAULT NULL , 
 ICONSKIN varchar2(255) DEFAULT NULL , 
 CATETYPE varchar2(32) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 HASCHILD number(4) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 DICID varchar2(32) DEFAULT NULL , 
 DEFAULTVALUE number(4) DEFAULT NULL , 
 DISCODE number(4) DEFAULT NULL , 
 URL varchar2(255) DEFAULT NULL , 
 MODULE varchar2(32) DEFAULT NULL , 
 MLEVEL varchar2(32) DEFAULT NULL , 
 RULES varchar2(100) DEFAULT NULL , 
 MENUTYPE varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Table structure for uk_systemconfig 
-- ---------------------------- 
DROP TABLE  uk_systemconfig; 
CREATE TABLE uk_systemconfig ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(100) DEFAULT NULL , 
 TITLE varchar2(100) DEFAULT NULL , 
 CODE varchar2(100) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 CTYPE varchar2(32) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 DESCRIPTION varchar2(255) DEFAULT NULL , 
 MEMO varchar2(32) DEFAULT NULL , 
 ICONSTR varchar2(255) DEFAULT NULL , 
 ICONSKIN varchar2(255) DEFAULT NULL , 
 CATETYPE varchar2(32) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 HASCHILD number(4) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 DICID varchar2(32) DEFAULT NULL , 
 DEFAULTVALUE number(4) DEFAULT NULL , 
 THEME varchar2(50) DEFAULT NULL , 
 LOGLEVEL varchar2(32) DEFAULT NULL , 
 ENABLESSL number(4) DEFAULT NULL , 
 JKSFILE varchar2(255) DEFAULT NULL , 
 JKSPASSWORD varchar2(255) DEFAULT NULL , 
 MAPKEY varchar2(255) DEFAULT NULL , 
 workorders number(4) DEFAULT NULL , 
 callcenter number(4) DEFAULT NULL , 
 cc_extention varchar2(32) DEFAULT NULL , 
 cc_quene varchar2(32) DEFAULT NULL , 
 cc_router varchar2(32) DEFAULT NULL , 
 cc_ivr varchar2(32) DEFAULT NULL , 
 cc_acl varchar2(32) DEFAULT NULL , 
 cc_siptrunk varchar2(32) DEFAULT NULL , 
 cc_callcenter varchar2(32) DEFAULT NULL , 
 CALLOUT number(4) DEFAULT NULL , 
 AUTH number(4) DEFAULT NULL , 
 enablemail number(4) DEFAULT '0' , 
 enablesms number(4) DEFAULT '0' , 
 emailid varchar2(32) DEFAULT NULL , 
 emailworkordertp varchar2(32) DEFAULT NULL , 
 smsid varchar2(32) DEFAULT NULL , 
 smsworkordertp varchar2(32) DEFAULT NULL , 
 mailcreatetp varchar2(32) DEFAULT NULL , 
 mailupdatetp varchar2(32) DEFAULT NULL , 
 mailprocesstp varchar2(32) DEFAULT NULL , 
 emailtocreater number(4) DEFAULT '0' , 
 emailshowrecipient number(4) DEFAULT '0' , 
 smscreatetp varchar2(32) DEFAULT NULL , 
 smsupdatetp varchar2(32) DEFAULT NULL , 
 smsprocesstp varchar2(32) DEFAULT NULL , 
 smstocreater number(4) DEFAULT '0' , 
 emailtocreatertp varchar2(32) DEFAULT NULL , 
 smstocreatertp varchar2(32) DEFAULT NULL , 
 enabletneant number(4) DEFAULT '0' , 
 tenantshare number(4) DEFAULT '0' , 
 namealias varchar2(100) DEFAULT NULL , 
 enableregorgi number(4) DEFAULT '0' , 
 tenantconsole number(4) DEFAULT '0'  
); 
 
-- ---------------------------- 
-- Records of uk_systemconfig 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_system_message 
-- ---------------------------- 
DROP TABLE  uk_system_message; 
CREATE TABLE uk_system_message ( 
 ID varchar2(32) NOT NULL , 
 MSGTYPE varchar2(20) DEFAULT NULL , 
 SMTPSERVER varchar2(255) DEFAULT NULL , 
 SMTPUSER varchar2(255) DEFAULT NULL , 
 SMTPPASSWORD varchar2(255) DEFAULT NULL , 
 MAILFROM varchar2(255) DEFAULT NULL , 
 SECLEV varchar2(50) DEFAULT NULL , 
 SSLPORT varchar2(50) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 URL varchar2(255) DEFAULT NULL , 
 smstype varchar2(32) DEFAULT NULL , 
 APPKEY varchar2(200) DEFAULT NULL , 
 APPSEC varchar2(200) DEFAULT NULL , 
 SIGN varchar2(50) DEFAULT NULL , 
 TPCODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 moreparam clob , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_system_message 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_tableproperties 
-- ---------------------------- 
DROP TABLE  uk_tableproperties; 
CREATE TABLE uk_tableproperties ( 
 ID varchar2(32) NOT NULL,
 NAME varchar2(255) DEFAULT NULL , 
 CODE varchar2(255) DEFAULT NULL , 
 GROUPID varchar2(255) DEFAULT NULL , 
 USERID varchar2(255) DEFAULT NULL , 
 FIELDNAME varchar2(255) DEFAULT NULL , 
 DATATYPECODE number(11) NOT NULL , 
 DATATYPENAME varchar2(255) DEFAULT NULL , 
 DBTABLEID varchar2(255) DEFAULT NULL , 
 INDEXDATATYPE varchar2(255) DEFAULT NULL , 
 PK NUMBER(6) DEFAULT NULL , 
 MODITS NUMBER(6) DEFAULT NULL , 
 INDEXFIELD varchar2(32) DEFAULT NULL , 
 PLUGIN varchar2(32) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 FKTABLE varchar2(32) DEFAULT NULL , 
 FKPROPERTY varchar2(32) DEFAULT NULL , 
 TABLENAME varchar2(255) DEFAULT NULL , 
 viewtype varchar2(255) DEFAULT NULL , 
 SORTINDEX number(11) DEFAULT NULL , 
 SYSTEMFIELD number(4) DEFAULT NULL , 
 INX number(4) DEFAULT NULL , 
 TOKEN number(4) DEFAULT NULL , 
 LENGTH number(11) DEFAULT NULL , 
 FIELDSTATUS number(4) DEFAULT NULL , 
 SELDATA number(4) DEFAULT NULL , 
 SELDATACODE varchar2(32) DEFAULT NULL , 
 SELDATAKEY varchar2(32) DEFAULT NULL , 
 SELDATAVALUE varchar2(32) DEFAULT NULL , 
 SELDATATYPE varchar2(32) DEFAULT NULL , 
 REFTBID varchar2(32) DEFAULT NULL , 
 REFTPID varchar2(32) DEFAULT NULL , 
 REFTYPE varchar2(32) DEFAULT NULL , 
 REFTBNAME varchar2(60) DEFAULT NULL , 
 REFTPNAME varchar2(60) DEFAULT NULL , 
 REFTPTITLEFIELD varchar2(60) DEFAULT NULL , 
 REFFK number(4) DEFAULT NULL , 
 DEFAULTSORT number(4) DEFAULT NULL , 
 DEFAULTVALUE varchar2(255) DEFAULT NULL , 
 DEFAULTVALUETITLE varchar2(255) DEFAULT NULL , 
 DEFAULTFIELDVALUE varchar2(255) DEFAULT NULL , 
 MULTPARTFILE number(4) DEFAULT NULL , 
 UPLOADTYPE varchar2(255) DEFAULT NULL , 
 cascadetype varchar2(255) DEFAULT NULL , 
 title number(4) DEFAULT NULL , 
 DESCORDER number(4) DEFAULT NULL , 
 impfield number(4) DEFAULT '0' , 
 tokentype varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_tableproperties 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_tabletask 
-- ---------------------------- 
DROP TABLE  uk_tabletask; 
CREATE TABLE uk_tabletask ( 
 ID varchar2(32) NOT NULL,
 NAME varchar2(255) DEFAULT NULL , 
 SECURE varchar2(255) DEFAULT NULL , 
 TASKSTATUS varchar2(255) DEFAULT NULL , 
 TABLEDIRID varchar2(255) DEFAULT NULL , 
 DBID varchar2(255) DEFAULT NULL , 
 CODE varchar2(255) DEFAULT NULL , 
 GROUPID varchar2(255) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 CREATERNAME varchar2(255) DEFAULT NULL , 
 TASKTYPE varchar2(255) DEFAULT NULL , 
 TASKNAME varchar2(255) DEFAULT NULL , 
 TASKPLAN varchar2(255) DEFAULT NULL , 
 CONFIGURE varchar2(255) DEFAULT NULL , 
 SECURECONF varchar2(255) DEFAULT NULL , 
 USERID varchar2(255) DEFAULT NULL , 
 PREVIEWTEMPLET clob , 
 LISTBLOCKTEMPLET clob , 
 TABLENAME varchar2(255) DEFAULT NULL , 
 TABLETYPE varchar2(255) DEFAULT NULL , 
 STARTINDEX number(11) NOT NULL , 
 UPDATETIME date DEFAULT NULL , 
 UPDATETIMENUMBER number(11) NOT NULL , 
 DATASQL clob , 
 DATABASETASK varchar2(32) DEFAULT NULL , 
 DRIVERPLUGIN varchar2(32) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 WORKFLOW number(10) DEFAULT NULL , 
 FROMDB number(4) DEFAULT NULL , 
 tabtype varchar2(32) DEFAULT NULL , 
 pid varchar2(32) DEFAULT NULL , 
 secmenuid varchar2(32) DEFAULT NULL , 
 reportid varchar2(32) DEFAULT NULL , 
 eventname varchar2(32) DEFAULT NULL , 
 tltemplet varchar2(32) DEFAULT NULL , 
 timeline number(4) DEFAULT NULL , 
 tbversion number(11) DEFAULT NULL , 
 LASTUPDATE date DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_tabletask 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_tag 
-- ---------------------------- 
DROP TABLE  uk_tag; 
CREATE TABLE uk_tag ( 
 id varchar2(32) NOT NULL , 
 tag varchar2(100) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 times number(11) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 tagtype varchar2(32) DEFAULT NULL , 
 icon varchar2(50) DEFAULT NULL , 
 color varchar2(10) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_tag 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_tagrelation 
-- ---------------------------- 
DROP TABLE  uk_tagrelation; 
CREATE TABLE uk_tagrelation ( 
 id varchar2(32) NOT NULL , 
 tagid varchar2(32) DEFAULT NULL , 
 userid varchar2(32) DEFAULT NULL , 
 dataid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_tagrelation 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_templet 
-- ---------------------------- 
DROP TABLE  uk_templet; 
CREATE TABLE uk_templet ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(255) DEFAULT NULL , 
 DESCRIPTION clob , 
 CODE varchar2(255) DEFAULT NULL , 
 GROUPID varchar2(255) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 USERID varchar2(255) DEFAULT NULL , 
 TEMPLETTITLE varchar2(500) DEFAULT NULL , 
 TEMPLETTEXT clob , 
 TEMPLETTYPE varchar2(255) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 ICONSTR varchar2(255) DEFAULT NULL , 
 MEMO varchar2(255) DEFAULT NULL , 
 ORDERINDEX number(11) DEFAULT NULL , 
 TYPEID varchar2(32) DEFAULT NULL , 
 SELDATA number(4) DEFAULT NULL , 
 layoutcols number(11) DEFAULT '0' , 
 datatype varchar2(32) DEFAULT NULL , 
 charttype varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Table structure for uk_tenant 
-- ---------------------------- 
DROP TABLE  uk_tenant; 
CREATE TABLE uk_tenant ( 
 id varchar2(32) NOT NULL,
 datasourceid varchar2(32) DEFAULT NULL , 
 tenantname varchar2(255) DEFAULT NULL , 
 tenantcode varchar2(255) DEFAULT NULL , 
 remark varchar2(200) DEFAULT NULL , 
 lastmenutime date DEFAULT NULL , 
 lastbasetime date DEFAULT NULL , 
 tenantlogo varchar2(255) DEFAULT NULL , 
 tenantvalid varchar2(10) DEFAULT NULL , 
 genpasstype varchar2(255) DEFAULT NULL , 
 password varchar2(255) DEFAULT NULL , 
 adminuser varchar2(255) DEFAULT NULL , 
 orgid varchar2(32) DEFAULT NULL , 
 initdb number(4) DEFAULT '0' , 
 inites number(4) DEFAULT NULL , 
 inited number(4) DEFAULT '0' , 
 systemtenant number(4) DEFAULT '0' , 
 createtime date DEFAULT NULL , 
 sign clob , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_tenant 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_user 
-- ---------------------------- 
DROP TABLE  uk_user; 
CREATE TABLE uk_user ( 
 ID varchar2(32) NOT NULL , 
 LANGUAGE varchar2(255) DEFAULT NULL , 
 USERNAME varchar2(255) DEFAULT NULL , 
 PASSWORD varchar2(255) DEFAULT NULL , 
 SECURECONF varchar2(255) DEFAULT NULL , 
 EMAIL varchar2(255) DEFAULT NULL , 
 FIRSTNAME varchar2(255) DEFAULT NULL , 
 MIDNAME varchar2(255) DEFAULT NULL , 
 LASTNAME varchar2(255) DEFAULT NULL , 
 JOBTITLE varchar2(255) DEFAULT NULL , 
 DEPARTMENT varchar2(255) DEFAULT NULL , 
 GENDER varchar2(255) DEFAULT NULL , 
 BIRTHDAY varchar2(255) DEFAULT NULL , 
 NICKNAME varchar2(255) DEFAULT NULL , 
 USERTYPE varchar2(255) DEFAULT NULL , 
 RULENAME varchar2(255) DEFAULT NULL , 
 SEARCHPROJECTID varchar2(255) DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 ORGID varchar2(32) DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 MEMO varchar2(255) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGAN varchar2(32) DEFAULT NULL , 
 MOBILE varchar2(32) DEFAULT NULL , 
 passupdatetime date DEFAULT NULL , 
 sign clob , 
 del number(4) DEFAULT '0' , 
 uname varchar2(100) DEFAULT NULL , 
 musteditpassword number(4) DEFAULT NULL , 
 AGENT number(4) DEFAULT NULL , 
 SKILL varchar2(32) DEFAULT NULL , 
 province varchar2(50) DEFAULT NULL , 
 city varchar2(50) DEFAULT NULL , 
 fans number(11) DEFAULT NULL , 
 follows number(11) DEFAULT NULL , 
 integral number(11) DEFAULT NULL , 
 lastlogintime date DEFAULT NULL , 
 status varchar2(10) DEFAULT NULL , 
 deactivetime date DEFAULT NULL , 
 title varchar2(50) DEFAULT NULL , 
 DATASTATUS number(4) DEFAULT NULL , 
 callcenter number(4) DEFAULT NULL , 
 SUPERUSER number(4) DEFAULT NULL , 
 maxuser number(11) DEFAULT '0' , 
 ordertype varchar2(20) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Table structure for uk_userevent 
-- ---------------------------- 
DROP TABLE  uk_userevent; 
CREATE TABLE uk_userevent ( 
 id varchar2(32) NOT NULL,
 username varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 orgi varchar2(50) DEFAULT NULL , 
 maintype varchar2(32) DEFAULT NULL , 
 subtype varchar2(32) DEFAULT NULL , 
 name varchar2(32) DEFAULT NULL , 
 admin number(32) DEFAULT NULL , 
 accessnum number(32) DEFAULT NULL , 
 ip varchar2(50) DEFAULT NULL , 
 hostname varchar2(50) DEFAULT NULL , 
 country varchar2(50) DEFAULT NULL , 
 region varchar2(50) DEFAULT NULL , 
 city varchar2(32) DEFAULT NULL , 
 isp varchar2(32) DEFAULT NULL , 
 province varchar2(32) DEFAULT NULL , 
 url varchar2(255) DEFAULT NULL , 
 sessionid varchar2(32) DEFAULT NULL , 
 param clob , 
 times number(11) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 ostype varchar2(100) DEFAULT NULL , 
 browser varchar2(50) DEFAULT NULL , 
 mobile varchar2(10) DEFAULT NULL , 
 model varchar2(10) DEFAULT NULL , 
 appid varchar2(32) DEFAULT NULL , 
 createdate varchar2(32) DEFAULT NULL , 
 referer varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_userevent 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_userrole 
-- ---------------------------- 
DROP TABLE  uk_userrole; 
CREATE TABLE uk_userrole ( 
 id varchar2(32) NOT NULL , 
 user_id varchar2(32) DEFAULT NULL , 
 role_id varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_userrole 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_webim_monitor 
-- ---------------------------- 
DROP TABLE  uk_webim_monitor; 
CREATE TABLE uk_webim_monitor ( 
 ID varchar2(50) NOT NULL , 
 ORGI varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 AGENTS number(11) DEFAULT NULL , 
 USERS number(11) DEFAULT NULL , 
 INQUENE number(11) DEFAULT NULL , 
 BUSY number(11) DEFAULT NULL , 
 TYPE varchar2(32) DEFAULT NULL , 
 DATESTR varchar2(32) DEFAULT NULL , 
 HOURSTR varchar2(32) DEFAULT NULL , 
 DATEHOURSTR varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_webim_monitor 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_weixinuser 
-- ---------------------------- 
DROP TABLE  uk_weixinuser; 
CREATE TABLE uk_weixinuser ( 
 id varchar2(32) NOT NULL , 
 snsid varchar2(32) DEFAULT NULL , 
 subscribe number(4) DEFAULT NULL , 
 openid varchar2(100) DEFAULT NULL , 
 nickname varchar2(50) DEFAULT NULL , 
 sex varchar2(50) DEFAULT NULL , 
 language varchar2(50) DEFAULT NULL , 
 city varchar2(50) DEFAULT NULL , 
 province varchar2(50) DEFAULT NULL , 
 country varchar2(50) DEFAULT NULL , 
 headimgurl varchar2(255) DEFAULT NULL , 
 subscribetime varchar2(32) DEFAULT NULL , 
 unionid varchar2(50) DEFAULT NULL , 
 sexid varchar2(50) DEFAULT NULL , 
 remark varchar2(100) DEFAULT NULL , 
 groupid varchar2(50) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 contactsid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_weixinuser 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_workorders 
-- ---------------------------- 
DROP TABLE  uk_workorders; 
CREATE TABLE uk_workorders ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PARENT varchar2(32) DEFAULT NULL , 
 ORDERNO number(11) DEFAULT NULL , 
 SESSIONID varchar2(32) DEFAULT NULL , 
 TITLE varchar2(255) DEFAULT NULL , 
 CONTENT clob , 
 PRICE number(11) DEFAULT NULL , 
 KEYWORD varchar2(255) DEFAULT NULL , 
 SUMMARY varchar2(255) DEFAULT NULL , 
 ANONYMOUS number(4) DEFAULT NULL , 
 TOP number(4) DEFAULT NULL , 
 ESSENCE number(4) DEFAULT NULL , 
 ACCEPT number(4) DEFAULT NULL , 
 FINISH number(4) DEFAULT NULL , 
 ANSWERS number(11) DEFAULT NULL , 
 sviews number(11) DEFAULT NULL , 
 FOLLOWERS number(11) DEFAULT NULL , 
 COLLECTIONS number(11) DEFAULT NULL , 
 COMMENTS number(11) DEFAULT NULL , 
 MOBILE number(4) DEFAULT NULL , 
 STATUS varchar2(32) DEFAULT NULL , 
 WOTYPE varchar2(32) DEFAULT NULL , 
 DATASTATUS number(4) DEFAULT NULL , 
 CATE varchar2(32) DEFAULT NULL , 
 PRIORITY varchar2(32) DEFAULT NULL , 
 CONTACTS varchar2(32) DEFAULT NULL , 
 CUSID varchar2(32) DEFAULT NULL , 
 INITIATOR clob , 
 BPMID varchar2(32) DEFAULT NULL , 
 TAGS varchar2(255) DEFAULT NULL , 
 ACCDEPT varchar2(32) DEFAULT NULL , 
 ACCUSER varchar2(32) DEFAULT NULL , 
 ASSIGNED number(4) DEFAULT NULL , 
 ORGAN varchar2(32) DEFAULT NULL , 
 AGENT varchar2(32) DEFAULT NULL , 
 SHARES clob , 
 SKILL varchar2(32) DEFAULT NULL , 
 ROWCOUNT number(11) DEFAULT NULL , 
 KEY varchar2(32) DEFAULT NULL , 
 MEMO varchar2(100) DEFAULT NULL , 
 frommobile number(4) DEFAULT '0' , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_workorders 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_workorder_type 
-- ---------------------------- 
DROP TABLE  uk_workorder_type; 
CREATE TABLE uk_workorder_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 BPM number(4) DEFAULT NULL , 
 PROCESSID varchar2(32) DEFAULT NULL , 
 SLA number(4) DEFAULT NULL , 
 SLAID varchar2(32) DEFAULT NULL , 
 PARENTID varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_workorder_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_worktime 
-- ---------------------------- 
DROP TABLE  uk_worktime; 
CREATE TABLE uk_worktime ( 
 id varchar2(32) NOT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 name varchar2(100) DEFAULT NULL , 
 orgi varchar2(100) DEFAULT NULL , 
 hostid varchar2(32) DEFAULT NULL , 
 type varchar2(32) DEFAULT NULL , 
 day varchar2(100) DEFAULT NULL , 
 begintime varchar2(20) DEFAULT NULL , 
 endtime varchar2(20) DEFAULT NULL , 
 timetype varchar2(10) DEFAULT NULL , 
 wfrom number(11) DEFAULT NULL , 
 wto number(11) DEFAULT NULL , 
 dfrom number(11) DEFAULT NULL , 
 dto number(11) DEFAULT NULL , 
 wbegintime varchar2(20) DEFAULT NULL , 
 wendtime varchar2(20) DEFAULT NULL , 
 dbegintime varchar2(20) DEFAULT NULL , 
 dendtime varchar2(20) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_worktime 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_work_monitor 
-- ---------------------------- 
DROP TABLE  uk_work_monitor; 
CREATE TABLE uk_work_monitor ( 
 ID varchar2(50) NOT NULL , 
 USERID varchar2(50) DEFAULT NULL , 
 AGENT varchar2(50) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 AGENTNO varchar2(50) DEFAULT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 STATUS varchar2(50) DEFAULT NULL , 
 BUSY number(4) DEFAULT '0' , 
 WORKSTATUS varchar2(50) DEFAULT NULL , 
 ORGI varchar2(50) DEFAULT NULL , 
 AGENTSERVICEID varchar2(50) DEFAULT NULL , 
 SKILL varchar2(50) DEFAULT NULL , 
 SKILLNAME varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 ANI varchar2(50) DEFAULT NULL , 
 CALLED varchar2(50) DEFAULT NULL , 
 SOURCE varchar2(50) DEFAULT NULL , 
 SERVICEID varchar2(32) DEFAULT NULL , 
 SERVICESTATUS varchar2(50) DEFAULT NULL , 
 DISCALLER varchar2(50) DEFAULT NULL , 
 DISCALLED varchar2(50) DEFAULT NULL , 
 ORGAN varchar2(50) DEFAULT NULL , 
 BEGINTIME date DEFAULT NULL , 
 ENDTIME date DEFAULT NULL , 
 FIRSTSTATUS number(4) DEFAULT '0' , 
 DATESTR varchar2(20) DEFAULT NULL , 
 DURATION number(11) DEFAULT NULL , 
 EVENTID varchar2(50) DEFAULT NULL , 
 WORKTYPE varchar2(32) DEFAULT NULL , 
 CALLENDTIME date DEFAULT NULL , 
 CALLSTARTTIME date DEFAULT NULL , 
 DIRECTION varchar2(50) DEFAULT NULL , 
 EXTNO varchar2(32) DEFAULT NULL , 
 ADMIN number(4) DEFAULT '0' , 
 firsttime number(4) DEFAULT '0' , 
 firsttimes number(11) DEFAULT '0' , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_work_monitor 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_wxmpevent 
-- ---------------------------- 
DROP TABLE  uk_wxmpevent; 
CREATE TABLE uk_wxmpevent ( 
 id varchar2(32) NOT NULL,
 fromuser varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 orgi varchar2(50) DEFAULT NULL , 
 country varchar2(50) DEFAULT NULL , 
 city varchar2(32) DEFAULT NULL , 
 province varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 event varchar2(255) DEFAULT NULL , 
 channel varchar2(255) DEFAULT NULL , 
 model varchar2(10) DEFAULT NULL , 
 appid varchar2(32) DEFAULT NULL , 
 snsid varchar2(32) DEFAULT NULL  
); 
 
-- ---------------------------- 
-- Records of uk_wxmpevent 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_config 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_config; 
CREATE TABLE uk_xiaoe_config ( 
 id varchar2(32) NOT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 name varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 enableask number(4) DEFAULT NULL , 
 askfirst number(4) DEFAULT NULL , 
 enablescene number(4) DEFAULT NULL , 
 scenefirst number(4) DEFAULT NULL , 
 enablekeyword number(4) DEFAULT NULL , 
 keywordnum number(11) DEFAULT NULL , 
 noresultmsg clob , 
 askqs number(4) DEFAULT NULL , 
 asktipmsg varchar2(255) DEFAULT NULL , 
 resolved varchar2(100) DEFAULT NULL , 
 unresolved varchar2(100) DEFAULT NULL , 
 redirectagent number(4) DEFAULT NULL , 
 redirecturl varchar2(255) DEFAULT NULL , 
 asktimes number(11) DEFAULT NULL , 
 selectskill number(11) DEFAULT NULL , 
 selectskillmsg varchar2(255) DEFAULT NULL , 
 aiid varchar2(32) , 
 welcomemsg clob , 
 waitmsg clob , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_config 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_kbs_type 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_kbs_type; 
CREATE TABLE uk_xiaoe_kbs_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 area clob , 
 parentid varchar2(32) DEFAULT '0' , 
 typeid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_kbs_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_scene 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_scene; 
CREATE TABLE uk_xiaoe_scene ( 
 id varchar2(32) NOT NULL , 
 sessionid varchar2(32) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 content clob , 
 keyword varchar2(100) DEFAULT NULL , 
 summary varchar2(255) DEFAULT NULL , 
 anonymous number(4) DEFAULT NULL , 
 begintime date DEFAULT NULL , 
 endtime date DEFAULT NULL , 
 top number(4) DEFAULT NULL , 
 essence number(4) DEFAULT NULL , 
 accept number(4) DEFAULT NULL , 
 finish number(4) DEFAULT NULL , 
 answers number(11) DEFAULT NULL , 
 sviews number(11) DEFAULT NULL , 
 followers number(11) DEFAULT NULL , 
 collections number(11) DEFAULT NULL , 
 COMMENTs number(11) DEFAULT NULL , 
 mobile number(4) DEFAULT NULL , 
 status varchar2(32) DEFAULT NULL , 
 tptype varchar2(32) DEFAULT NULL , 
 cate varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 memo varchar2(32) DEFAULT NULL , 
 price number(11) DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 replaytype varchar2(32) DEFAULT NULL , 
 allowask number(4) DEFAULT NULL , 
 inputcon varchar2(255) DEFAULT NULL , 
 outputcon varchar2(255) DEFAULT NULL , 
 userinput clob , 
 aireply clob , 
 frommobile number(4) DEFAULT '0' , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_scene 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_sceneitem 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_sceneitem; 
CREATE TABLE uk_xiaoe_sceneitem ( 
 id varchar2(32) NOT NULL , 
 content varchar2(255) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 sceneid varchar2(32) DEFAULT NULL , 
 inx number(11) DEFAULT NULL , 
 itemtype varchar2(32) DEFAULT NULL , 
 replaytype varchar2(32) DEFAULT NULL , 
 allowask number(4) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_sceneitem 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_scene_type 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_scene_type; 
CREATE TABLE uk_xiaoe_scene_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 area clob , 
 parentid varchar2(32) DEFAULT '0' , 
 typeid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_scene_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_topic 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_topic; 
CREATE TABLE uk_xiaoe_topic ( 
 id varchar2(32) NOT NULL , 
 sessionid varchar2(32) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 content clob , 
 keyword varchar2(100) DEFAULT NULL , 
 summary varchar2(255) DEFAULT NULL , 
 anonymous number(4) DEFAULT NULL , 
 begintime date DEFAULT NULL , 
 endtime date DEFAULT NULL , 
 top number(4) DEFAULT NULL , 
 essence number(4) DEFAULT NULL , 
 accept number(4) DEFAULT NULL , 
 finish number(4) DEFAULT NULL , 
 answers number(11) DEFAULT NULL , 
 sviews varchar2(32) DEFAULT NULL , 
 followers number(11) DEFAULT NULL , 
 collections number(11) DEFAULT NULL , 
 COMMENTs number(11) DEFAULT NULL , 
 mobile number(4) DEFAULT NULL , 
 status varchar2(32) DEFAULT NULL , 
 tptype varchar2(32) DEFAULT NULL , 
 cate varchar2(32) DEFAULT NULL , 
 username varchar2(32) DEFAULT NULL , 
 orgi varchar2(32) DEFAULT NULL , 
 creater varchar2(32) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 updatetime date DEFAULT NULL , 
 memo varchar2(32) DEFAULT NULL , 
 price number(11) DEFAULT NULL , 
 organ varchar2(32) DEFAULT NULL , 
 sms varchar2(255) DEFAULT NULL , 
 tts varchar2(255) DEFAULT NULL , 
 email clob , 
 weixin clob , 
 silimar clob , 
 aiid varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_topic 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_topic_item 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_topic_item; 
CREATE TABLE uk_xiaoe_topic_item ( 
 id varchar2(32) NOT NULL,
 topicid varchar2(255) DEFAULT NULL , 
 title varchar2(255) DEFAULT NULL , 
 orgi varchar2(255) DEFAULT NULL , 
 creater varchar2(255) DEFAULT NULL , 
 createtime date DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_topic_item 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_words 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_words; 
CREATE TABLE uk_xiaoe_words ( 
 ID varchar2(32) NOT NULL , 
 KEYWORD varchar2(50) DEFAULT NULL , 
 CONTENT clob , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 SUPERORDINATE varchar2(50) DEFAULT NULL , 
 PARTOFSPEECH varchar2(50) DEFAULT NULL , 
 CATE varchar2(32) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_words 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for uk_xiaoe_words_type 
-- ---------------------------- 
DROP TABLE  uk_xiaoe_words_type; 
CREATE TABLE uk_xiaoe_words_type ( 
 ID varchar2(32) NOT NULL , 
 NAME varchar2(50) DEFAULT NULL , 
 CODE varchar2(50) DEFAULT NULL , 
 CREATETIME date DEFAULT NULL , 
 CREATER varchar2(32) DEFAULT NULL , 
 UPDATETIME date DEFAULT NULL , 
 ORGI varchar2(32) DEFAULT NULL , 
 USERNAME varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (ID) 
); 
 
-- ---------------------------- 
-- Records of uk_xiaoe_words_type 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_cc_order 
-- ---------------------------- 
DROP TABLE  wf_cc_order; 
CREATE TABLE wf_cc_order ( 
 order_Id varchar2(32) DEFAULT NULL , 
 actor_Id varchar2(50) DEFAULT NULL , 
 creator varchar2(50) DEFAULT NULL , 
 create_Time varchar2(50) DEFAULT NULL , 
 finish_Time varchar2(50) DEFAULT NULL , 
 status number(1) DEFAULT NULL  
); 
 
-- ---------------------------- 
-- Records of wf_cc_order 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_hist_order 
-- ---------------------------- 
DROP TABLE  wf_hist_order; 
CREATE TABLE wf_hist_order ( 
 id varchar2(32) NOT NULL , 
 process_Id varchar2(32) NOT NULL , 
 order_State number(1) NOT NULL , 
 creator varchar2(50) DEFAULT NULL , 
 create_Time varchar2(50) NOT NULL , 
 end_Time varchar2(50) DEFAULT NULL , 
 expire_Time varchar2(50) DEFAULT NULL , 
 priority number(1) DEFAULT NULL , 
 parent_Id varchar2(32) DEFAULT NULL , 
 order_No varchar2(50) DEFAULT NULL , 
 variable varchar2(2000) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of wf_hist_order 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_hist_task 
-- ---------------------------- 
DROP TABLE  wf_hist_task; 
CREATE TABLE wf_hist_task ( 
 id varchar2(32) NOT NULL , 
 order_Id varchar2(32) NOT NULL , 
 task_Name varchar2(100) NOT NULL , 
 display_Name varchar2(200) NOT NULL , 
 task_Type number(1) NOT NULL , 
 perform_Type number(1) DEFAULT NULL , 
 task_State number(1) NOT NULL , 
 operator varchar2(50) DEFAULT NULL , 
 create_Time varchar2(50) NOT NULL , 
 finish_Time varchar2(50) DEFAULT NULL , 
 expire_Time varchar2(50) DEFAULT NULL , 
 action_Url varchar2(200) DEFAULT NULL , 
 parent_Task_Id varchar2(32) DEFAULT NULL , 
 variable varchar2(2000) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of wf_hist_task 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_hist_task_actor 
-- ---------------------------- 
DROP TABLE  wf_hist_task_actor; 
CREATE TABLE wf_hist_task_actor ( 
 task_Id varchar2(32) NOT NULL , 
 actor_Id varchar2(50) NOT NULL  
); 
 
-- ---------------------------- 
-- Records of wf_hist_task_actor 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_order 
-- ---------------------------- 
DROP TABLE  wf_order; 
CREATE TABLE wf_order ( 
 id varchar2(32) NOT NULL , 
 parent_Id varchar2(32) DEFAULT NULL , 
 process_Id varchar2(32) NOT NULL , 
 creator varchar2(50) DEFAULT NULL , 
 create_Time varchar2(50) NOT NULL , 
 expire_Time varchar2(50) DEFAULT NULL , 
 last_Update_Time varchar2(50) DEFAULT NULL , 
 last_Updator varchar2(50) DEFAULT NULL , 
 priority number(1) DEFAULT NULL , 
 parent_Node_Name varchar2(100) DEFAULT NULL , 
 order_No varchar2(50) DEFAULT NULL , 
 variable varchar2(2000) DEFAULT NULL , 
 version number(3) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of wf_order 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_process 
-- ---------------------------- 
DROP TABLE  wf_process; 
CREATE TABLE wf_process ( 
 id varchar2(32) NOT NULL , 
 name varchar2(100) DEFAULT NULL , 
 display_Name varchar2(200) DEFAULT NULL , 
 type varchar2(100) DEFAULT NULL , 
 instance_Url varchar2(200) DEFAULT NULL , 
 state number(1) DEFAULT NULL , 
 content BLOB , 
 version number(2) DEFAULT NULL , 
 create_Time varchar2(50) DEFAULT NULL , 
 creator varchar2(50) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of wf_process 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_surrogate 
-- ---------------------------- 
DROP TABLE  wf_surrogate; 
CREATE TABLE wf_surrogate ( 
 id varchar2(32) NOT NULL , 
 process_Name varchar2(100) DEFAULT NULL , 
 operator varchar2(50) DEFAULT NULL , 
 surrogate varchar2(50) DEFAULT NULL , 
 odate varchar2(64) DEFAULT NULL , 
 sdate varchar2(64) DEFAULT NULL , 
 edate varchar2(64) DEFAULT NULL , 
 state number(1) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of wf_surrogate 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_task 
-- ---------------------------- 
DROP TABLE  wf_task; 
CREATE TABLE wf_task ( 
 id varchar2(32) NOT NULL , 
 order_Id varchar2(32) NOT NULL , 
 task_Name varchar2(100) NOT NULL , 
 display_Name varchar2(200) NOT NULL , 
 task_Type number(1) NOT NULL , 
 perform_Type number(1) DEFAULT NULL , 
 operator varchar2(50) DEFAULT NULL , 
 create_Time varchar2(50) DEFAULT NULL , 
 finish_Time varchar2(50) DEFAULT NULL , 
 expire_Time varchar2(50) DEFAULT NULL , 
 action_Url varchar2(200) DEFAULT NULL , 
 parent_Task_Id varchar2(32) DEFAULT NULL , 
 variable varchar2(2000) DEFAULT NULL , 
 version number(1) DEFAULT NULL , 
 PRIMARY KEY (id) 
); 
 
-- ---------------------------- 
-- Records of wf_task 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_task_actor 
-- ---------------------------- 
DROP TABLE  wf_task_actor; 
CREATE TABLE wf_task_actor ( 
 task_Id varchar2(32) NOT NULL , 
 actor_Id varchar2(50) NOT NULL  
); 
 
-- ---------------------------- 
-- Records of wf_task_actor 
-- ---------------------------- 
 
-- ---------------------------- 
-- Table structure for wf_workitem 
-- ---------------------------- 
DROP TABLE  wf_workitem; 
CREATE TABLE wf_workitem ( 
 task_id varchar2(255) NOT NULL , 
 process_id varchar2(255) DEFAULT NULL , 
 order_id varchar2(255) DEFAULT NULL , 
 order_no varchar2(255) DEFAULT NULL , 
 process_name varchar2(255) DEFAULT NULL , 
 instance_url varchar2(255) DEFAULT NULL , 
 parent_id varchar2(255) DEFAULT NULL , 
 creator varchar2(255) DEFAULT NULL , 
 order_create_time varchar2(255) DEFAULT NULL , 
 order_expire_time varchar2(255) DEFAULT NULL , 
 order_variable varchar2(255) DEFAULT NULL , 
 task_name varchar2(255) DEFAULT NULL , 
 task_key varchar2(255) DEFAULT NULL , 
 operator varchar2(255) DEFAULT NULL , 
 task_create_time varchar2(255) DEFAULT NULL , 
 task_end_time varchar2(255) DEFAULT NULL , 
 task_expire_time varchar2(255) DEFAULT NULL , 
 action_url varchar2(255) DEFAULT NULL , 
 task_type number(11) DEFAULT NULL , 
 perform_type number(11) DEFAULT NULL , 
 task_variable varchar2(255) DEFAULT NULL , 
 PRIMARY KEY (task_id) 
); 
 
-- ---------------------------- 
-- Records of wf_workitem 
-- ---------------------------- 

