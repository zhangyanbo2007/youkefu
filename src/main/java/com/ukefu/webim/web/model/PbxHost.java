package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_callcenter_pbxhost")
@org.hibernate.annotations.Proxy(lazy = false)
public class PbxHost implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3932323765657445180L;
	private String id;
	private String name;
	private String hostname ;	//host name
	private String ipaddr ;		//IP
	private int port ;
	
	private int sipport = 5060;		//SIP 服务端口， 默认 5060,
	private String password ;	//pbx host password
	
	private String blacklist ;	//拦截字符串
	private String whitelist ;	//请求头中必须包含的字符串，无特殊字符串即被拦截
	
	private boolean connected ;
	
	private boolean callcenter ;

	
	private String recordpath ;	//录音文件存储路径
	
	private String asrrecordpath ;	//ASR结果文件存储路径
	private String ttsrecordpath ;	//TTS结果文件存储路径
	private String ivrpath ;	//IVR文件路径
	private String fspath ;		//FreeSwitch安装路径
	private String device ;	//设备厂商
	
	
	private boolean afterprocess ;	//启用坐席后处理功能
	
	private String orgi;
	
	private boolean autoanswer ;
	
	private boolean sipautoanswer ;
	
	private String abscodec = "PCMA";	//默认的 呼叫编码 
	
	private String callbacktype ;	//回呼送号 号码
	private String callbacknumber ;
	
	private String creater ;
	
	private String enableai ;
	private String aiid ;
	private String sceneid ;
	
	private String welcomemsg ;		//欢迎提示语
	private String waitmsg ;		//等待提示语
	private String tipmessage ;		//识别完成提示语
	
	private boolean enablewebrtc ;	//启用WEBRTC
	private String webrtcaddress; 	//WebRTC访问地址
	private String webrtcport; 		//WebRTC端口
	private boolean webrtcssl;		//启用SSL
	
	private String webrtcring ;		//来电铃声
	
	private boolean dissipphone ;	//外呼的时候，是否隐藏SIP话机上的号码
	
	private int maxnumlength ;		//主叫/被叫号码最大长度（用于ACL拦截，超过最大长度即被拦截）
	private int minnumlength ;		//主叫/被叫号码最小长度（用于ACL拦截，超过最小长度即被拦截）
	
	private String ipregionblack ;	//发起呼叫的IP黑名单地区
	private String ipregionwhite ;	//发起护甲的IP白名单地区
	
	private boolean savekillevent ;	//保持被拦截的通话记录
	
	
	private Date createtime = new Date();
	private Date updatetime = new Date();
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	@Transient
	public boolean isConnected() {
		return connected;
	}
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
	public boolean isAutoanswer() {
		return autoanswer;
	}
	public void setAutoanswer(boolean autoanswer) {
		this.autoanswer = autoanswer;
	}
	public String getCallbacknumber() {
		return callbacknumber;
	}
	public void setCallbacknumber(String callbacknumber) {
		this.callbacknumber = callbacknumber;
	}
	public boolean isCallcenter() {
		return callcenter;
	}
	public void setCallcenter(boolean callcenter) {
		this.callcenter = callcenter;
	}
	public String getRecordpath() {
		return recordpath;
	}
	public void setRecordpath(String recordpath) {
		this.recordpath = recordpath;
	}
	public String getIvrpath() {
		return ivrpath;
	}
	public void setIvrpath(String ivrpath) {
		this.ivrpath = ivrpath;
	}
	public String getFspath() {
		return fspath;
	}
	public void setFspath(String fspath) {
		this.fspath = fspath;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getCallbacktype() {
		return callbacktype;
	}
	public void setCallbacktype(String callbacktype) {
		this.callbacktype = callbacktype;
	}
	public boolean isSipautoanswer() {
		return sipautoanswer;
	}
	public void setSipautoanswer(boolean sipautoanswer) {
		this.sipautoanswer = sipautoanswer;
	}
	public String getAbscodec() {
		return abscodec;
	}
	public void setAbscodec(String abscodec) {
		this.abscodec = abscodec;
	}
	public String getEnableai() {
		return enableai;
	}
	public void setEnableai(String enableai) {
		this.enableai = enableai;
	}
	public String getAiid() {
		return aiid;
	}
	public void setAiid(String aiid) {
		this.aiid = aiid;
	}
	public String getSceneid() {
		return sceneid;
	}
	public void setSceneid(String sceneid) {
		this.sceneid = sceneid;
	}
	public String getWelcomemsg() {
		return welcomemsg;
	}
	public void setWelcomemsg(String welcomemsg) {
		this.welcomemsg = welcomemsg;
	}
	public String getWaitmsg() {
		return waitmsg;
	}
	public void setWaitmsg(String waitmsg) {
		this.waitmsg = waitmsg;
	}
	public String getTipmessage() {
		return tipmessage;
	}
	public void setTipmessage(String tipmessage) {
		this.tipmessage = tipmessage;
	}
	public String getAsrrecordpath() {
		return asrrecordpath;
	}
	public void setAsrrecordpath(String asrrecordpath) {
		this.asrrecordpath = asrrecordpath;
	}
	public String getTtsrecordpath() {
		return ttsrecordpath;
	}
	public void setTtsrecordpath(String ttsrecordpath) {
		this.ttsrecordpath = ttsrecordpath;
	}
	public boolean isAfterprocess() {
		return afterprocess;
	}
	public void setAfterprocess(boolean afterprocess) {
		this.afterprocess = afterprocess;
	}
	public boolean isEnablewebrtc() {
		return enablewebrtc;
	}
	public void setEnablewebrtc(boolean enablewebrtc) {
		this.enablewebrtc = enablewebrtc;
	}
	public String getWebrtcaddress() {
		return webrtcaddress;
	}
	public void setWebrtcaddress(String webrtcaddress) {
		this.webrtcaddress = webrtcaddress;
	}
	public String getWebrtcport() {
		return webrtcport;
	}
	public void setWebrtcport(String webrtcport) {
		this.webrtcport = webrtcport;
	}
	public boolean isWebrtcssl() {
		return webrtcssl;
	}
	public void setWebrtcssl(boolean webrtcssl) {
		this.webrtcssl = webrtcssl;
	}
	public boolean isDissipphone() {
		return dissipphone;
	}
	public void setDissipphone(boolean dissipphone) {
		this.dissipphone = dissipphone;
	}
	public int getSipport() {
		return sipport;
	}
	public void setSipport(int sipport) {
		this.sipport = sipport;
	}
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public String getWebrtcring() {
		return webrtcring;
	}
	public void setWebrtcring(String webrtcring) {
		this.webrtcring = webrtcring;
	}
	public String getWhitelist() {
		return whitelist;
	}
	public void setWhitelist(String whitelist) {
		this.whitelist = whitelist;
	}
	public int getMaxnumlength() {
		return maxnumlength;
	}
	public void setMaxnumlength(int maxnumlength) {
		this.maxnumlength = maxnumlength;
	}
	public int getMinnumlength() {
		return minnumlength;
	}
	public void setMinnumlength(int minnumlength) {
		this.minnumlength = minnumlength;
	}
	public String getIpregionblack() {
		return ipregionblack;
	}
	public void setIpregionblack(String ipregionblack) {
		this.ipregionblack = ipregionblack;
	}
	public String getIpregionwhite() {
		return ipregionwhite;
	}
	public void setIpregionwhite(String ipregionwhite) {
		this.ipregionwhite = ipregionwhite;
	}
	public boolean isSavekillevent() {
		return savekillevent;
	}
	public void setSavekillevent(boolean savekillevent) {
		this.savekillevent = savekillevent;
	}
}
