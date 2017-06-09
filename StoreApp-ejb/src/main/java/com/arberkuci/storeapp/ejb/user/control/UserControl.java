package com.arberkuci.storeapp.ejb.user.control;

import com.arberkuci.storeapp.ejb.user.dto.UserDto;

import java.util.List;


public interface UserControl {

    public UserDto storeUser(UserDto userDto);

    public UserDto findUserById(Long id);

    public List<UserDto> findAllUsers();

    public UserDto updateUser(UserDto userDto);

    public UserDto getUserByUserName(String userName);


}
