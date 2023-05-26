package com.example.examplehibernate;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import hibernate.repository.UserDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private UserDaoImpl userDao = new UserDaoImpl();
    public void init() {
        message = "inserito";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        Date date = new Date();

        // Crea un nuovo oggetto User
        User user = new User();
        user.setId(1);
        user.setUsername("user1");
        user.setPassword("password");
        user.setLastName("password");
        user.setFirstName("user1");
        user.setAdmin(true);
        user.setBirthday(date);
        user.setCreated(date);
        user.setEmail("user1@si2001.it");


        userDao.Insert(user);
    }
    public void destroy() {
    }
}