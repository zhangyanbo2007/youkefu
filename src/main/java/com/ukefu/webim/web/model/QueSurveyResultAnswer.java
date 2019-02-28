package com.ukefu.webim.web.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 问卷结果主表 - 答案记录关联字表
 */
@Entity
@Table(name = "uk_que_result_answer")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyResultAnswer implements java.io.Serializable{

	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	private String resultid;//结果主表ID
	private String processid;//问卷ID
	private String questionid;//问题ID
	private int quetype;//问题类型（0选择题1问答题）
	private String answerid;//答案ID
	private String answer;//问题答案（问答题）
	private int answerscore;//答案评分
	private String correct;//是否是正确答案（0正确1不正确）
	
	private String orgi;//租户ID
	private String creater;//创建人
	private Date createtime;//创建时间
	private int answertime;//回答时长（单个问题回答时长）
	
	private String anstatus;//状态
	
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
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
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
	public int getQuetype() {
		return quetype;
	}
	public void setQuetype(int quetype) {
		this.quetype = quetype;
	}
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getAnswerscore() {
		return answerscore;
	}
	public void setAnswerscore(int answerscore) {
		this.answerscore = answerscore;
	}
	public String getCorrect() {
		return correct;
	}
	public void setCorrect(String correct) {
		this.correct = correct;
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
	public int getAnswertime() {
		return answertime;
	}
	public void setAnswertime(int answertime) {
		this.answertime = answertime;
	}
	public String getAnstatus() {
		return anstatus;
	}
	public void setAnstatus(String anstatus) {
		this.anstatus = anstatus;
	}
	
	
}
