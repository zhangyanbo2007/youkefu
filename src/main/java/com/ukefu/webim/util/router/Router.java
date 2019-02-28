package com.ukefu.webim.util.router;

import com.ukefu.webim.web.model.MessageDataBean;

public abstract class Router {
	
	public abstract MessageDataBean handler(MessageDataBean message) ;
	
}
