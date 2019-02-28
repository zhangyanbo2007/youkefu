package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 知识库 - 知识分类（按钮）授权表
 */
@Entity
@Table(name = "uk_ekm_knowledge_auth")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeTypeAuth implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String userid ;//用户
	private String knowledgetypeid;//知识分类ID
	private String knowledgebaseid;//知识库ID
	
	private String auth;//按钮
	private boolean view;
	private boolean cover;//是否覆盖由父级继承下来的权限
	
	private String organ ;
	private String orgi ;
	private String creater;
	private Date createtime ;
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getKnowledgetypeid() {
		return knowledgetypeid;
	}
	public void setKnowledgetypeid(String knowledgetypeid) {
		this.knowledgetypeid = knowledgetypeid;
	}
	public String getKnowledgebaseid() {
		return knowledgebaseid;
	}
	public void setKnowledgebaseid(String knowledgebaseid) {
		this.knowledgebaseid = knowledgebaseid;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
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
	public boolean isView() {
		return view;
	}
	public void setView(boolean view) {
		this.view = view;
	}
	public boolean isCover() {
		return cover;
	}
	public void setCover(boolean cover) {
		this.cover = cover;
	}
	
	
}
