package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.daoapi.ScooterDao;
import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.Scooter;
import com.myproject.dto.dto.ScooterAdminDto;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.dto.mapper.ScooterMapper;
import com.myproject.serviceapi.ScooterServiceApi;
import com.myproject.serviceimpl.exceptions.ScooterServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ArrayList<ScooterDto> findRentalPointScootersById(int id) {
        ArrayList<ScooterDto> scooterDtos = new ArrayList<>();
        ArrayList<Scooter> all = scooterDao.findAllScooters();
        for (Scooter scooter : all) {
            if (scooter.getRentalPointId() == id) {
                scooterDtos.add(scooterMapper.toDto(scooter));
            }
        }
        return scooterDtos;
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
    public ScooterDto save(ScooterDto entity) {
        Scooter scooter = scooterMapper.toEntity(entity);
        if (scooter == null || scooter.getModel() == null ||
                scooter.getCost() == null || scooter.getRentalPointId() == 0
                || scooter.isAvailability() == Boolean.parseBoolean(null)) {
            throw new ScooterServiceException("Scooter service throws null");
        } else {
            return scooterMapper.toDto(scooterDao.saveScooter(scooter));
        }
    }

    @Transactional
    @Override
    public HttpStatus delete(int id) {
        ArrayList<Scooter> scooters;
        if (scooterDao.findAllScooters() != null) {
            scooters = scooterDao.findAllScooters();
            for (Scooter scooter : scooters) {
                if (scooter.getId() == id) {
                    scooterDao.deleteScooter(scooter);
                    return HttpStatus.OK;
                }
            }
        } else throw new ScooterServiceException("Scooter service throws null");

        return HttpStatus.BAD_REQUEST;
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
    public ScooterDto findById(int id) {
        Scooter scooter = scooterDao.findScooterById(id);
        if (scooter == null) {
            throw new ScooterServiceException("Error");
        } else {
            return scooterMapper.toDto(scooter);
        }
    }

    @Override
    public ScooterAdminDto findScooterById(int id) {
        Scooter scooter = scooterDao.findScooterById(id);
        if (scooter == null) {
            throw new ScooterServiceException("Error");
        } else {
            return scooterMapper.toAdminDto(scooter);
        }
    }
}
