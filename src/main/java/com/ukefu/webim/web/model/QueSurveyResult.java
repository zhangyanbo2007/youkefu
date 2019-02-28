package com.ukefu.webim.web.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷结果主表 - 通话记录关联
 */
@Entity
@Table(name = "uk_que_result")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResult implements java.io.Serializable{

	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String eventid;//通话记录ID
	private String processid;//问卷ID
	private String orgi;//租户ID
	private String creater;//创建人
	private Date createtime;//创建时间
	private Date endtime;//结束时间
	
	private String busstype;//业务类型（sale,quesurvey  //AiBussType
	private int processtime;//问答时长
	private int asktimes;//提问次数
	private int answertimes;//回答次数
	private int answertime;//回答时长（每个回答时间总和）
	private int errortimes;//回答错误次数
	private int timeouttimes;//回答超时次数
	private int retimes ;		//重复次数
	private String actid;//活动ID
	private String batchid;//批次ID
	private String filterid;//筛选表单ID
	private String formfilterid;//筛选记录ID
	private String nameid;//名单ID
	private String mobile;//手机号
	private String sumscore;//问卷评分
	private String organ;
	
	private int focustimes;//关注点次数
	private String level;//评级 a b c d
	
	private String discalled;//
	private String distype ;		//号码隐藏方式

	
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
	public String getEventid() {
		return eventid;
	}
	public void setEventid(String eventid) {
		this.eventid = eventid;
	}
	public String getProcessid() {
		return processid;
	}
	public void setProcessid(String processid) {
		this.processid = processid;
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
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public int getProcesstime() {
		return processtime;
	}
	public void setProcesstime(int processtime) {
		this.processtime = processtime;
	}
	public int getAsktimes() {
		return asktimes;
	}
	public void setAsktimes(int asktimes) {
		this.asktimes = asktimes;
	}
	public int getAnswertimes() {
		return answertimes;
	}
	public void setAnswertimes(int answertimes) {
		this.answertimes = answertimes;
	}
	public int getAnswertime() {
		return answertime;
	}
	public void setAnswertime(int answertime) {
		this.answertime = answertime;
	}
	public int getErrortimes() {
		return errortimes;
	}
	public void setErrortimes(int errortimes) {
		this.errortimes = errortimes;
	}
	public int getTimeouttimes() {
		return timeouttimes;
	}
	public void setTimeouttimes(int timeouttimes) {
		this.timeouttimes = timeouttimes;
	}
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	public String getFilterid() {
		return filterid;
	}
	public void setFilterid(String filterid) {
		this.filterid = filterid;
	}
	public String getFormfilterid() {
		return formfilterid;
	}
	public void setFormfilterid(String formfilterid) {
		this.formfilterid = formfilterid;
	}
	public String getNameid() {
		return nameid;
	}
	public void setNameid(String nameid) {
		this.nameid = nameid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSumscore() {
		return sumscore;
	}
	public void setSumscore(String sumscore) {
		this.sumscore = sumscore;
	}
	public int getRetimes() {
		return retimes;
	}
	public void setRetimes(int retimes) {
		this.retimes = retimes;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getBusstype() {
		return busstype;
	}
	public void setBusstype(String busstype) {
		this.busstype = busstype;
	}
	public int getFocustimes() {
		return focustimes;
	}
	public void setFocustimes(int focustimes) {
		this.focustimes = focustimes;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getDiscalled() {
		return discalled;
	}
	public void setDiscalled(String discalled) {
		this.discalled = discalled;
	}
	public String getDistype() {
		return distype;
	}
	public void setDistype(String distype) {
		this.distype = distype;
	}
	
	
}
