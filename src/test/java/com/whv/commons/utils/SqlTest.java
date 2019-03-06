package com.whv.commons.utils;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.whv.commons.utils.sql.PageInfo;
import com.whv.commons.utils.sql.SqlQueryTool;

public class SqlTest {

	@Test
	public void testQuery() {
		String sql = "SELECT CONS_ID,  CONS_NO, CONS_NAME, BUILD_DATE, REGION FROM C_CONS";
		Object[] params = new Object[] {};
		List<Map> rslt = SqlQueryTool.query(sql, params);
		System.out.println(rslt.size());
	}
	@Test
	public void testQueryFew() {
		String sql = "SELECT CONS_ID,  CONS_NO, CONS_NAME, BUILD_DATE, REGION FROM C_CONS where cons_name like '%'||?||'%' and region = ?";
		Object[] params = new Object[] {"一次","23401"};
		List<Map> rslt = SqlQueryTool.queryFew(sql, params,3);
		System.out.println(rslt.size());
	}
	@Test
	public void testQueryPage() {
		String sql = "SELECT CONS_ID,  CONS_NO, CONS_NAME, BUILD_DATE, REGION FROM C_CONS where cons_name like '%'||?||'%' and region = ?";
		Object[] params = new Object[] {"变","23401"};
		PageInfo page = new PageInfo(3,12);
		List allList = SqlQueryTool.query(sql,params);
		page.setTotalCount(allList==null?0:allList.size());
		List<Map> rslt = SqlQueryTool.queryPage(sql, params,page);
		System.out.println(rslt.size());
		System.out.println("每页"+page.getPageSize()+"条记录，当前第"+page.getCurrPage()+"页");
		System.out.println("共"+page.getTotalCount()+"条记录，共"+page.getTotalPage()+"页");
	}

}
