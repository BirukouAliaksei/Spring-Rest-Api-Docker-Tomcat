package com.myproject.serviceimpl;

import com.myproject.daoapi.RentalPointDao;
import com.myproject.domain.entity.RentalPoint;
import com.myproject.dto.dto.RentalPointDto;
import com.myproject.dto.mapper.RentalPointMapper;
import com.myproject.serviceimpl.exceptions.RentalPointServiceException;
import com.myproject.serviceimpl.exceptions.ServiceValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class RentalPointServiceTest {

    @InjectMocks
    private RentalPointService rentalPointService;

    @Mock
    private RentalPointDao rentalPointDao;

    @Mock
    private RentalPointMapper rentalPointMapper;

    private ArrayList<RentalPoint> rentalPoints = new ArrayList<>();

    private ArrayList<RentalPointDto> rentalPointDtos = new ArrayList<>();

    @BeforeEach
    void createMocks() {
        MockitoAnnotations.initMocks(this);
        rentalPoints.add(new RentalPoint());
        rentalPointDtos.add(new RentalPointDto(1, "point", 1,1,1));
    }

    @Test
    void findAll() {
        when(rentalPointDao.findAllRentalPoints()).thenReturn(rentalPoints);
        assertEquals(1, rentalPointService.findAll().size());
    }

    @Test
    void findAllWithNull() {
        when(rentalPointDao.findAllRentalPoints()).thenReturn(null);
        assertThrows(RentalPointServiceException.class,
                () -> rentalPointService.findAll());
    }

    @Test
    void save() {
        when(rentalPointMapper.toEntity(rentalPointDtos.get(0))).thenReturn(rentalPoints.get(0));
        rentalPointService.save(rentalPointDtos.get(0));
        verify(rentalPointDao, times(1)).saveRentalPoint(rentalPoints.get(0));
    }

    @Test
    void saveWithNull() {
        assertThrows(ServiceValidationException.class,
                () -> rentalPointService.save(new RentalPointDto()));
    }

    @Test
    void delete() {
        when(rentalPointDao.findAllRentalPoints()).thenReturn(rentalPoints);
        rentalPointService.delete(rentalPoints.get(0).getId());
        verify(rentalPointDao, times(1)).deleteRentalPoint(this.rentalPoints.get(0));
    }

    @Test
    void deleteWithNull() {
        when(rentalPointDao.findAllRentalPoints()).thenReturn(null);
        assertThrows(RentalPointServiceException.class,
                () -> rentalPointService.delete(0));
    }

    @Test
    void rentalPointInfoById() {
        when(rentalPointDao.findRentalPointById(1)).thenReturn(rentalPoints.get(0));
        when(rentalPointMapper.toDto(rentalPoints.get(0))).thenReturn(rentalPointDtos.get(0));
        assertEquals(rentalPointDtos.get(0), rentalPointService.rentalPointInfoById(1));
    }

    @Test
    void findByIdWithNull() {
        assertThrows(RentalPointServiceException.class,
                () -> rentalPointService.rentalPointInfoById(1));
    }
}