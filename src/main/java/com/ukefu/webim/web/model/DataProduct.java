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

@Entity
@Table(name = "uk_crm_dataproduct")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataProduct implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2593399616448881368L;
	private String id = UKTools.getUUID();
	private String name ;
	private String code ;			//变更用途，用于区分仪表盘，目录类型，默认为空
	private String title ;					
	private String dstype ; 		//dashboard类型
	private String dstemplet ;		//dashboard类型对应的  展示模板
	private String parentid;
	private String type ;
	private String memo ;			//改变用处，变更为  目录序号
	private String distitle;		//分栏时 左侧 栏目的 显示标题
	private String orgi ;
	private String status ;
	private String iconclass;
	private String cssstyle;
	private String creater ;
	private String authcode ;
	private String publishedtype ;
	private Date createtime = new Date();
	private Date updatetime ;
	private String description ;
	private String tabtype ;
	private String dictype ;
	private boolean spsearch = true;	//是否支持搜索
	private boolean defaultmenu ;
	private User user;
	private int sortindex = 1; 		//排序位置
	private String dataid ;			//dstype = singel 的时候，用于定义获取数据的 ID
	private String dicicon ;			//菜单图标
	private String curicon ;			//菜单选中时候 的 图标
	private String bgcolor ;			//在列表的时候显示的背景颜色，用于反显图标用的
	private String curbgcolor;			//在列表的时候显示的背景颜色，用于反显图标用的
	private String menupos ;			//菜单显示位置，左侧显示 ： 顶部显示
	
	private boolean navmenu ;			//顶部导航菜单按钮
	private boolean quickmenu ;			//左侧快捷菜单按钮
	
	private boolean duplicate ;				//是否允许重复数据
	private boolean design = false ;
	
	/**
	 * 文件夹类型 public
	 */
	@Transient
	public static final  String TABTYPE_PUB="pub";
	/**
	 * 文件夹类型 private 
	 */
	@Transient
	public static final  String TABTYPE_PRI="pri";
	
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
	
	public String getCssstyle() {
		return cssstyle;
	}
	public void setCssstyle(String cssstyle) {
		this.cssstyle = cssstyle;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getPublishedtype() {
		return publishedtype;
	}
	public void setPublishedtype(String publishedtype) {
		this.publishedtype = publishedtype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTabtype() {
		return tabtype;
	}
	public void setTabtype(String tabtype) {
		this.tabtype = tabtype;
	}
	
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getDstemplet() {
		return dstemplet;
	}
	public void setDstemplet(String dstemplet) {
		this.dstemplet = dstemplet;
	}
	
	@Transient
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getSortindex() {
		return sortindex;
	}
	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}
	public String getDictype() {
		return dictype;
	}
	public void setDictype(String dictype) {
		this.dictype = dictype;
	}
	public String getIconclass() {
		return iconclass;
	}
	public void setIconclass(String iconclass) {
		this.iconclass = iconclass;
	}
	public String getAuthcode() {
		return authcode;
	}
	public void setAuthcode(String authcode) {
		this.authcode = authcode;
	}
	public boolean isDefaultmenu() {
		return defaultmenu;
	}
	public void setDefaultmenu(boolean defaultmenu) {
		this.defaultmenu = defaultmenu;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	
	@Transient
	public boolean isSpsearch() {
		return spsearch;
	}
	public void setSpsearch(boolean spsearch) {
		this.spsearch = spsearch;
	}
	
	@Transient
	public String getTarget(){
		return this.getParentid()!=null && this.getParentid().equals("0") ? "product" : this.getDstype()+"_"+this.getId() ;
	}
	public String getDicicon() {
		return dicicon;
	}
	public void setDicicon(String dicicon) {
		this.dicicon = dicicon;
	}
	public String getCuricon() {
		return curicon;
	}
	public void setCuricon(String curicon) {
		this.curicon = curicon;
	}
	public String getBgcolor() {
		return bgcolor;
	}
	public void setBgcolor(String bgcolor) {
		this.bgcolor = bgcolor;
	}
	public String getCurbgcolor() {
		return curbgcolor;
	}
	public void setCurbgcolor(String curbgcolor) {
		this.curbgcolor = curbgcolor;
	}
	public String getMenupos() {
		return menupos;
	}
	public void setMenupos(String menupos) {
		this.menupos = menupos;
	}
	public String getDistitle() {
		return distitle;
	}
	public void setDistitle(String distitle) {
		this.distitle = distitle;
	}
	public boolean isNavmenu() {
		return navmenu;
	}
	public void setNavmenu(boolean navmenu) {
		this.navmenu = navmenu;
	}
	public boolean isQuickmenu() {
		return quickmenu;
	}
	public void setQuickmenu(boolean quickmenu) {
		this.quickmenu = quickmenu;
	}
	public boolean isDuplicate() {
		return duplicate;
	}
	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}
	public boolean isDesign() {
		return design;
	}
	public void setDesign(boolean design) {
		this.design = design;
	}
	
}
