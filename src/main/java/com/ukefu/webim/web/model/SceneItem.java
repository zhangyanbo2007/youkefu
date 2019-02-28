package com.ukefu.webim.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.ukefu.util.UKTools;

@Entity
@Table(name = "uk_xiaoe_sceneitem")
@org.hibernate.annotations.Proxy(lazy = false)
public class SceneItem  implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id  = UKTools.getUUID();
	
	private String content ;	//内容
	private String orgi ;
	private String creater;
	private Date createtime = new Date();
	private Date updatetime = new Date();
	
	private String replaytype ;	//回复方式 ， random ; order
	private boolean allowask ;	//允许AI自动询问用户
	
	private String sceneid ;
	private int inx ;		//位置  ， 
	private String itemtype ;	//类型， 用户提问 ： AI回复
	
	
	/**
	 * @return the id
	 */
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "assigned")	
	public String getId() {
		return id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
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


	public String getSceneid() {
		return sceneid;
	}


	public void setSceneid(String sceneid) {
		this.sceneid = sceneid;
	}


	public int getInx() {
		return inx;
	}


	public void setInx(int inx) {
		this.inx = inx;
	}


	public String getItemtype() {
		return itemtype;
	}


	public void setItemtype(String itemtype) {
		this.itemtype = itemtype;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getReplaytype() {
		return replaytype;
	}


	public void setReplaytype(String replaytype) {
		this.replaytype = replaytype;
	}


	public boolean isAllowask() {
		return allowask;
	}


	public void setAllowask(boolean allowask) {
		this.allowask = allowask;
	}
}
