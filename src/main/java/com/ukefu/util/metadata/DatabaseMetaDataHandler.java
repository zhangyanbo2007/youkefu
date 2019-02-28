/**
 * Licensed to the Rivulet under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     webapps/LICENSE-Rivulet-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ukefu.util.metadata;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ukefu.util.TabelSql;
import com.ukefu.webim.web.model.Database;


/**
 * @author jaddy0302 Rivulet DatabaseMetaDataHandler.java 2010-3-21
 * 
 */
public class DatabaseMetaDataHandler{
	/**
	 * 
	 * @param database
	 * @return
	 * @throws Exception
	 */
	public static List<UKTableMetaData> getTables(Connection conn) throws Exception{
		List<UKTableMetaData> tables = null ;
		{
			UKDatabaseMetadata rivuDatabase  = null ;
			
			try{
				rivuDatabase = new UKDatabaseMetadata(conn) ;
				tables = rivuDatabase.loadTables(null, null, null, true) ;
			}finally{
				if(conn!=null)
					conn.close() ;
			}
		}
		
		return tables;
	}
	/**
	 * 
	 * @param database
	 * @return
	 * @throws Exception
	 */
	public static List<UKTableMetaData> getTables(Connection conn , String tabltableNamePattern) throws Exception{
		List<UKTableMetaData> tables = null ;
		{
			UKDatabaseMetadata rivuDatabase  = null ;
			
			try{
				rivuDatabase = new UKDatabaseMetadata(conn) ;
				tables = rivuDatabase.loadTables(tabltableNamePattern, null, null, true) ;
			}finally{
				if(conn!=null)
					conn.close() ;
			}
		}
		
		return tables;
	}
	/**
	 * 
	 * @param database
	 * @return
	 * @throws Exception
	 */
	public static UKTableMetaData getTable(Connection conn , String tablename) throws Exception{
		UKTableMetaData rivuTableMetaData = null ;
		{
			UKDatabaseMetadata rivuDatabase  = null ;
			rivuDatabase = new UKDatabaseMetadata(conn) ;
			rivuTableMetaData = rivuDatabase.loadTable(tablename, null, null, true) ;
		}
			
		return rivuTableMetaData;
	}
	
	/**
	 * 
	 * @param database
	 * @return
	 * @throws Exception
	 */
	public static UKTableMetaData getSQL(Connection conn, String name,String datasql) throws Exception{
		UKTableMetaData rivuTableMetaData = null ;
		Statement statement = null ;
		try{
			UKDatabaseMetadata rivuDatabase  = null ;
			rivuDatabase = new UKDatabaseMetadata(conn) ;
			statement = conn.createStatement() ;
			rivuTableMetaData = rivuDatabase.loadSQL(statement, datasql, name, null, null, true);
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(statement!=null) {
				statement.close();
			}
		}
		return rivuTableMetaData;
	}
	
	/**
	 * 
	 * @param properties
	 * @param table
	 * @param startindex
	 * @param endindex
	 * @param field
	 * @param value
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public synchronized static TabelSql getSQL(Database database ,String tablename , int startindex ,int len) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		TabelSql tableSQL = null ;
		if(!StringUtils.isBlank(database.getDatabasetype())){
			
			if("oracle".equalsIgnoreCase(database.getDatabasetype())){
				StringBuffer strb = new StringBuffer() ;
				strb.append("select * from ( select row_.*, rownum rownum_ from ( ").append(tablename);
				{
					strb.append(")");
					strb.append(" row_ where rownum <= ") ;
					strb.append(startindex+len) ;
					strb.append(") where rownum_ > ").append(startindex) ;
					tableSQL = new TabelSql(strb.toString(),false , 1, null , null );
				}
			}else if("mysql".equalsIgnoreCase(database.getDatabasetype())){
				StringBuffer strb = new StringBuffer() ;
				strb.append("select * from ").append(tablename);
				
				strb.append(" limit ").append(startindex).append(",").append(len);
				tableSQL = new TabelSql(strb.toString(),false , 1, null , null );
			}else if("postgresql".equalsIgnoreCase(database.getDatabasetype())){
				StringBuffer strb = new StringBuffer() ;
				strb.append("select * from ( ").append(tablename).append(") ");
				
				strb.append("limit ").append(len).append(" offset ").append(startindex);
				tableSQL = new TabelSql(strb.toString(),false , 1, null , null );
				
			}
		}
		if(tableSQL==null){
			StringBuffer strb = new StringBuffer() ;
			strb.append("select * ").append(" from ").append(tablename) ;
			
			String sql = strb.toString() ;
			
			tableSQL = new TabelSql(sql,false , 1, null  ,null);
		}
		
		return  tableSQL;
	}
}
