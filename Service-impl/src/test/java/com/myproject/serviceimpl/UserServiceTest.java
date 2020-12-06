package com.myproject.serviceimpl;

import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.Role;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private UserMapper userMapper;

    private User user = new User();

    private UserDto userDto = new UserDto();

    private ArrayList<User> all = new ArrayList();

    private ArrayList<UserDto> allUserDto = new ArrayList();

    @BeforeEach
    public void createMocks() {
        MockitoAnnotations.initMocks(this);
//        all.add(new User(0, "Aleks", "pppp", "pppp", Role.USER.toString()));
//        all.add(new User(1, "Aleksa", "pp@", "pppp", Role.USER.toString()));
//
//        allUserDto.add(new UserDto(1,"Aleks", "pppp", "pppp", Role.USER.toString()));
//        allUserDto.add(new UserDto(1,"Aleksa", "pp@", "pppp", Role.USER.toString()));
    }

    @Test
    void findAll() {
        when(userDao.findAllUsers()).thenReturn(all);
        assertEquals(2, userService.findAll().size());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        when(userDao.findAllUsers()).thenReturn(all);
        userService.delete(all.get(0).getId());
        verify(userDao, times(1)).deleteUser(this.all.get(0));
    }

    @Test
    void save() {
//        user = new User(3, "Ale", "pp", "pppp", Role.USER.toString());
        userService.save(userDto);
        verify(userDao, times(1)).saveUser(user);
    }

    @Test
    void getUserByName() {
    }
}