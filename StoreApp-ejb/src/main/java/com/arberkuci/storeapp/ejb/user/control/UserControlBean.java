package com.arberkuci.storeapp.ejb.user.control;

import com.arberkuci.storeapp.ejb.cache.control.UserCache;
import com.arberkuci.storeapp.ejb.user.dao.UserDao;
import com.arberkuci.storeapp.ejb.user.dto.UserDto;
import com.arberkuci.storeapp.ejb.user.entity.UserEntity;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Local(UserControl.class)
public class UserControlBean implements UserControl {

    @Inject
    UserDao userDao;

    @Inject
    UserCache userCache;

    @Override
    public UserDto storeUser(UserDto userDto) {
        if (userDto != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(userDto.getFirstName());
            userEntity.setLastName(userDto.getLastName());
            userEntity.setUserName(userDto.getUserName());
            //When the user is stored for the first time, the number of points is 0!
            userEntity.setPoints(new Double(0));
            userEntity = this.userDao.persistUser(userEntity);
            userDto.setId(userEntity.getId());
            //Store the user in cache as well!
            this.userCache.storeUser(userDto);
        }
        return userDto;
    }


    @Override
    public UserDto findUserById(Long id) {
        //first search in cache.
        UserDto res = this.userCache.findUserById(id);
        if (res == null) {
            //try to search in db
            UserEntity foundUser = userDao.findUserById(id);
            if (foundUser != null) {
                res = new UserDto();
                res.setId(foundUser.getId());
                res.setFirstName(foundUser.getFirstName());
                res.setLastName(foundUser.getLastName());
                res.setUserName(foundUser.getUserName());
                res.setPoints(foundUser.getPoints());
                this.userCache.storeUser(res);
            }
        }
        return res;
    }


    //TODO: This is something that needs to be analyzed -> do we need to get the data
    // from cache or only from db or how?
    @Override
    public List<UserDto> findAllUsers() {
        List<UserDto> result = new ArrayList<>();
        List<UserEntity> foundUsers = userDao.getAllUsers();
        if (foundUsers != null && !foundUsers.isEmpty()) {
            foundUsers.stream().filter(e -> validateUserEntity(e)).map(UserControlBean::mapUser).forEach(result::add);
        }
        return result;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserDto updatedUser = null;
        if (userDto == null) {
            //TODO: Throw an exception here!
        } else {
            boolean userDoesNotExists = this.findUserById(userDto.getId()) == null;
            if (userDoesNotExists) {
                //TODO: Throw an exception here!
            } else {
                //writing this line of code was fun :D
                updatedUser = mapUser(this.userDao.updateUser(mapUser(userDto)));
                //store in cache as well.
                this.userCache.storeUser(updatedUser);
            }
        }
        return updatedUser;
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        UserDto foundUser = this.userCache.findUserByUsername(userName);
        if (foundUser == null) {
            List<UserEntity> listOfUsers = this.userDao.getUserByUserName(userName);

            boolean noUserFound = listOfUsers == null || listOfUsers.isEmpty();
            boolean moreThanOneUserWIthTheSameUsername = !noUserFound && listOfUsers.size() > 1;

            if (noUserFound || moreThanOneUserWIthTheSameUsername) {
                //TODO: Throw an exception here!
            } else {
                foundUser = mapUser(listOfUsers.get(0));
            }
        }
        return foundUser;
    }

    private static boolean validateUserEntity(UserEntity userEntity) {
        return userEntity != null && userEntity.getId() != null && userEntity.getFirstName() != null && userEntity.getLastName() != null
                && userEntity.getUserName() != null && userEntity.getPoints() != null;
    }

    private static UserDto mapUser(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setUserName(userEntity.getUserName());
        userDto.setPoints(userEntity.getPoints());
        return userDto;
    }

    private static UserEntity mapUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPoints(userDto.getPoints());
        return userEntity;
    }

}
