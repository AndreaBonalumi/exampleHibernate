package com.example.examplehibernate;

import entity.Booking;
import entity.User;
import hibernate.repository.BookingDaoImpl;
import hibernate.repository.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final UserDaoImpl userDao = new UserDaoImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        User user =  userDao.getById(1);

        httpSession.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("HomePageServlet");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
