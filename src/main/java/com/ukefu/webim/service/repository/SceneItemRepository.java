package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.SceneItem;

public abstract interface SceneItemRepository extends JpaRepository<SceneItem, String> {
	
	public abstract SceneItem findByIdAndOrgi(String id, String orgi);

	public abstract List<SceneItem> findByOrgiAndSceneid(String orgi , String sceneid) ;
	
	public abstract List<SceneItem> findByOrgiAndSceneidAndItemtype(String orgi , String sceneid , String itemtype) ;
	
	public abstract List<SceneItem> findByOrgiAndItemtype(String orgi , String itemtype) ;

}
