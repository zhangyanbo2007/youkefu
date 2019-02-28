package com.ukefu.webim.web.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷问题结果 - 通话记录关联
 */
@Entity
@Table(name = "uk_que_result_question")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResultQuestion implements java.io.Serializable{

	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String eventid;//通话记录ID
	private String resultid;////结果主表ID
	private String processid;//问卷ID
	private String questionid;//问题ID
	private String orgi;//租户ID
	private String creater;//创建人
	private Date createtime;//创建时间
	private Date endtime;//结束时间
	
	private int processtime;//问答时长 //统计 类型为时长的关注点
	private int asktimes;//提问次数
	private int answertimes;//回答次数
	private int answertime;//回答时长（每个回答时间总和）
	private int errortimes;//回答错误次数
	private int timeouttimes;//回答超时次数
	private int retimes ;		//重复次数
	private String answer;//回答内容 多次，分割
	private String nameid;//名单ID
	private String mobile;//手机号
	private int sumscore;//问题总评分
	private int score;//问题评分
	private String organ;
	
	private int focustimes;//关注点次数
	
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
	public int getRetimes() {
		return retimes;
	}
	public void setRetimes(int retimes) {
		this.retimes = retimes;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public int getSumscore() {
		return sumscore;
	}
	public void setSumscore(int sumscore) {
		this.sumscore = sumscore;
	}
	public int getFocustimes() {
		return focustimes;
	}
	public void setFocustimes(int focustimes) {
		this.focustimes = focustimes;
	}
	
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "QueSurveyResultQuestion [id=" + id + ", eventid=" + eventid + ", resultid=" + resultid + ", processid="
				+ processid + ", questionid=" + questionid + ", orgi=" + orgi + ", creater=" + creater + ", createtime="
				+ createtime + ", endtime=" + endtime + ", processtime=" + processtime + ", asktimes=" + asktimes
				+ ", answertimes=" + answertimes + ", answertime=" + answertime + ", errortimes=" + errortimes
				+ ", timeouttimes=" + timeouttimes + ", retimes=" + retimes + ", answer=" + answer + ", nameid="
				+ nameid + ", mobile=" + mobile + ", sumscore=" + sumscore + ", score=" + score + ", organ=" + organ
				+ ", focustimes=" + focustimes + "]";
	}

	
	
	
}
