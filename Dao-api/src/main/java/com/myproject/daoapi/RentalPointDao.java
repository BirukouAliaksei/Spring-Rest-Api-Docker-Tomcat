package com.myproject.daoapi;

import com.myproject.domain.entity.RentalPoint;
import com.myproject.domain.entity.Scooter;

import java.util.ArrayList;

public interface RentalPointDao {

    RentalPoint findRentalPointById(int id);

    ArrayList<RentalPoint> findAllRentalPoints();

    RentalPoint saveRentalPoint(RentalPoint entity);

    void deleteRentalPoint(RentalPoint rentalPoint);

    RentalPoint updateRentalPoint(RentalPoint entity);
}
