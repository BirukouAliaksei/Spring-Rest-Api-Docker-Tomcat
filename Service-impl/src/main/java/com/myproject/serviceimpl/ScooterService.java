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
import com.myproject.serviceimpl.exceptions.ServiceValidationException;
import com.myproject.serviceimpl.exceptions.UserServiceException;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Log4j
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
        if (entity == null || entity.getModel() == null
                || entity.getCost() == null || entity.getRentalPointId() == 0
                || entity.isAvailability() == Boolean.parseBoolean(null)) {
            log.info("not valid data");
            throw new ServiceValidationException();
        } else {
            Scooter scooter = scooterMapper.toEntity(entity);
            return scooterMapper.toDto(scooterDao.saveScooter(scooter));
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        if (scooterDao.findAllScooters() == null) {
            throw new UserServiceException("");
        } else {
            for (Scooter scooter : scooterDao.findAllScooters()) {
                if (scooter.getId() == id) {
                    scooterDao.deleteScooter(scooter);
                }else {
                    throw new ServiceValidationException();
                }
            }
        }
    }

    @Transactional
    @Override
    public ScooterDto update(ScooterDto entity, int id) {
        if (entity == null || entity.getModel() == null ||
                entity.getCost() == null || entity.getRentalPointId() == 0
                || entity.isAvailability() == Boolean.parseBoolean(null)) {
            throw new ServiceValidationException();
        } else {
            Scooter newScooter = scooterMapper.toEntity(entity);
            Scooter scooter = scooterDao.findScooterById(id);
            scooter.setId(id);
            scooter.setCost(newScooter.getCost());
            scooter.setModel(newScooter.getModel());
            scooter.setAvailability(newScooter.isAvailability());
            scooter.setBattery(newScooter.getBattery());
            return scooterMapper.toDto(scooterDao.updateScooter(scooter));
        }
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
