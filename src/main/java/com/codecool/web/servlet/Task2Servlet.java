package com.codecool.web.servlet;

import com.codecool.web.dao.database.NorthwindDAO;
import com.codecool.web.model.Task2;
import com.codecool.web.service.Task2Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/task2")
public final class Task2Servlet extends AbstractServlet {

    // https://www.postgresql.org/docs/current/static/errcodes-appendix.html
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Task2> test = (List<Task2>) req.getAttribute("list");
        if (test != null) {
            req.getRequestDispatcher("task2.jsp").forward(req, resp);

        } else {

            try (Connection connection = getConnection(req.getServletContext())) {
                NorthwindDAO dao = new NorthwindDAO(connection);
                Task2Service service = new Task2Service(dao);

                List<Task2> list = service.getAllResult();

                req.setAttribute("list", list);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
        req.getRequestDispatcher("task2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       try (Connection connection = getConnection(req.getServletContext())) {
           NorthwindDAO dao = new NorthwindDAO(connection);
           Task2Service service = new Task2Service(dao);

           int numberOfProducts;

           String numberString = req.getParameter("value");
           if (numberString.equals("")) {
               numberOfProducts = 0;
           } else {
               numberOfProducts = Integer.parseInt(numberString);
           }

           List<Task2> list = service.filter(numberOfProducts);
           req.setAttribute("list", list);

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
