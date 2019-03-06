package com.whv.commons.utils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.whv.commons.utils.db.DBUtils;
/**
 * jdbc查询工具类
 * @author huawei
 *
 */
public class SqlQueryTool {
	/**
	 * 查询
	 * @param selectSql 查询sql
	 * @param params 参数
	 * @return List
	 */
	public static List query(String selectSql,Object[] params) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection();
				pstmt = conn.prepareStatement(selectSql);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
		        int columnCount = md.getColumnCount();   //获得列数   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(conn, pstmt, rs);
			}          
		}
		return rsltList;
	}
	/**
	 * 查询
	 * @param dataSource 数据源
	 * @param selectSql 查询sql
	 * @param params 查询参数
	 * @return List
	 */
	public static List query(DataSource dataSource,String selectSql,Object[] params) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection(dataSource);
				pstmt = conn.prepareStatement(selectSql);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
		        int columnCount = md.getColumnCount();   //获得列数   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(conn, pstmt, rs);
			}          
		}
		return rsltList;
	}
	/**
	 * 查询
	 * @param selectSql 查询sql
	 * @param params 参数
	 * @param fetchSize 每次取多少条数据
	 * @return List
	 */
	public static List query(String selectSql,Object[] params,int fetchSize) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(selectSql);
				pstmt.setFetchSize(fetchSize);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
		        int columnCount = md.getColumnCount();   //获得列数   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(pstmt, rs);
				DBUtils.close(conn, true);
			}          
		}
		return rsltList;
	}
	/**
	 * 查询
	 * @param dataSource 数据源
	 * @param selectSql 查询sql
	 * @param params 查询参数
	 * @param fetchSize 每次取多少条数据
	 * @return List
	 */
	public static List query(DataSource dataSource,String selectSql,Object[] params,int fetchSize) 
	{
		List<Map<String,Object>> rsltList =new ArrayList<Map<String,Object>>();
		if (selectSql != null && !"".equals(selectSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection(dataSource);
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(selectSql);
				pstmt.setFetchSize(fetchSize);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rs = pstmt.executeQuery();
				ResultSetMetaData md = rs.getMetaData(); //获得结果集结构信息,元数据  
		        int columnCount = md.getColumnCount();   //获得列数   
				while(rs.next()){
					 Map<String,Object> rowData = new HashMap<String,Object>();  
			            for (int i = 1; i <= columnCount; i++) {  
			                rowData.put(md.getColumnName(i), rs.getObject(i));  
			            }  
			            rsltList.add(rowData);  
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				DBUtils.close(pstmt, rs);
				DBUtils.close(conn, true);
			}          
		}
		return rsltList;
	}
	/**
	 * 查询前几条记录
	 * @param selectSql 查询sql
	 * @param params 查询参数
	 * @param num 查询行数量
	 * @return List
	 */
	public static List queryFew(String selectSql,Object[] params,int num) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (");
		sb.append(selectSql);
		sb.append(") where rownum < ").append(num+1);
		return query(sb.toString(), params);
	}
	/**
	 * 查询前几条记录
	 * @param dataSource 数据源
	 * @param selectSql 查询sql
	 * @param params 查询参数
	 * @param num 查询行数量
	 * @return List
	 */
	public static List queryFew(DataSource dataSource,String selectSql,Object[] params,int num) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (");
		sb.append(selectSql);
		sb.append(") where rownum < ").append(num+1);
		return query(dataSource,sb.toString(), params);
	}
	
	/**
	 * 分页查询
	 * @param selectSql 查询sql
	 * @param params 查询参数
	 * @param page 分页信息
	 * @return List
	 */
	public static List queryPage(String selectSql,Object[] params,PageInfo page) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (SELECT T.*,ROWNUM RN FROM (");
		sb.append(selectSql);
		sb.append(") T  where rownum<")
		.append(page.getCurrPage()*page.getPageSize()+1)
		.append(") WHERE RN>")
		.append((page.getCurrPage()-1)*page.getPageSize());
		return query(sb.toString(), params);
	}
	/**
	 * 分页查询
	 * @param dataSource 数据源
	 * @param selectSql 查询sql
	 * @param params 查询参数
	 * @param page 分页信息
	 * @return List
	 */
	public static List queryPage(DataSource dataSource,String selectSql,Object[] params,PageInfo page) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM (SELECT T.*,ROWNUM RN FROM (");
		sb.append(selectSql);
		sb.append(") T  where rownum<")
		.append(page.getCurrPage()*page.getPageSize()+1)
		.append(") WHERE RN>")
		.append((page.getCurrPage()-1)*page.getPageSize());
		return query(dataSource,sb.toString(), params);
	}
}
