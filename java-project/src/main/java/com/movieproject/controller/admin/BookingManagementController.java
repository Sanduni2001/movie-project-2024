package com.movieproject.controller.admin;


import com.movieproject.model.Booking;
import com.movieproject.model.Movie;
import com.movieproject.model.User;
import com.movieproject.service.BookingService;

import com.movieproject.service.MovieManagementService;
import com.movieproject.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/bookings")
public class BookingManagementController extends HttpServlet {
    private BookingService bookingService=new BookingService();
    private UserService userService=new UserService();
    private MovieManagementService movieManagementService = new MovieManagementService();

    @Override
    public void init() {
        bookingService = new BookingService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Booking> bookings = bookingService.getAllBookings();

        if (bookings.isEmpty()) {
            request.setAttribute("bookings", bookings); // Empty list
            request.setAttribute("message", "No bookings available.");
        } else {
            Booking firstBooking = bookings.get(0); // Safe access now
            User user = userService.getUserById(firstBooking.getUserId());
            Movie movie = movieManagementService.getMovieById(firstBooking.getMovieId());

            request.setAttribute("bookings", bookings);
            request.setAttribute("user", user);
            request.setAttribute("movie", movie);
        }

        // Forward to the JSP
        request.getRequestDispatcher("/views/admin/manage-bookings.jsp").forward(request, response);
    }

}
