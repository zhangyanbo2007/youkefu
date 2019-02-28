package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 知识库表
 *
 */
@Entity
@Table(name = "uk_ekm_knowbase")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowbase implements java.io.Serializable{

	
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String name ;//名称
	private int total;//知识库下的知识条数
	private int viewnum;//知识库下的知识被浏览次数
	private int collectnum;//知识库下的知识被收藏次数
	private int audit;//是否审核（0是/1否）
	private String organ ;//用户所属部门ID
	
	private Date createtime ;
	private String creater;
	private String orgi ;
	private boolean datastatus;
	
	private String own ;
	private String kbtype;//知识库类型（knowledge 知识库 ）/（help 帮助平台）/（ask 问答）
	private String kbviewid;//访问知识库 短ID （base62生成）

	
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
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
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
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	public String getOwn() {
		return own;
	}
	public void setOwn(String own) {
		this.own = own;
	}
	public String getKbtype() {
		return kbtype;
	}
	public void setKbtype(String kbtype) {
		this.kbtype = kbtype;
	}
	public String getKbviewid() {
		return kbviewid;
	}
	public void setKbviewid(String kbviewid) {
		this.kbviewid = kbviewid;
	}
}
