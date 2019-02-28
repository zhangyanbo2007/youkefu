package com.ukefu.util.ai;

import java.io.IOException;
import java.util.List;

import com.ukefu.core.UKDataContext;
import com.ukefu.util.UKTools;
import com.ukefu.webim.service.repository.SceneItemRepository;
import com.ukefu.webim.web.model.AiConfig;
import com.ukefu.webim.web.model.SceneItem;

public class AiUtils {
	private static AiDicTrie aiDicTrie = new AiDicTrie();
	
	/**
	 * 初始化 AI语料库
	 * @param orgi
	 * @throws IOException
	 * @throws JcsegException 
	 */
	public static AiDicTrie init(String orgi) throws IOException{
		aiDicTrie.clean();
		SceneItemRepository sceneItemRes = UKDataContext.getContext().getBean(SceneItemRepository.class) ;
		List<SceneItem> sceneItemList = sceneItemRes.findByOrgiAndItemtype(orgi, UKDataContext.AiItemType.USERINPUT.toString()) ;
		for(SceneItem item : sceneItemList){
			aiDicTrie.insert(item.getContent(), item.getSceneid());
		}
		return aiDicTrie;
	}
	
	public static AiDicTrie getAiDicTrie(){
		return aiDicTrie ;
	}
	
	
	/**
	 * AI配置
	 * @param orgi
	 * @return
	 */
	public static AiConfig initAiConfig(String aiid,String orgi){
		return UKTools.initAiConfig(aiid, orgi) ;
	}
}
