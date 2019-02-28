package com.ukefu.webim.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import com.ukefu.util.UKTools;
import com.ukefu.webim.util.OnlineUserUtils;
import com.ukefu.webim.util.server.message.OtherMessageItem;

@Entity
@Table(name = "uk_xiaoe_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class AiConfig implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 565678041210332017L;
	private String id ;
	private String orgi ;
	private Date createtime = new Date() ;
	private String creater ;
	private String username ;
	private String name ;
	private String aiid;
	
	private String aiicon ;	//机器人头像
	
	private String welcomemsg ;
	private String waitmsg ;
	
	private boolean enableother ;	//启用外部机器人
	private boolean otherfirst ;	//外部机器人优先
	private String otherurl ;		//外部机器人URL
	private boolean otherssl ;		//外部机器人启用SSL
	private boolean otherlogin;		//需要登录
	private String otherappkey;		//外部机器人APPKey
	private String otherappsec;		//外部机器人AppSec
	private String otherparam ;		//外部机器人参数
	private String othermethod ;		//外部机器人请求方式  ， GET/POST
	private String othertempletinput ;	//外部机器人输入格式化模板
	private String othertempletoutput ;	//外部机器人输出格式化模板
	
	private boolean othertrans ;		//启用外部机器人转人工坐席
	private int othertimeout ;			//超时时长
	
	private String oqrdetailurl ;		//外部机器人内容URL
	private String oqrdetailinput ;	//外部机器人详情输入格式化模板
	private String oqrdetailoutput ;	//外部机器人详情输出格式化模板
	
	private boolean enablesuggest ;		//启用推荐
	private String suggestmsg ;			//推荐的提示消息
	
	private String othersuggestmsg ;			//成功命中结果以后的 推荐消息
	
	private boolean enableask = false;
	private boolean askfirst = false;
	private boolean enablescene = false;
	private boolean scenefirst = false;
	private boolean enablekeyword = false;
	private int keywordnum = 5;
	
	private boolean transagent ;	//在每一条机器人消息后面 显示转人工坐席
	
	private boolean askqs ;	//询问访客是否解决问题
	private int asktimes ;	//最长多久开始询问 默认 120秒（访客空闲时间超过120秒即断开链接）
	private String asktipmsg ;//询问访客的文本 ， 例如：您的问题是否已经解决？
	private String resolved ;	//已解决的提示文本
	private String unresolved ;	//未解决的提示文本
	private boolean redirectagent ;	//跳转到人工坐席
	private String redirecturl ;	//跳转到其他URL
	private boolean selectskill ;	//跳转到人工坐席之前开启选择技能组
	private String selectskillmsg ;	//选择技能组的提示信息
	
	private String noresultmsg ;
	
	private String hotmsg ;	//热点消息
	private boolean topicshot ;	//是否开启热点消息推荐
	
	private boolean topicuseful;	//是否开启知识评价功能
	private String topicusefulmsg ;	//知识评价提示消息
	private String topicusefulok ;	//知识评价提示消息"已解决"按钮
	private String topicusefulno ;	//知识评价提示消息"未解决"按钮
	
	private String topicusefultip ;	//评价后提示信息
	
	private boolean topicusefulask ;	//知识评价提示消息点击"未解决"后弹出 原因
	private boolean quickagent ;	//显示快速转人工坐席
	private String quickagentmsg ;	//快速转人工坐席提示消息
	private String noagentmsg ;	//无坐席在线提示消息
	
	private boolean enablesmartsuggest ;	//启用外部推荐内容
	private String smartsuggesturl ;		//外部推荐URL
	private boolean smartsuggestssl ;		//外部推荐启用SSL
	private boolean smartsuggestlogin;		//需要登录
	private String smartsuggestappkey;		//外部推荐APPKey
	private String smartsuggestappsec;		//推荐AppSec
	private String smartsuggestparam ;		//外部推荐参数
	private String smartsuggestmethod ;		//外部推荐请求方式  ， GET/POST
	private String smartsuggesttempletinput ;	//外部推荐输入格式化模板
	private String smartsuggesttempletoutput ;	//外部推荐输出格式化模板
	
	private int suggestnum ;
	
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isEnableask() {
		return enableask;
	}
	public void setEnableask(boolean enableask) {
		this.enableask = enableask;
	}
	public boolean isAskfirst() {
		return askfirst;
	}
	public void setAskfirst(boolean askfirst) {
		this.askfirst = askfirst;
	}
	public boolean isEnablescene() {
		return enablescene;
	}
	public void setEnablescene(boolean enablescene) {
		this.enablescene = enablescene;
	}
	public boolean isScenefirst() {
		return scenefirst;
	}
	public void setScenefirst(boolean scenefirst) {
		this.scenefirst = scenefirst;
	}
	public boolean isEnablekeyword() {
		return enablekeyword;
	}
	public void setEnablekeyword(boolean enablekeyword) {
		this.enablekeyword = enablekeyword;
	}
	public int getKeywordnum() {
		return keywordnum;
	}
	public void setKeywordnum(int keywordnum) {
		this.keywordnum = keywordnum;
	}
	public String getNoresultmsg() {
		return noresultmsg;
	}
	public void setNoresultmsg(String noresultmsg) {
		this.noresultmsg = noresultmsg;
	}
	public boolean isAskqs() {
		return askqs;
	}
	public void setAskqs(boolean askqs) {
		this.askqs = askqs;
	}
	public String getAsktipmsg() {
		return asktipmsg;
	}
	public void setAsktipmsg(String asktipmsg) {
		this.asktipmsg = asktipmsg;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	}
	public String getUnresolved() {
		return unresolved;
	}
	public void setUnresolved(String unresolved) {
		this.unresolved = unresolved;
	}
	public boolean isRedirectagent() {
		return redirectagent;
	}
	public void setRedirectagent(boolean redirectagent) {
		this.redirectagent = redirectagent;
	}
	public String getRedirecturl() {
		return redirecturl;
	}
	public void setRedirecturl(String redirecturl) {
		this.redirecturl = redirecturl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getAsktimes() {
		return asktimes;
	}
	public void setAsktimes(int asktimes) {
		this.asktimes = asktimes;
	}
	public boolean isSelectskill() {
		return selectskill;
	}
	public void setSelectskill(boolean selectskill) {
		this.selectskill = selectskill;
	}
	public String getSelectskillmsg() {
		return selectskillmsg;
	}
	public void setSelectskillmsg(String selectskillmsg) {
		this.selectskillmsg = selectskillmsg;
	}
	public String getAiid() {
		return aiid;
	}
	public void setAiid(String aiid) {
		this.aiid = aiid;
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
	public boolean isEnableother() {
		return enableother;
	}
	public void setEnableother(boolean enableother) {
		this.enableother = enableother;
	}
	public boolean isOtherfirst() {
		return otherfirst;
	}
	public void setOtherfirst(boolean otherfirst) {
		this.otherfirst = otherfirst;
	}
	public String getOtherurl() {
		return otherurl;
	}
	public void setOtherurl(String otherurl) {
		this.otherurl = otherurl;
	}
	public boolean isOtherlogin() {
		return otherlogin;
	}
	public void setOtherlogin(boolean otherlogin) {
		this.otherlogin = otherlogin;
	}
	public String getOtherappkey() {
		return otherappkey;
	}
	public void setOtherappkey(String otherappkey) {
		this.otherappkey = otherappkey;
	}
	public String getOtherappsec() {
		return otherappsec;
	}
	public void setOtherappsec(String otherappsec) {
		this.otherappsec = otherappsec;
	}
	public String getOtherparam() {
		return otherparam;
	}
	public void setOtherparam(String otherparam) {
		this.otherparam = otherparam;
	}
	public String getOthermethod() {
		return othermethod;
	}
	public void setOthermethod(String othermethod) {
		this.othermethod = othermethod;
	}
	public String getOthertempletinput() {
		return othertempletinput;
	}
	public void setOthertempletinput(String othertempletinput) {
		this.othertempletinput = othertempletinput;
	}
	public String getOthertempletoutput() {
		return othertempletoutput;
	}
	public void setOthertempletoutput(String othertempletoutput) {
		this.othertempletoutput = othertempletoutput;
	}
	public boolean isOtherssl() {
		return otherssl;
	}
	public void setOtherssl(boolean otherssl) {
		this.otherssl = otherssl;
	}
	public boolean isEnablesuggest() {
		return enablesuggest;
	}
	public void setEnablesuggest(boolean enablesuggest) {
		this.enablesuggest = enablesuggest;
	}
	public String getSuggestmsg() {
		return suggestmsg;
	}
	public void setSuggestmsg(String suggestmsg) {
		this.suggestmsg = suggestmsg;
	}
	public String getOqrdetailurl() {
		return oqrdetailurl;
	}
	public void setOqrdetailurl(String oqrdetailurl) {
		this.oqrdetailurl = oqrdetailurl;
	}
	public String getOqrdetailinput() {
		return oqrdetailinput;
	}
	public void setOqrdetailinput(String oqrdetailinput) {
		this.oqrdetailinput = oqrdetailinput;
	}
	public String getOqrdetailoutput() {
		return oqrdetailoutput;
	}
	public void setOqrdetailoutput(String oqrdetailoutput) {
		this.oqrdetailoutput = oqrdetailoutput;
	}
	public String getOthersuggestmsg() {
		return othersuggestmsg;
	}
	public void setOthersuggestmsg(String othersuggestmsg) {
		this.othersuggestmsg = othersuggestmsg;
	}
	public String getAiicon() {
		return aiicon;
	}
	public void setAiicon(String aiicon) {
		this.aiicon = aiicon;
	}
	public boolean isOthertrans() {
		return othertrans;
	}
	public void setOthertrans(boolean othertrans) {
		this.othertrans = othertrans;
	}
	public int getOthertimeout() {
		return othertimeout;
	}
	public void setOthertimeout(int othertimeout) {
		this.othertimeout = othertimeout;
	}
	public boolean isTransagent() {
		return transagent;
	}
	public void setTransagent(boolean transagent) {
		this.transagent = transagent;
	}
	public String getHotmsg() {
		return hotmsg;
	}
	public void setHotmsg(String hotmsg) {
		this.hotmsg = hotmsg;
	}
	
	public boolean isTopicshot() {
		return topicshot;
	}
	public void setTopicshot(boolean topicshot) {
		this.topicshot = topicshot;
	}
	public boolean isTopicuseful() {
		return topicuseful;
	}
	public void setTopicuseful(boolean topicuseful) {
		this.topicuseful = topicuseful;
	}
	public String getTopicusefulmsg() {
		return topicusefulmsg;
	}
	public void setTopicusefulmsg(String topicusefulmsg) {
		this.topicusefulmsg = topicusefulmsg;
	}
	public String getTopicusefulok() {
		return topicusefulok;
	}
	public void setTopicusefulok(String topicusefulok) {
		this.topicusefulok = topicusefulok;
	}
	public String getTopicusefulno() {
		return topicusefulno;
	}
	public void setTopicusefulno(String topicusefulno) {
		this.topicusefulno = topicusefulno;
	}
	public boolean isTopicusefulask() {
		return topicusefulask;
	}
	public void setTopicusefulask(boolean topicusefulask) {
		this.topicusefulask = topicusefulask;
	}
	public boolean isQuickagent() {
		return quickagent;
	}
	public void setQuickagent(boolean quickagent) {
		this.quickagent = quickagent;
	}
	public String getQuickagentmsg() {
		return quickagentmsg;
	}
	public void setQuickagentmsg(String quickagentmsg) {
		this.quickagentmsg = quickagentmsg;
	}
	public String getTopicusefultip() {
		return topicusefultip;
	}
	public void setTopicusefultip(String topicusefultip) {
		this.topicusefultip = topicusefultip;
	}
	public String getNoagentmsg() {
		return noagentmsg;
	}
	public void setNoagentmsg(String noagentmsg) {
		this.noagentmsg = noagentmsg;
	}
	public boolean isEnablesmartsuggest() {
		return enablesmartsuggest;
	}
	public void setEnablesmartsuggest(boolean enablesmartsuggest) {
		this.enablesmartsuggest = enablesmartsuggest;
	}
	public String getSmartsuggesturl() {
		return smartsuggesturl;
	}
	public void setSmartsuggesturl(String smartsuggesturl) {
		this.smartsuggesturl = smartsuggesturl;
	}
	public boolean isSmartsuggestssl() {
		return smartsuggestssl;
	}
	public void setSmartsuggestssl(boolean smartsuggestssl) {
		this.smartsuggestssl = smartsuggestssl;
	}
	public boolean isSmartsuggestlogin() {
		return smartsuggestlogin;
	}
	public void setSmartsuggestlogin(boolean smartsuggestlogin) {
		this.smartsuggestlogin = smartsuggestlogin;
	}
	public String getSmartsuggestappkey() {
		return smartsuggestappkey;
	}
	public void setSmartsuggestappkey(String smartsuggestappkey) {
		this.smartsuggestappkey = smartsuggestappkey;
	}
	public String getSmartsuggestappsec() {
		return smartsuggestappsec;
	}
	public void setSmartsuggestappsec(String smartsuggestappsec) {
		this.smartsuggestappsec = smartsuggestappsec;
	}
	public String getSmartsuggestparam() {
		return smartsuggestparam;
	}
	public void setSmartsuggestparam(String smartsuggestparam) {
		this.smartsuggestparam = smartsuggestparam;
	}
	public String getSmartsuggestmethod() {
		return smartsuggestmethod;
	}
	public void setSmartsuggestmethod(String smartsuggestmethod) {
		this.smartsuggestmethod = smartsuggestmethod;
	}
	public String getSmartsuggesttempletinput() {
		return smartsuggesttempletinput;
	}
	public void setSmartsuggesttempletinput(String smartsuggesttempletinput) {
		this.smartsuggesttempletinput = smartsuggesttempletinput;
	}
	public String getSmartsuggesttempletoutput() {
		return smartsuggesttempletoutput;
	}
	public void setSmartsuggesttempletoutput(String smartsuggesttempletoutput) {
		this.smartsuggesttempletoutput = smartsuggesttempletoutput;
	}
	public int getSuggestnum() {
		return suggestnum;
	}
	public void setSuggestnum(int suggestnum) {
		this.suggestnum = suggestnum;
	}
	@Transient
	public List<OtherMessageItem> getHot(){
		List<OtherMessageItem> otherMessageItemList = null ;
		if(!StringUtils.isBlank(this.getHotmsg())) {
			try {
				otherMessageItemList = OnlineUserUtils.objectMapper.readValue(this.getHotmsg(), UKTools.getCollectionType(ArrayList.class, OtherMessageItem.class))  ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return otherMessageItemList ;
	}
}
