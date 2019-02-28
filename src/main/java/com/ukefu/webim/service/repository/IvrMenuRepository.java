package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.IvrMenu;

public interface IvrMenuRepository extends JpaRepository<IvrMenu, String> {
	
	public IvrMenu findByIdAndOrgi(String id , String orgi);
	public List<IvrMenu> findByHostidAndOrgi(String hostid , String orgi);
	public List<IvrMenu> findByExtentionidAndHostidAndOrgi(String extentionid,String hostid, String orgi);
	public int countByNameAndOrgi(String name, String orgi);
}
