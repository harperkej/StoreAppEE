package com.arberkuci.storeapp.ejb.user.api;

import com.arberkuci.storeapp.common.rest.response.UserDto;

import java.util.List;


public interface UserFacade {

    public UserDto storeUser(UserDto userDto);

    public UserDto findByUserById(Long id);

    public List<UserDto> findAllUsers();

    public UserDto updateUser(UserDto userDto);

    public UserDto getUserByUserName(String userName);

}
