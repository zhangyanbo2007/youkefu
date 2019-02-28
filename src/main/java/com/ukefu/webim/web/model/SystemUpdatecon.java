package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 系统升级设置表
 *
 */
@Entity
@Table(name = "uk_system_updatecon")
@org.hibernate.annotations.Proxy(lazy = false)
public class SystemUpdatecon implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1749844062507147795L;
	
	private String id;
	private boolean schedule; //启用后，系统会根据设定的时间和通知方式，在即将重启升级前，进行提前通知(0关闭/1开启)
	private String orgi;
	private Date upgradetime;
	private int scheduletimes;//升级前通知的时间间隔
	private Date updatetime = new Date();
	
	
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
	public boolean isSchedule() {
		return schedule;
	}
	public void setSchedule(boolean schedule) {
		this.schedule = schedule;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public Date getUpgradetime() {
		return upgradetime;
	}
	public void setUpgradetime(Date upgradetime) {
		this.upgradetime = upgradetime;
	}
	public int getScheduletimes() {
		return scheduletimes;
	}
	public void setScheduletimes(int scheduletimes) {
		this.scheduletimes = scheduletimes;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
}
