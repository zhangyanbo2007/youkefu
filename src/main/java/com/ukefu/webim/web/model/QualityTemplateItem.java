package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 质检-模板质检项表
 *
 */
@Entity
@Table(name = "uk_qc_template_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityTemplateItem implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4814525962510834222L;

	private String id ;
	private String name ;//名称
	private Date createtime ;
	private String creater;
	private String orgi ;
	private int maxscore ;//最高分数
	private int minscore ;//最低分数
	private String scheme ;//评分方案
	private String templateid ;//质检模板id
	private String parentid ;//父级id
	private String type ;//质检项分类（plus评分/minus扣分/taboo禁忌项）
	
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
	public int getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(int maxscore) {
		this.maxscore = maxscore;
	}
	public int getMinscore() {
		return minscore;
	}
	public void setMinscore(int minscore) {
		this.minscore = minscore;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
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
	
	
}
