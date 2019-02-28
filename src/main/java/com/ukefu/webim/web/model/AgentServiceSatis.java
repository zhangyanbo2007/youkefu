package com.ukefu.webim.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "uk_agentservice")
@Proxy(lazy = false)
public class AgentServiceSatis implements Serializable {
	private static final long serialVersionUID = -5052623717164550681L;

	private String orgi;
	private String id;
	
	
	private boolean satisfaction ;
	private Date satistime ;
	private String satislevel ;
	private String satiscomment ;
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public boolean isSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(boolean satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Date getSatistime() {
		return satistime;
	}

	public void setSatistime(Date satistime) {
		this.satistime = satistime;
	}

	public String getSatislevel() {
		return satislevel;
	}

	public void setSatislevel(String satislevel) {
		this.satislevel = satislevel;
	}

	public String getSatiscomment() {
		return satiscomment;
	}

	public void setSatiscomment(String satiscomment) {
		this.satiscomment = satiscomment;
	}

	public void setId(String id) {
		this.id = id;
	}
}
