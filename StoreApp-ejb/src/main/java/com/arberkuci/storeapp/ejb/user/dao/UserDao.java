package com.arberkuci.storeapp.ejb.user.dao;

import com.arberkuci.storeapp.ejb.user.entity.UserEntity;

import java.util.List;


public interface UserDao {

    public UserEntity persistUser(UserEntity userEntity);

    public UserEntity findUserById(Long id);

    public List<UserEntity> getAllUsers();

    public UserEntity updateUser(UserEntity userDto);

    public List<UserEntity> getUserByUserName(String userName);


}
