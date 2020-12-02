package com.myproject.daoapi;

import java.util.ArrayList;
import java.util.List;

public interface GenericDao<T> {

    T findById(int id);

//    ArrayList<T> findAll(List<String> allParams);

    ArrayList<T> findAll();

    void save(T entity);

    void delete(T entity);

    T update(T entity);
}
