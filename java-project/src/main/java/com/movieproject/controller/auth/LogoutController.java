package com.movieproject.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")  // Map this servlet to /auth/logout
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logoutUser(request, response);
    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // Get the session if it exists, no session is created if it doesn't exist
        if (session != null) {
            System.out.println("Invalidating session for user: " + session.getAttribute("username"));
            session.invalidate(); // Invalidate the session to clear all attributes
        } else {
            System.out.println("No session to invalidate.");
        }
        response.sendRedirect(request.getContextPath() + "/home"); // Redirect to the home page
    }
}
