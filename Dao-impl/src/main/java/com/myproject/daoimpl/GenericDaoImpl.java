package com.myproject.daoimpl;

import com.myproject.daoapi.GenericDao;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;


public abstract class GenericDaoImpl<T> implements GenericDao<T> {

    @PersistenceContext
    public EntityManager entityManager;

    private Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        super();
        this.type = type;
    }

    public GenericDaoImpl() {
    }


    public T findById(int id) {
        T type = (T) entityManager.find(this.type, new Integer(id));
        entityManager.detach(type);
        return type;
    }


    public ArrayList<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> rootEntry = cq.from(type);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = entityManager.createQuery(all);
        return (ArrayList<T>) allQuery.getResultList();
    }


    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }
}