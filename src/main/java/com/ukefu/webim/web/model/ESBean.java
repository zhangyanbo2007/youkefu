package com.ukefu.webim.web.model;

import javax.persistence.Transient;

public abstract class ESBean {
	private User user ;
	
	@Transient
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public abstract boolean isDatastatus() ;
}
