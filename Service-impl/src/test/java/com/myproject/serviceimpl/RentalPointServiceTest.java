package com.myproject.serviceimpl;

import com.myproject.daoapi.RentalPointDao;
import com.myproject.domain.entity.RentalPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class RentalPointServiceTest {

    @Mock
    private RentalPointDao rentalPointDao;

    @InjectMocks
    private RentalPointService rentalPointService;

    private ArrayList<RentalPoint> rentalPoints = new ArrayList<>();

    @BeforeEach
    void createMocks() {
        MockitoAnnotations.initMocks(this);
        rentalPoints.add(new RentalPoint());
    }

    @Test
    void findAll() {
        when(rentalPointDao.findAllRentalPoints()).thenReturn(rentalPoints);
        assertEquals(0, rentalPointService.findAll().size());
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
        when(rentalPointDao.findAllRentalPoints()).thenReturn(rentalPoints);
        rentalPointService.delete(rentalPoints.get(0).getId());
        verify(rentalPointDao, times(1)).deleteRentalPoint(this.rentalPoints.get(0));
    }

    @Test
    void update() {
    }

    @Test
    void rentalPointInfoById() {
    }
}