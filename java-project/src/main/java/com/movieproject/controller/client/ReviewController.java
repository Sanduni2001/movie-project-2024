package com.movieproject.controller.client;

import com.movieproject.service.ReviewService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/add-review")
public class ReviewController extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init() throws ServletException {
        reviewService = new ReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        int userId = (Integer) session.getAttribute("userId");
        String description = request.getParameter("description");
        String starsParam = request.getParameter("stars");

        try {
            int stars = Integer.parseInt(starsParam);

            // Add the review
            boolean success = reviewService.addReview(userId, description, stars);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/views/client/review-success.jsp");
            } else {
                request.setAttribute("error", "Failed to add review.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid star rating.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("error", "An unexpected error occurred.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}
