package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.ukefu.util.UKTools;

/**
 * 
 * @author admin
 *
 */
@Entity
@Table(name = "uk_crm_datamodel")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataModel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id = UKTools.getUUID();
	private String name ;	
	private String code ;		//对象代码
	private String reporttype ; //0 代表动态报表  1 代表自助查询报表
	private String layouttype ;	//布局模式 ， 0：固定布局， 1：自由布局
	private String viewtype;	//在CRM中改变用处，修改为页面类型，新增表单、修改表单、列表页面，其他
	public String action ;
	private String actiontype ;
	private String btntype;
	private String title ;		 //变更用处，修改为 报表code		
	private String dicid ;	//目录ID
	private String authcode ;
	private String authitem ;
	private String html ;			//改变用处，用于存储 是否允许里面访问移动端报表
	private String reportcontent;	//报表序列化以后的结果，JSON格式
	private String status;
	private String rolename ;		//变更用处，标记为 动态报表 默认为 null 或者 0 都是 自助查询，1表示自定义报表
	private String userid ;			//变更用处，标记为 仪表盘的 属主ID
	private String blacklist ;		//变更用处，用于区分是否是  仪表盘
	private String reportpackage ;	//报表路径
	private String useacl ;			//启用权限控制    ,  变更用处，  用于控制是否覆盖上级目录的权限
	private String reportmodel	;	//自助查询的是 保存 Model 的ID
	private Date updatetime;		//修改时间 
	private String orgi ;
	private Date createtime ;
	private String creater;
	private String icon ;
	private String style ;
	private int objectcount = 0 ;
	private int reportversion ;
	private boolean design = false ;
	private boolean rtfedit = false ;
	private boolean codeedit = false ;
	private boolean mediaagent = false ;	//是否加载多媒体坐席信息
	private boolean hisnav = false ;		//是否显示导航
	private boolean fullscreen ; 			//是否启用大屏显示
	private boolean accesshis ;				//是否记录 访问历史
	private boolean searchhis ;				//是否记录搜索历史
	
	private boolean duplicate ;				//是否允许重复数据
	
	private boolean autorefresh	;			//大屏现实的时候是否自动刷新
	private int refreshtime	;			//大屏显示刷新时间间隔 , 以秒计
	private boolean autoscroll;			//是否自动滚动
	private String scrollspeed;			//自动滚动速度 ， 快/慢/适中
	private boolean onlytab ;			//大屏显示的时候是否只显示 页签
	private int rotationspeed = 5;			//大屏显示的时候轮播速度
	
	private String submittype ;	//表单提交后的更新内容方式 ， AJAX , FIXED 
	private String submitpos ;	//表单提交后更新页面位置， 左侧，中间， 右侧区域
	
	private String publishedtype ;
	private String tabtype ;
	private String username ;		//变更用处，修改为 Builder的 布局模式
	private String useremail ;
	private boolean cache;//1启用缓存，0不启用
	private String extparam;		//默认使用 player 打开
	private String source ;			//报表来源，如果是在  事件设计器里创建的 报表，则此字段不为空，无法保存
	private String proid ;			//产品ID
	private String tbid ;
	private String tbname ;
	private String dstype ;
	private String linktype ;		//按钮的链接类型
	private String linkurl ;		//按钮的 链接 地址
	private String params ;			//按钮的链接参数
	private int sortindex ;
	private String targetreport ;	
	private String description ;
	
	private boolean mgroup ;
	private String groupname ;
	private String groupicon ;
	private String groupcolor ;
	
	
	private boolean layoutleft ;	//启用左侧分栏
	private boolean layoutright ;	//启用右侧分栏
	private boolean layoutcenter ;	//启用中间分栏
	
	private String leftwidth ;
	private boolean leftscroll ;
	
	private String rightwidth;
	private boolean rightscroll ;
	
	private String centerheight ;
	private boolean centerscroll ;
	
	
	private boolean upload = false ;
	private boolean hasform = true;
	private boolean resetbtn = true;
	private boolean submitbtn = true;
	private String formtype ;
	private String submiturl ;
	private String submitpage ;
	private String submitlink ;
	private String submitpagerpt ;
	
	
	private String reseturl ;
	private String resetpage ;
	private String resetlink ;
	private String resetpagerpt ;
	
	private boolean workflow;	//operator workflow placeholder
	private String flowtype ;	//flow type
	private String successtip;	//success tip message
	private String errortip ;	//error tip message
	
	private transient String currentagent ;	//用于临时存放当前选中的 坐席服务用户
	
	private transient String query ;
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")
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
	public String getReporttype() {
		return reporttype;
	}
	public void setReporttype(String reporttype) {
		this.reporttype = reporttype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public int getObjectcount() {
		return objectcount;
	}
	public void setObjectcount(int objectcount) {
		this.objectcount = objectcount;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDicid() {
		return dicid;
	}
	public void setDicid(String dicid) {
		this.dicid = dicid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getViewtype() {
		return viewtype;
	}
	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBlacklist() {
		return blacklist != null ? blacklist : rolename ;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public String getReportpackage() {
		return reportpackage;
	}
	public void setReportpackage(String reportpackage) {
		this.reportpackage = reportpackage;
	}
	public String getUseacl() {
		return useacl;
	}
	public void setUseacl(String useacl) {
		this.useacl = useacl;
	}
	public String getReportmodel() {
		return reportmodel;
	}
	public void setReportmodel(String reportmodel) {
		this.reportmodel = reportmodel;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public int getReportversion() {
		return reportversion;
	}
	public void setReportversion(int reportversion) {
		this.reportversion = reportversion;
	}
	public String getReportcontent() {
		return reportcontent;
	}
	public void setReportcontent(String reportcontent) {
		this.reportcontent = reportcontent;
	}
	public String getPublishedtype() {
		return publishedtype;
	}
	public void setPublishedtype(String publishedtype) {
		this.publishedtype = publishedtype;
	}
	public String getTabtype() {
		return tabtype;
	}
	public void setTabtype(String tabtype) {
		this.tabtype = tabtype;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public boolean isCache() {
		return cache;
	}
	public void setCache(boolean cache) {
		this.cache = cache;
	}
	public String getExtparam() {
		return extparam;
	}
	public void setExtparam(String extparam) {
		this.extparam = extparam;
	}
	public String getTargetreport() {
		return targetreport;
	}
	public void setTargetreport(String targetreport) {
		this.targetreport = targetreport;
	}
	@Transient
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isDesign() {
		return design;
	}
	public void setDesign(boolean design) {
		this.design = design;
	}
	
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public String getAuthitem() {
		return authitem;
	}
	public void setAuthitem(String authitem) {
		this.authitem = authitem;
	}
	public String getTbid() {
		return tbid;
	}
	public void setTbid(String tbid) {
		this.tbid = tbid;
	}
	public String getTbname() {
		return tbname;
	}
	public void setTbname(String tbname) {
		this.tbname = tbname;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getLinktype() {
		return linktype;
	}
	public void setLinktype(String linktype) {
		this.linktype = linktype;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public boolean isRtfedit() {
		return rtfedit;
	}
	public void setRtfedit(boolean rtfedit) {
		this.rtfedit = rtfedit;
	}
	public boolean isCodeedit() {
		return codeedit;
	}
	public void setCodeedit(boolean codeedit) {
		this.codeedit = codeedit;
	}
	
	@Transient
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getLayouttype() {
		return layouttype;
	}
	public void setLayouttype(String layouttype) {
		this.layouttype = layouttype;
	}
	public boolean isUpload() {
		return upload;
	}
	public void setUpload(boolean upload) {
		this.upload = upload;
	}
	public boolean isHasform() {
		return hasform;
	}
	public void setHasform(boolean hasform) {
		this.hasform = hasform;
	}
	public String getFormtype() {
		return formtype;
	}
	public void setFormtype(String formtype) {
		this.formtype = formtype;
	}
	public String getSubmiturl() {
		return submiturl;
	}
	public void setSubmiturl(String submiturl) {
		this.submiturl = submiturl;
	}
	public String getSubmitpage() {
		return submitpage;
	}
	public void setSubmitpage(String submitpage) {
		this.submitpage = submitpage;
	}
	public String getSubmitlink() {
		return submitlink;
	}
	public void setSubmitlink(String submitlink) {
		this.submitlink = submitlink;
	}
	public String getReseturl() {
		return reseturl;
	}
	public void setReseturl(String reseturl) {
		this.reseturl = reseturl;
	}
	public String getResetpage() {
		return resetpage;
	}
	public void setResetpage(String resetpage) {
		this.resetpage = resetpage;
	}
	public String getResetlink() {
		return resetlink;
	}
	public void setResetlink(String resetlink) {
		this.resetlink = resetlink;
	}
	public boolean isResetbtn() {
		return resetbtn;
	}
	public void setResetbtn(boolean resetbtn) {
		this.resetbtn = resetbtn;
	}
	public boolean isSubmitbtn() {
		return submitbtn;
	}
	public void setSubmitbtn(boolean submitbtn) {
		this.submitbtn = submitbtn;
	}
	public String getSubmitpagerpt() {
		return submitpagerpt;
	}
	public void setSubmitpagerpt(String submitpagerpt) {
		this.submitpagerpt = submitpagerpt;
	}
	public String getResetpagerpt() {
		return resetpagerpt;
	}
	public void setResetpagerpt(String resetpagerpt) {
		this.resetpagerpt = resetpagerpt;
	}
	public boolean isLayoutleft() {
		return layoutleft;
	}
	public void setLayoutleft(boolean layoutleft) {
		this.layoutleft = layoutleft;
	}
	public boolean isLayoutright() {
		return layoutright;
	}
	public void setLayoutright(boolean layoutright) {
		this.layoutright = layoutright;
	}
	public boolean isLayoutcenter() {
		return layoutcenter;
	}
	public void setLayoutcenter(boolean layoutcenter) {
		this.layoutcenter = layoutcenter;
	}
	public String getLeftwidth() {
		return leftwidth;
	}
	public void setLeftwidth(String leftwidth) {
		this.leftwidth = leftwidth;
	}
	public boolean isLeftscroll() {
		return leftscroll;
	}
	public void setLeftscroll(boolean leftscroll) {
		this.leftscroll = leftscroll;
	}
	public String getRightwidth() {
		return rightwidth;
	}
	public void setRightwidth(String rightwidth) {
		this.rightwidth = rightwidth;
	}
	public boolean isRightscroll() {
		return rightscroll;
	}
	public void setRightscroll(boolean rightscroll) {
		this.rightscroll = rightscroll;
	}
	public String getCenterheight() {
		return centerheight;
	}
	public void setCenterheight(String centerheight) {
		this.centerheight = centerheight;
	}
	public boolean isCenterscroll() {
		return centerscroll;
	}
	public void setCenterscroll(boolean centerscroll) {
		this.centerscroll = centerscroll;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public String getBtntype() {
		return btntype;
	}
	public void setBtntype(String btntype) {
		this.btntype = btntype;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public boolean isMediaagent() {
		return mediaagent;
	}
	public void setMediaagent(boolean mediaagent) {
		this.mediaagent = mediaagent;
	}
	public boolean isHisnav() {
		return hisnav;
	}
	public void setHisnav(boolean hisnav) {
		this.hisnav = hisnav;
	}
	public String getSubmittype() {
		return submittype;
	}
	public void setSubmittype(String submittype) {
		this.submittype = submittype;
	}
	public String getSubmitpos() {
		return submitpos;
	}
	public void setSubmitpos(String submitpos) {
		this.submitpos = submitpos;
	}
	public boolean isMgroup() {
		return mgroup;
	}
	public void setMgroup(boolean mgroup) {
		this.mgroup = mgroup;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getGroupicon() {
		return groupicon;
	}
	public void setGroupicon(String groupicon) {
		this.groupicon = groupicon;
	}
	public String getGroupcolor() {
		return groupcolor;
	}
	public void setGroupcolor(String groupcolor) {
		this.groupcolor = groupcolor;
	}
	public boolean isWorkflow() {
		return workflow;
	}
	public void setWorkflow(boolean workflow) {
		this.workflow = workflow;
	}
	public String getFlowtype() {
		return flowtype;
	}
	public void setFlowtype(String flowtype) {
		this.flowtype = flowtype;
	}
	public String getSuccesstip() {
		return successtip;
	}
	public void setSuccesstip(String successtip) {
		this.successtip = successtip;
	}
	public String getErrortip() {
		return errortip;
	}
	public void setErrortip(String errortip) {
		this.errortip = errortip;
	}
	public boolean isFullscreen() {
		return fullscreen;
	}
	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}
	public boolean isAutorefresh() {
		return autorefresh;
	}
	public void setAutorefresh(boolean autorefresh) {
		this.autorefresh = autorefresh;
	}
	public int getRefreshtime() {
		return refreshtime;
	}
	public void setRefreshtime(int refreshtime) {
		this.refreshtime = refreshtime;
	}
	public boolean isAutoscroll() {
		return autoscroll;
	}
	public void setAutoscroll(boolean autoscroll) {
		this.autoscroll = autoscroll;
	}
	public String getScrollspeed() {
		return scrollspeed;
	}
	public void setScrollspeed(String scrollspeed) {
		this.scrollspeed = scrollspeed;
	}
	public boolean isOnlytab() {
		return onlytab;
	}
	public void setOnlytab(boolean onlytab) {
		this.onlytab = onlytab;
	}
	public int getRotationspeed() {
		return rotationspeed;
	}
	public void setRotationspeed(int rotationspeed) {
		this.rotationspeed = rotationspeed;
	}
	public boolean isAccesshis() {
		return accesshis;
	}
	public void setAccesshis(boolean accesshis) {
		this.accesshis = accesshis;
	}
	public boolean isSearchhis() {
		return searchhis;
	}
	public void setSearchhis(boolean searchhis) {
		this.searchhis = searchhis;
	}
	@Transient
	public String getCurrentagent() {
		return currentagent;
	}
	public void setCurrentagent(String currentagent) {
		this.currentagent = currentagent;
	}
	public int getSortindex() {
		return sortindex;
	}
	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isDuplicate() {
		return duplicate;
	}
	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}
}
