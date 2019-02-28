package com.ukefu.webim.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 知识库配置表
 *
 */
@Entity
@Table(name = "uk_ekm_knowbase_config")
@org.hibernate.annotations.Proxy(lazy = false)
public class EkmKnowbaseConfig implements java.io.Serializable{
	
	private static final long serialVersionUID = -960440475418396868L;
	
	private String id ;
	private String knowbaseid ; //知识库id
	private String basehost ; //站点根网址
	private String webname ; //网站名称
	private String powerby ; //网站版权信息
	private String keywords ; //站点关键字
	private String description ; //站点描述
	private String beian ; //网站备案号
	private String footer ; //页脚
	private String indexlog ; //首页图标
	private String hotwords ; //搜索热词
	private String banner ; //海报图片
	private String creater ; //
	private String orgi ; //
	private String direction ;
	private String notfoundtip ;//未搜索到结果提示语
	
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
	public String getKnowbaseid() {
		return knowbaseid;
	}
	public void setKnowbaseid(String knowbaseid) {
		this.knowbaseid = knowbaseid;
	}
	public String getBasehost() {
		return basehost;
	}
	public void setBasehost(String basehost) {
		this.basehost = basehost;
	}
	public String getWebname() {
		return webname;
	}
	public void setWebname(String webname) {
		this.webname = webname;
	}
	public String getPowerby() {
		return powerby;
	}
	public void setPowerby(String powerby) {
		this.powerby = powerby;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBeian() {
		return beian;
	}
	public void setBeian(String beian) {
		this.beian = beian;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getIndexlog() {
		return indexlog;
	}
	public void setIndexlog(String indexlog) {
		this.indexlog = indexlog;
	}
	public String getHotwords() {
		return hotwords;
	}
	public void setHotwords(String hotwords) {
		this.hotwords = hotwords;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
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
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getNotfoundtip() {
		return notfoundtip;
	}
	public void setNotfoundtip(String notfoundtip) {
		this.notfoundtip = notfoundtip;
	}
	
}
