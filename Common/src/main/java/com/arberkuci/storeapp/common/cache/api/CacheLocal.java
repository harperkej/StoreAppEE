package com.arberkuci.storeapp.common.cache.api;


import java.util.concurrent.ConcurrentMap;

public interface CacheLocal {


    ConcurrentMap<Object, Object> getCache(Object key);

    ConcurrentMap<Object, ConcurrentMap<Object, Object>> getAllCaches();

    void clearAllCaches();

    boolean isAnyCacheAvailable();

}
