package com.myproject.daoimpl;

import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
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
    public User findUserByName(String name) {
        //FIXME add user by name
        return null;
    }

    @Override
    public ArrayList<User> findAllUsers() {
        return this.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User entity) {
        this.save(entity);
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
