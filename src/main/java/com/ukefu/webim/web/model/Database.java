/**
 * Licensed to the Rivulet under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     webapps/LICENSE-Rivulet
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ukefu.webim.web.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "uk_databasetask")
@org.hibernate.annotations.Proxy(lazy = false)
public class Database implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6953830122384734119L;
	private String id;//主键id
	private String name;//资源名称
	private String code;//访问标识
	private String address; // Database Address ip or domain
	private String lastcrawl; // Multiple files will be saved over the end of
								// time
	private String account ; //用户名
	private String password ;//密码
	private String attachment;// 附件
	private int port ;			//Database Server Port
	private String databasetype ;//Mysql Oracle SQL Server
	private String databasename ; //ORACLE SID  ; SQL Server/MySQL Database Name
	private String connectparam ; //DriverManage Param
	private String encoding ;//编码
	private String databaseurl ;  //Database URL
	private String driverclazz ;  //Database Driver
	private String configure ;		//区分大小写	
	private String secureconf ;//安全配置
	private String previewtemplet ;//预览模板
	private String listblocktemplet ;//单块样板
	private String sqldialect;//方言
	private String orgi ;

	
	private String jndiname;//JDNI名称
	private String jndiparam;//JDNI更多参数
	private String connctiontype;//区分连接的是哪个驱动
	
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
	@Transient
	public String getType(){
		return "database" ;
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

	public String getLastcrawl() {
		return lastcrawl;
	}

	public void setLastcrawl(String lastcrawl) {
		this.lastcrawl = lastcrawl;
	}
	public String getConnctiontype() {
		return connctiontype;
	}
	public void setConnctiontype(String connctiontype) {
		this.connctiontype = connctiontype;
	}

	/**
	 * @return the attachment
	 */
	public String getAttachment() {
		return attachment;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getDoctype() {
		return doctype;
	}

	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}

	public String getSecure() {
		return secure;
	}

	public void setSecure(String secure) {
		this.secure = secure;
	}

	// @OneToOne
	// @JoinColumn(name = "secureconfigure")
	// public SecureConfigure getSecureconfigure() {
	// return secureconfigure;
	// }
	// public void setSecureconfigure(SecureConfigure secureconfigure) {
	// this.secureconfigure = secureconfigure;
	// }
	public Date getLastupdate() {
		return lastupdate;
	}

	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	private String doctype;
	private String secure;
	// private SecureConfigure secureconfigure;
	private Date lastupdate;
	private String taskname;
	private String taskplan; // task time plan
	private String taskstatus;
	private String tasktype; // Automated tasks : Manual tasks
	private Date createtime;

	private String userid ;
	/**
	 * @return the groupid
	 */
	public String getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	private String groupid;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	private String createuser;

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getTasktype() {
		return tasktype;
	}

	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getTaskplan() {
		return taskplan;
	}

	public void setTaskplan(String taskplan) {
		this.taskplan = taskplan;
	}

	public String getTaskstatus() {
		return taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the databasetype
	 */
	public String getDatabasetype() {
		return databasetype;
	}

	/**
	 * @param databasetype the databasetype to set
	 */
	public void setDatabasetype(String databasetype) {
		this.databasetype = databasetype;
	}

	/**
	 * @return the databasename
	 */
	public String getDatabasename() {
		return databasename;
	}

	/**
	 * @param databasename the databasename to set
	 */
	public void setDatabasename(String databasename) {
		this.databasename = databasename;
	}

	/**
	 * @return the connectparam
	 */
	public String getConnectparam() {
		return connectparam;
	}

	/**
	 * @param connectparam the connectparam to set
	 */
	public void setConnectparam(String connectparam) {
		this.connectparam = connectparam;
	}

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @return the databaseurl
	 */
	public String getDatabaseurl() {
		return databaseurl;
	}

	/**
	 * @param databaseurl the databaseurl to set
	 */
	public void setDatabaseurl(String databaseurl) {
		this.databaseurl = databaseurl;
	}

	/**
	 * @return the driverclazz
	 */
	public String getDriverclazz() {
		return driverclazz;
	}

	/**
	 * @param driverclazz the driverclazz to set
	 */
	public void setDriverclazz(String driverclazz) {
		this.driverclazz = driverclazz;
	}
	/**
	 * @return the configure
	 */
	public String getConfigure() {
		return configure;
	}
	/**
	 * @param configure the configure to set
	 */
	public void setConfigure(String configure) {
		this.configure = configure;
	}
	/**
	 * @return the secureconf
	 */
	public String getSecureconf() {
		return secureconf;
	}
	/**
	 * @param secureconf the secureconf to set
	 */
	public void setSecureconf(String secureconf) {
		this.secureconf = secureconf;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPreviewtemplet() {
		return previewtemplet;
	}
	public void setPreviewtemplet(String previewtemplet) {
		this.previewtemplet = previewtemplet;
	}
	public String getListblocktemplet() {
		return listblocktemplet;
	}
	public void setListblocktemplet(String listblocktemplet) {
		this.listblocktemplet = listblocktemplet;
	}
	public String getSqldialect() {
		return sqldialect;
	}
	public void setSqldialect(String sqldialect) {
		this.sqldialect = sqldialect;
	}
	
	/**
	 * @return the jndiname
	 */
	public String getJndiname() {
		return jndiname;
	}
	/**
	 * @param jndiname the jndiname to set
	 */
	public void setJndiname(String jndiname) {
		this.jndiname = jndiname;
	}

	/**
	 * @return the jndiparam
	 */
	public String getJndiparam() {
		return jndiparam;
	}
	/**
	 * @param jndiparam the jndiparam to set
	 */
	public void setJndiparam(String jndiparam) {
		this.jndiparam = jndiparam;
	}
}
