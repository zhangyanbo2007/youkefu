package com.ukefu.webim.service.es;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.histogram.InternalDateHistogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;

import com.ukefu.webim.web.model.KbsTopic;
import com.ukefu.webim.web.model.KbsTopicComment;


public class UKAggResultExtractor extends UKResultMapper{
	
	private String term ;
	
	public UKAggResultExtractor(String term){
		this.term = term ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
		Aggregations aggregations = response.getAggregations();
		List<T> results = new ArrayList<T>();
		long total = 0 ;
		if(aggregations!=null && aggregations.get(term)!=null){
			if(aggregations.get(term) instanceof Terms){
				Terms agg = aggregations.get(term) ;
				if(agg!=null){
					total = agg.getSumOfOtherDocCounts() ;
					if(agg.getBuckets()!=null && agg.getBuckets().size()>0){
						for (Terms.Bucket entry : agg.getBuckets()) {
							if(clazz.equals(KbsTopic.class)){
								KbsTopic topic = new KbsTopic();
								topic.setCreater(entry.getKeyAsString());
								topic.setRowcount((int) entry.getDocCount());
								results.add((T) topic) ;
							}else if(clazz.equals(KbsTopicComment.class)){
								KbsTopicComment topicComment = new KbsTopicComment();
								topicComment.setCreater(entry.getKeyAsString());
								topicComment.setRowcount((int) entry.getDocCount());
								results.add((T) topicComment) ;
							}
						}
					}
				}
			}else if(aggregations.get(term) instanceof InternalDateHistogram){
				InternalDateHistogram agg = aggregations.get(term) ;
				total = response.getHits().getTotalHits() ;
				if(agg!=null){
	//				if(agg.getBuckets()!=null && agg.getBuckets().size()>0){
	//					for (DateHistogram.Bucket entry : agg.getBuckets()) {
	//						if(clazz.equals(KbsTopic.class)){
	//							KbsTopic topic = new KbsTopic();
	//							topic.setKey(entry.getKey().substring(0 , 10));
	//							topic.setRowcount((int) entry.getDocCount());
	//							results.add((T) topic) ;
	//						}	
	//					}
	//				}
				}
			}
		}
		return new AggregatedPageImpl<T>(results, pageable, total);
	}
}
