package com.myproject.serviceimpl;

import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.History;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.Role;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.UserMapper;
import com.myproject.serviceimpl.exceptions.UserServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDao userDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user = new User();

    private UserDto userDto = new UserDto();

    private ArrayList<User> all = new ArrayList();

    private ArrayList<UserDto> allUserDto = new ArrayList();

    private Set<History> histories = new LinkedHashSet<>();

    @BeforeEach
    void createMocks() {
        MockitoAnnotations.initMocks(this);
        all.add(new User(histories, 1, "d", "s", "b", Role.USER));
        allUserDto.add(new UserDto());
        allUserDto.add(new UserDto());
    }

    @Test
    void findAll() {
        when(userDao.findAllUsers()).thenReturn(all);
        assertEquals(1, userService.findAll().size());
    }

    @Test
    void findAllWithNull() {
        when(userDao.findAllUsers()).thenReturn(null);
        assertThrows(UserServiceException.class,
                () -> userService.findAll());
    }

    @Test
    void delete() {
        when(userDao.findAllUsers()).thenReturn(all);
        userService.delete(all.get(0).getId());
        verify(userDao, times(1)).deleteUser(this.all.get(0));
    }

    @Test
    void deleteWithNull() {
        when(userDao.findAllUsers()).thenReturn(null);
        assertThrows(UserServiceException.class,
                () -> userService.delete(1));
    }

    @Test
    void save() {
        UserDto userDto = new UserDto(1, "d", "s", "b", Role.USER.getAuthority(), histories);
        when(userMapper.toEntity(userDto)).thenReturn(all.get(0));
        userService.save(userDto);
        verify(userDao, times(1)).saveUser(all.get(0));
    }

    @Test
    void saveWithNull() {
        assertThrows(UserServiceException.class,
                () -> userService.save(new UserDto()));
    }

    @Test
    void findById() {
        when(userDao.findUserById(1)).thenReturn(all.get(0));
        assertEquals(all.get(0), userService.findById(1));
    }

    @Test
    void findByIdWithNull() {
        assertThrows(UserServiceException.class,
                () -> userService.findById(1));
    }
}