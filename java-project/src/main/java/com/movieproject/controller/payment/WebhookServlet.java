package com.movieproject.controller.payment;

import com.stripe.exception.StripeException;
import com.stripe.net.Webhook;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "WebhookServlet", urlPatterns = {"/stripe-webhook"})
public class WebhookServlet extends HttpServlet {
    private static final String endpointSecret = "whsec_63de28dd4987baf96d564a344005a08811d01e16f0fb2314c80437505dcabfea"; // Replace with your endpoint's signing secret

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String payload = request.getReader().lines().reduce("", (acc, line) -> acc + line);
        String sigHeader = request.getHeader("Stripe-Signature");

        try {
            // Verify the event by constructing it with the signature header and payload
            Event event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );

            // Handle the specific event type: checkout.session.completed
            if ("checkout.session.completed".equals(event.getType())) {
                Session session = (Session) event.getDataObjectDeserializer().getObject().get();
                // Handle successful checkout
                System.out.println("Payment for session " + session.getId() + " was successful!");
            }
            // Set the HTTP status to 200 OK if the event is processed successfully
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (StripeException e) {
            // Set the HTTP status to 400 Bad Request if there is an error processing the event
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            throw new ServletException("Stripe webhook error: " + e.getMessage(), e);
        }
    }
}

