package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityResultItem;

public abstract interface QualityResultItemRepository extends JpaRepository<QualityResultItem,String>{
	
public abstract Page<QualityResultItem> findByOrgi(String orgi , Pageable page) ;
	
	public abstract List<QualityResultItem> findByOrgi(String orgi) ;
	
	public abstract QualityResultItem findByIdAndOrgi(String id, String orgi);
	
	public abstract QualityResultItem findByResultidAndItemidAndOrgi(String resultid,String itemid ,String orgi);
	
	public abstract List<QualityResultItem> findByResultidAndParentidAndOrgi(String resultid,String parentid ,String orgi);
	
	public abstract List<QualityResultItem> findByResultidAndOrgi(String resultid ,String orgi);

}
