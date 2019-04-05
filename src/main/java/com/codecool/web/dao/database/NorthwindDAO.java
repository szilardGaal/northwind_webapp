package com.codecool.web.dao.database;

import com.codecool.web.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class NorthwindDAO extends AbstractDao {

    public NorthwindDAO(Connection connection) {
        super(connection);
    }

    public List<Task1> task1() throws SQLException {
        List<Task1> taskList = new ArrayList<>();
        String sql = "SELECT Product_name AS Product, company_name AS Company FROM Products\n" +
                     "INNER JOIN suppliers\n" +
                     "ON Products.supplier_id = suppliers.supplier_id\n" +
                     "ORDER BY Product ASC, Company ASC;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                taskList.add(fetchTask1(resultSet));
            }
        }
        return taskList;
    }

    public List<Task1> getFilteredTask1(String companyName) throws SQLException {
        List<Task1> taskList = new ArrayList<>();
        String sql = "SELECT Product_name AS Product, company_name AS Company FROM Products\n" +
                     "INNER JOIN suppliers\n" +
                     "ON Products.supplier_id = suppliers.supplier_id\n" +
                     "WHERE company_name = ?\n" +
                     "ORDER BY Product ASC, Company ASC;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, companyName);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    taskList.add(fetchTask1(resultSet));
                }
            }
        }
        return taskList;
    }

    private Task1 fetchTask1(ResultSet resultSet) throws SQLException {
        String product = resultSet.getString("Product");
        String company = resultSet.getString("Company");
        return new Task1(product, company);
    }

    public List<Task2> task2() throws SQLException {
        List<Task2> taskList = new ArrayList<>();
        String sql = "SELECT COUNT(products.supplier_id) AS NumberOfProducts, company_name AS Company FROM products\n" +
                     "INNER JOIN suppliers\n" +
                     "ON suppliers.supplier_id = products.supplier_id\n" +
                     "GROUP BY suppliers.company_name\n" +
                     "ORDER BY numberofproducts DESC, Company ASC;\n";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                taskList.add(fetchTask2(resultSet));
            }
        }
        return taskList;
    }

    public List<Task2> getFilteredTask2(int numberOfProducts) throws SQLException {
        List<Task2> taskList = new ArrayList<>();
        String sql = "SELECT COUNT(products.supplier_id) AS NumberOfProducts, company_name AS Company FROM products\n" +
                     "INNER JOIN suppliers\n" +
                     "ON suppliers.supplier_id = products.supplier_id\n" +
                     "GROUP BY suppliers.company_name\n" +
                     "HAVING COUNT(products.supplier_id) >= ? \n" +
                     "ORDER BY numberofproducts DESC, Company ASC;\n";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, numberOfProducts);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    taskList.add(fetchTask2(resultSet));
                }
            }
        }
        return taskList;
    }

    private Task2 fetchTask2(ResultSet resultSet) throws SQLException {
        int product = resultSet.getInt("NumberOfProducts");
        String company = resultSet.getString("Company");
        return new Task2(product, company);
    }

    public List<Task3> task3() throws SQLException {
        List<Task3> taskList = new ArrayList<>();
        String sql = "SELECT company_name AS Company FROM products\n" +
                     "INNER JOIN suppliers\n" +
                     "ON suppliers.supplier_id = products.supplier_id\n" +
                     "GROUP BY suppliers.company_name\n" +
                     "HAVING COUNT(products.supplier_id) = 5\n" +
                     "ORDER BY Company ASC;\n";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                taskList.add(fetchTask3(resultSet));
            }
        }
        return taskList;
    }

    public List<Task3> getFilteredTask3(int numberOfProducts) throws SQLException {
        List<Task3> taskList = new ArrayList<>();
        String sql = "SELECT company_name AS Company FROM products\n" +
                     "INNER JOIN suppliers\n" +
                     "ON suppliers.supplier_id = products.supplier_id\n" +
                     "GROUP BY suppliers.company_name\n" +
                     "HAVING COUNT(products.supplier_id) = ?\n" +
                     "ORDER BY Company ASC;\n";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, numberOfProducts);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    taskList.add(fetchTask3(resultSet));
                }
            }
        }
        return taskList;
    }

    private Task3 fetchTask3(ResultSet resultSet) throws SQLException {
        String company = resultSet.getString("Company");
        return new Task3(company);
    }

    public List<Task4> task4() throws SQLException {
        List<Task4> taskList = new ArrayList<>();
        String sql = "SELECT company_name AS Company, ARRAY_AGG(orders.order_id) AS OrderIDs FROM customers\n" +
                     "LEFT JOIN orders\n" +
                     "ON customers.customer_id = orders.customer_id\n" +
                     "GROUP BY company_name\n" +
                     "ORDER BY company_name ASC;";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                taskList.add(fetchTask4(resultSet));
            }
        }
        return taskList;
    }

    public List<Task4> getFilteredTask4(Integer orderID) throws SQLException {
        List<Task4> taskList = new ArrayList<>();
        String sql = "SELECT company_name AS Company, ARRAY_AGG(orders.order_id) AS OrderIDs FROM customers\n" +
                     "LEFT JOIN orders\n" +
                     "ON customers.customer_id = orders.customer_id\n" +
                     "WHERE orders.order_id = ?" +
                     "GROUP BY company_name\n" +
                     "ORDER BY company_name ASC;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    taskList.add(fetchTask4(resultSet));
                }
            }
        }
        return taskList;
    }

    private Task4 fetchTask4(ResultSet resultSet) throws SQLException {
        String company = resultSet.getString("Company");
        String orderIDs = resultSet.getString("OrderIDs");
        return new Task4(company, orderIDs);
    }

    public List<Task5> task5() throws SQLException {
        List<Task5> taskList = new ArrayList<>();
        String sql = "SELECT s.company_name AS Company, p.product_name AS Product, p.unit_price AS Price\n" +
                     "FROM products AS p\n" +
                     "JOIN suppliers AS s ON p.supplier_id = s.supplier_id\n" +
                     "LEFT JOIN products AS pp ON p.supplier_id = pp.supplier_id AND p.unit_price < pp.unit_price\n" +
                     "WHERE pp.product_id IS NULL\n" +
                     "ORDER BY p.unit_price DESC, Product ASC, Company ASC;\n";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                taskList.add(fetchTask5(resultSet));
            }
        }
        return taskList;
    }

    public List<Task5> getFilteredTask5(Integer limit) throws SQLException {
        List<Task5> taskList = new ArrayList<>();
        String sql = "SELECT s.company_name AS Company, p.product_name AS Product, p.unit_price AS Price\n" +
                     "FROM products AS p\n" +
                     "JOIN suppliers AS s ON p.supplier_id = s.supplier_id\n" +
                     "LEFT JOIN products AS pp ON p.supplier_id = pp.supplier_id AND p.unit_price < pp.unit_price\n" +
                     "WHERE pp.product_id IS NULL AND p.unit_price > ?\n" +
                     "ORDER BY p.unit_price DESC, Product ASC, Company ASC;\n";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, limit);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    taskList.add(fetchTask5(resultSet));
                }
            }
        }
        return taskList;
    }

    private Task5 fetchTask5(ResultSet resultSet) throws SQLException {
        String company = resultSet.getString("Company");
        String product = resultSet.getString("Product");
        float price = resultSet.getFloat("Price");
        return new Task5(company, product, price);
    }
}
