package com.myproject.daoapi;

import com.myproject.domain.entity.Scooter;

import java.util.ArrayList;

public interface ScooterDao {

    Scooter findScooterById(int id);

    ArrayList<Scooter> findAllScooters();

    Scooter saveScooter(Scooter entity);

    void deleteScooter(Scooter entity);

    Scooter updateScooter(Scooter entity);
}
