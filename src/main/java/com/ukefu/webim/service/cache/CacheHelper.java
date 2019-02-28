package com.ukefu.webim.service.cache;

import com.ukefu.webim.service.cache.hazelcast.HazlcastCacheHelper;

public class CacheHelper {
	private static CacheHelper instance = new CacheHelper();
	
	/**
	 * 获取缓存实例
	 */
	public static CacheHelper getInstance(){
		return instance ;
	}
	private static CacheInstance cacheInstance = new HazlcastCacheHelper();
	
	public static CacheBean getAgentStatusCacheBean() {
		return cacheInstance!=null ? cacheInstance.getAgentStatusCacheBean() : null;
	}
	public static CacheBean getAgentUserCacheBean() {
		return cacheInstance!=null ? cacheInstance.getAgentUserCacheBean() : null ;
	}
	public static CacheBean getOnlineUserCacheBean() {
		return cacheInstance!=null ? cacheInstance.getOnlineCacheBean() : null;
	}
	public static CacheBean getSystemCacheBean() {
		return cacheInstance!=null ? cacheInstance.getSystemCacheBean() : null ;
	}
	
	public static CacheBean getIMRCacheBean() {
		return cacheInstance!=null ? cacheInstance.getIMRCacheBean() : null ;
	}
	public static CacheBean getCallCenterCacheBean() {
		return cacheInstance!=null ? cacheInstance.getCallCenterCacheBean() : null ;
	}
	public static CacheBean getCallCenterAgentCacheBean() {
		return cacheInstance!=null ? cacheInstance.getCallCenterAgentCacheBean() : null ;
	}
	public static CacheBean getApiUserCacheBean() {
		return cacheInstance!=null ? cacheInstance.getApiUserCacheBean() : null ;
	}
	public static CacheBean getJobCacheBean() {
		return cacheInstance!=null ? cacheInstance.getJobCacheBean(): null ;
	}
	public static CacheBean getCallOutCacheBean() {
		return cacheInstance!=null ? cacheInstance.getCallOutCacheBean(): null ;
	}
	public static CacheBean getQcQueueCacheBean() {
		return cacheInstance!=null ? cacheInstance.getQcQueueBean(): null ;
	}
}
