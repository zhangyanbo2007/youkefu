package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * QC质检 - 质检结果主表
 *
 */
@Entity
@Table(name = "uk_qc_result")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityResult implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -726547850228590446L;
	
	private String id ;
	private String orgi ;		//租户ID
	private String creater ;//创建人
	private String missionid ;//任务主表id
	private String dataid ;//数据ID（通话记录ID/工单记录ID/会话记录ID）
	
	private String remarks ;//质检备注
	private String adcom ;//优点评语
	private String qacom ;//QA评语
	private String imcom ;//改进评语
	
	private Date createtime ;	//创建时间
	private String arithmetic ;//算分机制(plus评分/minus扣分)
	private int score ;//实际得分
	private int passscore ;//合格分
	private int totalscore ;//总分
	private String qualityuser ;//实际质检人
	
	private String status ;//	状态（已质检、已归档）
	private Date archivedate ; //归档日期
	
	private String qualitytype ; //质检类型
	
	private boolean isvp ;//是否有否决权（1是/0否）
	private boolean isadcom ;//是否有优点评语（1是/0否）
	private boolean isqacom ;//是否QA评语（1是/0否）
	private boolean isimcom ;//是否有改进评语（1是/0否）
	private boolean isrmk ;//质检时是否有备注（1是/0否）
	private boolean isitemrmk ;//质检项是否能填备注（1是/0否）
	private boolean isitemdir ;//质检项是否有说明（1是/0否）
	
	
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
	public String getMissionid() {
		return missionid;
	}
	public void setMissionid(String missionid) {
		this.missionid = missionid;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAdcom() {
		return adcom;
	}
	public void setAdcom(String adcom) {
		this.adcom = adcom;
	}
	public String getQacom() {
		return qacom;
	}
	public void setQacom(String qacom) {
		this.qacom = qacom;
	}
	public String getImcom() {
		return imcom;
	}
	public void setImcom(String imcom) {
		this.imcom = imcom;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getArithmetic() {
		return arithmetic;
	}
	public void setArithmetic(String arithmetic) {
		this.arithmetic = arithmetic;
	}
	public int getPassscore() {
		return passscore;
	}
	public void setPassscore(int passscore) {
		this.passscore = passscore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getQualityuser() {
		return qualityuser;
	}
	public void setQualityuser(String qualityuser) {
		this.qualityuser = qualityuser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getArchivedate() {
		return archivedate;
	}
	public void setArchivedate(Date archivedate) {
		this.archivedate = archivedate;
	}
	public String getQualitytype() {
		return qualitytype;
	}
	public void setQualitytype(String qualitytype) {
		this.qualitytype = qualitytype;
	}
	public boolean isIsvp() {
		return isvp;
	}
	public void setIsvp(boolean isvp) {
		this.isvp = isvp;
	}
	public boolean isIsadcom() {
		return isadcom;
	}
	public void setIsadcom(boolean isadcom) {
		this.isadcom = isadcom;
	}
	public boolean isIsqacom() {
		return isqacom;
	}
	public void setIsqacom(boolean isqacom) {
		this.isqacom = isqacom;
	}
	public boolean isIsimcom() {
		return isimcom;
	}
	public void setIsimcom(boolean isimcom) {
		this.isimcom = isimcom;
	}
	public boolean isIsrmk() {
		return isrmk;
	}
	public void setIsrmk(boolean isrmk) {
		this.isrmk = isrmk;
	}
	public boolean isIsitemrmk() {
		return isitemrmk;
	}
	public void setIsitemrmk(boolean isitemrmk) {
		this.isitemrmk = isitemrmk;
	}
	public boolean isIsitemdir() {
		return isitemdir;
	}
	public void setIsitemdir(boolean isitemdir) {
		this.isitemdir = isitemdir;
	}
	
	
}
