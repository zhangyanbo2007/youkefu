package com.ukefu.webim.web.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_dataevent")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataEvent implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7632315428995964771L;
	
	private String id ;
	private String name ;
	
	private String orgi ;
	private String modifyid;	//变更 ID， UUID，随机生成
	
	private User user ;
	private String creater ;
	private Date createtime ;
	
	private String content ;		//只发布  回复的 记录
	
	private String eventtype ;		//修改审批人员 ： 普通变更	
	
	private String dataid ;
	
	private List<PropertiesEvent> eventList ;
	
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

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getModifyid() {
		return modifyid;
	}

	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}
	@OneToOne
    @JoinColumn(name="creater",insertable=false , updatable = false,unique=false)  
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="modifyid")
	public List<PropertiesEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<PropertiesEvent> eventList) {
		this.eventList = eventList;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}	
