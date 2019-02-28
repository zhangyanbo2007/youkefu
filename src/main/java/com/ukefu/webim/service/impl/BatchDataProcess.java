package com.ukefu.webim.service.impl;

import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.es.UKDataBean;
import com.ukefu.util.task.process.JPAProcess;
import com.ukefu.webim.web.model.MetadataTable;

public class BatchDataProcess implements JPAProcess{
	
	private MetadataTable metadata;
	private ESDataExchangeImpl esDataExchangeImpl ;
	private BulkRequestBuilder builder ;
	
	public BatchDataProcess(MetadataTable metadata , ESDataExchangeImpl esDataExchangeImpl) {
		this.metadata = metadata ;
		this.esDataExchangeImpl = esDataExchangeImpl ;
		builder = UKDataContext.getTemplet().getClient().prepareBulk() ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void process(Object data) {
		UKDataBean dataBean = new UKDataBean();
		if(data instanceof UKDataBean) {
			dataBean = (UKDataBean)data;
		}else {
			dataBean.setTable(this.metadata);
			dataBean.setValues((Map<String, Object>) data);
		}
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

	@Override
	public void end() {
		if(builder!=null && builder.numberOfActions() > 0) {
			builder.setRefresh(true).execute().actionGet();
		}
	}
}
