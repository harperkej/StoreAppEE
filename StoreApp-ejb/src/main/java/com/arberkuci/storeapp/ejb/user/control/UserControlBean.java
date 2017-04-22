package com.arberkuci.storeapp.ejb.user.control;

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

    private final String className = this.getClass().getName();

    private final Logger logger = Logger.getLogger(className);

    @Override
    public UserDto storeUser(UserDto userDto) {
        final String MN = "storeUser";
        logger.exiting(className, MN);
        if (userDto != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setName(userDto.getName());
            userEntity.setSurName(userDto.getSurName());
            userDao.persistUser(userEntity);
            userDto.setId(userEntity.getId());
        }

        logger.exiting(className, MN);
        return userDto;
    }


    @Override
    public UserDto findUserById(Long id) {
        final String MN = "findUserById";
        logger.entering(className, MN);
        UserEntity foundUser = userDao.findUserById(id);
        UserDto res = null;
        if (foundUser != null) {
            res = new UserDto();
            res.setId(foundUser.getId());
            res.setName(foundUser.getName());
            res.setSurName(foundUser.getSurName());
        }
        logger.exiting(className, MN);
        return res;
    }


    @Override
    public List<UserDto> findAllUsers() {
        final String MN = "findAllUsers";
        logger.exiting(className, MN);
        List<UserDto> result = new ArrayList<>();
        List<UserEntity> foundUsers = userDao.getAllUsers();
        if (foundUsers != null && !foundUsers.isEmpty()) {
            foundUsers.stream().filter(e -> validateUserEntity(e)).map(UserControlBean::mapUser).forEach(result::add);
        }
        logger.exiting(className, MN);
        return result;
    }

    private static boolean validateUserEntity(UserEntity userEntity) {
        return userEntity != null && userEntity.getId() != null && userEntity.getName() != null && userEntity.getSurName() != null;
    }

    private static UserDto mapUser(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setSurName(userEntity.getSurName());
        return userDto;
    }

}
