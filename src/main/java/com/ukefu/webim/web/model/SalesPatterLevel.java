package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 话术-评级表
 * @author kashing
 *
 */
@Entity
@Table(name = "uk_spt_level")
@org.hibernate.annotations.Proxy(lazy = false)
public class SalesPatterLevel implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7504688689801569089L;
	
	private String id ;
	private String name;//'评级名称'
	private int sortindex;//序号
	private String processid;//话术ID'
	private Integer minscore;//开始评分范围
	private Integer maxscore;//结束评分范围
	private String orgi;//租户ID
	private String creater;//创建人
	private Date createtime;//创建时间
	private Date updatetime;//更新时间

	
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
	public int getSortindex() {
		return sortindex;
	}
	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}
	public String getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid = processid;
	}
	public Integer getMinscore() {
		return minscore;
	}
	public void setMinscore(Integer minscore) {
		this.minscore = minscore;
	}
	public Integer getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(Integer maxscore) {
		this.maxscore = maxscore;
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

}
