package com.movieproject.controller.auth;

import com.movieproject.service.AuthService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/auth/verify-email-password", "/auth/password-verify-code", "/auth/reset-password"})
public class PasswordResetServlet extends HttpServlet {

    private final AuthService authService;

    public PasswordResetServlet() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/auth/verify-email-password":
                // Redirect to email verification page for password recovery
                forwardToPage(request, response, "/views/auth/email-for-password-recover.jsp");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/auth/verify-email-password":
                sendVerificationCode(request, response);
                break;
            case "/auth/password-verify-code":
                verifyCode(request, response);
                break;
            case "/auth/reset-password":
                resetPassword(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void sendVerificationCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            request.setAttribute("error", "Email address is required.");
            forwardToPage(request, response, "/views/auth/forget-password.jsp");
            return;
        }
        if (authService.sendForgotPasswordCode(email)) {
            request.setAttribute("email", email);
            forwardToPage(request, response, "/views/auth/verify-email-for-password.jsp");
        } else {
            request.setAttribute("error", "Failed to send verification code. Please check the email provided and try again.");
            forwardToPage(request, response, "/views/auth/forget-password.jsp");
        }
    }

    private void verifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("code");

        if (authService.verifyCode(authService.getUserIdByEmail(email), code)) {
            request.setAttribute("email", email); // Pass email forward to reset password page
            request.setAttribute("code", code); // Pass email forward to reset password page

            forwardToPage(request, response, "/views/auth/reset-password.jsp");
        } else {
            request.setAttribute("error", "Invalid or expired code.");
            forwardToPage(request, response, "/views/auth/verify-email-for-password.jsp");
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String code = request.getParameter("code");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match.");
            forwardToPage(request, response, "/views/auth/reset-password.jsp");
            return;
        }
        System.out.println(email);
        System.out.println(newPassword);
        System.out.println(code);


        if (authService.resetPassword(email, code, newPassword)) {
            request.setAttribute("success", "Password reset successfully. You can now log in.");
            forwardToPage(request, response, "/views/auth/signIn.jsp");
        } else {
            request.setAttribute("error", "Failed to reset password. Please try again.");
            forwardToPage(request, response, "/error.jsp");  // Updated path here
        }

    }
}
