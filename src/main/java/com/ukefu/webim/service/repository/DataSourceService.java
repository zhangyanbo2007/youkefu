package com.ukefu.webim.service.repository;

import mondrian.olap.Connection;
import mondrian.olap.DriverManager;
import mondrian.olap.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.pool.DruidDataSource;

@Service
public class DataSourceService {
	
	@Autowired
    private DruidDataSource dataSource;
	/**
	 * 
	 * @param cube
	 * @throws Exception
	 */
	public Connection service(String xml) throws Exception {
		Connection dataSourceObject = null ;
		StringBuffer strb = new StringBuffer();
		Util.PropertyList properties = Util.parseConnectString(strb.append("Provider=mondrian;")
					.append(
				       "Catalog=").append(xml).append(";").toString());
		if(properties!=null){
			dataSourceObject = DriverManager.getConnection(properties,null , dataSource) ;
		}
		return dataSourceObject ;
	}
}
