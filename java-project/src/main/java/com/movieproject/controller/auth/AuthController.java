package com.movieproject.controller.auth;

import com.movieproject.model.User;
import com.movieproject.service.AuthService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/auth/register",
        "/auth/login",
        "/auth/forgot-password",
        "/auth/enter-code"
})

public class AuthController extends HttpServlet {

    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        switch (action) {
            case "/auth/register":
                forwardToPage(request, response, "/views/auth/signup.jsp");
                break;
            case "/auth/login":
                forwardToPage(request, response, "/views/auth/signIn.jsp");
                break;
            case "/auth/forgot-password":
                forwardToPage(request, response, "/views/auth/forget-password.jsp");
                break;
            case "/auth/verify-email":
                forwardToPage(request, response, "/views/auth/email_sent.jsp");
                break;
            case "/auth/enter-code":
                forwardToPage(request, response, "/views/auth/enter_code.jsp");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();

        switch (action) {
            case "/auth/register":
                registerUser(request, response);
                break;
            case "/auth/login":
                loginUser(request, response);
                break;
            case "/auth/forgot-password":
                forgotPassword(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String mobileStr = request.getParameter("mobile");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (fullName == null || email == null || mobileStr == null || password == null || confirmPassword == null) {
            request.setAttribute("error", "All fields are required.");
            forwardToPage(request, response, "/views/auth/signup.jsp");
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match!");
            forwardToPage(request, response, "/views/auth/signup.jsp");
            return;
        }

        int mobile = Integer.parseInt(mobileStr);

        try {
            boolean isRegistered = authService.registerUser(fullName, email, mobile, password);
            if (isRegistered) {
                response.sendRedirect(request.getContextPath() + "/auth/verify-email?email=" + email);
            } else {
                request.setAttribute("error", "Registration failed. Please try again!");
                forwardToPage(request, response, "/views/auth/signup.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An internal error occurred during registration.");
            forwardToPage(request, response, "/views/auth/signup.jsp");
        }
    }

    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Validate input
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Both email and password are required.");
            forwardToPage(request, response, "/views/auth/signIn.jsp");
            return;
        }

        try {
            User user = authService.loginUser(email, password);

            if (user == null) {
                request.setAttribute("error", "Invalid email or password.");
                forwardToPage(request, response, "/views/auth/signIn.jsp");
                return;
            }

            if (!user.getIsActive()) {
                request.setAttribute("error", "Your account is inactive. Please contact support.");
                forwardToPage(request, response, "/views/auth/signIn.jsp");
                return;
            }

            if (!user.isEmailVerified()) {
                request.setAttribute("error", "Please verify your email before logging in.");
                forwardToPage(request, response, "/views/auth/signIn.jsp");
                return;
            }



// Create session and store user info
            HttpSession session = request.getSession(true); // Create a new session or use existing
            session.setAttribute("userId", user.getId());  // Store user ID
            session.setAttribute("username", user.getUsername());  // Store username
            session.setAttribute("userRole", user.getRole());  // Store user role for role-based access


            // Set session timeout to 1 hour (3600 seconds)
            session.setMaxInactiveInterval(3600);

            // Redirect based on user role
            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/home");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An internal error occurred. Please try again later.");
            forwardToPage(request, response, "/views/auth/signIn.jsp");
        }
    }


    private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");

        try {
            boolean emailSent = authService.sendForgotPasswordCode(email);
            if (emailSent) {
                response.sendRedirect(request.getContextPath() + "/auth/verify-email?email=" + email);
            } else {
                response.sendRedirect(request.getContextPath() + "/auth/forgot-password?error=user_not_found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to send password reset email");
        }
    }



}
