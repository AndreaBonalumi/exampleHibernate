package com.example.examplehibernate;

import entity.User;
import hibernate.repository.UserDao;
import hibernate.repository.impl.UserDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();
    private String pageRecipient;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User temp = new User();
        temp.setFirstName("Manuel");
        temp.setLastName("Tocchi");
        temp.setUsername("manuel.tocchi");
        temp.setPassword("password");
        temp.setEmail("manuel.tocchi@si2001.it");
        temp.setnPatente("MI7834593");
        temp.setCreated(LocalDate.now());
        temp.setBirthday(LocalDate.now());
        temp.setAdmin(true);

        userDao.Insert(temp);


        String action = request.getParameter("action");
        switch (action) {
            case "new": break;
            case "edit": break;
            case "delete": break;
            default: login(request);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(pageRecipient);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    protected void login(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        User user;

        if (httpSession.getAttribute("user") == null) {
            user =  userDao.getById(1);
            httpSession.setAttribute("user", user);
        } else {
            user = (User) httpSession.getAttribute("user");
        }
        if (user.isAdmin()) {
            List<User> users = userDao.getAll();
            request.setAttribute("userList", users);
            pageRecipient = "homePage.jsp";
        } else {
            pageRecipient = "BookingServlet";
        }
    }
}
