package com.example.examplehibernate;

import entity.User;
import hibernate.repository.UserDao;
import hibernate.repository.impl.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();
    private String pageRecipient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "edit": goEdit(request); break;
            case "delete": deleteUser(request); break;
            case "user": viewUser(request); break;
            default: home(request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "new": newUser(request); break;
            case "edit": doEdit(request); break;
            case "filter": filterUser(request); break;
            default: login(request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);
        dispatcher.forward(request, response);
    }



    protected void login(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        User user;

        user =  userDao.getByUsPw(request.getParameter("username"), request.getParameter("password"));

        if (user == null) {
            httpSession.setAttribute("error", "user name o password errate");

            pageRecipient = "index.jsp";
        } else {
            if(httpSession.getAttribute("error") != null)
                httpSession.removeAttribute("error");

            httpSession.setAttribute("user", user);
            home(request);
        }

    }
    protected void home(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();

        User user = (User) httpSession.getAttribute("user");

        if (user.isAdmin()) {
            List<User> users = userDao.getAll();
            request.setAttribute("userList", users);
            pageRecipient = "homePage.jsp";
        } else {
            pageRecipient = "BookingServlet";
        }
    }

    protected void newUser(HttpServletRequest request) {
        User newUser = new User();

        newUser.setAdmin(false);
        newUser.setCreated(LocalDate.now());
        newUser.setFirstName(request.getParameter("firstName"));
        newUser.setLastName(request.getParameter("lastName"));
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setnPatente(request.getParameter("nPatente"));
        newUser.setBirthday(LocalDate.parse(request.getParameter("bd")));

        userDao.Insert(newUser);

        home(request);

    }

    protected void deleteUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User tempUser = userDao.getById(id);
        userDao.delete(tempUser);

        home(request);
    }
    protected void goEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.getById(id);
        request.setAttribute("userProfile", user);

        pageRecipient = "editUser.jsp";
    }

    protected void doEdit(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.getById(id);
        userDao.edit(user);

        home(request);
    }

    protected void viewUser(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDao.getById(id);
        request.setAttribute("userBooking", user);

        pageRecipient = "BookingServlet";
    }

    protected void filterUser(HttpServletRequest request) {
        String field = request.getParameter("fieldSearch");
        String value = request.getParameter("filter");

        List<User> users = userDao.searchUsers(field, value);

        request.setAttribute("userList", users);

        pageRecipient = "homePage.jsp";
    }
}
