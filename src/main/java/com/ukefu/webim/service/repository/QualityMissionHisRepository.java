package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.QualityMissionHis;

public abstract interface QualityMissionHisRepository  extends JpaRepository<QualityMissionHis,String>{
	
	
	public abstract List<QualityMissionHis> findByOrgi(String orgi);
	
	public abstract List<QualityMissionHis> findByDataidAndOrgi(String dataid, String orgi);
	
	public abstract List<QualityMissionHis> findByFormfilteridAndOrgi(String formfilterid, String orgi);
	
	public abstract List<QualityMissionHis> findByActidAndOrgi(String actid, String orgi);
	
	public abstract List<QualityMissionHis> findByFilteridAndOrgi(String filterid, String orgi);
	
	public abstract List<QualityMissionHis> findByTaskidAndOrgi(String taskid, String orgi);
	
	public abstract List<QualityMissionHis> findByQualitypassAndOrgi(String qualitypass, String orgi);
	
	public  Page<QualityMissionHis> findAll(Specification<QualityMissionHis> spec, Pageable pageable);  //分页按条件查询 
	
	public abstract List<QualityMissionHis> findByOrgiAndQualitydisuser(String orgi,String qualitydisuser);
}
