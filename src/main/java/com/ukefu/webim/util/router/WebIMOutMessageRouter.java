package com.ukefu.webim.util.router;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ukefu.util.client.NettyClients;
import com.ukefu.webim.web.model.MessageOutContent;

@Component
public class WebIMOutMessageRouter implements OutMessageRouter{

	@Bean(name="webim")
	public WebIMOutMessageRouter initWebIMessageRouter(){
		return new WebIMOutMessageRouter() ;
	}
	@Override
	public void handler(String touser, String msgtype, String appid,
			MessageOutContent outMessage) {
		NettyClients.getInstance().sendIMEventMessage(touser, msgtype, outMessage);
	}

}
