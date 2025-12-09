package com.movieproject.controller.auth;


import com.movieproject.service.AuthService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/auth/verify-email", "/auth/verify-code"})
public class EmailVerificationServlet extends HttpServlet {

    private final AuthService authService;

    public EmailVerificationServlet() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/auth/verify-email":
                forwardToPage(request, response, "/views/auth/email-verification.jsp");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/auth/verify-code":
                verifyCode(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void verifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract email and code from the URL
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        System.out.println("Email parameter: " + email);
        System.out.println("Code parameter: " + code);

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email is null or empty");
        }

        if (code == null || code.trim().isEmpty()) {
            System.out.println("Code is null or empty");
        }

        try {
            int userId = authService.getUserIdByEmail(email);

            if (userId == -1) {
                request.setAttribute("error", "Invalid email. Please try again.");
                forwardToPage(request, response, "/views/auth/email-verification.jsp");
                return;
            }

            boolean isVerified = authService.verifyCode(userId, code);

            if (isVerified) {
                request.setAttribute("success", "Email verified successfully. You can now log in.");
                forwardToPage(request, response, "/views/auth/signIn.jsp");
            } else {
                request.setAttribute("error", "Invalid or expired verification code. Please try again.");
                forwardToPage(request, response, "/views/auth/email-verification.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An internal error occurred. Please try again later.");
            forwardToPage(request, response, "/views/auth/email-verification.jsp");
        }
    }}
