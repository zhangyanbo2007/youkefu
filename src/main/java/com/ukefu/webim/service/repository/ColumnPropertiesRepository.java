package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.ColumnProperties;

public abstract interface ColumnPropertiesRepository extends JpaRepository<ColumnProperties, String> {

	public List<ColumnProperties> findByDataid(String id);

	public List<ColumnProperties> findByModelid(String id);

	public List<ColumnProperties> findByModelidAndCurOrderBySortindexAsc(String id, String cur);

	public ColumnProperties findByIdAndOrgi(String id, String orgi);

}
