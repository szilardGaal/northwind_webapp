package com.codecool.web.servlet;

import com.codecool.web.dao.database.NorthwindDAO;
import com.codecool.web.model.Task5;
import com.codecool.web.service.Task5Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/task5")
public final class Task5Servlet extends AbstractServlet {

    // https://www.postgresql.org/docs/current/static/errcodes-appendix.html
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task5> test = (List<Task5>) req.getAttribute("list");
        if (test != null) {
            req.setAttribute("list", test);
        } else {
            try (Connection connection = getConnection(req.getServletContext())) {
                NorthwindDAO dao = new NorthwindDAO(connection);
                Task5Service service = new Task5Service(dao);

                List<Task5> list = service.getAllResult();

                req.setAttribute("list", list);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
        req.getRequestDispatcher("task5.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            NorthwindDAO dao = new NorthwindDAO(connection);
            Task5Service service = new Task5Service(dao);

            Integer limit;
            String limitString = req.getParameter("value");
            if (limitString.equals("")) {
                doGet(req, resp);
            } else {
                limit = Integer.parseInt(limitString);
                req.setAttribute("list", service.filter(limit));
            }

        } catch (SQLException ex) {
            if (SQL_ERROR_CODE_UNIQUE_VIOLATION.equals(ex.getSQLState())) {
                req.setAttribute("error", "Task1 has been already added to one of the selected shops");
            } else {
                throw new ServletException(ex);
            }
        }
        doGet(req, resp);
    }
}
