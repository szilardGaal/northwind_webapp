package com.codecool.web.service;

import com.codecool.web.dao.database.NorthwindDAO;

import com.codecool.web.model.Task3;

import java.sql.SQLException;
import java.util.List;

public final class Task3Service {

    private NorthwindDAO database;

    public Task3Service(NorthwindDAO dao) {
        this.database = dao;
    }

    public List<Task3> getAllResult() throws SQLException {
        return database.task3();
    }
}
