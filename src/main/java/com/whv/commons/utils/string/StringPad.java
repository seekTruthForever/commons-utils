package com.whv.commons.utils.string;
/**
 * 填补字符串，包含：左侧填补、右侧填补、两边填补
 * @author huawei
 *
 */
public class StringPad {
	
	/**
	 * 左侧填补字符串
	 * @param content 原字符串
	 * @param padChar 填补的字符
	 * @param len 填补后的字符串长度
	 * @return
	 */
	public static String lpad(String content ,char padChar,int len) {
		if(content==null) return null;
		if(len < content.length() ) return content;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<len-content.length();i++) {
			sb.append(padChar);
		}
		sb.append(content);
		return sb.toString();
	}
	/**
	 * 右侧填补字符串
	 * @param content 原字符串
	 * @param padChar 填补的字符
	 * @param len 填补后的字符串长度
	 * @return
	 */
	public static String rpad(String content ,char padChar,int len) {
		if(content==null) return null;
		if(len < content.length() ) return content;
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		for(int i=0;i<len-content.length();i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}
	/**
	 * 两侧填充
	 * @param content 原字符串
	 * @param padChar 填补的字符
	 * @param len 填补后的字符串长度
	 * @return
	 */
	public static String bothpad(String content ,char padChar,int len) {
		if(content==null) return null;
		//原字符串长度
		int sourceLength = content.length();
		if(len < sourceLength ) return content;
		//左侧添加的长度
		int lpadLength = (len-sourceLength)/2;
		//右侧添加的长度
		int rpadLength = len-sourceLength - lpadLength;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lpadLength;i++) {
			sb.append(padChar);
		}
		sb.append(content);
		for(int i=0;i<rpadLength;i++) {
			sb.append(padChar);
		}
		return sb.toString();
	}
	/**
	 * 左侧填补字符串
	 * @param content 原字符串
	 * @param padStr 填补的字符串
	 * @param len 填补后的字符串长度
	 * @return
	 */
	public static String lpad(String content ,String padStr,int len) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		return sb.toString();
	}
	/**
	 * 右侧填补字符串
	 * @param content 原字符串
	 * @param padStr 填补的字符串
	 * @param len 填补后的字符串长度
	 * @return
	 */
	public static String rpad(String content ,String padStr,int len) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
	/**
	 * 两侧填补字符串
	 * @param content 原字符串
	 * @param padStr 填补的字符串
	 * @param len 填补后的字符串长度
	 * @return
	 */
	public static String bothpad(String content ,String padStr,int len) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return content;
		int padCount = (len-content.length())/padStr.length();
		//左侧添加的长度
		int lpadLength = padCount/2;
		//右侧添加的长度
		int rpadLength = padCount - lpadLength;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lpadLength;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		for(int i=0;i<rpadLength;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
	/**
	 * 左侧填补字符串
	 * @param content 原字符串
	 * @param padStr 填补的字符串
	 * @param len 填补后的字符串长度
	 * @param flag 是否至少填补一次，true:是，false:否
	 * @return
	 */
	public static String lpad(String content ,String padStr,int len,boolean flag) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return flag?padStr+content:content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		return sb.toString();
	}
	/**
	 * 右侧填补字符串
	 * @param content 原字符串
	 * @param padStr 填补的字符串
	 * @param len 填补后的字符串长度
	 * @param flag 是否至少填补一次，true:是，false:否
	 * @return
	 */
	public static String rpad(String content ,String padStr,int len,boolean flag) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return flag?content+padStr:content;
		int padCount = (len-content.length())/padStr.length();
		StringBuffer sb = new StringBuffer();
		sb.append(content);
		for(int i=0;i<padCount;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
	/**
	 * 两侧填补字符串
	 * @param content 原字符串
	 * @param padStr 填补的字符串
	 * @param len 填补后的字符串长度
	 * @param flag 是否至少填补一次，true:是，false:否
	 * @return
	 */
	public static String bothpad(String content ,String padStr,int len,boolean flag) {
		if(content==null) return null;
		if(padStr == null || "".equals(padStr)) return content;
		if(len - content.length() < padStr.length() ) return flag?padStr+content+padStr:content;
		int padCount = (len-content.length())/padStr.length();
		//左侧添加的长度
		int lpadLength = padCount/2;
		//右侧添加的长度
		int rpadLength = padCount - lpadLength;
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<lpadLength;i++) {
			sb.append(padStr);
		}
		sb.append(content);
		for(int i=0;i<rpadLength;i++) {
			sb.append(padStr);
		}
		return sb.toString();
	}
}
