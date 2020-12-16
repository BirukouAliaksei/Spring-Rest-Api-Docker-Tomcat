package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.domain.entity.History;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.mapper.HistoryMapper;
import com.myproject.serviceimpl.exceptions.HistoryServiceException;
import com.myproject.serviceimpl.exceptions.RentalPointServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HistoryServiceTest {

    @InjectMocks
    private HistoryService historyService;

    @Mock
    private HistoryDao historyDao;

    @Mock
    private HistoryMapper historyMapper;

    private ArrayList<History> histories = new ArrayList<>();

    private ArrayList<HistoryDto> historyDtos = new ArrayList<>();

    @BeforeEach
    void createMocks() {
        MockitoAnnotations.initMocks(this);
        histories.add(new History());
        historyDtos.add(new HistoryDto(1,1,1.0, LocalDateTime.now(), LocalDateTime.now(), 1,1,"ss", 1,1,"d"));
    }


    @Test
    void findAll() {
        when(historyDao.findAllHistory()).thenReturn(histories);
        assertEquals(1, historyService.findAll().size());
    }

    @Test
    void findAllWithNull() {
        when(historyDao.findAllHistory()).thenReturn(null);
        assertThrows(HistoryServiceException.class,
                () -> historyService.findAll());
    }

    @Test
    void findById() {
        when(historyDao.findHistoryById(1)).thenReturn(histories.get(0));
        when(historyMapper.toDto(histories.get(0))).thenReturn(historyDtos.get(0));
        assertEquals(historyDtos.get(0), historyService.findById(1));
    }

    @Test
    void findByIdWithNull() {
        assertThrows(HistoryServiceException.class,
                () -> historyService.findById(1));
    }
}