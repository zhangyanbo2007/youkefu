package com.ukefu.webim.service.rpc;

import org.apache.commons.lang3.StringUtils;

import com.corundumstudio.socketio.SocketIONamespace;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.rpc.RPCDataBean;

public class NameSpaceTopicListener implements MessageListener<Object>{
	@Override
    public void onMessage(Message<Object> message) {
		RPCDataBean rpcDataBean = (RPCDataBean) message.getMessageObject() ;
		if(rpcDataBean!=null && !StringUtils.isBlank(rpcDataBean.getId())) {
			UKDataContext.getContext().getBean(rpcDataBean.getId() , SocketIONamespace.class) .getBroadcastOperations().sendEvent(rpcDataBean.getEvent(), rpcDataBean.getData());
		}
    }
}
