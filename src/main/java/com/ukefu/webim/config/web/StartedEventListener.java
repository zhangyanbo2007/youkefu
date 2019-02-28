package com.ukefu.webim.config.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.BlackListRepository;
import com.ukefu.webim.service.repository.GenerationRepository;
import com.ukefu.webim.service.repository.SysDicRepository;
import com.ukefu.webim.service.repository.SystemConfigRepository;
import com.ukefu.webim.service.repository.TablePropertiesRepository;
import com.ukefu.webim.web.model.BlackEntity;
import com.ukefu.webim.web.model.Generation;
import com.ukefu.webim.web.model.SysDic;
import com.ukefu.webim.web.model.SystemConfig;

@Component
public class StartedEventListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Value("${web.upload-path}")
    private String path;
	
	private SysDicRepository sysDicRes;
	
	private BlackListRepository blackListRes ;

	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    	if(UKDataContext.getContext() == null){
    		UKDataContext.setApplicationContext(event.getApplicationContext());
    	}
    	sysDicRes = event.getApplicationContext().getBean(SysDicRepository.class) ;
    	blackListRes = event.getApplicationContext().getBean(BlackListRepository.class) ;
    	List<SysDic> sysDicList = sysDicRes.findAll() ;
    	
    	for(SysDic dic : sysDicList){
    		CacheHelper.getSystemCacheBean().put(dic.getId(), dic, dic.getOrgi());
			if(dic.getParentid().equals("0")){
				List<SysDic> sysDicItemList = new ArrayList<SysDic>();
				for(SysDic item : sysDicList){
					if(item.getDicid()!=null && item.getDicid().equals(dic.getId())){
						sysDicItemList.add(item) ;
					}
				}
				CacheHelper.getSystemCacheBean().put(dic.getCode(), sysDicItemList, dic.getOrgi());
			}
		}
    	List<BlackEntity> blackList = blackListRes.findByOrgi(UKDataContext.SYSTEM_ORGI) ;
    	for(BlackEntity black : blackList){
    		if(!StringUtils.isBlank(black.getUserid())) {
	    		if(black.getEndtime()==null || black.getEndtime().after(new Date())){
	    			CacheHelper.getSystemCacheBean().put(black.getUserid(), black, black.getOrgi());
	    		}
    		}
    	}
    	/**
    	 * 加载系统全局配置
    	 */
    	SystemConfigRepository systemConfigRes = event.getApplicationContext().getBean(SystemConfigRepository.class) ;
    	SystemConfig config = systemConfigRes.findByOrgi(UKDataContext.SYSTEM_ORGI) ;
    	if(config != null){
    		CacheHelper.getSystemCacheBean().put("systemConfig", config, UKDataContext.SYSTEM_ORGI);
    	}
    	GenerationRepository generationRes = event.getApplicationContext().getBean(GenerationRepository.class) ;
    	List<Generation> generationList = generationRes.findAll() ;
    	for(Generation generation : generationList){
    		CacheHelper.getSystemCacheBean().setAtomicLong(UKDataContext.ModelType.WORKORDERS.toString(), generation.getStartinx());
    	}
    	
    	UKTools.initSystemArea();
    	
    	UKTools.initSystemSecField(event.getApplicationContext().getBean(TablePropertiesRepository.class));
    	//UKTools.initAdv();//初始化广告位
    }
}