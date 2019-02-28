package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_qc_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityConfig implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1115593425069549681L;
	
	private String id ;
	
	private boolean phonetic ;			//是否开启语音转写（0关闭1打开）
	private String engine ;			//语音转写引擎（百度/讯飞）
	private String appid ;			//引擎的 App_Id
	private String secretkey ;		//引擎的 secret_key
	private String lfasrhost ;		//引擎的 api接口网址
	private String filepiecesize ;	//引擎的上传录音文件最大尺寸
	private String storepath ;		//引擎的转写结果保存位置
	
	private int maxthreads;		//最大线程数

	private String creater;
	private Date createtime ;
	private String updater;
	private Date updatetime ;

	private String orgi ;
	
	private int archivetime ;//质检自动归档时间
	private int aplarchivetime ;//已申诉质检自动归档时间
	
	private boolean phonetrans;
	
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
	public boolean isPhonetic() {
		return phonetic;
	}
	public void setPhonetic(boolean phonetic) {
		this.phonetic = phonetic;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getSecretkey() {
		return secretkey;
	}
	public void setSecretkey(String secretkey) {
		this.secretkey = secretkey;
	}
	public String getLfasrhost() {
		return lfasrhost;
	}
	public void setLfasrhost(String lfasrhost) {
		this.lfasrhost = lfasrhost;
	}
	public String getFilepiecesize() {
		return filepiecesize;
	}
	public void setFilepiecesize(String filepiecesize) {
		this.filepiecesize = filepiecesize;
	}
	public String getStorepath() {
		return storepath;
	}
	public void setStorepath(String storepath) {
		this.storepath = storepath;
	}
	public int getMaxthreads() {
		return maxthreads;
	}
	public void setMaxthreads(int maxthreads) {
		this.maxthreads = maxthreads;
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
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
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
	public int getArchivetime() {
		return archivetime;
	}
	public void setArchivetime(int archivetime) {
		this.archivetime = archivetime;
	}
	public int getAplarchivetime() {
		return aplarchivetime;
	}
	public void setAplarchivetime(int aplarchivetime) {
		this.aplarchivetime = aplarchivetime;
	}
	public boolean isPhonetrans() {
		return phonetrans;
	}
	public void setPhonetrans(boolean phonetrans) {
		this.phonetrans = phonetrans;
	}
	
}
