package com.whv.commons.utils.cache;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 使用LinkedHashMap实现LRU缓存
 * @author whv
 *
 * @param <K>
 * @param <V>
 */
public class CacheMap<K,V> extends LinkedHashMap<K, V> implements Serializable{
	private static final long serialVersionUID = -5570171865231127226L;
	private final static int DEFAULT_CACHE_SIZE = 100;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private int maxSize = 0;
	/**
	 * 缓存MAP构造器
	 * 初始化容量和最大容量都为默认值
	 */
	public CacheMap() {
        this(DEFAULT_CACHE_SIZE);
    }
	/**
	 * 缓存MAP构造器
	 * @param size 初始化容量和最大容量
	 */
	public CacheMap(int size) {
        this(size,size);
    }
	/**
	 * 缓存MAP构造器
	 * @param initialCapacity 初始化容量
	 * @param maxSize 最大容量
	 */
	public CacheMap(int initialCapacity,int maxSize) {
        this(initialCapacity,maxSize,DEFAULT_LOAD_FACTOR);
    }
	/**
	 * 缓存MAP构造器
	 * @param size 初始化容量和最大容量
	 * @param loadFactor 负载因子
	 */
	public CacheMap(int size, float loadFactor) {
        this(size,size,loadFactor);
    }
	/**
	 * 缓存MAP构造器
	 * @param initialCapacity 初始化容量
	 * @param maxSize 最大容量
	 * @param loadFactor 负载因子
	 */
	public CacheMap(int initialCapacity,int maxSize, float loadFactor) {
        super(initialCapacity,loadFactor,true);
        this.maxSize = maxSize;
    }
	protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return size()>this.maxSize;
    }
}
