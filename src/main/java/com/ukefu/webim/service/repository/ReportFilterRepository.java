package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.ReportFilter;

public abstract interface ReportFilterRepository extends JpaRepository< ReportFilter, String> {


	public ReportFilter findByIdAndOrgi(String id, String orgi);
	
	public ReportFilter findById(String id);

	public List<ReportFilter> findByModelidOrderBySortindexAsc(String modelid);

	public List<ReportFilter> findByCascadeidAndOrgi(String id, String orgi);

	public List<ReportFilter> findByReportidAndFiltertypeAndOrgi(String reportid,String filtertype, String orgi);
}
