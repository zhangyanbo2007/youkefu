package com.ukefu.webim.service.es;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;

import com.ukefu.webim.service.es.UKResultMapper;

public class EKMResultMapper extends UKResultMapper {

	public <T> T mapEntity(String source , SearchHit hit , Class<T> clazz) {
		T t = mapEntity(source , clazz) ;
		
		Map<String, HighlightField> highlightFields = hit.getHighlightFields();
		HighlightField highlightNameField = highlightFields.get("title");
		HighlightField contentHightlightField = highlightFields.get("content");
		try {
			if(highlightNameField!=null&&highlightNameField.fragments()!=null){
				PropertyUtils.setProperty(t, "title" , highlightNameField.fragments()[0].string());
			}
			if(contentHightlightField!=null && contentHightlightField.fragments()!=null && contentHightlightField.fragments().length >0 && !StringUtils.isBlank(contentHightlightField.fragments()[0].string())){
				PropertyUtils.setProperty(t, "summary" , contentHightlightField.fragments()[0].string());
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		} 
		return t;
	}
}