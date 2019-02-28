package com.ukefu.webim.web.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Parent;

import com.ukefu.util.UKTools;

@Document(indexName = "uckefu", type = "uk_ekm_kb_collect" , createIndex = false )
@Entity
@Table(name = "uk_ekm_knowledge_collect")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeCollect implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3569382502397170880L;
	
	private String id = UKTools.getUUID();
	private int version;//版本号
	private String status = "true";//收藏状态
	private String knowledgeower ;//知识作者
	private String knowledgeid ;
	
	@Field(type = FieldType.String, store = true)  
    @Parent(type = "uk_ekm_kb_master")  
	private String kbid ;
	
	private EkmKnowledgeMaster knowledge;
	
	private String creater;
	private Date createtime ;
	private String orgi ;
	
	private String folderid ; //收藏夹id
	
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
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getKnowledgeower() {
		return knowledgeower;
	}
	public void setKnowledgeower(String knowledgeower) {
		this.knowledgeower = knowledgeower;
	}
	public String getKbid() {
		return kbid;
	}
	public void setKbid(String kbid) {
		this.kbid = kbid;
	}
	public String getFolderid() {
		return folderid;
	}
	public void setFolderid(String folderid) {
		this.folderid = folderid;
	}
	
}
