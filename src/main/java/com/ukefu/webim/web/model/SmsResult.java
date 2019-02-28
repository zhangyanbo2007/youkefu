/**
 * 
 */
package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author iceworld
 *
 */
@Entity
@Table(name = "uk_sms_record")
@org.hibernate.annotations.Proxy(lazy = false)
public class SmsResult implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1946579239823440392L;
	private String id ;
	private String name ;
	private String code ;		//修改用处，变更为 SysDic 的 code 		
	private String userid ;					
	private String groupid ;			
	private String description ;
	private String templettitle;//邮件头
	private String smstext ;	//邮件内容
	private Date subtime ;	//提交时间
	private Date sendtime;	//发送时间
	private String smsid;	//发送网关
	private String extention;//分机ID
	private String extno ;	//分机号码
	private String sendresult;	//发送结果
	
	private String organ ;		//部门
	
	private String smstype ;	//发送的语音网关类型
	private String appkey ;		//发送短信的 APPKEY
	
	private boolean sendok ;	//发送成功
	
	
	private String dataid ;		//UKDataBean对象ID
	private String taskid ;		//任务ID
	private String filterid;	//筛选ID
	private String actid ;		//活动ID
	private String batid ;		//批次ID
	
	private String phonenumber;	//电话号码
	private String distype ;	//号码隐藏
	
	private String templettype ; //List OR Preview
	private Date createtime = new Date();
	private String orgi ;
	private String iconstr;	//模板图标
	private String memo ; 	//模板说明内容
	private String typeid;//分组id
	private int layoutcols ;		//列数
	private String datatype ;		//样例数据
	private String charttype ;		//报表类型
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSmstext() {
		return smstext;
	}
	public void setSmstext(String smstext) {
		this.smstext = smstext;
	}
	public Date getSubtime() {
		return subtime;
	}
	public void setSubtime(Date subtime) {
		this.subtime = subtime;
	}
	public Date getSendtime() {
		return sendtime;
	}
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	public String getSmsid() {
		return smsid;
	}
	public void setSmsid(String smsid) {
		this.smsid = smsid;
	}
	public String getSendresult() {
		return sendresult;
	}
	public void setSendresult(String sendresult) {
		this.sendresult = sendresult;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getTemplettype() {
		return templettype;
	}
	public void setTemplettype(String templettype) {
		this.templettype = templettype;
	}
	@Transient
	public String getTitle(){
		return this.groupid ;
	}
	public String getIconstr() {
		return iconstr;
	}
	public void setIconstr(String iconstr) {
		this.iconstr = iconstr;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTemplettitle() {
		return templettitle;
	}
	public void setTemplettitle(String templettitle) {
		this.templettitle = templettitle;
	}
	public int getLayoutcols() {
		return layoutcols;
	}
	public void setLayoutcols(int layoutcols) {
		this.layoutcols = layoutcols;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getCharttype() {
		return charttype;
	}
	public void setCharttype(String charttype) {
		this.charttype = charttype;
	}
	public String getSmstype() {
		return smstype;
	}
	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	public String getFilterid() {
		return filterid;
	}
	public void setFilterid(String filterid) {
		this.filterid = filterid;
	}
	public String getActid() {
		return actid;
	}
	public void setActid(String actid) {
		this.actid = actid;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getDistype() {
		return distype;
	}
	public void setDistype(String distype) {
		this.distype = distype;
	}
	public String getBatid() {
		return batid;
	}
	public void setBatid(String batid) {
		this.batid = batid;
	}
	public String getOrgan() {
		return organ;
	}
	public void setOrgan(String organ) {
		this.organ = organ;
	}
	public boolean isSendok() {
		return sendok;
	}
	public void setSendok(boolean sendok) {
		this.sendok = sendok;
	}
	public String getExtention() {
		return extention;
	}
	public void setExtention(String extention) {
		this.extention = extention;
	}
	public String getExtno() {
		return extno;
	}
	public void setExtno(String extno) {
		this.extno = extno;
	}
}
