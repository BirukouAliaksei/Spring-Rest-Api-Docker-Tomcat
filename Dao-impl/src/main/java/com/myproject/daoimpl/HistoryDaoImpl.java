package com.myproject.daoimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.domain.entity.History;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class HistoryDaoImpl extends GenericDaoImpl<History> implements HistoryDao {

    public HistoryDaoImpl() {
        super(History.class);
    }

    @Override
    public History findHistoryById(int id) {
        return this.findById(id);
    }

    @Transactional
    @Override
    public ArrayList<History> findAllHistory() {
        return this.findAll();
    }

    @Transactional
    @Override
    public History saveHistory(History entity) {
        return this.save(entity);
    }

    @Transactional
    @Override
    public void deleteHistory(History entity) {
        this.delete(entity);
    }

    @Transactional
    @Override
    public History updateHistory(History entity) {
        return this.update(entity);
    }
}
