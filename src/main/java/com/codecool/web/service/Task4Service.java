package com.codecool.web.service;

import com.codecool.web.dao.database.NorthwindDAO;

import com.codecool.web.model.Task4;

import java.sql.SQLException;
import java.util.List;

public final class Task4Service {

    private NorthwindDAO database;

    public Task4Service(NorthwindDAO dao) {
        this.database = dao;
    }

    public List<Task4> getAllResult() throws SQLException {
        return database.task4();
    }
}
