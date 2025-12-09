package com.movieproject.controller.admin;


import com.movieproject.model.Review;
import com.movieproject.model.User;
import com.movieproject.service.ReviewService;
import com.movieproject.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/manage-reviews")
public class AdminReviewController extends HttpServlet {

    private ReviewService reviewService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        reviewService = new ReviewService();
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all reviews
        List<Review> reviews = reviewService.getAllReviews();

        if (reviews == null || reviews.isEmpty()) {
            // Handle the case when no reviews are found
            request.setAttribute("message", "No reviews found.");
            // Consider sending to a different page or updating the same page with the message
            request.getRequestDispatcher("/views/admin/manage-reviews.jsp").forward(request, response);
        } else {
            // Get the user details for the first review if reviews exist
            User user = userService.getUserById(reviews.get(0).getUserId());

            // Set reviews and user as a request attribute
            request.setAttribute("reviews", reviews);
            request.setAttribute("user", user);

            // Forward to admin review management JSP
            request.getRequestDispatcher("/views/admin/manage-reviews.jsp").forward(request, response);
        }
    }
}
