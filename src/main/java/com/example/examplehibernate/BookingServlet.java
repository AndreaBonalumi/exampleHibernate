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
    CarDao carDao = new CarDaoImpl();
    private String pageRecipient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "home": viewByUser(request); break;
            case "delete": deleteBooking(request); break;
            case "edit": editBooking(request); break;
            case "user": viewByUserAdmin(request); break;
            case "approve": approveBooking(request); break;
            case "decline": declineBooking(request); break;
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

        List<Booking> bookings = bookingDao.getAll();
        List<Car> carsDate = new ArrayList<>();
        List<Car> cars = carDao.getAll();

        for (Booking booking : bookings) {
            if ((booking.getDateBookingEnd().isAfter(LocalDate.parse(request.getParameter("start")))
                    || booking.getDateBookingStart().isBefore(LocalDate.parse(request.getParameter("end"))))
                    || booking.getStatus() == 2) {
                if (!carsDate.contains(booking.getCar())){
                    carsDate.add(booking.getCar());
                }
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

        booking.setDateBookingStart(LocalDate.parse(request.getParameter("start")));
        booking.setDateBookingEnd(LocalDate.parse(request.getParameter("end")));
        booking.setCar(carDao.getById(Integer.parseInt(request.getParameter("carSelected"))));
        booking.setUser((User) httpSession.getAttribute("user"));
        booking.setStatus(0);

        bookingDao.insert(booking);

        pageRecipient = "newBooking.jsp";


    }

    protected void deleteBooking(HttpServletRequest request) {
        Booking booking = bookingDao.getById(Integer.parseInt(request.getParameter("id")));
        bookingDao.delete(booking);

        pageRecipient = "newBooking.jsp";
    }
    protected void editBooking(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking booking = bookingDao.getById(id);
        request.setAttribute("start", booking.getDateBookingStart());
        request.setAttribute("end", booking.getDateBookingEnd());
        lookAvailable(request);
    }

    protected void viewByUserAdmin(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Booking> bookings = bookingDao.getAllByUserId(id);
        request.setAttribute("bookings", bookings);

        pageRecipient = "userBooking.jsp";
    }
    protected void viewByUserAdmin(HttpServletRequest request, int id) {
        List<Booking> bookings = bookingDao.getAllByUserId(id);
        request.setAttribute("bookings", bookings);

        pageRecipient = "userBooking.jsp";
    }

    protected void approveBooking(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking booking = bookingDao.getById(id);
        booking.setStatus(1);
        bookingDao.edit(booking);

        request.setAttribute("userBooking", booking.getUser());

        viewByUserAdmin(request, booking.getUser().getId());
    }

    protected void declineBooking(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking booking = bookingDao.getById(id);
        booking.setStatus(2);
        bookingDao.edit(booking);

        viewByUserAdmin(request);
    }
}
