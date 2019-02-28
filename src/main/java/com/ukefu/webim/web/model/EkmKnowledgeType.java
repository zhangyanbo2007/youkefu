package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 知识库-对应的知识分类树表
 *
 */
@Entity
@Table(name = "uk_ekm_knowledge_type")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeType implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String name ;//名称
	private int total;//该分类下的知识条数
	private int viewnum;//该分类下的知识被浏览次数
	private int collectnum;//该分类下的知识被收藏次数
	private boolean audit;//是否审核（1是/0否）
	private String auditer ;//审核人
	private String organ ;//用户所属部门ID
	private String parentid ;//父级ID
	private String knowbaseid ;//所属知识库ID

	private Date createtime ;
	private String creater;
	private String orgi ;
	private boolean datastatus;
	
	private boolean navshow;//导航栏显示（1是/0否）
	private boolean deskshow;//首页显示（1是/0否）
	
	private String icon ;
	
	
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
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getViewnum() {
		return viewnum;
	}
	public void setViewnum(int viewnum) {
		this.viewnum = viewnum;
	}
	public int getCollectnum() {
		return collectnum;
	}
	public void setCollectnum(int collectnum) {
		this.collectnum = collectnum;
	}
	public boolean isAudit() {
		return audit;
	}
	public void setAudit(boolean audit) {
		this.audit = audit;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getAuditer() {
		return auditer;
	}
	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getKnowbaseid() {
		return knowbaseid;
	}
	public void setKnowbaseid(String knowbaseid) {
		this.knowbaseid = knowbaseid;
	}
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	public boolean isNavshow() {
		return navshow;
	}
	public void setNavshow(boolean navshow) {
		this.navshow = navshow;
	}
	public boolean isDeskshow() {
		return deskshow;
	}
	public void setDeskshow(boolean deskshow) {
		this.deskshow = deskshow;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}
