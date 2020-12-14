package com.myproject.serviceimpl;

import com.myproject.daoapi.ScooterDao;
import com.myproject.domain.entity.Scooter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ScooterServiceTest {

    @Mock
    private ScooterDao scooterDao;

    @InjectMocks
    private ScooterService scooterService;

    private ArrayList<Scooter> scooters = new ArrayList<>();

    @BeforeEach
    void createMocks() {
        MockitoAnnotations.initMocks(this);
        scooters.add(new Scooter());
    }

    @Test
    void findAll() {
        when(scooterDao.findAllScooters()).thenReturn(scooters);
        assertEquals(0, scooterService.findAll().size());
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
        when(scooterDao.findAllScooters()).thenReturn(scooters);
        scooterService.delete(scooters.get(0).getId());
        verify(scooterDao, times(1)).deleteScooter(this.scooters.get(0));
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findScooterById() {
    }
}