package com.example.examplehibernate;

import entity.Car;
import hibernate.repository.CarDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "EditServlet", value = "/EditServlet")
public class EditServlet extends HttpServlet {
    private final CarDaoImpl carDao = new CarDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carDao.getById(id);

        car.setColor(request.getParameter("color"));
        car.setDescription(request.getParameter("description"));
        car.setLink(request.getParameter("link"));

        carDao.edit(car);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AutoServlet");
        dispatcher.forward(request, response);
    }
}
