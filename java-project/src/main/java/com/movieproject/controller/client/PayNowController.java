package com.movieproject.controller.client;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/pay-now")
public class PayNowController extends HttpServlet {

    /**
     * Handles GET requests to show the payments.jsp page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Forward to the payments.jsp file
            request.getRequestDispatcher("/views/client/payment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to load the payment page.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    /**
     * Handles POST requests to process the payment and redirect to success.jsp.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Simulate payment processing (You can add real logic or integration here)
            String cardNumber = request.getParameter("cardNumber");
            String expirationDate = request.getParameter("expirationDate");
            String cvv = request.getParameter("cvv");
            String holderName = request.getParameter("holderName");

            // Print details to console for debugging
            System.out.println("Card Number: " + cardNumber);
            System.out.println("Expiration Date: " + expirationDate);
            System.out.println("CVV: " + cvv);
            System.out.println("Holder Name: " + holderName);

            response.sendRedirect(request.getContextPath() + "/views/client/booking-success.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during payment processing.");
            request.getRequestDispatcher("/views/client/payments.jsp").forward(request, response);
        }
    }
}
