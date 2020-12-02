package com.myproject.daoapi;

import com.myproject.domain.entity.History;
import com.myproject.domain.entity.Scooter;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;

public interface HistoryDao {

    ArrayList<History> findAllHistory();

    History saveHistory(History entity);

    void deleteHistory(History entity);

    History updateHistory(History entity);

    History findHistoryById(int id);
}
