package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.domain.entity.History;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.mapper.HistoryMapper;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceimpl.exceptions.HistoryServiceException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Log4j
@Service
@NoArgsConstructor
public class HistoryService implements HistoryServiceApi {

    @Autowired
    private HistoryDao historyDaoApi;

    @Autowired
    private HistoryMapper historyMapper;

    @Transactional
    @Override
    public ArrayList<HistoryDto> findAll() {
        ArrayList<HistoryDto> historyDtos = new ArrayList<>();
        if (historyDaoApi.findAllHistory() != null) {
            for (History history : historyDaoApi.findAllHistory()) {
                historyDtos.add(historyMapper.toDto(history));
            }
            return historyDtos;
        } else throw new HistoryServiceException("History service throws null");
    }

    @Override
    public HistoryDto findById(int id) {
        History history = historyDaoApi.findHistoryById(id);
        if (history != null) {
            return historyMapper.toDto(history);
        } else {
            throw new HistoryServiceException("History service throws null");
        }
    }

}
