package com.arberkuci.storeapp.ejb.cache.control;

import com.arberkuci.storeapp.ejb.user.dto.UserDto;

/**
 * Created by harperkej on 9.6.17..
 */
public interface UserCache {

    public void storeUser(UserDto userDto);

    public UserDto findUserById(Long id);

    public UserDto findUserByUsername(String username);

}
