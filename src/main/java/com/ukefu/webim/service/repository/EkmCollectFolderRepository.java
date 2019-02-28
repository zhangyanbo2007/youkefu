package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.EkmCollectFolder;

public interface EkmCollectFolderRepository extends JpaRepository<EkmCollectFolder,String>{

	public abstract List<EkmCollectFolder> findByCreaterAndOrgi(String creater , String orgi);
	
	public abstract List<EkmCollectFolder> findByCreaterAndParentidAndOrgi(String creater ,String parentid , String orgi);
	
	public abstract EkmCollectFolder findByCreaterAndNameAndOrgi(String creater ,String name , String orgi);
	
	public abstract EkmCollectFolder findByIdAndOrgi(String id , String orgi);
}
