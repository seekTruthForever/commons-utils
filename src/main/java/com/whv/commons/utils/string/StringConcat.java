package com.whv.commons.utils.string;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串拼接剪切操作
 * @author huawei
 *
 */
public class StringConcat {
	/**
	 * 把 "a,b,c" 改成 "'a','b','c'"
	 * @param seia 字符串
	 * @param spliter  分符
	 * @param newspliter 分符
	 * @param concat 如空或者是 '
	 * @return 返回组织好的字符串
	 */
	public static String splitConcat(String seia,String spliter,String newspliter,String concat)
	{
		if(seia==null||spliter==null)
		{
			return concat+concat;
		}
		if(seia.endsWith(spliter))
		{
			seia=seia.substring(0,seia.lastIndexOf(spliter));
		}
		String[] spl=seia.trim().split(spliter);
		if(spl==null||spl.length==1)
		{
			return concat+seia+concat;
		}
		StringBuffer newString = new StringBuffer();
		for(int i=0;i<spl.length;i++)
		{
			newString.append(concat).append(spl[i]).append(concat);
			if(i!=spl.length-1)
			{
				newString.append(newspliter);
			}
		}
		return newString.toString();
	}
	/**
	 * 串中的最后部分去除 如果没有，或者不是以laster参数对应串结尾则返回原串
	 * @param serial
	 * @param laster
	 * @return
	 */
	public static String concatLast(String serial,String laster)
	{
		if(serial==null||laster==null)
		{
			return serial;
		}
		if(serial.endsWith(laster))
		{
			return serial.substring(0,serial.lastIndexOf(laster));
		}
		return serial;
	}
	/**
	 * 整形数字千分位分割
	 * @param number
	 * @return
	 */
		public String longThousandsSplit(String number){
			String returnNumber = "";
			if(null != number && !"".equals(number)){
				NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher isNum = pattern.matcher(number);
				if( isNum.matches() ){
				   Long numbers = Long.parseLong(number);
				   returnNumber = numberFormat1.format(numbers);
				}else{
				   returnNumber = number;
				}
			}
			return returnNumber;
		}
}
