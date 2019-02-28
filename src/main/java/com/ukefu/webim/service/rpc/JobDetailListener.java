package com.ukefu.webim.service.rpc;

import org.apache.commons.lang3.StringUtils;

import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;
import com.ukefu.core.UKDataContext;
import com.ukefu.util.rpc.RPCDataBean;
import com.ukefu.webim.web.model.JobDetail;

public class JobDetailListener implements MessageListener<Object>{
	@Override
    public void onMessage(Message<Object> message) {
		RPCDataBean rpcDataBean = (RPCDataBean) message.getMessageObject() ;
		if(rpcDataBean!=null && !StringUtils.isBlank(rpcDataBean.getId())) {
			JobDetail jobDetail = UKDataContext.localJobDetailMap.get(rpcDataBean.getId()) ;
			if(jobDetail!=null) {
				jobDetail.setFetcher(false);
			}
		}
    }
}
