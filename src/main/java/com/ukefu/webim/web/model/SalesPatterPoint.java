package com.ukefu.webim.web.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 话术-关注点表
 * @author kashing
 *
 */
@Entity
@Table(name = "uk_spt_point")
@org.hibernate.annotations.Proxy(lazy = false)
public class SalesPatterPoint implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1735624207903818828L;
	private String id ;
	private String questionid;//问题ID
	private String name;//名称
	private String pointtype;//关注点类型（0关键字 1通话时长）'
	private String focusword;//'关键字'
	private Integer mincalltime;//开始通话时长范围
	private Integer maxcalltime;//结束通话时长范围
	private int score;//'评分',
	private String orgi;//租户ID
	private String creater;//创建人
	private Date createtime;//创建时间
	private Date updatetime;//更新时间
	private String processid;//问卷ID

	
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
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}
	public String getPointtype() {
		return pointtype;
	}
	public void setPointtype(String pointtype) {
		this.pointtype = pointtype;
	}
	public String getFocusword() {
		return focusword;
	}
	public void setFocusword(String focusword) {
		this.focusword = focusword;
	}
	public Integer getMincalltime() {
		return mincalltime;
	}
	public void setMincalltime(Integer mincalltime) {
		this.mincalltime = mincalltime;
	}
	public Integer getMaxcalltime() {
		return maxcalltime;
	}
	public void setMaxcalltime(Integer maxcalltime) {
		this.maxcalltime = maxcalltime;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	public String getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid = processid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
