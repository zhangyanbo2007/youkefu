package com.ukefu.webim.service.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.JobDetail;

public abstract interface JobDetailRepository extends JpaRepository<JobDetail, String> {
	
	public abstract JobDetail findByIdAndOrgi(String id, String orgi);

	public abstract Page<JobDetail> findByTasktypeAndOrgi(String tasktype , String orgi , Pageable page) ;
	
	public abstract Page<JobDetail> findByTaskstatus(String taskstatus , Pageable page) ;
	
	public abstract List<JobDetail> findByTasktypeAndOrgi(String tasktype , String orgi) ;
	
	public abstract Page<JobDetail> findByPlantaskAndTaskstatusAndNextfiretimeLessThan(boolean plantask ,String taskstatus,Date time , Pageable page) ;
	
	public abstract Page<JobDetail> findAll(Specification<JobDetail> spec, Pageable page) ;
	
	public abstract List<JobDetail> findAll(Specification<JobDetail> spec) ;
	
	public abstract List<JobDetail> findByOrgiAndTasktypeAndOrganLike(String orgi, String tasktype, String organ);
	
	
	public abstract List<JobDetail> findByOrgiAndBatchtypeAndOrganid(String orgi, String batchtype, String organ);
	
	//通过orgi和机器人id查询
	public abstract List<JobDetail> findByOrgiAndExtention(String orgi, String extention);
	
	public abstract Page<JobDetail> findByOrgiAndForecastid(String orgi, String forecastid,Pageable page);
}
