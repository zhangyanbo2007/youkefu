package com.ukefu.webim.util.router;

import com.ukefu.webim.web.model.MessageDataBean;
import com.ukefu.webim.web.model.MessageOutContent;

public class AgentUserRouter extends Router{

	@Override
	public MessageDataBean handler(MessageDataBean message) {
		MessageDataBean outMessage = new MessageOutContent() ;
		return outMessage;
	}
}
