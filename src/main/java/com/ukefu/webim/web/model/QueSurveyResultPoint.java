package com.ukefu.webim.web.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷问题关注点
 */
@Entity
@Table(name = "uk_que_result_point")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResultPoint implements java.io.Serializable{

	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String eventid;//通话记录ID
	private String resultid;////结果主表ID
	private String resultqueid;////结果问题ID
	private String processid;//问卷ID
	private String questionid;//问题ID
	private String pointid;//关注点id
	private String answer;//回答
	private int answertime;//回答时长
	private String nameid;//es名单id
	private String orgi;//租户ID
	private String creater;//创建人
	private Date createtime;//创建时间
	
	private String pointname;//关注点名称
	private String pointtype;//关注点类型（0关键字 1通话时长）'
	private String focusword;//'关键字'
	private Integer mincalltime;//开始通话时长范围
	private Integer maxcalltime;//结束通话时长范围
	private int score;//'评分',
	
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
	public String getQuestionid() {
		return questionid;
	}
	public void setQuestionid(String questionid) {
		this.questionid = questionid;
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
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	public String getResultqueid() {
		return resultqueid;
	}
	public void setResultqueid(String resultqueid) {
		this.resultqueid = resultqueid;
	}
	public String getPointid() {
		return pointid;
	}
	public void setPointid(String pointid) {
		this.pointid = pointid;
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
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getNameid() {
		return nameid;
	}
	public void setNameid(String nameid) {
		this.nameid = nameid;
	}
	public int getAnswertime() {
		return answertime;
	}
	public void setAnswertime(int answertime) {
		this.answertime = answertime;
	}
	
	public String getPointname() {
		return pointname;
	}
	public void setPointname(String pointname) {
		this.pointname = pointname;
	}
	@Override
	public String toString() {
		return "QueSurveyResultPoint [id=" + id + ", eventid=" + eventid + ", resultid=" + resultid + ", resultqueid="
				+ resultqueid + ", processid=" + processid + ", questionid=" + questionid + ", pointid=" + pointid
				+ ", orgi=" + orgi + ", creater=" + creater + ", createtime=" + createtime
				+ ", pointtype=" + pointtype + ", focusword=" + focusword + ", mincalltime=" + mincalltime
				+ ", maxcalltime=" + maxcalltime + ", score=" + score + "]";
	}
	
	
	
	
}
