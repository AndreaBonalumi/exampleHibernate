package com.example.examplehibernate;

import entity.Car;
import hibernate.repository.CarDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "InsertCarServlet", value = "/InsertCarServlet")
public class InsertCarServlet extends HttpServlet {

    private final CarDaoImpl carDao = new CarDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Date date = new Date();
        Car car = new Car();
        car.setBrand(request.getParameter("brand"));
        car.setModel(request.getParameter("model"));
        car.setCreated(date);
        car.setColor(request.getParameter("color"));
        car.setDescription(request.getParameter("description"));
        car.setLink(request.getParameter("link"));
        car.setYear(Integer.parseInt(request.getParameter("year")));

        carDao.insert(car);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoServlet");

        dispatcher.forward(request, response);
    }
}
