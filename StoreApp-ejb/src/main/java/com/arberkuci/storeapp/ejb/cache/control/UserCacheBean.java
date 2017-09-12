package com.arberkuci.storeapp.ejb.cache.control;

import com.arberkuci.storeapp.common.cache.api.CacheLocal;
import com.arberkuci.storeapp.ejb.cache.common.Constant;
import com.arberkuci.storeapp.common.rest.response.UserDto;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.logging.Logger;

@Stateless
@Local(UserCache.class)
public class UserCacheBean implements UserCache {

    @Inject
    CacheLocal cacheLocal;

    private final String className = this.getClass().getName();

    private Logger logger = Logger.getLogger(className);

    public void storeUser(UserDto userDto) {
        if (userDto != null) {
            userDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
            if (userDto.getId() != null) {
                cacheLocal.getCache(Constant.CACHE_USER_BY_ID).put(userDto.getId(), userDto);
                logger.info("The following entry " + userDto + " is cached associated with id -> " + userDto.getId());
            } else {
                logger.info("The id is null, nothing stored in cache.");
            }
            if (userDto.getUserName() != null) {
                cacheLocal.getCache(Constant.CACHE_USER_BY_USERNAME).put(userDto.getUserName(), userDto);
                logger.info("THe following entry ->" + userDto + " is cached associated with username -> " + userDto.getUserName());
            } else {
                logger.warning("The username is null, nothing stored in cache.");
            }
        } else {
            logger.warning("The dto was null, nothing stored in cache!");
        }
    }

    @Override
    public UserDto findUserById(Long id) {
        UserDto foundUser = (UserDto) this.cacheLocal.getCache(Constant.CACHE_USER_BY_ID).get(id);
        if(foundUser != null){
            foundUser.setTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        logger.info("With the given id " + id + " the following result is found in cache " + foundUser);
        return foundUser;
    }

    @Override
    public UserDto findUserByUsername(String username) {
        UserDto foundUser = (UserDto) this.cacheLocal.getCache(Constant.CACHE_USER_BY_USERNAME).get(username);
        if(foundUser != null){
            foundUser.setTimestamp(new Timestamp(System.currentTimeMillis()));
        }
        logger.info("With the given username " + username + " the following result is found in cache " + foundUser);
        return foundUser;
    }


}
