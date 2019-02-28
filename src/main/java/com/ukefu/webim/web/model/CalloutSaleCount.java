package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_sale_count")
@org.hibernate.annotations.Proxy(lazy = false)
public class CalloutSaleCount implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String dataid ;//坐席/部门/机器人ID
	private String type ;//类型（坐席user/部门organ/机器人ai）
	private int namenum ;//分配总数
	private int notcall ;//未拨打

	private int callsuccess ;//拨打成功
	private int callfaild ;//拨打失败
	private int aptrue ;//已预约
	private int apfalse ;//未预约
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
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNamenum() {
		return namenum;
	}
	public void setNamenum(int namenum) {
		this.namenum = namenum;
	}
	public int getNotcall() {
		return notcall;
	}
	public void setNotcall(int notcall) {
		this.notcall = notcall;
	}
	public int getCallsuccess() {
		return callsuccess;
	}
	public void setCallsuccess(int callsuccess) {
		this.callsuccess = callsuccess;
	}
	public int getCallfaild() {
		return callfaild;
	}
	public void setCallfaild(int callfaild) {
		this.callfaild = callfaild;
	}
	public int getAptrue() {
		return aptrue;
	}
	public void setAptrue(int aptrue) {
		this.aptrue = aptrue;
	}
	public int getApfalse() {
		return apfalse;
	}
	public void setApfalse(int apfalse) {
		this.apfalse = apfalse;
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
