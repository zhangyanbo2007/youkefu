package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * QC质检 - 质检结果子表
 *
 */
@Entity
@Table(name = "uk_qc_result_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityResultItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 608284169849125614L;
	
	private String id ;
	private String name ;//名称
	private Date createtime ;
	private String creater;
	private String orgi ;
	private int maxscore ;//最高分数
	private int minscore ;//最低分数
	private int score ;//评分
	private String scheme ;//评分方案
	private String resultid ;//结果id
	private String parentid ;//质检项父级id
	private String type ;//质检项分类（plus评分/minus扣分/taboo禁忌项）
	private String remarks ;//质检项备注
	private String itemid ;//质检项id
	
	
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public int getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(int maxscore) {
		this.maxscore = maxscore;
	}
	public int getMinscore() {
		return minscore;
	}
	public void setMinscore(int minscore) {
		this.minscore = minscore;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	
	
	

}
