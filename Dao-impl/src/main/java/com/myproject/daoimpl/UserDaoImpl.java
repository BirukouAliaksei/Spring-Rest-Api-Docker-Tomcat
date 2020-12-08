package com.myproject.daoimpl;

import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findUserById(int id) {
        return this.findById(id);
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        User userById = null;
        for (User user : findAllUsers()) {
            if (user.getUsername().equalsIgnoreCase(login)) {
                userById = findUserById(user.getId());
                break;
            }
        }
        return userById;
//        return entityManager.find(User.class, login);
    }

    @Override
    public ArrayList<User> findAllUsers() {
        return this.findAll();
    }

    @Transactional
    @Override
    public User saveUser(User entity) {
        return this.save(entity);
    }

    @Transactional
    @Override
    public void deleteUser(User entity) {
        this.delete(entity);
    }

    @Transactional
    @Override
    public User updateUser(User entity) {
        return this.update(entity);
    }


}
