package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * EKM-访客记录，浏览表
 *
 */
@Entity
@Table(name = "uk_ekm_access")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmAccess implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id  ;
	private String knowledgeid;//知识ID
	private String knowledgeower;//知识所属人ID
	private boolean datastatus;//数据状态
	private int version;//版本号

	private Date createtime ;
	private String creater;
	private String orgi ;
	
	

	
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
	public String getKnowledgeid() {
		return knowledgeid;
	}
	public void setKnowledgeid(String knowledgeid) {
		this.knowledgeid = knowledgeid;
	}
	public String getKnowledgeower() {
		return knowledgeower;
	}
	public void setKnowledgeower(String knowledgeower) {
		this.knowledgeower = knowledgeower;
	}
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
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
	
	
	
}
