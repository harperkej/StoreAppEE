package com.arberkuci.storeapp.ejb.user.control;

import com.arberkuci.storeapp.ejb.user.dto.UserDto;

import javax.json.JsonObject;
import java.util.List;

/**
 * Created by a.kuci on 4/9/2017.
 */
public interface UserControl {

    public UserDto storeUser(UserDto userDto);

    public UserDto findUserById(Long id);

    public List<UserDto> findAllUsers();

}
