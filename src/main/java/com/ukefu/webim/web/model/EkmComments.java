package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 评论表
 *
 */
@Entity
@Table(name = "uk_ekm_comments")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmComments implements java.io.Serializable{

	
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String knowledgeid ;//知识ID
	private String knowledgeower ;//知识ID
	private String dismenid ;//所属维度ID（根级目录）
	private String dismentypeid ;//所属维度分类ID（分支ID）
	private String knowledgetypeid ;//所属知识分类ID
	private String knowbaseid ;//所属知识库ID
	private String content ;//评论内容

	private Date createtime ;//评论时间
	private String creater;//评论人
	private String organ ;//用户所属部门ID
	private String orgi ;
	private boolean datastatus;
	
	private double rate ;//评分
	private String title;

	
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
	public String getDismenid() {
		return dismenid;
	}
	public void setDismenid(String dismenid) {
		this.dismenid = dismenid;
	}
	public String getDismentypeid() {
		return dismentypeid;
	}
	public void setDismentypeid(String dismentypeid) {
		this.dismentypeid = dismentypeid;
	}
	public String getKnowledgetypeid() {
		return knowledgetypeid;
	}
	public void setKnowledgetypeid(String knowledgetypeid) {
		this.knowledgetypeid = knowledgetypeid;
	}
	public String getKnowbaseid() {
		return knowbaseid;
	}
	public void setKnowbaseid(String knowbaseid) {
		this.knowbaseid = knowbaseid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	public String getKnowledgeower() {
		return knowledgeower;
	}
	public void setKnowledgeower(String knowledgeower) {
		this.knowledgeower = knowledgeower;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
