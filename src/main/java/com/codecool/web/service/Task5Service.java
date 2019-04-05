package com.codecool.web.service;

import com.codecool.web.dao.database.NorthwindDAO;

import com.codecool.web.model.Task5;

import java.sql.SQLException;
import java.util.List;

public final class Task5Service {

    private NorthwindDAO database;

    public Task5Service(NorthwindDAO dao) {
        this.database = dao;
    }

    public List<Task5> getAllResult() throws SQLException {
        return database.task5();
    }

    public List<Task5> filter(Integer limit) throws SQLException {
        return database.getFilteredTask5(limit);
    }
}
