package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ukefu.util.UKTools;
/**
 * 质检系统 - 筛选表单-条件
 *
 */
@Entity
@Table(name = "uk_qc_formfilter_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityFormFilterItem  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id  = UKTools.getUUID();
	
	private String qcformfilterid;
	
	private String field ;	//字段
	private String cond;	//条件
	private String value;	//值
	
	
	private String contype;	//条件类型
	
	private String orgi ;
	private String creater;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	
	private String itemtype ;	//类型， 
	
	private String comp ;		//逻辑条件
	
	
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


	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getCond() {
		return cond;
	}


	public void setCond(String cond) {
		this.cond = cond;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getContype() {
		return contype;
	}


	public void setContype(String contype) {
		this.contype = contype;
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


	public String getItemtype() {
		return itemtype;
	}


	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}

	public String getComp() {
		return comp;
	}


	public void setComp(String comp) {
		this.comp = comp;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getQcformfilterid() {
		return qcformfilterid;
	}


	public void setQcformfilterid(String qcformfilterid) {
		this.qcformfilterid = qcformfilterid;
	}


	
	
	
}