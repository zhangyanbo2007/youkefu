package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 公告表
 *
 */
@Entity
@Table(name = "uk_notice")
@org.hibernate.annotations.Proxy(lazy = false)
public class Notice implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1749844062507147795L;
	
	private String id;
	private String title; //标题
	private String content;//内容
	private String summary;//摘要
	private String tags;//标签
	private String keyword;//关键字
	private Date createtime;
	private Date updatetime;
	private String creater;
	private String orgi;
	private String organ; //部门id
	
	private String smsserver ;
	private String emailserver ;
	
	private String smstemplate ;
	private String emailtemplate ;
	
	private String type ;//业务类型business、系统类型system、平台类型platform
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getSmsserver() {
		return smsserver;
	}
	public void setSmsserver(String smsserver) {
		this.smsserver = smsserver;
	}
	public String getEmailserver() {
		return emailserver;
	}
	public void setEmailserver(String emailserver) {
		this.emailserver = emailserver;
	}
	public String getSmstemplate() {
		return smstemplate;
	}
	public void setSmstemplate(String smstemplate) {
		this.smstemplate = smstemplate;
	}
	public String getEmailtemplate() {
		return emailtemplate;
	}
	public void setEmailtemplate(String emailtemplate) {
		this.emailtemplate = emailtemplate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
