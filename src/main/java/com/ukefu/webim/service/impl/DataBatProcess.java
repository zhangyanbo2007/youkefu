package com.ukefu.webim.service.impl;

import org.elasticsearch.action.bulk.BulkRequestBuilder;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.task.process.JPAProcess;

public class DataBatProcess implements JPAProcess{
	
	private ESDataExchangeImpl esDataExchangeImpl ;
	private BulkRequestBuilder builder ;
	
	public DataBatProcess(ESDataExchangeImpl esDataExchangeImpl) {
		this.esDataExchangeImpl = esDataExchangeImpl ;
		builder = UKDataContext.getTemplet().getClient().prepareBulk() ;
	}
	
	@Override
	public void process(Object data) {
		if(data instanceof UKDataBean){
			UKDataBean dataBean = (UKDataBean)data;
			try {
				if(builder!=null) {
					builder.add(esDataExchangeImpl.saveBulk(dataBean)) ;
				}else {
					esDataExchangeImpl.saveIObject(dataBean);
				}
				if(builder.numberOfActions() % 1000 ==0) {
					builder.execute().actionGet();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void end() {
		if(builder!=null) {
			builder.setRefresh(true).execute().actionGet();
		}
	}
}
