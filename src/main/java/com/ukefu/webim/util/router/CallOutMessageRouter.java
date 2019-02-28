package com.ukefu.webim.util.router;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.ukefu.webim.web.model.MessageOutContent;

@Component
public class CallOutMessageRouter implements OutMessageRouter{

	@Bean(name="phone")
	public CallOutMessageRouter initWebIMessageRouter(){
		return new CallOutMessageRouter() ;
	}
	@Override
	public void handler(String touser, String msgtype, String appid,
			MessageOutContent outMessage) {
		//do nothing
		//电话渠道通过TTS播放给电话系统
	}

}
