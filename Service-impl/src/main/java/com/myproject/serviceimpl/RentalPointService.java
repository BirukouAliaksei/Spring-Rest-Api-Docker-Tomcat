package com.myproject.serviceimpl;

import com.myproject.daoapi.RentalPointDao;
import com.myproject.domain.entity.RentalPoint;
import com.myproject.dto.dto.RentalPointDetailsDto;
import com.myproject.dto.dto.RentalPointDto;
import com.myproject.dto.mapper.RentalPointMapper;
import com.myproject.serviceapi.RentalPointServiceApi;
import com.myproject.serviceimpl.exceptions.RentalPointServiceException;
import com.myproject.serviceimpl.exceptions.ServiceValidationException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Log4j
@Service
@NoArgsConstructor
public class RentalPointService implements RentalPointServiceApi {

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
    public RentalPointDto save(RentalPointDto entity) {
        if (entity.getAddress() == null || entity.getParentId() == 0
                || entity.getLatitude() == 0 || entity.getLongitude() == 0) {
            throw new ServiceValidationException();
        } else {
            RentalPoint rentalPoint = rentalPointMapper.toEntity(entity);
            return rentalPointMapper.toDto(rentalPointDao.saveRentalPoint(rentalPoint));
        }
    }

    @Transactional
    @Override
    public String delete(int id) {
        if (rentalPointDao.findAllRentalPoints() == null) {
            throw new RentalPointServiceException("");
        } else {
            for (RentalPoint rentalPoint : rentalPointDao.findAllRentalPoints()) {
                if (rentalPoint.getId() == id) {
                    rentalPointDao.deleteRentalPoint(rentalPoint);
                    return "Rental point deleted";
                }
            }
        }
        return "Rental point already deleted";
    }

    @Transactional
    @Override
    public RentalPointDto update(RentalPointDto entity, int id) {
        if (entity.getAddress() == null || entity.getParentId() == 0
                || entity.getLatitude() == 0 || entity.getLongitude() == 0) {
            throw new ServiceValidationException();
        } else {
            rentalPointInfoById(id);
            RentalPoint rentalPoint = rentalPointMapper.toEntity(entity);
            rentalPoint.setId(id);
            return rentalPointMapper.toDto(rentalPointDao.updateRentalPoint(rentalPoint));
        }
    }

    @Override
    public RentalPointDto rentalPointInfoById(int id) {
        RentalPoint rentalPoint = rentalPointDao.findRentalPointById(id);
        if (rentalPoint == null) {
            throw new RentalPointServiceException("Rental point service throw null");
        } else {
            return rentalPointMapper.toDto(rentalPoint);
        }
    }

    @Override
    public RentalPointDetailsDto findRentalPointScootersById(int id) {
        RentalPoint rentalPoint = rentalPointDao.findRentalPointById(id);
        if (rentalPoint == null) {
            throw new RentalPointServiceException("Rental point service throw null");
        } else {
            return rentalPointMapper.toDtoWithDetails(rentalPoint);
        }
    }
}
