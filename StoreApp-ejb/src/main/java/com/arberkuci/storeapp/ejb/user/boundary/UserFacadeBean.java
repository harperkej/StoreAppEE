package com.arberkuci.storeapp.ejb.user.boundary;

import com.arberkuci.storeapp.ejb.user.api.UserFacade;
import com.arberkuci.storeapp.ejb.user.control.UserControl;
import com.arberkuci.storeapp.ejb.user.dto.UserDto;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;


@Stateless
@Local(UserFacade.class)
public class UserFacadeBean implements UserFacade {

    @Inject
    UserControl userControl;

    private final String className = this.getClass().getName();

    private final Logger logger = Logger.getLogger(className);

    @Override
    public UserDto storeUser(UserDto userDto) {
        final String MN = "storeUser";
        logger.entering(className, MN);
        UserDto storedUser = userControl.storeUser(userDto);
        logger.exiting(className, MN);
        return storedUser;
    }

    @Override
    public UserDto findByUserById(Long id) {
        final String MN = "findByUserById";
        logger.entering(className, MN);
        UserDto foundUser = userControl.findUserById(id);
        logger.exiting(className, MN);
        return foundUser;
    }

    @Override
    public List<UserDto> findAllUsers() {
        final String MN = "findAllUsers";
        logger.entering(className, MN);
        List<UserDto> foundUsers = this.userControl.findAllUsers();
        logger.exiting(className, MN);
        return foundUsers;
    }
}
