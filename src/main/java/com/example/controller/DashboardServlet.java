package com.example.controller;

import com.example.DAO.UserDAO;
import com.example.model.Role;
import com.example.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Role> roles = (List<Role>) session.getAttribute("roles");

        //check isAdmin

        boolean isAdmin = roles.stream().anyMatch(r -> r.getName().equalsIgnoreCase("admin"));
        req.setAttribute("isAdmin", isAdmin);

        if (isAdmin) {
            UserDAO userDAO = new UserDAO();
            List<User> allUsers = userDAO.getAllUser();
            req.setAttribute("allUsers", allUsers);
        }

        req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
    }
}
