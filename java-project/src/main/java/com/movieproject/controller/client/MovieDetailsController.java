package com.movieproject.controller.client;

import com.movieproject.model.Movie;
import com.movieproject.model.ShowDate;
import com.movieproject.service.MovieManagementService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/movie-details")
public class MovieDetailsController extends HttpServlet {
    private final MovieManagementService movieDetailsService = new MovieManagementService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            Movie movie = movieDetailsService.getMovieById(movieId);
            List<ShowDate> showDates = movieDetailsService.getShowDatesByMovieId(movieId);

            request.setAttribute("movie", movie);
            request.setAttribute("showDates", showDates);
            request.getRequestDispatcher("/views/client/movie-details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid movie ID");
        }
    }
}
