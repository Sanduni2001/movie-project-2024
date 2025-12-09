package com.movieproject.controller.client;

import com.movieproject.model.*;
import com.movieproject.service.*;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private final MovieManagementService movieService = new MovieManagementService();
    private final BookingService bookingService       = new BookingService();
    private final UserService userService             = new UserService();
    private final EmailService emailService           = new EmailService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // User must be logged in
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        try {
            switch (action) {
                case "confirm":
                    // Stripe success URL
                    handleConfirmation(request, response);
                    break;
                case "cancel":
                    // Stripe cancel URL
                    handleCancellation(request, response);
                    break;
                case "my-booking":
                    forwardToList(request, response, userId);
                    break;
                case "delete":
                    deleteBooking(request, response);
                    break;
                case "booking":
                    handleDetails(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (Exception e) {
            log("Error handling GET request", e);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("create".equals(action)) {
            createBooking(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    /**
     * 1) Create booking record with 'pending' status
     * 2) Create a Stripe checkout session
     * 3) Redirect user to Stripe's payment page
     */
    private void createBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }

        try {
            int userId        = (Integer) session.getAttribute("userId");
            int movieId       = Integer.parseInt(request.getParameter("movieId"));
            String showDate   = request.getParameter("showDate");
            String showTime   = request.getParameter("showTime");
            int numberOfSeats = Integer.parseInt(request.getParameter("numberOfSeats"));
            double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));

            // Create booking in 'pending' state
            Booking booking = new Booking();
            booking.setUserId(userId);
            booking.setMovieId(movieId);
            booking.setShowDate(showDate);
            booking.setShowTime(showTime);
            booking.setNumberOfSeats(numberOfSeats);
            booking.setTotalPrice(totalPrice);
            booking.setStatus("pending");

            User user = userService.getUserById(userId);
            Movie movie = movieService.getMovieById(movieId);

            // Attempt to create Stripe session
            boolean success = createStripeSession(request, response, booking, user, numberOfSeats, totalPrice/numberOfSeats);
            if (success) {
                // If session creation is successful, store booking in DB
                if (bookingService.createBooking(booking)) {
                    System.out.println("Booking created");

                    // We can define a link for "View Booking" or something similar
                    String bookingLink = "http://localhost:8080/movie-booking/booking?action=my-booking";


                    emailService.sendBookingConfirmationEmail(
                            user.getEmail(),
                            movie.getMovieName(),
                            showDate,
                            showTime,
                            numberOfSeats,
                            totalPrice,
                            bookingLink
                    );

                } else {
                    request.setAttribute("error", "Failed to create booking record in DB.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }

        } catch (NumberFormatException e) {
            log("Invalid input format", e);
            request.setAttribute("error", "Invalid input. Please check your details.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            log("Error creating booking", e);
            request.setAttribute("error", "An unexpected error occurred. Please try again.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    /**
     * Creates a Stripe checkout session and redirects user to the session URL.
     *
     * @return true if session creation & redirect succeed, false otherwise
     */
    private boolean createStripeSession(HttpServletRequest request, HttpServletResponse response,
                                        Booking booking, User user,
                                        int numberOfSeats, double totalPrice)
            throws IOException, ServletException {

        // Use your actual Stripe secret key
        Stripe.apiKey = "";

        try {
            // Grab the movie name for the line item
            Movie movie = movieService.getMovieById(booking.getMovieId());
            String movieName = (movie != null) ? movie.getMovieName() : "Movie Ticket";

            // The successUrl should come back to this servlet with action=confirm & bookingId
            String successUrl = String.format("%s://%s:%d%s/booking?action=confirm&bookingId=%d&session_id={CHECKOUT_SESSION_ID}",
                    request.getScheme(), request.getServerName(), request.getServerPort(),
                    request.getContextPath(), booking.getBookingId());

            // The cancelUrl if user cancels or payment fails
            String cancelUrl = String.format("%s://%s:%d%s/booking?action=cancel",
                    request.getScheme(), request.getServerName(), request.getServerPort(),
                    request.getContextPath());

            // Build the session params
            SessionCreateParams sessionParams = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(cancelUrl)
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity((long) numberOfSeats)
                                    .setPriceData(
                                            SessionCreateParams.LineItem.PriceData.builder()
                                                    .setCurrency("usd")
                                                    .setUnitAmount((long) (totalPrice * 100)) // in cents
                                                    .setProductData(
                                                            SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                    .setName(movieName)
                                                                    .setDescription("Booking for " + user.getUsername())
                                                                    .build()
                                                    )
                                                    .build()
                                    )
                                    .build()
                    )
                    .build();

            // Create session via Stripe API
            Session stripeSession = Session.create(sessionParams);

            // Optionally store the Stripe session ID in the booking object or DB
            // booking.setStripeSessionId(stripeSession.getId());
            // bookingService.updateBooking(booking);

            // Redirect user to Stripe payment URL
            response.sendRedirect(stripeSession.getUrl());
            System.out.println("stripe ok");
            return true;

        } catch (StripeException e) {
            log("Stripe API exception", e);
            request.setAttribute("error", "Failed to initiate payment process with Stripe. " + e.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return false;
        }
    }

    /**
     * Called once Stripe payment is successful.
     * We mark the booking as 'confirmed' in DB, show success page.
     */
    private void handleConfirmation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            // Show success page
            request.setAttribute("message", "Booking confirmed successfully!");
            request.getRequestDispatcher("/views/client/booking-success.jsp").forward(request, response);
    }

    /**
     * If user cancels or payment fails, Stripe will redirect here.
     */
    private void handleCancellation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("message", "Booking cancellation or payment was unsuccessful.");
        request.getRequestDispatcher("/views/client/booking-failed.jsp").forward(request, response);
    }

    /**
     * Show user’s existing bookings.
     */
    private void forwardToList(HttpServletRequest request, HttpServletResponse response, int userId)
            throws ServletException, IOException {

        List<Booking> bookings = bookingService.getAllBookingsByUserId(userId);
        if (bookings == null || bookings.isEmpty()) {
            request.setAttribute("message", "You have no bookings yet.");
            request.getRequestDispatcher("/views/client/user-bookings.jsp").forward(request, response);
        } else {

            // We'll build a list of maps or a list of custom DTO objects
            // each containing both booking and its movie.
            List<Map<String, Object>> bookingMovieList = new ArrayList<>();

            for (Booking b : bookings) {
                // 1) For each booking, get the movie ID
                int movieId = b.getMovieId();

                // 2) Fetch the movie from DB
                Movie movie = movieService.getMovieById(movieId);

                // 3) Create a small map (or custom object) to hold them together
                Map<String, Object> row = new HashMap<>();
                row.put("booking", b);
                row.put("movie", movie);

                // 4) Add it to our list
                bookingMovieList.add(row);
            }

            // Finally, pass this list to the JSP
            request.setAttribute("bookingMovieList", bookingMovieList);

            // Forward to user-bookings.jsp
            request.getRequestDispatcher("/views/client/user-bookings.jsp").forward(request, response);
        }
    }


    /**
     * Allows user to delete a booking if you have such a feature.
     */
    private void deleteBooking(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/views/auth/login.jsp");
            return;
        }

        try {
            int userId = (Integer) session.getAttribute("userId");
            int bookingId = Integer.parseInt(request.getParameter("bookingId"));

            if (bookingService.deleteBooking(bookingId)) {
                User user = userService.getUserById(userId);
                // Optionally send cancellation email or other logic
                emailService.sendBookingCancellationEmail(user.getEmail(),
                        "Booking Cancellation",
                        "Your booking has been canceled successfully.");

                response.sendRedirect(request.getContextPath() + "/booking?action=my-booking&message=Booking+deleted+successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp?message=Unable+to+delete+booking");
            }
        } catch (NumberFormatException e) {
            log("NumberFormatException: Invalid Booking ID Format", e);
            response.sendRedirect(request.getContextPath() + "/error.jsp?message=Invalid+Booking+ID+Format");
        } catch (Exception e) {
            log("Error deleting booking", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Show details page for a particular movie/time
     */
    private void handleDetails(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int movieId    = Integer.parseInt(request.getParameter("movieId"));
            int showTimeId = Integer.parseInt(request.getParameter("showTimeId"));
            int showDateId = Integer.parseInt(request.getParameter("showDateId"));

            Movie movie    = movieService.getMovieById(movieId);
            ShowTime st    = movieService.getShowTimeById(showTimeId);
            ShowDate date  = movieService.getShowDateById(showDateId);

            if (movie == null || st == null || date == null) {
                request.setAttribute("error", "Movie, Show Time or Show Date not found.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
                return;
            }

            request.setAttribute("movie", movie);
            request.setAttribute("showDate", date);
            request.setAttribute("showTime", st);
            request.getRequestDispatcher("/views/client/booking.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            log("NumberFormatException: Invalid input for ID", e);
            request.setAttribute("error", "Invalid ID format.");
            try {
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
