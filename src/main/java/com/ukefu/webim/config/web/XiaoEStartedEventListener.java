package com.ukefu.webim.config.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.ai.AiUtils;
import com.ukefu.util.ai.DicSegment;
import com.ukefu.webim.service.cache.CacheHelper;
import com.ukefu.webim.service.repository.SceneItemRepository;
import com.ukefu.webim.service.repository.SceneRepository;
import com.ukefu.webim.service.repository.WordsRepository;
import com.ukefu.webim.web.model.Scene;
import com.ukefu.webim.web.model.SceneItem;

@Component
public class XiaoEStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {
	@Value("${web.upload-path}")
    private String path;
	
	@Autowired
	private SceneRepository sceneRes ;
	
	@Autowired
	private SceneItemRepository sceneItemRes ;
	
	@Autowired
	private WordsRepository wordsRes ;
	
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	try {
    		DicSegment.loadDic(new File(path , "dic").getAbsolutePath(),wordsRes.findByOrgi(UKDataContext.SYSTEM_ORGI));
			AiUtils.init(UKDataContext.SYSTEM_ORGI);
			/**
			 * 缓存对话场景条目
			 */
			List<Scene> sceneList = sceneRes.findAll() ;
			List<SceneItem> sceneItemList = sceneItemRes.findAll() ;
			for(Scene scene : sceneList){
				List<SceneItem> sceneItems = new ArrayList<SceneItem>();
				for(SceneItem sceneItem : sceneItemList){
					if(sceneItem.getSceneid()!=null && sceneItem.getSceneid().equals(scene.getId()) && sceneItem.getItemtype().equals(UKDataContext.AiItemType.AIREPLY.toString())){
						sceneItems.add(sceneItem) ;
					}
				}
				CacheHelper.getSystemCacheBean().put(scene.getId(), sceneItems, scene.getOrgi());
				
				CacheHelper.getSystemCacheBean().put("scene."+scene.getId(), scene, scene.getOrgi());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}