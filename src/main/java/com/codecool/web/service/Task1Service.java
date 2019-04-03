package com.codecool.web.service;

import com.codecool.web.dao.database.NorthwindDAO;
import com.codecool.web.model.Task1;

import java.sql.SQLException;
import java.util.List;

public final class Task1Service {

   private NorthwindDAO database;

    public Task1Service(NorthwindDAO dao) {
        this.database = dao;
    }

    public List<Task1> getAllResult() throws SQLException {
        return database.task1();
    }
}
