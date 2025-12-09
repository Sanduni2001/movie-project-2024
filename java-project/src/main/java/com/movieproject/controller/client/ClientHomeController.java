package com.movieproject.controller.client;

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

@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class ClientHomeController extends HttpServlet {

    private final MovieManagementService movieService = new MovieManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch movies data based on status
        List<Movie> nowShowingMovies = movieService.getAllMovies();

        // Set movies lists as request attributes
        request.setAttribute("nowShowingMovies", nowShowingMovies);

        // Forward to the home.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/home.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/home");
    }
}