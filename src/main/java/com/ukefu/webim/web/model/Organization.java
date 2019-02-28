package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author ricy Tenant.java 2010-3-17
 * 
 */
@Entity
@Table(name = "uk_organization")
@org.hibernate.annotations.Proxy(lazy = false)
public class Organization implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id ;
	private String name;
	private String code;
	private String orgtype ;	//机构类型
	private String orgscale;//规模
	private String orgindustry;//行业
	private Date createtime  = new Date();//创建时间
	private String logo ;
	private String memo ;//
	
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrgtype() {
		return orgtype;
	}
	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrgscale() {
		return orgscale;
	}
	public void setOrgscale(String orgscale) {
		this.orgscale = orgscale;
	}
	public String getOrgindustry() {
		return orgindustry;
	}
	public void setOrgindustry(String orgindustry) {
		this.orgindustry = orgindustry;
	}
}
