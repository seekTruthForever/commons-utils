package com.whv.commons.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateTool {
	/**
	 * 获得当前日期+时间（wuhui 20101227 add）
	 * @return yyyy-mm-dd hh24:mi:ss
	 */
	public static String getCurrDate()
	{
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	//HH大写，代表是24小时制，日期中的MM必须大写	
		return sdf.format(d);
	}
	
	/**
	 * 获得当前日期+时间（wuhui 20101227 add）
	 * @return yyyy-mm-dd hh24:mi
	 */
	public static String getCurrDateEnPai()
	{
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");	//HH大写，代表是24小时制，日期中的MM必须大写	
		return sdf.format(d);
	}
	
	
	
	/**
	 * 获取当前日期+时间
	 * @return yyyy年mm月dd日 hh:MM:ss
	 */
	public static String getCurrStrDate()
	{
		String datim = getCurrDate();
		datim = datim.substring(0,4)+"年"+datim.substring(5,7)+"月"+datim.substring(8,10)+"日"+datim.substring(10);
		return datim;
	}
	/**
	 * 获得往数据库字段类型为Date型时，插入的时间
	 * @return
	 */
	public static java.sql.Date getCurrSqlDate()
	{
		return new java.sql.Date(new java.util.Date().getTime());
	}
	/**
	 * 返回相应格式的服务器日期字符串[也可以返回月份],如果为空，默认取yyyyMMdd
	 * @param style like yyyyMMdd/yyyy-MM-dd/yyyy年MM月dd日  /yyyyMM/yyyy-MM等
	 * @return 相应格式的日期字符串
	 */
	public static String getCurrDate(String style) 
	{	
		if("".equals(style)||style==null){style="yyyyMMdd";}
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		return sdf.format(d);
	}
	/**
	 * 获取当前月的第一天
	 * @param style
	 * @return
	 * @author Wangwanxin
	 */
	public static String getMonthFirstDay(String style) 
	{	
		if("".equals(style)||style==null){style="yyyyMMdd";}
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		String firstday= sdf.format(d).substring(0, 7).concat("-01");
		return firstday;
	}	
	/**
	 * 返回延迟若干天后，8位的日期字符串，如：20031225
	 * getDateByDate("20030101", 1)  -> 20030102
	 * getDateByDate("20030101", -2) -> 20021230
	 */
	public static String getDateByDate(String date, int deltaDay) 
	{
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, deltaDay);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 返回延迟若干天后，8位的日期字符串，如：20031225
	 * getDateByDate("20030101", 1)  -> 20030102
	 * getDateByDate("20030101", -2) -> 20021230
	 */
	public static String getBeforDateByDate(String date) 
	{
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			c.setTime(sdf.parse(date));
			c.add(Calendar.DATE, -1);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	  * 返回年月日的正常表示，如：2004年2月2日	
	  *
	 */
	public static String getDateExpression(String date) 
	{
		String returnDate = null;
		try {
			if (date.length() != 8 || !date.substring(0, 3).equals("201")) 
			{
				returnDate = date;
			} else {
				String year = date.substring(0, 4);
				String month = date.substring(4, 6);
				if (month.substring(0, 1).equals("0")) 
				{
					month = month.substring(1, 2);
				}
				String day = date.substring(6, 8);
				if (day.substring(0, 1).equals("0")) 
				{
					day = day.substring(1, 2);
				}
				returnDate = year + "年" + month + "月" + day + "日";
			}
			return returnDate;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 由yyyymmdd转换成不同格式的日期
	 * @param date like 20090101
	 * @param style like yyyy-MM-dd/yyyy年MM月dd日,如果为空，默认取yyyyMMdd
	 * @return 相应格式的日期字符串
	 */
	public static String getStyleDate(String date,String tostyle) 
	{
		if("".equals(tostyle)||tostyle==null){tostyle="yyyyMMdd";}
		String sYear = date.substring(0, 4);
		String sMonth = date.substring(4, 6);
		String sDay = date.substring(6, 8);
		int year = Integer.parseInt(sYear);		
		int monthint = Integer.parseInt(sMonth);		
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, monthint - 1, dday);
		SimpleDateFormat df = new SimpleDateFormat(tostyle);
		return df.format(cal.getTime());		
	}	
	/**
	 * 获取输入日期的下 n 天的日期的相应格式的日期字符串,如果n为负值，则是返回输入日期的前n天
	 * @param day like 20090101
	 * @param n
	 * @param style like yyyyMMdd/yyyy-MM-dd/yyyy年MM月dd日,如果为空，默认取yyyyMMdd
	 * @return
	 */
	public static String getNextDay(String day, int n,String style) 
	{
		if (day == null || "".equals(day) || day.length() != 8) 
		{
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		if("".equals(style)||style==null){style="yyyyMMdd";}
		try {
			String sYear = day.substring(0, 4);
			String sMonth = day.substring(4, 6);
			String sDay = day.substring(6, 8);
			int year = Integer.parseInt(sYear);			
			int month = Integer.parseInt(sMonth);			
			int dday = Integer.parseInt(sDay);
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, dday);
			cal.add(Calendar.DATE, n);
			SimpleDateFormat df = new SimpleDateFormat(style);
			return df.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException("进行日期运算时输入得参数不符合系统规格." + e);
		}
	}
	/**
	 * 获取输入日期的前days 工作日 
	 * @param startDate 输入日期 格式与pattern参数相符
	 * @param days      想要前（后）推的日数
	 * @param pattern   输入日期、与输出日期的格式，默认为yyyyMMdd
	 * @return 返回 8位 like 20090101
	 */
	public static String workDays(String startDate, int days,String style)
	{
		if("".equals(style)||style==null){style="yyyyMMdd";}
		if (startDate == null || "".equals(startDate)) 
		{
			throw new RuntimeException("由于缺少必要的参数，系统无法进行制定的日期换算.");
		}
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Date date = new Date();
		try{
			date = sdf.parse(startDate);//从给定字符串的开始分析文本，以生成一个日期。返回Date
		}catch (Exception e){}
        
		Date endDate = date;
		cal.setTime(endDate);
		int count = 0;
		while (count >= days)
		{			
			int day = cal.get(Calendar.DAY_OF_WEEK);//判断是不是星期   五、			
			if (day < 7 && day > 1)//1: week = "星期天";7: week = "星期六"; 
				days++;
			cal.add(Calendar.DATE, -1);//往前推一天
			endDate = cal.getTime();			
		}
		String datenow=sdf.format(endDate);	
		return datenow;
	}
	/**
	 * 获取输入日期的前n年同期，如去年同期
	 * @param date like 2009/200901/20090101
	 * @param n
	 * @return 如：2009-->2008，200901-->200801,20090101-->20080101
	 */
	public static String getPreYearSamePeriod(String date,int n)
	{
		String datenow="";
		if(date.length()==6||date.length()==8)
		{
			String dateyear=date.substring(0, 4);			
			int year=Integer.parseInt(dateyear);
			int yeartemp=year-n;
			datenow=String.valueOf(yeartemp)+date.substring(4);
		}else if(date.length()==4){
			int year=Integer.parseInt(date);
			int yeartemp=year-n;
			datenow=String.valueOf(yeartemp);
		}else{
			throw new RuntimeException("由于缺少必要的参数，系统无法进行指定的换算.");
		}
		return datenow;
	}
	/**
	 * 获取输入 月份的下[前] n 月份 返回 6位 like 200901
	 * @param month  like 200901
	 * @param n
	 * @param style 月份格式 如yyyyMMdd yyyy-MM等 默认为yyyyMMdd
	 * @return
	 */
	public static String getNextMonth(String month, int n,String style) 
	{
		if("".equals(style)||style==null){style="yyyyMMdd";}
		if (month == null || "".equals(month) || month.length() != 6) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行指定的月份换算.");
		}
		try {
			String sYear = month.substring(0, 4);
			int year = Integer.parseInt(sYear);
			String sMonth = month.substring(4, 6);
			int mon = Integer.parseInt(sMonth);
			Calendar cal = Calendar.getInstance();
			cal.set(year, mon - 1, 1);
			cal.add(Calendar.MARCH, n);
			SimpleDateFormat df = new SimpleDateFormat(style);
			return df.format(cal.getTime());
		} catch (Exception e) {
			throw new RuntimeException("进行月份运算时输入得参数不符合系统规格." + e);
		}
	}
	
	/**
	 * 判断输入日期为星期几：0：星期天 6：星期六
	 * @param date like 20090101
	 * @return 星期所对应的数字
	 */
	public static int getWeekday(String date) 
	{
		if (date == null || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行指定的换算.");
		}
		String sYear = date.substring(0, 4);
		String sMonth = date.substring(4, 6);
		String sDay = date.substring(6, 8);
		int year = Integer.parseInt(sYear);		
		int mon = Integer.parseInt(sMonth);		
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		int weekday = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekday;
	}
	/**
	 * 得到输入日期所对应的农历日期
	 * @param date like 20090101
	 * @return
	 */
	public static Lunar getLunar(String date) 
	{
		Date d = getDate(date);
		Lunar lunar = new Lunar();
		lunar.Lunar1(d);
		return lunar;
	}
	/**
	 * 得到输入日期所对应的Date对象
	 * @param date like 20090101
	 * @return
	 */
	private static Date getDate(String date) 
	{
		if (date == null || date.length() != 8) {
			throw new RuntimeException("由于缺少必要的参数，系统无法进行指定的换算.");
		}
		String sYear = date.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = date.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = date.substring(6, 8);
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		return cal.getTime();
	}
	/**
	 * 获取currentMonth指定月份前n个季度的季度数。如2006年1月的前一个季度为2005第4季度：getPreviousQuarter("200702",
	 * 1)，将返回"20064"。
	 * 
	 * @param currentMonth
	 *            指定月份：yyyyMMdd
	 * @param n
	 * @return yyyyQ
	 */
	public static String getPreviousQuarter(String currentMonth, int n) 
	{
		String yearMonth = getNextMonth(currentMonth, -n * 3,"");
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(4, 6);
		String quarter = null;
		if ("09".compareTo(month) < 0)
			quarter = "4";
		else if ("06".compareTo(month) < 0)
			quarter = "3";
		else if ("03".compareTo(month) < 0)
			quarter = "2";
		else
			quarter = "1";

		return year + quarter;
	}
	/**
	 * 获取两个查询月份之间（含开始月份和结束月份）的所有月份列表
	 * @author 
	 * @return
	 */
	public static List InterzoneMonth(String BeginMonth,String EndMonth)
	{
		List MonthList = new ArrayList();
		if(BeginMonth.equals(EndMonth)){
			MonthList.add(BeginMonth);
		}else{
			String beginyear = BeginMonth.substring(0, 4);
			String endyear = EndMonth.substring(0, 4);
			String beginmonth = BeginMonth.substring(4, 6);
			String endmonth = EndMonth.substring(4, 6);
			String month = "";
			int k = 0;
			if(beginyear.equals(endyear)){
				k = Integer.parseInt(endmonth)-Integer.parseInt(beginmonth);
			}else{
				k = (12-Integer.parseInt(beginmonth))+ 
				(12*(Integer.parseInt(endyear)-Integer.parseInt(beginyear)-1))+Integer.parseInt(endmonth);
			}
			for(int i=0;i<=k;i++){
				month = getNextMonth(BeginMonth,i,"");
				MonthList.add(month);
			}
		}
		return MonthList;
	}
	/**
	 * 取得一个月提前forward天的开始结束日期
	 * @author 
	 * @return
	 */
	public static String[] getMonthForwardBeginEnd(String month,int forward)
	{
		String[] result=new String[]{"","'"};
		String sYear = month.substring(0, 4);
		int year = Integer.parseInt(sYear);
		String sMonth = month.substring(4, 6);
		int mon = Integer.parseInt(sMonth);
		String sDay = "01";
		int dday = Integer.parseInt(sDay);
		Calendar cal = Calendar.getInstance();
		cal.set(year, mon - 1, dday);
		int days=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		result[1]=sYear+sMonth+String.valueOf(days-forward);
		cal.add(Calendar.DATE,-forward);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		result[0]=df.format(cal.getTime());
		return result;
	}
	/**
	 * 获取与季度相关的月份日期：起始月，起始日期；结束月，结束日期
	 * 
	 * @param quarter
	 *            1：第一季度；2：第二季度；3：第三季度；4：第四季度；
	 * @return 键值分别为beginMonth(MM), beginDate(MMdd), endMonth(MM),
	 *         endDate的String(MMdd)
	 */
	public static Map getQuarterStartEndMonthDate(int quarter) {
		HashMap rtn = new HashMap();
		String beginMonth, beginDate, endMonth, endDate;
		switch (quarter) {
		case 1:
			beginMonth = "01";
			beginDate = "0101";
			endMonth = "03";
			endDate = "0331";
			break;
		case 2:
			beginMonth = "04";
			beginDate = "0401";
			endMonth = "06";
			endDate = "0630";
			break;
		case 3:
			beginMonth = "07";
			beginDate = "0701";
			endMonth = "09";
			endDate = "0930";
			break;
		case 4:
			beginMonth = "10";
			beginDate = "1001";
			endMonth = "12";
			endDate = "1231";
			break;
		default:
			throw new RuntimeException("季度数不正确，输入季度数为‘" + quarter + "’");
		}
		rtn.put("beginMonth", beginMonth);
		rtn.put("beginDate", beginDate);
		rtn.put("endMonth", endMonth);
		rtn.put("endDate", endDate);
		return rtn;
	}

//=================以下为时间相关的方法===================================//
	
	/**
	 * 获取当前系统时间 返回 12:12:12 本质方法
	 * 
	 * @return
	 */
	public static long getCurrentTimeMillis() 
	{
		return System.currentTimeMillis();
	}
	/**
	 * 返回8位的服务器时间字符串，如：23595900
	 */
	public static String getCurrTime() 
	{
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss00");
		return sdf.format(d);
	}	
	/**
	 * 获取当前系统时间 参数 format yyyyMMddHHmmss 其中的部分 返回与 format格式相同的时间
	 * 
	 * @return
	 */
	public static String getCurrentTimeMillisAsString(String format) 
	{
		long currTimeM = getCurrentTimeMillis();
		Date date = new Date(currTimeM);
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	/**
	 * 得到一个时间加延迟时间后的值，按照HHmmss格式，6位
	 * @param time
	 * @param delay_time
	 * @return
	 */
	public static String getTimeByTime(String time ,int delay_time)
	{
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			c.setTime(sdf.parse(time));
			c.add(Calendar.MINUTE, delay_time);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 根据时间返回相应的格式字符串
	 * @param time：8位的时间字符串
	 * @param pattern
	 * @return
	 * add by wzq
	 * pattern:格式
	 * 12:10:20(1...24小时制)		hh:mm:ss
	 * 00:10:20(0...23小时制)		HH:mm:ss
	 * 12:10:20	PM(/AM)				hh:mm:ss a
	 * 00:10:20 PM(/AM) 			HH:mm:ss a
	 * 12时10分20秒 				hh时mm分ss秒
	 * 00时10分20秒					HH时mm分ss抄
	 */
	public static String formatTime(String time, String pattern) 
	{
		if (time == null)
			return "";
		time = time.trim();
		if (time.length() > 6)
			time = time.substring(0, 6);
		else {
			for (int i = 0; i < 6 - time.trim().length(); i++) {
				time = time.concat("0");
			}
		}
		if (pattern == null || pattern.trim().equals(""))
			return time;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			Date dd = sdf.parse(time);
			sdf = new SimpleDateFormat(pattern);
			return sdf.format(dd);
		} catch (Throwable t) {
			//log.info("HelpTool.formatTime exception ", t);
			return time;
		}
	}
	/**  
     * 验证某一时间是否在某一时间段  
     * @param currTime 某一时间  
     * @param String[] timeSlot 某一时间段  如{"8:00", "16:00"}
     * @return true/false  
     */  
    public static boolean isShift(final long currTime, String[] timeSlot)
	{   
		Calendar tempCalendar = Calendar.getInstance(); //获得Calendar实例		
		tempCalendar.setTimeInMillis(currTime);//currTime：新时间，以从历元至现在所经过的 UTC 毫秒数形式  
		
		String[] tmpArray = timeSlot[0].split(":");   //timeSlot[0]:"8:00"
		long startTime, stopTime;   
		//得到开始时间数值
		//清除的日历字段:小时、分钟
		//Calendar.HOUR_OF_DAY:指示一天中的小时。HOUR_OF_DAY 用于 24 小时制时钟。例如，在 晚上10:04:15.250 PM 这一时刻，HOUR_OF_DAY 为 22。 
		tempCalendar.clear(Calendar.HOUR_OF_DAY);   
		//Calendar.MINUTE:指示一小时中的分钟。例如，在 10:04:15.250 PM 这一时刻，MINUTE 为 4。 
		tempCalendar.clear(Calendar.MINUTE);   
		//设置小时、分钟
		tempCalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(tmpArray[0]));   
		tempCalendar.set(Calendar.MINUTE, Integer.parseInt(tmpArray[1]));   

		startTime = tempCalendar.getTimeInMillis();//得到开始时间数值
		
		//得到结束时间数值
		tmpArray = timeSlot[1].split(":");   //"16:00"
		int stopHour  = Integer.parseInt(tmpArray[0]), stopMinute = Integer.parseInt(tmpArray[1]);   
		if (stopHour == 0) 
		{   
			tempCalendar.add(Calendar.DAY_OF_MONTH, 1);   
		}   
		tempCalendar.clear(Calendar.HOUR_OF_DAY);   
		tempCalendar.clear(Calendar.MINUTE);   
		tempCalendar.set(Calendar.HOUR_OF_DAY, stopHour);   
		tempCalendar.set(Calendar.MINUTE, stopMinute);   
		stopTime = tempCalendar.getTimeInMillis(); 
		
		return ((startTime < currTime && currTime <= stopTime) ? true : false);   
    }   
    
    
//=================以下为日期比较的相关方法===================================//	
	/**
	 * 判断时间date1是否在时间date2之前
	 * @param time,date2：时间格式 2005-4-21 16:16:34
	 * @param boolean
	 * @return
	 */
	public static boolean isDateBefore(String date1,String date2)
	{
	   try{
		   DateFormat df = DateFormat.getDateTimeInstance();
		   return df.parse(date1).before(df.parse(date2));
	   }catch(ParseException e){
		   //System.out.print("[SYS] " + e.getMessage());
		   return false;
	   }
	}
	/**
	 * 判断当前时间是否在时间date2之前
	 * @param date2：时间格式 2005-4-21 16:16:34
	 * @param boolean
	 * @return
	 */	
	public static boolean isDateBefore(String date2)
	{
	   try{
		   Date date1 = new Date();
		   DateFormat df = DateFormat.getDateTimeInstance();
		   return date1.before(df.parse(date2));
	   }catch(ParseException e){
		   //System.out.print(\"[SYS] \" + e.getMessage());
		   return false;
	   }
	}	
	/**
	 * 判断当前时间是否在时间date2之后或相等
	 * @param date2：时间格式 2005-4-21
	 * @param boolean
	 * @return
	 */	
	public static boolean isDateAfterOrEqual(String date2)
	{
	   boolean flag=false;
	   try{
		   DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
		   Date date1 = new Date();
		   if(fmt.parse(fmt.format(date1)).equals(fmt.parse(date2))||fmt.parse(fmt.format(date1)).after(fmt.parse(date2)))
			   flag=true;
	   }catch(ParseException e){
		   //System.out.print("[SYS] " + e.getMessage());
		   flag = false;
	   }
	   return flag;
	}
	
	/**
	 * 将字符串格式日期解析为日期对象
	 * 
	 * @author 程伟平
	 * @date 2011-01-05
	 * @param source 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getDateTime(String source)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try
		{
			return df.parse(source);
		}
		catch (ParseException e)
		{
			return null;
		}
	}
	
	/**
	 * 将日期格式的字符串解析成给定的日期的格式
	 * 
	 * @author 张宗魁
	 * @date 2011-03-08
	 * @param style 日期的格式，dateString 日期格式的字符串
	 * @return
	 */
	public static String getDateString(String style,String dateString) 
	{	
		Calendar c = Calendar.getInstance();
		String restr = dateString;
		
		if("".equals(style)||style==null)
		{
			style="yyyy-MM-dd"; 
		}
		if(dateString==null||"".equalsIgnoreCase(dateString)){
			Date d = c.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat(style);
			restr = sdf.format(d);
		}
		
		return restr;
	}
	
	/**
	 * 得到某年某月的最後一天,如果选择的当前日期，则返回当前日期。
	 * 仅适用于用采
	 */ 
	public static String getLastDayOfMonth(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4));
		
		String new_date = new SimpleDateFormat("yyyyMM").format(new Date());
		
		if(date.equals(new_date)){
			return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}
	
	/**
	 * 得到指定日期的月天数
	 */ 
	public static String getDaysOfMonth(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4));
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()).substring(8);

	}
	
	/**
	 * 得到某年某月的第一天,如果选择的当前日期，则返回当前日期。
	 * 仅适用于用采
	 */ 
	public static String getfirstDayOfMonth(String date) {
		int year = Integer.valueOf(date.substring(0, 4));
		int month = Integer.valueOf(date.substring(4));
		
		 Calendar cal = Calendar.getInstance();     
         cal.set(Calendar.YEAR, year);     
         cal.set(Calendar.MONTH, month-1);  
         cal.set(Calendar.DAY_OF_MONTH,cal.getMinimum(Calendar.DATE));  

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}

	/**
	 * 返回指定日期延迟若干周期后，指定格式的日期数据
	 * @param date 指定日期
	 * @param format 指定格式 如果为空则默认“yyyy-MM-dd HH:mm:ss”
	 * @param range 指定周期 1:年,2:季度,3:周期,4:月,5:时,6:分,7:秒
	 * @param rangeNum 周期数
	 * @return
	 */
	public static String addDate(String date, String format, int range, int rangeNum) 
	{
		int feild = 0;
		if (null == date || "".equals(date)) {
			return "";
		}
		if (rangeNum == 0) {
			return date;
		}
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		// 周期
		switch (range) {
			case 1:		// YEAR
				feild = Calendar.YEAR;
				break;

			case 2:		// 季度
				feild = Calendar.MONTH;
				rangeNum = rangeNum * 3;
				break;

			case 3:		// MONTH
				feild = Calendar.MONTH;
				break;

			case 4:		// DATE
				feild = Calendar.DATE;
				break;

			case 5:		// HOUR
				feild = Calendar.HOUR;
				break;

			case 6:		// MINUTE
				feild = Calendar.MINUTE;
				break;

			case 7:		// SECOND
				feild = Calendar.SECOND;
				break;

			default:
				return "";
		}
		try {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			c.setTime(sdf.parse(date));
			c.add(feild, rangeNum);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取当前日期前几个月的所有日期
	 * yyyy-MM-dd
	 * 返回List数组
	 */
	public static List getAllDatesBefore(int month){
		Date dEnd = new Date();//当前时间
		Calendar cal = Calendar.getInstance();//得到日历
		cal.setTime(dEnd);//当前时间赋给日历
		cal.add(cal.MONTH, month);//设置为前几个月
		Date dBegin = new Date();
		dBegin = cal.getTime();//得到前几个月的时间
		List<String> IDate = findDates(dBegin,dEnd);
		return IDate;
	}
	/**
	 * 获取两个日期之间的所有日期
	 * yyyy-MM-dd
	 * 返回List数组
	 */
	public static List<String> findDates(Date dBegin,Date dEnd){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> IDate = new ArrayList();
		//使用给定的Date设置此Calendar的时间
		Calendar calBegin = Calendar.getInstance();
		calBegin.setTime(dBegin);
		//测试此日期是否在指定日期之后
		while(dEnd.compareTo(calBegin.getTime())>=0){
			IDate.add(sdf.format(calBegin.getTime()));
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
		}
		return IDate;
	}
}
