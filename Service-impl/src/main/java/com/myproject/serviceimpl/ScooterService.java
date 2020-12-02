package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.daoapi.RentalPointDao;
import com.myproject.daoapi.ScooterDao;
import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.History;
import com.myproject.domain.entity.RentalPoint;
import com.myproject.domain.entity.Scooter;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.OfferType;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.dto.mapper.RentalPointMapper;
import com.myproject.dto.mapper.ScooterMapper;
import com.myproject.serviceapi.ScooterServiceApi;
import com.myproject.serviceimpl.exceptions.ScooterServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ScooterService implements ScooterServiceApi {

    public ScooterService() {
    }

    @Autowired
    private ScooterDao scooterDao;

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ScooterMapper scooterMapper;


    @Transactional
    public  ArrayList<ScooterDto> findRentalPointScootersById(int id){
        ArrayList<ScooterDto> scooterDtos = new ArrayList<>();
        ArrayList<Scooter> all = scooterDao.findAllScooters();
        for (Scooter scooter : all) {
            if (scooter.getRentalPointId()==id) {
                scooterDtos.add(scooterMapper.toDto(scooter));
            }
        }return scooterDtos;
    }

    @Transactional
    @Override
    public ArrayList<ScooterDto> findAll() {
        ArrayList<ScooterDto> userDtos = new ArrayList<>();
        if (scooterDao.findAllScooters() != null) {
            for (Scooter scooter : scooterDao.findAllScooters()) {
                userDtos.add(scooterMapper.toDto(scooter));
            }
            return userDtos;
        } else throw new ScooterServiceException("Scooter service throws null");
    }

    @Transactional
    @Override
    public void save(ScooterDto entity) {
        Scooter scooter = scooterMapper.toEntity(entity);
//        User user = userDao.findUserById(1);
//        History history = new History();
//        history.setUserId2(user.getId());
//        history.setMileade(2.0);
//        history.setDate(LocalDate.of(2020,2,2));
//        history.setStartLocationId(scooter.getRentalPointId());
//        history.setFinishLocationId(1);
//        history.setOfferType(OfferType.ONCE_TIME.toString());
//        history.setOfferCost(5.0);
//        history.setScooterId(scooter.getId());
        if (scooter != null) {
            scooterDao.saveScooter(scooter);
//            history.setFinishLocationId(1);
//            historyDao.saveHistory(history);
        } else {
            throw new ScooterServiceException("Scooter service throws null");
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        ArrayList<Scooter> scooters;
        if (scooterDao.findAllScooters() != null) {
            scooters = scooterDao.findAllScooters();
            for (Scooter scooter : scooters) {
                if (scooter.getId() == id) {
                    scooterDao.deleteScooter(scooter);
                }
            }
        } else throw new ScooterServiceException("Scooter service throws null");

    }

    @Transactional
    @Override
    public ScooterDto update(ScooterDto entity, int id) {
        Scooter newScooter = scooterMapper.toEntity(entity);
        Scooter scooter = scooterDao.findScooterById(id);
        scooter.setId(id);
        scooter.setCost(newScooter.getCost());
        scooter.setModel(newScooter.getModel());
        scooter.setAvailability(newScooter.isAvailability());
        scooter.setBattery(newScooter.getBattery());
        return scooterMapper.toDto(scooterDao.updateScooter(scooter));
    }

    @Override
    public void setScooterPrice(Double price, int id) {
        Scooter scooter = scooterDao.findScooterById(id);
        scooter.setId(id);
        scooter.setCost(price);
        scooter.setModel(scooter.getModel());
        scooter.setAvailability(scooter.isAvailability());
        scooter.setBattery(scooter.getBattery());
        scooterDao.updateScooter(scooter);
    }

    @Override
    public void ScooterTariffication(Double rate) {
    }


    @Override
    public ScooterDto findById(int id) {
        return scooterMapper.toDto(scooterDao.findScooterById(id));
    }
}
