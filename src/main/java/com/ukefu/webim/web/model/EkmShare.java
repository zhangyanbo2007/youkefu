package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 知识 - 分享表
 *
 */
@Entity
@Table(name = "uk_ekm_knowledge_share")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmShare implements java.io.Serializable{

	
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String knowledgeid ;//知识ID
	private String knowledgeower ;
	private String shareuser ;

	private Date createtime ;
	private String creater;
	private String organid ;//用户所属部门ID
	private String orgi ;
	private boolean datastatus;
	

	
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
	public String getShareuser() {
		return shareuser;
	}
	public void setShareuser(String shareuser) {
		this.shareuser = shareuser;
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
	public String getOrganid() {
		return organid;
	}
	public void setOrganid(String organid) {
		this.organid = organid;
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
	
	
}
