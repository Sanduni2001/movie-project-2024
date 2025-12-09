package com.movieproject.controller.admin;

import com.movieproject.model.User;
import com.movieproject.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/user-management")
public class UserManagementController extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch users with role "CLIENT"
        List<User> clients = userService.getUsersByRole("CLIENT");

        // Set users as request attribute to be accessed in JSP
        request.setAttribute("clients", clients);

        // Forward the request to user-management.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/user-management.jsp");
        dispatcher.forward(request, response);
    }
}
