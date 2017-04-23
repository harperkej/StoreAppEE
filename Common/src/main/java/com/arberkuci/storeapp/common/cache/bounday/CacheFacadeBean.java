package com.arberkuci.storeapp.common.cache.bounday;


import com.arberkuci.storeapp.common.cache.api.CacheLocal;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Singleton;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Singleton
@Local(CacheLocal.class)
public class CacheFacadeBean implements CacheLocal {

    private ConcurrentMap<Object, ConcurrentMap<Object, Object>> allCaches;

    @PostConstruct
    public void initCache() {
        allCaches = new ConcurrentHashMap<>();
    }

    @Override
    public ConcurrentMap<Object, Object> getCache(Object key) {
        ConcurrentMap<Object, Object> cache = getOrCreateNewCache(key);
        return cache;
    }

    private ConcurrentMap<Object, Object> getOrCreateNewCache(Object key) {
        ConcurrentMap<Object, Object> cache = this.allCaches.get(key);
        if (cache == null) {
            cache = new ConcurrentHashMap<>();
            allCaches.put(key, cache);
        }
        return cache;
    }

    @Override
    public ConcurrentMap<Object, ConcurrentMap<Object, Object>> getAllCaches() {
        return this.allCaches;
    }

    @Override
    public void clearAllCaches() {

        Set<Object> keys = this.allCaches.keySet();

        keys.forEach(e -> {
            ConcurrentMap<Object, Object> cache = this.allCaches.get(e);
            Set<Object> keysOfCache = cache.keySet();
            keysOfCache.forEach(k -> {
                cache.remove(k);
            });
            this.allCaches.remove(e);
        });

    }

    @Override
    public boolean isAnyCacheAvailable() {
        return this.allCaches.size() != 0;
    }
}
