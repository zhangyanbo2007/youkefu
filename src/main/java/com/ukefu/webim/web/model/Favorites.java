package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

import com.ukefu.util.UKTools;

@Document(indexName = "uckefu", type = "uk_favorites" , createIndex = false )
@Entity
@Table(name = "uk_favorites")
@org.hibernate.annotations.Proxy(lazy = false)
public class Favorites implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8667838872697390231L;
	private String id = UKTools.getUUID();
	private String name ;
	private String code = "true";
	private String title ;
	private String model ;
	
	@Field(type = FieldType.String, store = true)  
    @Parent(type = "uk_workorders")  
	private String orderid ;
	
	private WorkOrders workOrders;
	
	
	private Date createtime = new Date();
	
	private Date updatetime = new Date();
	private String creater;
	private String username ;
	private String orgi ;
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator= "paymentableGenerator")
	@GenericGenerator(name= "paymentableGenerator",strategy = "assigned")
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Transient
	public WorkOrders getWorkOrders() {
		return workOrders;
	}
	public void setWorkOrders(WorkOrders workOrders) {
		this.workOrders = workOrders;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
}
