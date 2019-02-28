package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 公告-接收内容表
 *
 */
@Entity
@Table(name = "uk_noticemsg")
@org.hibernate.annotations.Proxy(lazy = false)
public class NoticeMsg implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5240050970734645845L;
	
	private String id;//主键ID
	private String name;//名称
	private Date createtime;//创建时间
	private String creater;//创建人
	private String createrusername;//发布推送人id
	private String orgi;
	private String target;//推送对象
	private String title; //标题
	private String content;//内容
	private String summary;//摘要
	private String tags;//标签
	private String keyword;//关键字	
	private boolean status;//阅读状态（0 未读|1已读）
	private boolean datastatus;//数据状态（0 未删除|1 删除到回收站）
	
	private String type ;//业务类型business、系统类型system、平台类型platform
	
	private String terracetype  ;//平台公告类型（通知 notice /系统升级 system）
	
	private String jarurl ;//下载jar包地址
	
	private boolean confirm ;//是否确认升级
	
	private Date invalidtime;//失效时间
	
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
	public String getCreaterusername() {
		return createrusername;
	}
	public void setCreaterusername(String createrusername) {
		this.createrusername = createrusername;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isDatastatus() {
		return datastatus;
	}
	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTerracetype() {
		return terracetype;
	}
	public void setTerracetype(String terracetype) {
		this.terracetype = terracetype;
	}
	public String getJarurl() {
		return jarurl;
	}
	public void setJarurl(String jarurl) {
		this.jarurl = jarurl;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	public Date getInvalidtime() {
		return invalidtime;
	}
	public void setInvalidtime(Date invalidtime) {
		this.invalidtime = invalidtime;
	}
	

}
