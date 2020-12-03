package com.myproject.daoimpl;

import com.myproject.daoapi.RentalPointDao;
import com.myproject.domain.entity.RentalPoint;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public class RentalPointDaoImpl extends GenericDaoImpl<RentalPoint> implements RentalPointDao {

    public RentalPointDaoImpl() {
        super(RentalPoint.class);
    }

    @Override
    public RentalPoint findRentalPointById(int id) {
        return this.findById(id);
    }

    @Transactional
    @Override
    public ArrayList<RentalPoint> findAllRentalPoints() {
        return this.findAll();
    }

    @Transactional
    @Override
    public RentalPoint saveRentalPoint(RentalPoint entity) {
        return this.save(entity);
    }

    @Transactional
    @Override
    public void deleteRentalPoint(RentalPoint entity) {
        this.delete(entity);
    }

    @Transactional
    @Override
    public RentalPoint updateRentalPoint(RentalPoint entity) {
        return this.update(entity);
    }
}
