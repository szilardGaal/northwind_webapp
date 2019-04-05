package com.codecool.web.service;

import com.codecool.web.dao.database.NorthwindDAO;
import com.codecool.web.model.Task2;

import java.sql.SQLException;
import java.util.List;

public final class Task2Service {

    private NorthwindDAO database;

    public Task2Service(NorthwindDAO dao) {
        this.database = dao;
    }

    public List<Task2> getAllResult() throws SQLException {
        return database.task2();
    }

    public List<Task2> filter(int numberOfProducts) throws SQLException {
        return database.getFilteredTask2(numberOfProducts);
    }
}
