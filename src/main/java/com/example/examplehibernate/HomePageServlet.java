package com.example.examplehibernate;

import entity.Booking;
import entity.User;
import hibernate.repository.impl.BookingDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomePageServlet", value = "/HomePageServlet")
public class HomePageServlet extends HttpServlet {
    private final BookingDaoImpl bookingDao = new BookingDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        List<Booking> bookings = bookingDao.getByIdUser(user.getId());
        httpSession.setAttribute("bookings", bookings);

        RequestDispatcher dispatcher = request.getRequestDispatcher("homePage.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
