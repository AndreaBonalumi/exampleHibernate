package com.example.examplehibernate;

import entity.Car;
import hibernate.repository.CarDao;
import hibernate.repository.impl.CarDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CarServlet", value = "/CarServlet")
public class CarServlet extends HttpServlet {

    private final CarDao carDao = new CarDaoImpl();
    private String pageRecipient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "delete": delete(request);
            case "edit": goEdit(request); break;
            case "all": viewAll(request); break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("insert")) {
            newCar(request);
        } else if (action.equals("edit")) {
            doEdit(request);
        }
    }



    protected void viewAll(HttpServletRequest request) {
        List<Car> cars = carDao.getAll();
        request.setAttribute("cars", cars);

        pageRecipient = "view-cars.jsp";
    }
    protected void newCar(HttpServletRequest request) {
        Car car = new Car();

        car.setBrand(request.getParameter("brand"));
        car.setModel(request.getParameter("model"));
        car.setCreated(LocalDate.now());
        car.setColor(request.getParameter("color"));
        car.setDescription(request.getParameter("description"));
        car.setLink(request.getParameter("link"));
        car.setYear(Integer.parseInt(request.getParameter("year")));

        carDao.insert(car);

        viewAll(request);
    }
    protected void goEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carDao.getById(id);

        request.setAttribute("car", car);

        pageRecipient = "editPage.jsp";
    }

    protected void doEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carDao.getById(id);

        car.setColor(request.getParameter("color"));
        car.setDescription(request.getParameter("description"));
        car.setLink(request.getParameter("link"));

        carDao.edit(car);

        viewAll(request);
    }

    protected void delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Car car = carDao.getById(id);

        carDao.delete(car);

        viewAll(request);
    }
}
