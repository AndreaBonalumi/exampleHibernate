package com.example.examplehibernate;

import entity.Booking;
import entity.User;
import hibernate.repository.BookingDao;
import hibernate.repository.impl.BookingDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookingServlet", value = "/BookingServlet")
public class BookingServlet extends HttpServlet {

    private final BookingDao bookingDao = new BookingDaoImpl();
    private String pageRecipient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("home")) {
            viewAll(request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void viewAll(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user = (User) httpSession.getAttribute("user");

        List<Booking> bookings = bookingDao.getByIdUser(user.getId());
        httpSession.setAttribute("bookings", bookings);

        pageRecipient = "homePage.jsp";
    }
}
