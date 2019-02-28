package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_recentuser")
@org.hibernate.annotations.Proxy(lazy = false)
public class RecentUser implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8151070598454810118L;
	private String id;
	private String orgi;
	private int newmsg ;
	private String lastmsg ;
	private String creater ;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	private User user ;
	
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
	@OneToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getNewmsg() {
		return newmsg;
	}
	public void setNewmsg(int newmsg) {
		this.newmsg = newmsg;
	}
	public String getLastmsg() {
		return lastmsg;
	}
	public void setLastmsg(String lastmsg) {
		this.lastmsg = lastmsg;
	}	
}
