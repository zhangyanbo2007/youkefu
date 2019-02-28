package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_ekm_knowledge_verison")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowledgeVersion implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3083028783904910295L;
	
	private String id  ;
	private String knowid ;//知识id
	private String title ;//名称
	private String summary;//摘要
	private String content;//内容
	private String tags;//标签
	private String keyword;//关键字
	private String dimenid;//所属维度ID（根级目录）
	private String dimentypeid;//所属维度分类ID（分支ID）
	private String organ ;//所属部门ID
	private String knowledgetypeid ;//所属知识分类ID
	private String knowbaseid ;//所属知识库ID
	private String pubstatus ;//知识状态（新建 new/审核中 wait/发布成功 pass/被驳回 rejected /已下架 down）
	private boolean datastatus;//数据状态
	private int version;//版本号
	private String knowledgetype ;//知识类型

	private Date begintime ;//有效期-开始
	private Date endtime ;//有效期-结束
	
	private Date createtime = new Date();
	private String creater;
	private String orgi ;
	
	private String auditor;
	
	private String nlpnr;//人名nr
	private String nlpns;//地名ns
	private String nlpnt;//机构名称nt
	private String nlpnz;//其他专有名词nz
	private String keyphrase;//关键短语
	
	private Date updatetime;
	private String attr;

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

	public String getKnowid() {
		return knowid;
	}

	public void setKnowid(String knowid) {
		this.knowid = knowid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getDimenid() {
		return dimenid;
	}

	public void setDimenid(String dimenid) {
		this.dimenid = dimenid;
	}

	public String getDimentypeid() {
		return dimentypeid;
	}

	public void setDimentypeid(String dimentypeid) {
		this.dimentypeid = dimentypeid;
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}

	public String getKnowledgetypeid() {
		return knowledgetypeid;
	}

	public void setKnowledgetypeid(String knowledgetypeid) {
		this.knowledgetypeid = knowledgetypeid;
	}

	public String getKnowbaseid() {
		return knowbaseid;
	}

	public void setKnowbaseid(String knowbaseid) {
		this.knowbaseid = knowbaseid;
	}

	public String getPubstatus() {
		return pubstatus;
	}

	public void setPubstatus(String pubstatus) {
		this.pubstatus = pubstatus;
	}

	public boolean isDatastatus() {
		return datastatus;
	}

	public void setDatastatus(boolean datastatus) {
		this.datastatus = datastatus;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getKnowledgetype() {
		return knowledgetype;
	}

	public void setKnowledgetype(String knowledgetype) {
		this.knowledgetype = knowledgetype;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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

	public String getOrgi() {
		return orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getNlpnr() {
		return nlpnr;
	}

	public void setNlpnr(String nlpnr) {
		this.nlpnr = nlpnr;
	}

	public String getNlpns() {
		return nlpns;
	}

	public void setNlpns(String nlpns) {
		this.nlpns = nlpns;
	}

	public String getNlpnt() {
		return nlpnt;
	}

	public void setNlpnt(String nlpnt) {
		this.nlpnt = nlpnt;
	}

	public String getNlpnz() {
		return nlpnz;
	}

	public void setNlpnz(String nlpnz) {
		this.nlpnz = nlpnz;
	}

	public String getKeyphrase() {
		return keyphrase;
	}

	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

}
