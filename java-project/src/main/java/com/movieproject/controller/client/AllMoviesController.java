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

@WebServlet("/movies")
public class AllMoviesController extends HttpServlet {
    private final MovieManagementService movieService = new MovieManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch the list of movies
            List<Movie> moviesList = movieService.getAllMovies();

            // Set the list as a request attribute
            request.setAttribute("movies", moviesList);

            // Forward the request to the JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/client/all-movies.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to fetch movies at this time.");
        }
    }
}
