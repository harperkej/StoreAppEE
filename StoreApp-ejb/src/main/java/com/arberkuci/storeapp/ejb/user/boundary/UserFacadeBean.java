package com.arberkuci.storeapp.ejb.user.boundary;

import com.arberkuci.storeapp.ejb.user.api.UserFacade;
import com.arberkuci.storeapp.ejb.user.control.UserControl;
import com.arberkuci.storeapp.ejb.user.dto.UserDto;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by a.kuci on 4/6/2017.
 */
@Stateless
@Local(UserFacade.class)
public class UserFacadeBean implements UserFacade {

    @Inject
    UserControl userControl;

    public UserDto storeUser(UserDto userDto) {
        return userControl.storeUser(userDto);
    }

    public UserDto findByUserById(Long id) {
        return userControl.findUserById(id);
    }

    public List<UserDto> findAllUsers() {
        return userControl.findAllUsers();
    }
}
