package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.domain.entity.History;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.mapper.HistoryMapper;
import com.myproject.serviceapi.HistoryServiceApi;
import com.myproject.serviceimpl.exceptions.HistoryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class HistoryService implements HistoryServiceApi {

    public HistoryService() {
    }

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

    @Transactional
    @Override
    public void save(HistoryDto entity) {
        History history = historyMapper.toEntity(entity);
        if (history != null) {
            historyDaoApi.saveHistory(history);
        } else {
            throw new HistoryServiceException("History service throws null");
        }
    }

    @Transactional
    @Override
    public void delete(int id) {
        ArrayList<History> histories;
        if (historyDaoApi.findAllHistory() != null) {
            histories = historyDaoApi.findAllHistory();
            for (History history : histories) {
                if (history.getId() == id) {
                    historyDaoApi.deleteHistory(history);
                }
            }
        } else throw new HistoryServiceException("History service throws null");

    }

    @Transactional
    @Override
    public HistoryDto update(HistoryDto entity) {
        return null;
    }


    @Override
    public HistoryDto findById(int id) {
        return historyMapper.toDto(historyDaoApi.findHistoryById(id));
    }

    @Transactional
    @Override
    public ArrayList<HistoryDto> findHistoryByUserId(int id) {
        ArrayList<HistoryDto> idList = new ArrayList<>();
        ArrayList<History> histories = historyDaoApi.findAllHistory();
        if (histories != null) {
            for (History history : histories) {
                if (history.getUserId() == id) {
                    idList.add(historyMapper.toDto(history));
                }
            }
            return idList;
        } else throw new HistoryServiceException("History service throws null");
    }

    @Transactional
    @Override
    public ArrayList<HistoryDto> findScooterHistoryById(int id) {
        ArrayList<HistoryDto> idList = new ArrayList<>();
        if (historyDaoApi.findAllHistory() != null) {
            for (History history : historyDaoApi.findAllHistory()) {
                if (history.getScooterId() == id) {
                    idList.add(historyMapper.toDto(history));
                }
            }
        } else throw new HistoryServiceException("History service exception");
        return idList;
    }

}
