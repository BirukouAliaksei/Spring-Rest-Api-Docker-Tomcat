package com.myproject.daoimpl;

import com.myproject.daoapi.ScooterDao;
import com.myproject.domain.entity.Scooter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class ScooterDaoImpl extends GenericDaoImpl<Scooter> implements ScooterDao {

    public ScooterDaoImpl() {
        super(Scooter.class);
    }

    @Transactional
    @Override
    public Scooter findScooterById(int id) {
        return this.findById(id);
    }

    @Transactional
    @Override
    public ArrayList<Scooter> findAllScooters() {
        return this.findAll();
    }

    @Transactional
    @Override
    public Scooter saveScooter(Scooter entity) {
        return this.save(entity);
    }

    @Transactional
    @Override
    public void deleteScooter(Scooter entity) {
        this.delete(entity);
    }

    @Transactional
    @Override
    public Scooter updateScooter(Scooter entity) {
        return this.update(entity);
    }
}
