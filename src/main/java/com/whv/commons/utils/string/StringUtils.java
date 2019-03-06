package com.whv.commons.utils.string;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类：匹配
 * @author huawei
 *
 */
public class StringUtils {
	/**
	  * 判断是否为整数 
	  * @param str 传入的字符串 
	  * @return 是整数返回true,否则返回false 
	*/
	  public static boolean isInteger(String str) {  
	        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	        return pattern.matcher(str).matches();  
	  }
	  
	  /**
	     * 匹配是否包含数字
	     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
	     * @return
	     */
	    public static boolean isNumeric(String str) {
	        // 该正则表达式可以匹配所有的数字 包括负数
	        Pattern pattern = Pattern.compile("^[-\\\\+]?[0-9]+\\.?[0-9]*$");
	        String bigStr;
	        try {
	            bigStr = new BigDecimal(str).toString();
	        } catch (Exception e) {
	            return false;//异常 说明包含非数字。
	        }

	        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
	        if (!isNum.matches()) {
	            return false;
	        }
	        return true;
	    } 
	    public static final char UNDERLINE = '_';

	    /**
	     * 驼峰格式字符串转换为下划线格式字符串
	     * 
	     * @param param
	     * @return
	     */
	    public static String camel2ul(String param) {
	        if (param == null || "".equals(param.trim())) {
	            return "";
	        }
	        int len = param.length();
	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            char c = param.charAt(i);
	            if (Character.isUpperCase(c)) {
	                sb.append(UNDERLINE);
	                sb.append(Character.toLowerCase(c));
	            } else {
	                sb.append(c);
	            }
	        }
	        return sb.toString();
	    }

	    /**
	     * 下划线格式字符串转换为驼峰格式字符串
	     * 
	     * @param param
	     * @return
	     */
	    public static String ul2camel(String param) {
	        if (param == null || "".equals(param.trim())) {
	            return "";
	        }
	        int len = param.length();
	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            char c = param.charAt(i);
	            if (c == UNDERLINE) {
	                if (++i < len) {
	                    sb.append(Character.toUpperCase(param.charAt(i)));
	                }
	            } else {
	                sb.append(c);
	            }
	        }
	        return sb.toString();
	    }

	    /**
	     * 下划线格式字符串转换为驼峰格式字符串2
	     * 
	     * @param param
	     * @return
	     */
	    public static String ul2camel2(String param) {
	        if (param == null || "".equals(param.trim())) {
	            return "";
	        }
	        StringBuilder sb = new StringBuilder(param);
	        Matcher mc = Pattern.compile("_").matcher(param);
	        int i = 0;
	        while (mc.find()) {
	            int position = mc.end() - (i++);
	            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
	        }
	        return sb.toString();
	    }
	    /**
	      <pre>
	     * 例：
	     * String strVal="This is a dog";
	     * String strResult=StringUtils.replace(strVal,"dog","cat");
	     * 结果：
	     * strResult equals "This is cat"
	     *
	     * @param strSrc 要进行替换操作的字符串
	     * @param strOld 要查找的字符串
	     * @param strNew 要替换的字符串
	     * @return 替换后的字符串
	      <pre>
	     */
	    public static final String replace(String strSrc, String strOld,
	                                       String strNew) {
	        if (strSrc == null || strOld == null || strNew == null)
	            return "";

	        int i = 0;
	        
	        if (strOld.equals(strNew)) //避免新旧字符一样产生死循环
	        	return strSrc;
	        
	        if ((i = strSrc.indexOf(strOld, i)) >= 0) {
	            char[] arr_cSrc = strSrc.toCharArray();
	            char[] arr_cNew = strNew.toCharArray();

	            int intOldLen = strOld.length();
	            StringBuffer buf = new StringBuffer(arr_cSrc.length);
	            buf.append(arr_cSrc, 0, i).append(arr_cNew);

	            i += intOldLen;
	            int j = i;

	            while ((i = strSrc.indexOf(strOld, i)) > 0) {
	                buf.append(arr_cSrc, j, i - j).append(arr_cNew);
	                i += intOldLen;
	                j = i;
	            }

	            buf.append(arr_cSrc, j, arr_cSrc.length - j);

	            return buf.toString();
	        }

	        return strSrc;
	    }

	    /**
	     * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
	     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
	     * @param strSrc 要进行替换操作的字符串
	     * @return 替换特殊字符后的字符串
	     * @since  1.0
	     */

	    public static String htmlEncode(String strSrc) {
	        if (strSrc == null)
	            return "";

	        char[] arr_cSrc = strSrc.toCharArray();
	        StringBuffer buf = new StringBuffer(arr_cSrc.length);
	        char ch;

	        for (int i = 0; i < arr_cSrc.length; i++) {
	            ch = arr_cSrc[i];

	            if (ch == '<')
	                buf.append("&lt;");
	            else if (ch == '>')
	                buf.append("&gt;");
	            else if (ch == '"')
	                buf.append("&quot;");
	            else if (ch == '\'')
	                buf.append("&#039;");
	            else if (ch == '&')
	                buf.append("&amp;");
	            else
	                buf.append(ch);
	        }

	        return buf.toString();
	    }

	    /**
	     * 用于将字符串中的特殊字符转换成Web页中可以安全显示的字符串
	     * 可对表单数据据进行处理对一些页面特殊字符进行处理如'<','>','"',''','&'
	     * @param strSrc 要进行替换操作的字符串
	     * @param quotes 为0时单引号和双引号都替换，为1时不替换单引号，为2时不替换双引号，为3时单引号和双引号都不替换
	     * @return 替换特殊字符后的字符串
	     * @since  1.0
	     */
	    public static String htmlEncode(String strSrc, int quotes) {

	        if (strSrc == null)
	            return "";
	        if (quotes == 0) {
	            return htmlEncode(strSrc);
	        }

	        char[] arr_cSrc = strSrc.toCharArray();
	        StringBuffer buf = new StringBuffer(arr_cSrc.length);
	        char ch;

	        for (int i = 0; i < arr_cSrc.length; i++) {
	            ch = arr_cSrc[i];
	            if (ch == '<')
	                buf.append("&lt;");
	            else if (ch == '>')
	                buf.append("&gt;");
	            else if (ch == '"' && quotes == 1)
	                buf.append("&quot;");
	            else if (ch == '\'' && quotes == 2)
	                buf.append("&#039;");
	            else if (ch == '&')
	                buf.append("&amp;");
	            else
	                buf.append(ch);
	        }

	        return buf.toString();
	    }

	    /**
	     * 和htmlEncode正好相反
	     * @param strSrc 要进行转换的字符串
	     * @return 转换后的字符串
	     * @since  1.0
	     */
	    public static String htmlDecode(String strSrc) {
	        if (strSrc == null)
	            return "";
	        strSrc = strSrc.replaceAll("&lt;", "<");
	        strSrc = strSrc.replaceAll("&gt;", ">");
	        strSrc = strSrc.replaceAll("&quot;", "\"");
	        strSrc = strSrc.replaceAll("&#039;", "'");
	        strSrc = strSrc.replaceAll("&amp;", "&");
	        return strSrc;
	    }

	    /**
	     * 在将数据存入数据库前转换
	     * @param strVal 要转换的字符串
	     * @return 从“ISO8859_1”到“GBK”得到的字符串
	     * @since  1.0
	     */
	    public static String toChinese(String strVal) {
	        try {
	            if (strVal == null) {
	                return "";
	            } else {
	                strVal = strVal.trim();
	                strVal = new String(strVal.getBytes("ISO8859_1"), "GBK");
	                return strVal;
	            }
	        } catch (Exception exp) {
	            return "";
	        }
	    }
	    /**
	     * 编码转换 从UTF-8到GBK
	     * @param strVal
	     * @return
	     */
	    public static String toGBK(String strVal) {
	        try {
	            if (strVal == null) {
	                return "";
	            } else {
	                strVal = strVal.trim();
	                strVal = new String(strVal.getBytes("UTF-8"), "GBK");
	                return strVal;
	            }
	        } catch (Exception exp) {
	            return "";
	        }
	    }

	    /**
	     * 将数据从数据库中取出后转换   *
	     * @param strVal 要转换的字符串
	     * @return 从“GBK”到“ISO8859_1”得到的字符串
	     * @since  1.0
	     */
	    public static String toISO(String strVal) {
	        try {
	            if (strVal == null) {
	                return "";
	            } else {
	                strVal = new String(strVal.getBytes("GBK"), "ISO8859_1");
	                return strVal;
	            }
	        } catch (Exception exp) {
	            return "";
	        }
	    }
	    public static String gbk2UTF8(String strVal) {
	        try {
	            if (strVal == null) {
	                return "";
	            } else {
	                strVal = new String(strVal.getBytes("GBK"), "UTF-8");
	                return strVal;
	            }
	        } catch (Exception exp) {
	            return "";
	        }
	    }
	    public static String ISO2UTF8(String strVal) {
	       try {
	           if (strVal == null) {
	               return "";
	           } else {
	               strVal = new String(strVal.getBytes("ISO-8859-1"), "UTF-8");
	               return strVal;
	           }
	       } catch (Exception exp) {
	           return "";
	       }
	   }
	   public static String UTF82ISO(String strVal) {
	       try {
	           if (strVal == null) {
	               return "";
	           } else {
	               strVal = new String(strVal.getBytes("UTF-8"), "ISO-8859-1");
	               return strVal;
	           }
	       } catch (Exception exp) {
	           return "";
	       }
	   }



	    /**
	     *显示大文本块处理(将字符集转成ISO)
	     *@deprecated
	     *@param str 要进行转换的字符串
	     *@return 转换成html可以正常显示的字符串
	     */
	    public static String toISOHtml(String str) {
	        return toISO(htmlDecode(null2Blank((str))));
	    }

	    /**
	     *实际处理 return toChineseNoReplace(null2Blank(str));
	     *主要应用于老牛的信息发布
	     *@param str 要进行处理的字符串
	     *@return 转换后的字符串
	     *@see com.whv.commons.utils.string.StringUtils#toChinese
	     *@see com.whv.commons.utils.string.StringUtils#null2Blank
	     */
	    public static String toChineseAndHtmlEncode(String str, int quotes) {
	        return htmlEncode(toChinese(str), quotes);
	    }

	    /**
	     *把null值和""值转换成&nbsp;
	     *主要应用于页面表格格的显示
	     *@param str 要进行处理的字符串
	     *@return 转换后的字符串
	     */
	    public static String str4Table(String str) {
	        if (str == null)
	            return "&nbsp;";
	        else if (str.equals(""))
	            return "&nbsp;";
	        else
	            return str;
	    }

	    /**
	     * String型变量转换成int型变量
	     * @param str 要进行转换的字符串
	     * @return intVal 如果str不可以转换成int型数据，返回-1表示异常,否则返回转换后的值
	     * @since  1.0
	     */
	    public static int str2Int(String str) {
	        int intVal;

	        try {
	            intVal = Integer.parseInt(str);
	        } catch (Exception e) {
	            intVal = 0;
	        }

	        return intVal;
	    }

	    public static double str2Double(String str) {
	        double dVal = 0;

	        try {
	            dVal = Double.parseDouble(str);
	        } catch (Exception e) {
	            dVal = 0;
	        }

	        return dVal;
	    }


	    public static long str2Long(String str) {
	        long longVal = 0;

	        try {
	            longVal = Long.parseLong(str);
	        } catch (Exception e) {
	            longVal = 0;
	        }

	        return longVal;
	    }

	    public static float str2Float(String floatstr) {
	        Float floatee;
	        floatee = Float.valueOf(floatstr);
	        return floatee.floatValue();
	    }

	    //change the float type to the string type
	    public static String float2Str(float value) {
	        Float floatee = new Float(value);
	        return floatee.toString();
	    }

	    /**
	     *int型变量转换成String型变量
	     *@param intVal 要进行转换的整数
	     *@return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
	     */
	    /**
	     *int型变量转换成String型变量
	     *@param intVal 要进行转换的整数
	     *@return str 如果intVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
	     */
	    public static String int2Str(int intVal) {
	        String str;

	        try {
	            str = String.valueOf(intVal);
	        } catch (Exception e) {
	            str = "";
	        }

	        return str;
	    }

	    /**
	     *long型变量转换成String型变量
	     *@param longVal 要进行转换的整数
	     *@return str 如果longVal不可以转换成String型数据，返回空值表示异常,否则返回转换后的值
	     */

	    public static String long2Str(long longVal) {
	        String str;

	        try {
	            str = String.valueOf(longVal);
	        } catch (Exception e) {
	            str = "";
	        }

	        return str;
	    }

	    /**
	     *null 处理
	     *@param str 要进行转换的字符串
	     *@return 如果str为null值，返回空串"",否则返回str
	     */
	    public static String null2Blank(String str) {
	        if (str == null)
	            return "";
	        else
	            return str;
	    }

	    /**
	     *null 处理
	     *@param d 要进行转换的日期对像
	     *@return 如果d为null值，返回空串"",否则返回d.toString()
	     */

	    public static String null2Blank(Date d) {
	        if (d == null)
	            return "";
	        else
	            return d.toString();
	    }

	    /**
	     *null 处理
	     *@param str 要进行转换的字符串
	     *@return 如果str为null值，返回空串整数0,否则返回相应的整数
	     */
	    public static int null2Zero(String str) {
	        int intTmp;
	        intTmp = str2Int(str);
	        if (intTmp == -1)
	            return 0;
	        else
	            return intTmp;
	    }
	    /**
	     * 把null转换为字符串"0"
	     * @param str
	     * @return
	     */
	    public static String null2SZero(String str) {
	        str = StringUtils.null2Blank(str);
	        if (str.equals(""))
	            return "0";
	        else
	            return str;
	    }

	    /**
	     * sql语句 处理
	     * @param sql 要进行处理的sql语句
	     * @param dbtype 数据库类型
	     * @return 处理后的sql语句
	     */
	    public static String sql4DB(String sql, String dbtype) {
	        if (!dbtype.equalsIgnoreCase("oracle")) {
	            sql = StringUtils.toISO(sql);
	        }
	        return sql;
	    }

	    /**
	     * 对字符串进行md5加密
	     * @param s 要加密的字符串
	     * @return md5加密后的字符串
	     */
	    public final static String MD5(String s) {
	        char hexDigits[] = {
	                           '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                           'a', 'b', 'c', 'd',
	                           'e', 'f'};
	        try {
	            byte[] strTemp = s.getBytes();
	            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
	            mdTemp.update(strTemp);
	            byte[] md = mdTemp.digest();
	            int j = md.length;
	            char str[] = new char[j * 2];
	            int k = 0;
	            for (int i = 0; i < j; i++) {
	                byte byte0 = md[i];
	                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	                str[k++] = hexDigits[byte0 & 0xf];
	            }
	            return new String(str);
	        } catch (Exception e) {
	            return null;
	        }
	    }
	    /**
	     * 字符串从GBK编码转换为Unicode编码
	     * @param text
	     * @return
	     */
	    public static String str2Unicode(String text) {
	        String result = "";
	        int input;
	        StringReader isr;
	        try {
	            isr = new StringReader(new String(text.getBytes(), "GBK"));
	        } catch (UnsupportedEncodingException e) {
	            return "-1";
	        }
	        try {
	            while ((input = isr.read()) != -1) {
	                result = result + "&#x" + Integer.toHexString(input) + ";";

	            }
	        } catch (IOException e) {
	            return "-2";
	        }
	        isr.close();
	        return result;

	    }
	    /**
	     * 
	     * @param inStr
	     * @return
	     */
	    public static String gb2utf(String inStr) {
	        char temChr;
	        int ascInt;
	        int i;
	        String result = new String("");
	        if (inStr == null) {
	            inStr = "";
	        }
	        for (i = 0; i < inStr.length(); i++) {
	            temChr = inStr.charAt(i);
	            ascInt = temChr + 0;
	            //System.out.println("1=="+ascInt);
	            //System.out.println("1=="+Integer.toBinaryString(ascInt));
	            if( Integer.toHexString(ascInt).length() > 2 ) {
	                result = result + "&#x" + Integer.toHexString(ascInt) + ";";
	            }
	            else
	            {
	                result = result + temChr;
	            }

	        }
	        return result;
	    }
	    /**
	     * This method will encode the String to unicode.
	     *
	     * @param gbString
	     * @return
	     */

	    //代码:--------------------------------------------------------------------------------
	    public static String gbEncoding(final String gbString) {
	        char[] utfBytes = gbString.toCharArray();
	        String unicodeBytes = "";
	        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
	            String hexB = Integer.toHexString(utfBytes[byteIndex]);
	            if (hexB.length() <= 2) {
	                hexB = "00" + hexB;
	            }
	            unicodeBytes = unicodeBytes + "\\u" + hexB;
	        }
	        System.out.println("unicodeBytes is: " + unicodeBytes);
	        return unicodeBytes;
	    }

	    /**
	     * This method will decode the String to a recognized String
	     * in ui.
	     * @param dataStr
	     * @return
	     */
	    public static StringBuffer decodeUnicode(final String dataStr) {
	        int start = 0;
	        int end = 0;
	        final StringBuffer buffer = new StringBuffer();
	        while (start > -1) {
	            end = dataStr.indexOf("\\u", start + 2);
	            String charStr = "";
	            if (end == -1) {
	                charStr = dataStr.substring(start + 2, dataStr.length());
	            } else {
	                charStr = dataStr.substring(start + 2, end);
	            }
	            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
	            buffer.append(new Character(letter).toString());
	            start = end;
	        }
	        return buffer;
	    }

	    /**
	     * 转换编码 ISO-8859-1到GB2312
	     * @param text
	     * @return
	     */
	    public static final String ISO2GB(String text) {
	      String result = "";
	      try {
	        result = new String(text.getBytes("ISO-8859-1"), "GB2312");
	      }
	      catch (UnsupportedEncodingException ex) {
	        result = ex.toString();
	      }
	      return result;
	    }

	    /**
	     * 转换编码 GB2312到ISO-8859-1
	     * @param text
	     * @return
	     */
	    public static final String GB2ISO(String text) {
	      String result = "";
	      try {
	        result = new String(text.getBytes("GB2312"), "ISO-8859-1");
	      }
	      catch (UnsupportedEncodingException ex) {
	        ex.printStackTrace();
	      }
	      return result;
	    }
	    /**
	     * Utf8URL编码
	     * @param s
	     * @return
	     */
	    public static final String Utf8URLencode(String text) {
	      StringBuffer result = new StringBuffer();

	      for (int i = 0; i < text.length(); i++) {

	        char c = text.charAt(i);
	        if (c >= 0 && c <= 255) {
	          result.append(c);
	        }else {

	          byte[] b = new byte[0];
	          try {
	            b = Character.toString(c).getBytes("UTF-8");
	          }catch (Exception ex) {
	          }

	          for (int j = 0; j < b.length; j++) {
	            int k = b[j];
	            if (k < 0) k += 256;
	            result.append("%" + Integer.toHexString(k).toUpperCase());
	          }

	        }
	      }

	      return result.toString();
	    }

	    /**
	     * Utf8URL解码
	     * @param text
	     * @return
	     */
	    public static final String Utf8URLdecode(String text) {
	      String result = "";
	      int p = 0;

	      if (text!=null && text.length()>0){
	        text = text.toLowerCase();
	        p = text.indexOf("%e");
	        if (p == -1) return text;

	        while (p != -1) {
	          result += text.substring(0, p);
	          text = text.substring(p, text.length());
	          if (text == "" || text.length() < 9) return result;

	          result += code2Word(text.substring(0, 9));
	          text = text.substring(9, text.length());
	          p = text.indexOf("%e");
	        }

	      }

	      return result + text;
	    }

	    /**
	     * utf8URL编码转字符
	     * @param text
	     * @return
	     */
	    private static final String code2Word(String text) {
	      String result;

	      if (Utf8codeCheck(text)) {
	        byte[] code = new byte[3];
	        code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
	        code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
	        code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
	        try {
	          result = new String(code, "UTF-8");
	        }catch (UnsupportedEncodingException ex) {
	          result = null;
	        }
	      }
	      else {
	        result = text;
	      }

	      return result;
	    }

	    /**
	     * 编码是否有效
	     * @param text
	     * @return
	     */
	    private static final boolean Utf8codeCheck(String text){
	      String sign = "";
	      if (text.startsWith("%e"))
	        for (int i = 0, p = 0; p != -1; i++) {
	          p = text.indexOf("%", p);
	          if (p != -1)
	            p++;
	          sign += p;
	        }
	      return sign.equals("147-1");
	    }

	    /**
	     * 判断是否Utf8Url编码
	     * @param text
	     * @return
	     */
	    public static final boolean isUtf8Url(String text) {
	      text = text.toLowerCase();
	      int p = text.indexOf("%");
	      if (p != -1 && text.length() - p > 9) {
	        text = text.substring(p, p + 9);
	      }
	      return Utf8codeCheck(text);
	    }
	    public static boolean isEmpty(String str)
	    {
	      return (str == null) || (str.length() == 0);
	    }

	    public static boolean isNotEmpty(String str)
	    {
	      return (str != null) && (str.length() > 0);
	    }

	    public static boolean isBlank(String str)
	    {
	      int strLen;
	      if ((str == null) || ((strLen = str.length()) == 0))
	        return true;
	      for (int i = 0; i < strLen; i++) {
	        if (!Character.isWhitespace(str.charAt(i))) {
	          return false;
	        }
	      }
	      return true;
	    }

	    public static boolean isNotBlank(String str)
	    {
	      int strLen;
	      if ((str == null) || ((strLen = str.length()) == 0))
	        return false;
	      for (int i = 0; i < strLen; i++) {
	        if (!Character.isWhitespace(str.charAt(i))) {
	          return true;
	        }
	      }
	      return false;
	    }
	    public static String join(Object[] array)
	    {
	      return join(array, null);
	    }

	    public static String join(Object[] array, char separator)
	    {
	      if (array == null) {
	        return null;
	      }
	      int arraySize = array.length;
	      int bufSize = arraySize == 0 ? 0 : 
	        ((array[0] == null ? 16 : 
	        array[0].toString().length()) + 1) * 
	        arraySize;
	      StringBuffer buf = new StringBuffer(bufSize);

	      for (int i = 0; i < arraySize; i++) {
	        if (i > 0) {
	          buf.append(separator);
	        }
	        if (array[i] != null) {
	          buf.append(array[i]);
	        }
	      }
	      return buf.toString();
	    }

	    public static String join(Object[] array, String separator)
	    {
	      if (array == null) {
	        return null;
	      }
	      if (separator == null) {
	        separator = "";
	      }
	      int arraySize = array.length;

	      int bufSize = arraySize == 0 ? 0 : 
	        arraySize * (
	        (array[0] == null ? 16 : 
	        array[0].toString().length()) + (separator != null ? 
	        separator.length() : 0));

	      StringBuffer buf = new StringBuffer(bufSize);

	      for (int i = 0; i < arraySize; i++) {
	        if ((separator != null) && (i > 0)) {
	          buf.append(separator);
	        }
	        if (array[i] != null) {
	          buf.append(array[i]);
	        }
	      }
	      return buf.toString();
	    }

	    public static String join(Iterator iterator, char separator)
	    {
	      if (iterator == null) {
	        return null;
	      }
	      StringBuffer buf = new StringBuffer(256);

	      while (iterator.hasNext()) {
	        Object obj = iterator.next();
	        if (obj != null) {
	          buf.append(obj);
	        }
	        if (iterator.hasNext()) {
	          buf.append(separator);
	        }
	      }
	      return buf.toString();
	    }

	    public static String join(Iterator iterator, String separator)
	    {
	      if (iterator == null) {
	        return null;
	      }
	      StringBuffer buf = new StringBuffer(256);

	      while (iterator.hasNext()) {
	        Object obj = iterator.next();
	        if (obj != null) {
	          buf.append(obj);
	        }
	        if ((separator != null) && (iterator.hasNext())) {
	          buf.append(separator);
	        }
	      }
	      return buf.toString();
	    }
	    public static boolean hasText(CharSequence str)
	    {
	      if (!hasLength(str)) {
	        return false;
	      }
	      int strLen = str.length();
	      for (int i = 0; i < strLen; i++) {
	        if (!Character.isWhitespace(str.charAt(i))) {
	          return true;
	        }
	      }
	      return false;
	    }

	    public static boolean hasText(String str)
	    {
	      return hasText(str);
	    }
	    public static boolean hasLength(CharSequence str)
	    {
	      return (str != null) && (str.length() > 0);
	    }

	    public static boolean hasLength(String str)
	    {
	      return hasLength(str);
	    }
	    public static String[] tokenizeToStringArray(String str, String delimiters)
	    {
	      return tokenizeToStringArray(str, delimiters, true, true);
	    }

	    public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
	    {
	      if (str == null) {
	        return null;
	      }
	      StringTokenizer st = new StringTokenizer(str, delimiters);
	      List tokens = new ArrayList();
	      while (st.hasMoreTokens()) {
	        String token = st.nextToken();
	        if (trimTokens) {
	          token = token.trim();
	        }
	        if ((!ignoreEmptyTokens) || (token.length() > 0)) {
	          tokens.add(token);
	        }
	      }
	      return toStringArray(tokens);
	    }
	    public static String[] toStringArray(Collection<String> collection)
	    {
	      if (collection == null) {
	        return null;
	      }
	      return (String[])collection.toArray(new String[collection.size()]);
	    }

	    public static String[] toStringArray(Enumeration<String> enumeration)
	    {
	      if (enumeration == null) {
	        return null;
	      }
	      List list = (List)Collections.list(enumeration);
	      return (String[])list.toArray(new String[list.size()]);
	    }

}
