package com.ukefu.util.es;


import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ukefu.core.UKDataContext;
import com.ukefu.webim.web.model.MetadataTable;
import com.ukefu.webim.web.model.TableProperties;

public class ESTools {
	private static final Logger log = LoggerFactory.getLogger(ESTools	.class); 
	public static boolean checkMapping(String tb,String orgi){
		return UKDataContext.getTemplet().typeExists(orgi, tb) ;
	}
	
	public static void mapping(MetadataTable tb , String orgi) throws ElasticsearchException, IOException{
		log.info(tb.getTablename()+" ORGI : "+orgi+" Mapping Not Exists , Waiting Form init ......");

		XContentBuilder builder = jsonBuilder().startObject()  
                .startObject(tb.getTablename().toLowerCase())
                .startObject("properties")  ;
		for(TableProperties tp:tb.getTableproperty()){
			builder.startObject(tp.getFieldname().toLowerCase()) ;
			if(tp.getDatatypename().equalsIgnoreCase("text") && !tp.getFieldname().equalsIgnoreCase("id")){
				builder.field("type", "string");
				if(tp.isToken() && "keyword".equalsIgnoreCase(tp.getTokentype())){
					builder.field("analyzer" , "whitespace") ;
				}
				if(!tp.isToken()){
					builder.field("ignore_above" , "256") ;
				}
			}else if(tp.getDatatypename().toLowerCase().equals("date") ){
				builder.field("type", "long");
			}else if(tp.getDatatypename().toLowerCase().equals("datetime")){
				builder.field("type", "long");
			}else if(tp.getDatatypename().toLowerCase().equals("long")){
				builder.field("type", "long");
			}else if(tp.getDatatypename().toLowerCase().equals("textarea")){
				builder.field("type", "string");
			}else if(tp.getDatatypename().toLowerCase().equals("nlp")){
				builder.field("type", "string");
			}else if(tp.getDatatypename().toLowerCase().equals("url")){
				builder.field("type", "string");
			}else if(tp.getDatatypename().toLowerCase().equals("email")){
				builder.field("type", "string");
			}else if(tp.getDatatypename().toLowerCase().equals("number") ){
				builder.field("type", "float");
			}else if(tp.getDatatypename().toLowerCase().equals("boolean") ){
				builder.field("type", "boolean");
			}else{
				builder.field("type", "string");
			}
			builder.endObject() ;
		}
		builder.endObject().endObject().endObject();
        UKDataContext.getTemplet().putMapping(UKDataContext.CALLOUT_INDEX, tb.getTablename(), builder);
	}
}
