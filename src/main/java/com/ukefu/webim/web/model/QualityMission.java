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
 * 	QC质检 - 任务主表
 *
 */
@Entity
@Table(name="uk_qc_mission")
@Proxy(lazy=true)
public class QualityMission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id ;  //主键ID 
	private String name ;  //任务名称（系统分配生成） 
	private Date createtime ;  //创建时间 
	private String creater ;  //创建人 
	private Date updatetime ;  //更新时间 
	private String orgi ;  //租户ID 
	private String assuser ;  //分配执行人 
	private Date asstime ;  //分配时间 
	private String status ;  //状态 
	private String filtertype ;  //筛选类型（callevent通话筛选/workorders工单筛选/agentservice会话筛选） 
	private String dataid ;  //数据ID（通话记录ID/工单记录ID/会话记录ID） 
	private String datakey ;  //数据（通话（主叫号码）/工单记录（工单标题）/会话记录（访客用户名）） 
	private String datavalue ;  //数据（通话（被叫号码）/工单记录（处理人）/会话记录（服务坐席）） 
	private String templateid ;  //质检模板ID 
	private String actid ;  //质检活动ID 
	private String formfilterid ;  //质检筛选表单ID 
	private String filterid ;  //质检筛选记录ID 
	private String taskid ;  //质检任务ID 
	private int datastatus;  //数据状态 
	private String qualitystatus ;  //质检状态 
	private String qualitydisorgan ;  //分配的质检部门 
	private String qualitydisuser ;  //分配的质检用户 
	private String qualityorgan ;  //实际质检部门 
	private String qualityuser ;  //实际质检人 
	private int qualityscore;  //质检得分 
	private Date qualitytime ;  //质检时间 
	private String qualitytype ;  //质检类型 
	
	private String agentdata ;  //会话质检（访客用户名）
	
	private String organ ;  //部门
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid",strategy= "uuid")
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
	public String getAssuser() {
		return assuser;
	}
	public void setAssuser(String assuser) {
		this.assuser = assuser;
	}
	public Date getAsstime() {
		return asstime;
	}
	public void setAsstime(Date asstime) {
		this.asstime = asstime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFiltertype() {
		return filtertype;
	}
	public void setFiltertype(String filtertype) {
		this.filtertype = filtertype;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getDatakey() {
		return datakey;
	}
	public void setDatakey(String datakey) {
		this.datakey = datakey;
	}
	public String getDatavalue() {
		return datavalue;
	}
	public void setDatavalue(String datavalue) {
		this.datavalue = datavalue;
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
	public int getDatastatus() {
		return datastatus;
	}
	public void setDatastatus(int datastatus) {
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
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	
	

}
