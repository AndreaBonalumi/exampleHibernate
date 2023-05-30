package com.example.examplehibernate;

import entity.Car;
import hibernate.repository.impl.CarDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "autoServlet", value = "/AutoServlet")
public class AutoServlet extends HttpServlet {

    private final CarDaoImpl carDao = new CarDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Car> cars = carDao.getAll();

        request.setAttribute("cars", cars);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/view-cars.jsp");

        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
