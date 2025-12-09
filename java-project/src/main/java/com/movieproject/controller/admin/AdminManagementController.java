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

@WebServlet(urlPatterns = {"/admin/admin-management"})
public class AdminManagementController extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // Default to showing all admins if no action is specified
        switch (action == null ? "showAdmins" : action) {
            case "showAdmins":
                showAdmins(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteAdmin(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch (action) {
            case "add":
                addAdmin(request, response);
                break;
            case "update":
                updateAdmin(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    /**
     * Shows the list of all admins.
     */
    private void showAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> admins = userService.getUsersByRole("ADMIN");
        request.setAttribute("admins", admins);
        forwardToPage(request, response, "/views/admin/admin-management.jsp");
    }

    /**
     * Displays the form for adding a new admin.
     */
    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("action", "add");
        forwardToPage(request, response, "/views/admin/admin-management-form.jsp");
    }

    /**
     * Displays the form for editing an existing admin.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int adminId = Integer.parseInt(request.getParameter("id"));
            User admin = userService.getUserById(adminId);

            if (admin != null) {
                request.setAttribute("admin", admin);
                request.setAttribute("action", "update");
                forwardToPage(request, response, "/views/admin/admin-management-form.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Admin not found");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid admin ID");
        }
    }

    /**
     * Handles adding a new admin.
     */
    private void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int mobile = Integer.parseInt(request.getParameter("mobile"));

            User admin = new User(0, username, email, password, mobile, "ADMIN", true, true);

            if (userService.addAdmin(admin)) {
                response.sendRedirect(request.getContextPath() + "/admin/admin-management?action=showAdmins");
            } else {
                request.setAttribute("errorMessage", "Failed to add admin. Please try again.");
                request.setAttribute("action", "add");
                forwardToPage(request, response, "/views/admin/admin-management-form.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input. Please check your data.");
            request.setAttribute("action", "add");
            forwardToPage(request, response, "/views/admin/admin-management-form.jsp");
        }
    }

    /**
     * Handles updating an existing admin.
     */
    private void updateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int mobile = Integer.parseInt(request.getParameter("mobile"));

            User admin = new User(id, username, email, password, mobile, "ADMIN", true, true);

            if (userService.updateUser(admin)) {
                response.sendRedirect(request.getContextPath() + "/admin/admin-management?action=showAdmins");
            } else {
                request.setAttribute("errorMessage", "Failed to update admin. Please try again.");
                request.setAttribute("admin", admin);
                request.setAttribute("action", "update");
                forwardToPage(request, response, "/views/admin/admin-management-form.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input. Please check your data.");
            request.setAttribute("action", "update");
            forwardToPage(request, response, "/views/admin/admin-management-form.jsp");
        }
    }

    /**
     * Handles deleting an admin.
     */
    private void deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int adminId = Integer.parseInt(request.getParameter("id"));
            if (userService.deleteUser(adminId)) {
                response.sendRedirect(request.getContextPath() + "/admin/admin-management?action=showAdmins");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete admin");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid admin ID");
        }
    }

    /**
     * Utility method to forward requests to JSP pages.
     */
    private void forwardToPage(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
