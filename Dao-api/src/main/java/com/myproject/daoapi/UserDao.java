package com.myproject.daoapi;


import com.myproject.domain.entity.User;

import java.util.ArrayList;

public interface UserDao  {

    User findUserByName(String name);

    ArrayList<User> findAllUsers();

    void saveUser(User entity);

    void deleteUser(User entity);

    User updateUser(User entity);

    User findUserById(int id);
}
