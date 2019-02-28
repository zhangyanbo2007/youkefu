package com.ukefu.webim.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 质检系统 - （通话质检/工单质检/会话质检）表单筛选
 * 
 */
@Entity
@Table(name = "uk_qc_formfilter")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityFormFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2258870729818431384L;

	private String id;
	private String name;//表单名
	private String code;
	private Date createtime = new Date();
	private String creater;
	private Date updatetime = new Date();
	private String orgi;
	private String username;//创建人用户名
	private String status;
	private String filtertype;//筛选类型（callevent通话筛选/workorders工单筛选/agentservice会话筛选)
	private String tablename;
	private boolean datastatus;
	private String organ;
	private String description;//备注
	private int filternum;//筛选次数
	private int conditional;//条件个数
	private int execnum	;	//执行次数

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
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

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFiltertype() {
		return filtertype;
	}

	public void setFiltertype(String filtertype) {
		this.filtertype = filtertype;
	}

	public boolean isDatastatus() {
		return datastatus;
	}

	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFilternum() {
		return filternum;
	}

	public void setFilternum(int filternum) {
		this.filternum = filternum;
	}

	public int getConditional() {
		return conditional;
	}

	public void setConditional(int conditional) {
		this.conditional = conditional;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public int getExecnum() {
		return execnum;
	}

	public void setExecnum(int execnum) {
		this.execnum = execnum;
	}

	
}
