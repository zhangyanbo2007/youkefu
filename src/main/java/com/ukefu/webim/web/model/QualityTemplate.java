package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 质检-模板表
 *
 */
@Entity
@Table(name = "uk_qc_template")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityTemplate implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5856183678852482424L;
	
	private String id ;
	private String name ;//名称
	private String organ ;//用户所属部门ID
	private Date createtime ;
	private Date updatetime ;
	private String creater;
	private String orgi ;
	private String arithmetic ;//算分机制(plus评分/minus扣分)
	private String type ;//模板类型
	private String status ;//模板状态
	private int totalscore ;//总分
	private int passscore ;//合格分
	private String remarks ;//备注
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
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
	public String getArithmetic() {
		return arithmetic;
	}
	public void setArithmetic(String arithmetic) {
		this.arithmetic = arithmetic;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotalscore() {
		return totalscore;
	}
	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}
	public int getPassscore() {
		return passscore;
	}
	public void setPassscore(int passscore) {
		this.passscore = passscore;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
