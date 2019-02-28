package com.ukefu.webim.util.router;

import com.ukefu.webim.web.model.MessageOutContent;

public interface OutMessageRouter {
	
	public void handler(String touser, String msgtype , String appid ,  MessageOutContent outMessage)  ;
}
