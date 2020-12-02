package com.myproject.serviceimpl;

import com.myproject.daoapi.RentalPointDao;
import com.myproject.domain.entity.RentalPoint;
import com.myproject.dto.dto.RentalPointDto;
import com.myproject.dto.mapper.RentalPointMapper;
import com.myproject.serviceapi.RentalPointServiceApi;
import com.myproject.serviceimpl.exceptions.RentalPointServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class RentalPointService implements RentalPointServiceApi {

    public RentalPointService() {
    }

    @Autowired
    private RentalPointDao rentalPointDao;

    @Autowired
    private RentalPointMapper rentalPointMapper;


    @Transactional
    @Override
    public ArrayList<RentalPointDto> findAll() {
        ArrayList<RentalPointDto> rentalPoints = new ArrayList<>();
        if (rentalPointDao.findAllRentalPoints() != null) {
            for (RentalPoint rentalPoint : rentalPointDao.findAllRentalPoints()) {
                rentalPoints.add(rentalPointMapper.toDto(rentalPoint));
            }
            return rentalPoints;
        } else throw new RentalPointServiceException("Rental point service throws null");
    }

    @Transactional
    @Override
    public void save(RentalPointDto entity) {
        RentalPoint rentalPoint = rentalPointMapper.toEntity(entity);
        if (rentalPoint != null) {
            rentalPointDao.saveRentalPoint(rentalPoint);
        } else {
            throw new RentalPointServiceException("Rental Point service throws null");
        }
    }

    @Transactional
    @Override
    public HttpStatus delete(int id) {
        ArrayList<RentalPoint> rentalPoints;
        if (rentalPointDao.findAllRentalPoints() != null) {
            rentalPoints = rentalPointDao.findAllRentalPoints();
            for (RentalPoint rentalPoint : rentalPoints) {
                if (rentalPoint.getId() == id) {
                    rentalPointDao.deleteRentalPoint(rentalPoint);
                    return HttpStatus.OK;
                }
            }
        } else throw new RentalPointServiceException("Rentalpoint service throws null");

        return HttpStatus.BAD_REQUEST;
    }

    @Transactional
    @Override
    public RentalPointDto update(RentalPointDto entity, int id) {
        RentalPoint newRentalPoint = rentalPointMapper.toEntity(entity);
        RentalPoint rentalPoint = rentalPointDao.findRentalPointById(id);
        rentalPoint.setId(id);
        rentalPoint.setAddress(newRentalPoint.getAddress());
        rentalPoint.setParentId(newRentalPoint.getParentId());
        rentalPoint.setLatitude(newRentalPoint.getLatitude());
        rentalPoint.setLongitude(newRentalPoint.getLongitude());
        return rentalPointMapper.toDto(rentalPointDao.updateRentalPoint(rentalPoint));
    }

    @Override
    public RentalPointDto rentalPointInfoById(int id) {
        return rentalPointMapper.toDto(rentalPointDao.findRentalPointById(id));
    }
}
