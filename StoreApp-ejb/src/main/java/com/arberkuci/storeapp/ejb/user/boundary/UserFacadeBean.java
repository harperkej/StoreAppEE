package com.arberkuci.storeapp.ejb.user.boundary;

import com.arberkuci.storeapp.ejb.user.api.UserFacade;
import com.arberkuci.storeapp.ejb.user.control.UserControl;
import com.arberkuci.storeapp.common.rest.response.UserDto;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
@Local(UserFacade.class)
public class UserFacadeBean implements UserFacade {

    @Inject
    UserControl userControl;

    @Override
    public UserDto storeUser(UserDto userDto) {
        return userControl.storeUser(userDto);
    }

    @Override
    public UserDto findByUserById(Long id) {
        return userControl.findUserById(id);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return this.userControl.findAllUsers();
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        return this.userControl.updateUser(userDto);
    }

    @Override
    public UserDto getUserByUserName(String userName) {
        return this.userControl.getUserByUserName(userName);
    }
}
