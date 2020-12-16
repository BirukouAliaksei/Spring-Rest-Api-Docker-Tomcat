package com.myproject.serviceimpl;

import com.myproject.daoapi.ScooterDao;
import com.myproject.domain.entity.Scooter;
import com.myproject.dto.dto.ScooterDto;
import com.myproject.dto.mapper.ScooterMapper;
import com.myproject.serviceimpl.exceptions.ScooterServiceException;
import com.myproject.serviceimpl.exceptions.ServiceValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

class ScooterServiceTest {

    @InjectMocks
    private ScooterService scooterService;

    @Mock
    private ScooterDao scooterDao;

    @Mock
    private ScooterMapper scooterMapper;

    private ArrayList<Scooter> scooters = new ArrayList<>();

    private ArrayList<ScooterDto> scooterDtos = new ArrayList<>();

    @BeforeEach
    void createMocks() {
        MockitoAnnotations.initMocks(this);
        scooters.add(new Scooter());
        scooterDtos.add(new ScooterDto(1, 20.0, "xxx", true, 100, 1));
    }

    @Test
    void findAll() {
        when(scooterDao.findAllScooters()).thenReturn(scooters);
        assertEquals(1, scooterService.findAll().size());
    }

    @Test
    void findAllWithNull() {
        when(scooterDao.findAllScooters()).thenReturn(null);
        assertThrows(ScooterServiceException.class,
                () -> scooterService.findAll());
    }

    @Test
    void save() {
        when(scooterDao.findAllScooters()).thenReturn(scooters);
        when(scooterMapper.toEntity(scooterDtos.get(0))).thenReturn(scooters.get(0));
        scooterService.save(scooterDtos.get(0));
        verify(scooterDao, times(1)).saveScooter(scooters.get(0));
    }

    @Test
    void saveWithNull() {
        assertThrows(ServiceValidationException.class,
                () -> scooterService.save(new ScooterDto()));
    }

    @Test
    void delete() {
        when(scooterDao.findAllScooters()).thenReturn(scooters);
        scooterService.delete(scooters.get(0).getId());
        verify(scooterDao, times(1)).deleteScooter(this.scooters.get(0));
    }

    @Test
    void deleteWithNull() {
        when(scooterDao.findAllScooters()).thenReturn(null);
        assertThrows(ScooterServiceException.class,
                () -> scooterService.delete(0));
    }

    @Test
    void findById() {
        when(scooterDao.findScooterById(1)).thenReturn(scooters.get(0));
        when(scooterMapper.toDto(scooters.get(0))).thenReturn(scooterDtos.get(0));
        assertEquals(scooterDtos.get(0), scooterService.findById(1));
    }

    @Test
    void findByIdWithNull() {
        assertThrows(ScooterServiceException.class,
                () -> scooterService.findById(1));
    }
}