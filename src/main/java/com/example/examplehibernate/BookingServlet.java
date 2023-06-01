package com.example.examplehibernate;

import entity.Booking;
import entity.Car;
import entity.User;
import hibernate.repository.BookingDao;
import hibernate.repository.CarDao;
import hibernate.repository.impl.BookingDaoImpl;
import hibernate.repository.impl.CarDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {

    private final BookingDao bookingDao = new BookingDaoImpl();
    private String pageRecipient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "home": viewByUser(request); break;
            case "delete": deleteBooking(request); break;
            case "edit": editBooking(request); break;
            case "user": viewByUserAdmin(request); break;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if(action.equals("new")) {
            lookAvailable(request);
        } else if (action.equals("book")) {
            bookCar(request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);
        dispatcher.forward(request, response);
    }

    protected void viewByUser(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        List<Booking> bookings = bookingDao.getAllByUserId(user.getId());
        httpSession.setAttribute("bookings", bookings);

        pageRecipient = "homePage.jsp";
    }

    protected void lookAvailable(HttpServletRequest request) {
        CarDao carDao = new CarDaoImpl();
        List<Booking> bookings = bookingDao.getAll();
        List<Car> carsDate = new ArrayList<>();
        List<Car> cars = carDao.getAll();

        for (Booking booking : bookings) {
            if (booking.getDateBookingEnd().isBefore(LocalDate.parse(request.getParameter("start")))) {
                carsDate.add(booking.getCar());
            }
        }
        for (Car car : cars) {
            if(!carsDate.contains(car)) {
                carsDate.add(car);
            }
        }
        request.setAttribute("carsDate", carsDate);

        pageRecipient = "newBooking.jsp";
    }

    protected void bookCar(HttpServletRequest request) {
        Booking booking = new Booking();
        CarDao carDao = new CarDaoImpl();
        HttpSession httpSession = request.getSession();

        booking.setDateBookingStart(LocalDate.now());
        booking.setDateBookingEnd(LocalDate.now());
        booking.setCar(carDao.getById(Integer.parseInt(request.getParameter("carSelected"))));
        booking.setUser((User) httpSession.getAttribute("user"));

        bookingDao.insert(booking);

        pageRecipient = "newBooking.jsp";


    }

    protected void deleteBooking(HttpServletRequest request) {
        Booking booking = bookingDao.getById(Integer.parseInt(request.getParameter("id")));
        bookingDao.delete(booking);

        pageRecipient = "newBooking.jsp";
    }
    protected void editBooking(HttpServletRequest request) {}

    protected void viewByUserAdmin(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Booking> bookings = bookingDao.getAllByUserId(id);
        request.setAttribute("bookings", bookings);

        pageRecipient = "userBooking.jsp";
    }
}
