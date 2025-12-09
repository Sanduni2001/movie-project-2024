package com.movieproject.service;

import com.movieproject.utils.EmailUtility;

public class EmailService {

    // Method to send verification email
    public void sendVerificationEmail(String toAddress, String verificationCode) throws Exception {
        String subject = "Email Verification Code";
        String message = getStyledEmailContent("Email Verification", verificationCode, "Verify Email", "http://localhost:8080/demo/verify-code.html");
        EmailUtility.sendEmail(toAddress, subject, message);
    }

    // Method to send password reset email
    public void sendForgotPasswordEmail(String toAddress, String resetCode) throws Exception {
        String subject = "Password Reset Code";
        String message = getStyledEmailContent("Password Reset", resetCode, "Reset Password", "http://localhost:8080/demo/reset-password.html");
        EmailUtility.sendEmail(toAddress, subject, message);
    }

    public void sendBookingConfirmationEmail(String toAddress,
                                             String movieName,
                                             String showDate,
                                             String showTime,
                                             int numberOfSeats,
                                             double totalPrice,
                                             String bookingLink) throws Exception
    {
        // Subject line
        String subject = "Your Booking Confirmation for " + movieName;

        // Build details in HTML or simple line-break style
        // For example, we can use <br/> tags or separate paragraphs:
        String details = ""
                + "<strong>Movie Name:</strong> " + movieName + "<br/>"
                + "<strong>Show Date:</strong> " + showDate + "<br/>"
                + "<strong>Show Time:</strong> " + showTime + "<br/>"
                + "<strong>Seats:</strong> " + numberOfSeats + "<br/>"
                + "<strong>Total Price:</strong> $" + totalPrice + "<br/>";

        // Construct the final HTML
        String messageHtml = getBookingEmailContent(
                "Your Booking Confirmation",
                details,
                "View Booking",
                bookingLink
        );

        // Use your EmailUtility (or your mail library) to send HTML email
        EmailUtility.sendEmail(toAddress, subject, messageHtml);
    }

    // Method to send booking cancellation email
    public void sendBookingCancellationEmail(String toAddress, String bookingDetails, String s) throws Exception {
        String subject = "Booking Cancellation";
        String message = getCancellationEmailContent("Your Booking Cancellation", bookingDetails, "Contact Support", "http://localhost:8080/demo/contact-support.html");
        EmailUtility.sendEmail(toAddress, subject, message);
    }

    // Helper method to create styled email content
    private String getStyledEmailContent(String title, String code, String actionText, String actionLink) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>" + styleSheet() + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>" + title + "</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>We received a request for " + title.toLowerCase() + ". Please use the code below:</p>"
                + "            <div class='verification-code'>" + code + "</div>"
                + "            <a href='" + actionLink + "'>" + actionText + "</a>"
                + "            <p>If you did not request this, please ignore this email.</p>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Your Company. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }

    // Helper method for booking email content
    private String getBookingEmailContent(String title, String details,
                                          String actionText, String actionLink) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>" + styleSheet() + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>" + title + "</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>Thank you for your booking. Here are your booking details:</p>"
                + "            <p>" + details + "</p>"
                + "            <p>"
                + "                <a href='" + actionLink + "'>" + actionText + "</a>"
                + "            </p>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Your Company. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }

    // Helper method for cancellation email content
    private String getCancellationEmailContent(String title, String details, String actionText, String actionLink) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>" + styleSheet() + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>" + title + "</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>Your booking has been successfully canceled. Here are the details:</p>"
                + "            <p>" + details + "</p>"
                + "            <p>Your money will be automatically refunded within 5-7 business days.</p>"
                + "            <a href='" + actionLink + "'>" + actionText + "</a>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Your Company. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }

    // Method to define common email style
    private String styleSheet() {
        return "    <style>"
                + "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                + "        .email-container { max-width: 600px; margin: 20px auto; background: white; border: 1px solid #ddd; border-radius: 5px; }"
                + "        .email-header { background-color: #007bff; color: white; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; }"
                + "        .email-body { padding: 20px; }"
                + "        .email-body p { font-size: 16px; color: #333; line-height: 1.5; }"
                + "        .email-body .verification-code { font-size: 24px; font-weight: bold; color: #007bff; margin: 20px 0; text-align: center; }"
                + "        .email-body a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; font-size: 16px; }"
                + "        .email-body a:hover { background-color: #0056b3; }"
                + "        .email-footer { text-align: center; padding: 10px; font-size: 12px; color: #777; background: #f9f9f9; border-top: 1px solid #ddd; }"
                + "    </style>";
    }
}
