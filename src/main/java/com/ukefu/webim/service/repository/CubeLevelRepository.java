package com.ukefu.webim.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukefu.webim.web.model.CubeLevel;

public abstract interface CubeLevelRepository extends JpaRepository<CubeLevel, String> {

	public List<CubeLevel> findByOrgiAndDimid(String orgi, String dimid);

	public List<CubeLevel> findByCubeid(String cubeid);

	public int countByTablename(String tablename);

}
