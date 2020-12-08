package com.myproject.daoapi;

import java.util.ArrayList;

public interface GenericDao<T> {

    T findById(int id);

    ArrayList<T> findAll();

    T save(T entity);

    void delete(T entity);

    T update(T entity);
}
