package com.whv.commons.utils.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.whv.commons.utils.db.DBUtils;
/**
 * jdbc更新工具类
 * @author huawei
 *
 */
public class SqlUpdateTool {
	/**
	 * 更新
	 * @param updateSql 更新sql语句
	 * @param params 参数
	 * @return 更新记录行数
	 */
	public static int update(String updateSql,Object[] params) {
		int rowCount = 0;
		if (updateSql != null && !"".equals(updateSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(updateSql);
				if(params != null && params.length > 0) {
					for(int i=0;i<params.length;i++) {
						pstmt.setObject(i+1, params[i]);
					}
				}
				rowCount = pstmt.executeUpdate();
			} catch (SQLException e) {
				try {
					if(conn != null && !conn.isClosed()) conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally{
				DBUtils.close( pstmt, rs);
				DBUtils.close(conn, true);
			}          
		}
		return rowCount;
	}
	/**
	 * 批量更新
	 * @param updateSql 更新sql语句
	 * @param paramList 参数列表
	 */
	public static void batchUpdate(String updateSql,List<Object[]> paramList) {
		if (updateSql != null && !"".equals(updateSql)) 
		{
			 Connection conn = null;
			   PreparedStatement pstmt = null;
			   ResultSet rs = null;
			   try {
				conn = DBUtils.getConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(updateSql);
				if(paramList != null && paramList.size()>0) {
					for(int i=0;i<paramList.size();i++) {
						Object[] params = paramList.get(i);
						if(params != null && params.length > 0) {
							for(int j=0;j<params.length;j++) {
								pstmt.setObject(j+1, params[j]);
							}
							pstmt.addBatch();
						}
					}
				}
				
				pstmt.executeBatch();
			} catch (SQLException e) {
				try {
					if(conn != null && !conn.isClosed()) conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally{
				DBUtils.close( pstmt, rs);
				DBUtils.close(conn, true);
			}          
		}
	}
}
