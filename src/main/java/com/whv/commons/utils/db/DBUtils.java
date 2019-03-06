package com.whv.commons.utils.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.whv.commons.utils.io.PropOptUtil;
import com.whv.commons.utils.string.StringUtils;
/**
 * 数据库操作工具类
 * @author huawei
 *
 */
public class DBUtils {
	private static Properties jdbcProperties;
	private static DataSource dataSource = null;
	static{
		
	}
	private static void initPropDataSource(String propsFile) {
		if(propsFile==null||"".equals(propsFile)) {
			propsFile = "jdbc.properties";
		}
		PropOptUtil propUtil = new PropOptUtil(propsFile);
		jdbcProperties = propUtil.getProperties();
		String username = jdbcProperties.getProperty("username");
		String password = jdbcProperties.getProperty("password");
		String driver = jdbcProperties.getProperty("driver");
		String url = jdbcProperties.getProperty("url");
		String maxActive = jdbcProperties.getProperty("maxActive");
		maxActive = StringUtils.isInteger(maxActive)?"10":maxActive;
		String initialSize = jdbcProperties.getProperty("initialSize");
		initialSize = StringUtils.isInteger(initialSize)?"2":initialSize;
		//创建连接池对象
		BasicDataSource bds = new BasicDataSource();
		//设置连接参数
		bds.setDriverClassName(driver);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setMaxActive(Integer.valueOf(maxActive));//最大连接数
		bds.setInitialSize(Integer.valueOf(initialSize));//初始连接数
		setDataSource(bds);
	}

	public DBUtils() {
	} 

	/**
	 * 获取应用本身的数据库链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() {
		if (getDataSource() == null)
			DBUtils.initPropDataSource(null);
		// 记录获取连接的类名和方法名
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
		
	}
	/**
	 * 获取应用本身的数据库链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String propsFile) {
		if (getDataSource() == null)
			DBUtils.initPropDataSource(propsFile);
		// 记录获取连接的类名和方法名
		Connection conn = null;
		try {
			conn = getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
		
	}
	/**
	 * 获取应用本身的数据库链接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(DataSource ds) {
		if (ds == null) {
			DBUtils.initPropDataSource(null);
			ds = getDataSource();
		}
		// 记录获取连接的类名和方法名
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn ;
		
	}
	public static void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs = null;
		}
	}

	public static void close(Statement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt = null;
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	public static void close(Connection conn,boolean commit) {
		try {
			if (conn != null && !conn.isClosed()) {
				if(commit) conn.commit();
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn = null;
		}
	}
	public static void close(Connection conn, Statement pstmt, ResultSet rs) {
		close(rs);
		close(pstmt);
		close(conn);
	}

	public static void close(Statement pstmt, ResultSet rs) {
		close(rs);
		close(pstmt);
	}

	public static  Properties getJdbcProperties() {
		return jdbcProperties;
	}

	public static void setJdbcProperties(Properties jdbcProperties) {
		DBUtils.jdbcProperties = jdbcProperties;
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		DBUtils.dataSource = dataSource;
	}
}
