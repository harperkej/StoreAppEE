package com.arberkuci.storeapp.ejb.user.api;

import com.arberkuci.storeapp.ejb.user.dto.UserDto;

import java.util.List;


public interface UserFacade {

    public UserDto storeUser(UserDto userDto);

    public UserDto findByUserById(Long id);


    public List<UserDto> findAllUsers();


}
