package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * EKM-总配置表
 *
 */
@Entity
@Table(name = "uk_ekm_configitem")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmConfigItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id  ;
	private String upfilesize;//知识附件上传的限制尺寸

	private Date createtime ;
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
	public String getUpfilesize() {
		return upfilesize;
	}
	public void setUpfilesize(String upfilesize) {
		this.upfilesize = upfilesize;
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
	
	
	
}
