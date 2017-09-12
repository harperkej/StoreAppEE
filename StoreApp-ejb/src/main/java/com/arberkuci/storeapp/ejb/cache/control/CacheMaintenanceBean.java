package com.arberkuci.storeapp.ejb.cache.control;

import com.arberkuci.storeapp.common.cache.api.CacheLocal;
import com.arberkuci.storeapp.ejb.cache.common.Constant;
import com.arberkuci.storeapp.common.rest.response.UserDto;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by harperkej on 5.8.17..
 */
@Stateless
@Local(CacheMaintenance.class)
public class CacheMaintenanceBean implements CacheMaintenance {

    private static final String TIMER_NAME = "CACHE_MAINTENANCE_NAME";

    @Resource
    TimerService timer;

    @Inject
    CacheLocal cache;

    @Override
    public void initCacheMaintenance() {

        this.stopPreviousTimers();

        TimerConfig timerConfig = new TimerConfig();
        timerConfig.setInfo(TIMER_NAME);
        timerConfig.setPersistent(false);
        timer.createIntervalTimer(1000, TimeUnit.MINUTES.toMillis(5), timerConfig);

    }

    @Override
    @Timeout
    public void checkCache() {

        ConcurrentMap<Object, ConcurrentMap<Object, Object>> allCaches = cache.getAllCaches();

        allCaches.forEach((k, v) -> {
            if (Constant.CACHE_USER_BY_ID.equals(k)) {

                ConcurrentMap allUsersStoredInCacheById = allCaches.get(k);

                allUsersStoredInCacheById.forEach((id, user) -> {
                    UserDto userDto = (UserDto) user;

                    if (isTimestampOlderThan5Minutes(userDto.getTimestamp())) {
                        cache.getCache(Constant.CACHE_USER_BY_ID).remove(id);
                    }

                });

            }

            if (Constant.CACHE_USER_BY_USERNAME.equals(k)) {

                ConcurrentMap allUsersStoredInCacheById = allCaches.get(k);

                allUsersStoredInCacheById.forEach((username, user) -> {
                    UserDto userDto = (UserDto) user;

                    if (isTimestampOlderThan5Minutes(userDto.getTimestamp())) {
                        cache.getCache(Constant.CACHE_USER_BY_USERNAME).remove(username);
                    }

                });
            }
        });

    }

    private boolean isTimestampOlderThan5Minutes(Timestamp timestamp) {
        return TimeUnit.MILLISECONDS.toMinutes((new Timestamp(System.currentTimeMillis())).getTime())
                - TimeUnit.MILLISECONDS.toMinutes((timestamp).getTime()) > 5;
    }


    private void stopPreviousTimers() {

        timer.getAllTimers().forEach(e -> {
            if (TIMER_NAME.equals(e.getInfo())) {
                e.cancel();
            }
        });

    }
}
