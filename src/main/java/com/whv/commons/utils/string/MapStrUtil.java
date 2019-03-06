package com.whv.commons.utils.string;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * map转换为字符串工具类
 * 功能：map转字符串、list转字符串、map移除空键值
 * @author whv
 *
 */
public class MapStrUtil {
	/**
	 * 方法名称:map2str
	 * 传入参数:map
	 * 返回值:String
	*/
	public static String map2str(Map<?, ?> map){
	  java.util.Map.Entry<?, ?> entry;
	  StringBuffer sb = new StringBuffer();
	  for(Iterator<?> iterator = map.entrySet().iterator(); iterator.hasNext();)
	  {
	    entry = (java.util.Map.Entry<?, ?>)iterator.next();
	      sb.append(entry.getKey().toString()).append( ":" );
	      if (entry.getValue()  instanceof String) {
	    	  if(null==entry.getValue()) {
	    		  sb.append("null").append (iterator.hasNext() ? "," : "");
	    	  }else {
	    		  String value = entry.getValue().toString().trim();
	    		  if(value.startsWith("'")&&value.endsWith("'")) {
	    			  sb.append(value).append (iterator.hasNext() ? "," : "");
	    		  }else {
	    			  sb.append("'");
	    			  sb.append(value);
	    			  sb.append("'");
	    			  sb.append (iterator.hasNext() ? "," : "");
	    		  }
	    	  }
			
		}else  if (entry.getValue() instanceof List<?>) {
			 if(null==entry.getValue()) {
	    		  sb.append("null").append (iterator.hasNext() ? "," : "");
	    	  }else {
	    		  sb.append(list2str((List<?>) entry.getValue())).append (iterator.hasNext() ? "," : "");
	    	  }
        } else if (entry.getValue() instanceof Map<?, ?>) {
        	 if(null==entry.getValue()) {
	    		  sb.append("null").append (iterator.hasNext() ? "," : "");
	    	  }else {
	    		  sb.append(map2str((Map<?, ?>) entry.getValue())).append (iterator.hasNext() ? "," : "");
	    	  }
        } else{
			 sb.append(entry.getValue()).append (iterator.hasNext() ? "," : "");  
		}
	  }
	  return sb.toString();
	}
	 /**
     * List转换String
     * 
     * @param list
     *            :需要转换的List
     * @return String转换后的字符串
     */
    public static String list2str(List<?> list) {
    	Object obj;
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
        	 for(Iterator<?> iterator = list.iterator(); iterator.hasNext();){
        		 obj = iterator.next();
                // 如果值是list类型则调用自己
                if (obj  instanceof String) {
      	    	  if(null==obj) {
      	    		  sb.append("null").append (iterator.hasNext() ? "," : "");
      	    	  }else {
      	    		  String value = obj.toString().trim();
      	    		  if(value.startsWith("'")&&value.endsWith("'")) {
      	    			  sb.append(value).append (iterator.hasNext() ? "," : "");
      	    		  }else {
      	    			  sb.append("'");
      	    			  sb.append(value);
      	    			  sb.append("'");
      	    			  sb.append (iterator.hasNext() ? "," : "");
      	    		  }
      	    	  }
      	    	  
      			
      		}else if (obj instanceof List) {
                    sb.append(list2str((List<?>) obj));
                    sb.append (iterator.hasNext() ? "," : "");
                } else if (obj instanceof Map) {
                    sb.append(map2str((Map<?, ?>) obj));
                    sb.append (iterator.hasNext() ? "," : "");
                } else {
                    sb.append(obj);
                    sb.append (iterator.hasNext() ? "," : "");
                }
            }
        }
        return sb.toString();
    }
	/**
	 * 方法名称:str2map
	 * 传入参数:mapString
	 * 返回值:Map
	*/
	public static Map str2map(String mapString){
	  Map map = new HashMap();
	  java.util.StringTokenizer items;
	  for(StringTokenizer entrys = new StringTokenizer(mapString, ",");entrys.hasMoreTokens(); 
	    map.put(items.nextToken().trim(), items.hasMoreTokens() ? ((Object) (items.nextToken().trim())) : null))
	      items = new StringTokenizer(entrys.nextToken().trim(), ":");
	  return map;
	}
	
	/** 
     * 移除map中空key或者value空值 
     * @param map 
     */  
    public static void removeNullEntry(Map map){  
        removeNullKey(map);  
        removeNullValue(map);  
    }  
      
    /** 
     * 移除map的空key 
     * @param map 
     * @return 
     */  
    public static void removeNullKey(Map map){  
        Set set = map.keySet();  
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
            Object obj = (Object) iterator.next();  
            remove(obj, iterator);  
        }  
    }  
      
    /** 
     * 移除map中的value空值 
     * @param map 
     * @return 
     */  
    public static void removeNullValue(Map map){  
        Set set = map.keySet();  
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {  
            Object obj = (Object) iterator.next();  
            Object value =(Object)map.get(obj);  
            remove(value, iterator);  
        }  
    }  
      
    /** 
     * Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。  
     * Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变， 
     * 所以当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。 
     * 所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。 
     * 但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。 
     * @param obj 
     * @param iterator 
     */  
    private static void remove(Object obj,Iterator iterator){  
        if(obj instanceof String){  
            String str = (String)obj;  
            if(str==null || str.isEmpty()){  
                iterator.remove();  
            }  
        }else if(obj instanceof Collection){  
            Collection col = (Collection)obj;  
            if(col==null||col.isEmpty()){  
                iterator.remove();  
            }  
              
        }else if(obj instanceof Map){  
            Map temp = (Map)obj;  
            if(temp==null||temp.isEmpty()){  
                iterator.remove();  
            }  
              
        }else if(obj instanceof Object[]){  
            Object[] array =(Object[])obj;  
            if(array==null||array.length<=0){  
                iterator.remove();  
            }  
        }else{  
            if(obj==null){  
                iterator.remove();  
            }  
        }  
    }  
}
