package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.PbxHostLog;

public interface PbxHostLogRepository extends JpaRepository<PbxHostLog, String> {
	
	public PbxHostLog findByIdAndOrgi(String id , String orgi);
	public List<PbxHostLog> findByHostidAndOrgi(String hostid,String orgi, Pageable paramPageable);
}
