package com.ukefu.webim.service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.ukefu.webim.web.model.CalloutSaleCount;

public interface CalloutSaleCountRepository extends JpaRepository<CalloutSaleCount,String>{
	
	public List<CalloutSaleCount> findByOrgi(String orgi);
	
	@Transactional
    @Modifying
    @Query("delete from CalloutSaleCount where orgi = ?1")
	public void deleteByOrgi(String orgi);
	
	public List<CalloutSaleCount> findByOrgiAndDataid(String orgi, String dataid);
}
