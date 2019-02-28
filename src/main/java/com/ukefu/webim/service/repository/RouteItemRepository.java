package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.RouteItem;

public interface RouteItemRepository extends JpaRepository<RouteItem, String> {
	
	public RouteItem findByIdAndOrgi(String id , String orgi);
	
	public List<RouteItem> findByRouteidAndOrgi(String routeid , String orgi , Sort sort);
	public List<RouteItem> findByRouteidAndHostidAndOrgi(String routeid,String hostid, String orgi , Sort sort);
	public int countByNameAndOrgi(String name, String orgi);
}
