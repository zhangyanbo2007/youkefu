package com.ukefu.webim.service.rpc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.corundumstudio.socketio.SocketIOClient;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.ukefu.util.client.NettyClients;
import com.ukefu.util.rpc.RPCDataBean;

public class IMTopicListener implements MessageListener<Object>{
	@Override
    public void onMessage(Message<Object> message) {
		RPCDataBean rpcDataBean = (RPCDataBean) message.getMessageObject() ;
		if(rpcDataBean!=null && !StringUtils.isBlank(rpcDataBean.getId())) {
			List<SocketIOClient> clents = NettyClients.getInstance().getIMClients().getClients(rpcDataBean.getId()) ;
			if(clents!=null && clents.size() > 0) {
				for(SocketIOClient client : clents){
					client.sendEvent(rpcDataBean.getEvent(), rpcDataBean.getData());
				}
			}
		}
    }
}
