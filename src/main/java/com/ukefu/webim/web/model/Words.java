package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_xiaoe_words")
@org.hibernate.annotations.Proxy(lazy = false)
public class Words  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id  = UKTools.getUUID();
	
	private String keyword;
	private String superordinate ;	//上位词
	private String partofspeech ;	//词性
	private String content ;	//同义词
	private String cate ;		//分类
	
	private String orgi ;
	private String creater;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	
	public Words(){}
	
	public Words(String keyword , String superordinate , String content){
		this.keyword = keyword ;
		this.superordinate = superordinate ;
		this.content = content ;
	}
	
	/**
	 * @return the id
	 */
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")	
	public String getId() {
		return id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	public Date getUpdatetime() {
		return updatetime;
	}


	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getSuperordinate() {
		return superordinate;
	}


	public void setSuperordinate(String superordinate) {
		this.superordinate = superordinate;
	}


	public String getPartofspeech() {
		return partofspeech;
	}


	public void setPartofspeech(String partofspeech) {
		this.partofspeech = partofspeech;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCate() {
		return cate;
	}


	public void setCate(String cate) {
		this.cate = cate;
	}
}
