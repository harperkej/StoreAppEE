package com.arberkuci.storeapp.ejb.user.dao;

import com.arberkuci.storeapp.ejb.user.entity.UserEntity;

import java.util.List;

/**
 * Created by a.kuci on 4/9/2017.
 */
public interface UserDao {

    public UserEntity persistUser(UserEntity userEntity);

    public UserEntity findUserById(Long id);

    public List<UserEntity> getAllUsers();

}
