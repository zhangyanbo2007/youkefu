package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_ekm_audit")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmAudit implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7158755032475918151L;

	private String id;
	private String knowid;
	private Date knowtime;//知识创建时间
	private String pubstatus;//审核状态（待审核 wait/通过 pass/驳回 rejected）
	private String auditor;//审核人ID
	private Date auditime;//审核时间
	private String creater;//创建人（知识所属人，提交审核人）
	private Date createtime;//创建时间（提交审核时间）
	private String orgi;
	private String reject;//驳回原因
	private int version;//版本号
	private boolean datastatus;//数据状态（逻辑删除，0未删除/1删除）
	private String knowtitle;//知识标题
	private String auditorname;//审核人名称
	private String knowcreatername;//知识创建人名称
	private String organ;//所属部门id
	
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
	public String getKnowid() {
		return knowid;
	}
	public void setKnowid(String knowid) {
		this.knowid = knowid;
	}
	public Date getKnowtime() {
		return knowtime;
	}
	public void setKnowtime(Date knowtime) {
		this.knowtime = knowtime;
	}
	public String getPubstatus() {
		return pubstatus;
	}
	public void setPubstatus(String pubstatus) {
		this.pubstatus = pubstatus;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getAuditime() {
		return auditime;
	}
	public void setAuditime(Date auditime) {
		this.auditime = auditime;
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getReject() {
		return reject;
	}
	public void setReject(String reject) {
		this.reject = reject;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	public String getKnowtitle() {
		return knowtitle;
	}
	public void setKnowtitle(String knowtitle) {
		this.knowtitle = knowtitle;
	}
	public String getAuditorname() {
		return auditorname;
	}
	public void setAuditorname(String auditorname) {
		this.auditorname = auditorname;
	}
	public String getKnowcreatername() {
		return knowcreatername;
	}
	public void setKnowcreatername(String knowcreatername) {
		this.knowcreatername = knowcreatername;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	
	
	
	
	
}
