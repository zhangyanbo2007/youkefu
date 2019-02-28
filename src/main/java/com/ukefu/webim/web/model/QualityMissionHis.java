package com.ukefu.webim.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;
/**
 * 	QC质检 - 任务历史表
 *
 */
@Entity
@Table(name = "uk_qc_mission_his")
@Proxy(lazy = false)
public class QualityMissionHis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2796276564445713776L;

	private String id ;
	private Date createtime = new Date() ;
	private Date updatetime = new Date() ;
	private String organ ;  //部门 
	private String orgi ;  //租户ID 
	
	private String dataid ;  //数据ID（uk_callcenter_event 通话/uk_workorders 工单/uk_agentservice 在线客服） 
	private String templateid ;  //质检模板ID 
	private String actid ;  //质检活动ID 
	private String formfilterid ;  //质检筛选表单ID 
	private String filterid ;  //质检筛选记录ID 
	private String taskid ;  //质检任务ID 
	private boolean datastatus ;  //数据状态
	
	private String qualitystatus ;  //质检状态 
	private String qualitydisorgan ;  //分配的质检部门 
	private String qualitydisuser ;  //分配的质检用户 
	private String qualityorgan ;  //实际质检部门 
	private String qualityuser ;  //实际质检人 
	private int qualityscore ;  //质检得分 
	private Date qualitytime ;  //质检时间 
	private String qualitytype ;  //质检类型 
	private String agentdata ;  //会话质检（访客用户名） 
	
	private String assuser;//分配执行人
	
	private String appealremarks; //申诉备注
	private String arbremarks; //仲裁备注
	private String rejectremarks; //驳回备注
	private String resultid; //结果id

	private int qualitypass=2; //质检是否合格(默认2为未质检)'
	private int qualityappeal=2; //质检是否申诉过(默认2为未质检)
	private int qualityarbitrate=2; //质检是否仲裁过(默认2为未质检)
	
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
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	public String getFormfilterid() {
		return formfilterid;
	}
	public void setFormfilterid(String formfilterid) {
		this.formfilterid = formfilterid;
	}
	public String getFilterid() {
		return filterid;
	}
	public void setFilterid(String filterid) {
		this.filterid = filterid;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	public String getQualitystatus() {
		return qualitystatus;
	}
	public void setQualitystatus(String qualitystatus) {
		this.qualitystatus = qualitystatus;
	}
	public String getQualitydisorgan() {
		return qualitydisorgan;
	}
	public void setQualitydisorgan(String qualitydisorgan) {
		this.qualitydisorgan = qualitydisorgan;
	}
	public String getQualitydisuser() {
		return qualitydisuser;
	}
	public void setQualitydisuser(String qualitydisuser) {
		this.qualitydisuser = qualitydisuser;
	}
	public String getQualityorgan() {
		return qualityorgan;
	}
	public void setQualityorgan(String qualityorgan) {
		this.qualityorgan = qualityorgan;
	}
	public String getQualityuser() {
		return qualityuser;
	}
	public void setQualityuser(String qualityuser) {
		this.qualityuser = qualityuser;
	}
	public int getQualityscore() {
		return qualityscore;
	}
	public void setQualityscore(int qualityscore) {
		this.qualityscore = qualityscore;
	}
	public Date getQualitytime() {
		return qualitytime;
	}
	public void setQualitytime(Date qualitytime) {
		this.qualitytime = qualitytime;
	}
	public String getQualitytype() {
		return qualitytype;
	}
	public void setQualitytype(String qualitytype) {
		this.qualitytype = qualitytype;
	}
	public String getAgentdata() {
		return agentdata;
	}
	public void setAgentdata(String agentdata) {
		this.agentdata = agentdata;
	}
	public String getAssuser() {
		return assuser;
	}
	public void setAssuser(String assuser) {
		this.assuser = assuser;
	}
	public String getAppealremarks() {
		return appealremarks;
	}
	public void setAppealremarks(String appealremarks) {
		this.appealremarks = appealremarks;
	}
	public String getArbremarks() {
		return arbremarks;
	}
	public void setArbremarks(String arbremarks) {
		this.arbremarks = arbremarks;
	}
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	public String getRejectremarks() {
		return rejectremarks;
	}
	public void setRejectremarks(String rejectremarks) {
		this.rejectremarks = rejectremarks;
	}
	public int getQualitypass() {
		return qualitypass;
	}
	public void setQualitypass(int qualitypass) {
		this.qualitypass = qualitypass;
	}
	public int getQualityappeal() {
		return qualityappeal;
	}
	public void setQualityappeal(int qualityappeal) {
		this.qualityappeal = qualityappeal;
	}
	public int getQualityarbitrate() {
		return qualityarbitrate;
	}
	public void setQualityarbitrate(int qualityarbitrate) {
		this.qualityarbitrate = qualityarbitrate;
	}
	
	
	
}
