package com.movieproject.controller.admin;

import com.movieproject.model.Movie;
import com.movieproject.service.MovieManagementService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/home", "/admin/dashboard"})
public class AdminHomeController extends HttpServlet {

    private final MovieManagementService movieService = new MovieManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Fetch movie details
        List<Movie> movies = movieService.getAllMovies();
// Debugging
        System.out.println("Fetched Movies: " + movies);
        // Set attributes for the JSP
        request.setAttribute("movies", movies);
        request.setAttribute("movieCount", movies.size());

        // Render Admin Home Page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin/admin-home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle POST requests (future extensions)
        response.sendRedirect(request.getContextPath() + "/admin/home");
    }
}
