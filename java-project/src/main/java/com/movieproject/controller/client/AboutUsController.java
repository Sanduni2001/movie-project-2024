package com.movieproject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/about-us")
public class AboutUsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pass dynamic content to the About Us page if needed
        request.setAttribute("companyName", "ABC Cinema");
        request.setAttribute("description", "ABC Cinema is dedicated to providing you with an exceptional movie experience. We pride ourselves on offering the latest blockbusters, the best facilities, and a welcoming environment for movie enthusiasts.");
        request.setAttribute("contactEmail", "contact@abccinema.com");
        request.setAttribute("contactPhone", "+1-234-567-890");

        // Forward to JSP
        request.getRequestDispatcher("/views/client/aboutus.jsp").forward(request, response);
    }
}
