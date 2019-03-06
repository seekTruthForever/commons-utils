package com.whv.commons.utils.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性文件操作工具类
 * @author huawei
 *
 */
public class PropOptUtil {
	//默认编码
		private final static String DEF_CHARSET="UTF-8";
		private String charset;
		private InputStream in;
		public PropOptUtil() {
			charset = DEF_CHARSET;
			in = null;
		}
		/**
		 * 通过文件输入流构建工具类构造器
		 * @param fis 文件输入流
		 */
		public PropOptUtil(InputStream fis) {
			this(fis,null);
		}
		/**
		 * 通过文件输入流构建工具类构造器
		 * @param fis 文件输入流
		 * @param encode 编码
		 */
		public PropOptUtil(InputStream fis,String encode) {
			if(encode==null||"".equals(encode)) {
				charset = DEF_CHARSET;
			}else {
				charset = encode;
			}
			in = fis;
		}
		/**
		 * 通过文件路径构建工具类构造器
		 * @param filePath 文件相对路径
		 */
		public PropOptUtil(String filePath) {
			this(filePath,null,true);
		}
		/**
		 * 通过文件路径构建工具类构造器
		 * @param filePath 文件路径
		 * @param isRelative 是否相对路径
		 */
		public PropOptUtil(String filePath,boolean isRelative) {
			this(filePath,null,isRelative);
		}
		/**
		 * 通过文件路径构建工具类构造器
		 * @param filePath 文件相对路径
		 * @param encode 编码
		 */
		public PropOptUtil(String filePath,String encode) {
			this(filePath,encode,true);
		}
		/**
		 * 通过文件路径构建工具类构造器
		 * @param filePath 文件路径
		 * @param encode 编码
		 * @param isRelative 是否相对路径
		 */
		public PropOptUtil(String filePath,String encode,boolean isRelative) {
			if(encode==null||"".equals(encode)) {
				charset = DEF_CHARSET;
			}else {
				charset = encode;
			}
			if(isRelative) {
				 in = PropOptUtil.class.getClassLoader().getResourceAsStream(filePath);
			}else {
				try {
					in = new FileInputStream(new File(filePath));
				} catch (FileNotFoundException e) {
					in = null;
				}
			}
		}
	/**
	 * 从属性文件中获取值
	 * @param filePath 类加载器相对路径
	 * @return Properties
	 */
	public  Properties getProperties() {
		if(in==null) {
			throw new RuntimeException("文件不存在");
		}
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(in,charset));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	/**
	 * 从属性文件中获取值
	 * @param filePath 类加载器相对路径
	 * @param key 键
	 * @return 值
	 */
	public String getPropertiesValue(String key) {
		Properties properties = getProperties();
		String value = properties.getProperty(key).trim();
		return value;
	}
	/**
	 * 从属性文件中获取值
	 * @param filePath 类加载器相对路径
	 * @param key 键
	 * @param defaultValue 默认值
	 * @return 值
	 */
	public String getPropertiesValue(String key,String defaultValue) {
		Properties properties = getProperties();
		String value = properties.getProperty(key,defaultValue).trim();
		return value;
	}
	/**
	 * 写入属性值
	 * @param filePath 属性文件路径，类加载器相对路径
	 * @param key 属性名称
	 * @param value 属性值
	 */
	public static void createProperties(String filePath,String key,String value){
		createProperties(filePath, key, value, DEF_CHARSET);
	}
	/**
	 * 写入属性值
	 * @param filePath 属性文件路径，类加载器相对路径
	 * @param key 属性名称
	 * @param value 属性值
	 */
	public static void createProperties(String filePath,String key,String value,String charset){
		createProperties(filePath, key, value, charset,true);
	}
	/**
	 * 写入属性值
	 * @param filePath 属性文件路径，类加载器相对路径
	 * @param key 属性名称
	 * @param value 属性值
	 */
	public static void createPropertiesAB(String filePath,String key,String value,String charset){
		createProperties(filePath, key, value, charset,false);
	}
	/**
	 * 写入属性值
	 * @param filePath 属性文件路径，类加载器相对路径
	 * @param key 属性名称
	 * @param value 属性值
	 */
	public static void createPropertiesAB(String filePath,String key,String value){
		createPropertiesAB(filePath, key, value, DEF_CHARSET);
	}
	/**
	 * 写入属性值
	 * @param filePath 属性文件路径，类加载器相对路径
	 * @param key 属性名称
	 * @param value 属性值
	 * @param isRelative 是否相对路径
	 */
	public static void createProperties(String filePath,String key,String value,String charset,boolean isRelative){
		Properties properties = new Properties();
		String url = null;
		if(isRelative) {
			url = filePath;
		}else {
			url = PropOptUtil.class.getClassLoader().getResource(filePath).getPath();
		}
		InputStream in = null;
		try {
			File file = new File(url);
			if(!file.exists()){
				file.createNewFile();
			}
			in = new FileInputStream(file);
			properties.load(new InputStreamReader(in,charset));
			properties.setProperty(key, value);
			FileOutputStream out = new FileOutputStream(file);
			properties.store(new OutputStreamWriter(out,charset), "修改属性文件"+url);
		} catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
    /**
     * 读取属性文件中的属性值
     * @return
     */
    public Map<String,String> readAllProps(){
        Map<String,String> h = new HashMap<String,String>();
        try {
        	Properties props = getProperties();
            Enumeration<String> er = (Enumeration<String>) props.propertyNames();
            while (er.hasMoreElements()) {
                String paramName = er.nextElement();
                h.put(paramName, props.getProperty(paramName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return h;
    }
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
}
