package com.example.examplehibernate;

import entity.Car;
import hibernate.repository.impl.CarDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ManageServlet", value = "/ManageServlet")
public class ManageServlet extends HttpServlet {

    private final CarDaoImpl carDao = new CarDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carDao.getById(id);
        if (action != null) {
            if (action.equals("delete")) {
                carDao.delete(car);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homePage.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("car", car);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editPage.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
