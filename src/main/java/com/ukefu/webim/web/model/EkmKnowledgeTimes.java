package com.ukefu.webim.web.model;

import javax.persistence.Transient;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

import com.ukefu.util.UKTools;
import com.ukefu.util.task.ESData;

@Document(indexName = "uckefu", type = "uk_ekm_kb_times" , createIndex = false)

public class EkmKnowledgeTimes  implements ESData,java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8667838872697390231L;
	private String id = UKTools.getUUID();
	private int viewtimes;//被浏览次数
	private int commentstimes;//被评论次数
	private int collectimes;//被收藏次数
	private int version;//版本号
	
	private String knowledgeid ;
	
	@Field(type = FieldType.String, store = true)  
    @Parent(type = "uk_ekm_kb_master")  
	private String kbid;
	
	private EkmKnowledgeMaster knowledge;
	
	private Date createtime ;
	private String orgi ;
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator= "paymentableGenerator")
	@GenericGenerator(name= "paymentableGenerator",strategy = "assigned")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getViewtimes() {
		return viewtimes;
	}
	public void setViewtimes(int viewtimes) {
		this.viewtimes = viewtimes;
	}
	public int getCommentstimes() {
		return commentstimes;
	}
	public void setCommentstimes(int commentstimes) {
		this.commentstimes = commentstimes;
	}
	public int getCollectimes() {
		return collectimes;
	}
	public void setCollectimes(int collectimes) {
		this.collectimes = collectimes;
	}
	public String getKnowledgeid() {
		return knowledgeid;
	}
	public void setKnowledgeid(String knowledgeid) {
		this.knowledgeid = knowledgeid;
	}
	
	@Transient
	public EkmKnowledgeMaster getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(EkmKnowledgeMaster knowledge) {
		this.knowledge = knowledge;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getKbid() {
		return kbid;
	}
	public void setKbid(String kbid) {
		this.kbid = kbid;
	}
	
}
