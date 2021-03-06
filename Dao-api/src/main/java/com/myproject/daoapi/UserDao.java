package com.myproject.daoapi;


import com.myproject.domain.entity.User;

import java.util.ArrayList;

public interface UserDao {

    ArrayList<User> findAllUsers();

    User saveUser(User entity);

    void deleteUser(User entity);

    User updateUser(User entity);

    User findUserById(int id);

    User findByLogin(String login);
}
