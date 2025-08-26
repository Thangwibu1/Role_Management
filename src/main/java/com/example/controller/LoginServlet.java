package com.example.controller;

import com.example.DAO.RoleDAO;
import com.example.DAO.UserDAO;
import com.example.model.Role;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Đơn giản là chuyển tiếp đến file JSP để hiển thị form
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp"); // Hoặc đường dẫn đúng đến file login.jsp của bạn
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // Get user name and password from request
        UserDAO userDAO = new UserDAO();
        //create object userDAO, Data Access Object
        if (userDAO.checkLogin(username, password) != null) {
            // check if true they has atleast one account
            RoleDAO roleDAO = new RoleDAO();
            // create object roleDAO
            List<Role> roles = roleDAO.getRolesByUserId(userDAO.checkLogin(username, password).getId());
            // get all roles of user
            HttpSession session = req.getSession();
            // create session
            session.setAttribute("roles", roles);
            session.setAttribute("user", userDAO.checkLogin(username, password));
            // set attribute for session
            resp.sendRedirect("dashboard");
        } else {
            resp.sendRedirect("login.jsp");

        }

    }
}
