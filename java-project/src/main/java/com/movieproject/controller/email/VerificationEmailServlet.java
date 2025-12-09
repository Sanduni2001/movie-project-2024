package com.movieproject.controller.email;
import com.movieproject.utils.EmailUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
@WebServlet(name = "VerificationEmailServlet", value = "/send-verification-email")
public class VerificationEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toAddress = request.getParameter("toAddress");

        // Generate a 6-digit verification code
        String verificationCode = generateVerificationCode();

        // Create the styled email content
        String subject = "Email Verification Code";
        String message = getStyledEmailContent(verificationCode);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Send the email
            EmailUtility.sendEmail(toAddress, subject, message);

            // Store the verification code in the session
            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode);

            out.println("<html><body>");
            out.println("<h3>Email sent successfully to " + toAddress + "!</h3>");
            out.println("</body></html>");
        } catch (Exception ex) {
            out.println("<html><body>");
            out.println("<h3>Failed to send email: " + ex.getMessage() + "</h3>");
            out.println("</body></html>");
        }
    }

    // Utility to generate a 6-digit verification code
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generate a random 6-digit number
        return String.valueOf(code);
    }

    // Utility to get styled email content
    private String getStyledEmailContent(String verificationCode) {
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "    <style>"
                + "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }"
                + "        .email-container { max-width: 600px; margin: 20px auto; background: white; border: 1px solid #ddd; border-radius: 5px; }"
                + "        .email-header { background-color: #007bff; color: white; padding: 20px; text-align: center; font-size: 24px; font-weight: bold; }"
                + "        .email-body { padding: 20px; }"
                + "        .email-body p { font-size: 16px; color: #333; line-height: 1.5; }"
                + "        .email-body .verification-code { font-size: 24px; font-weight: bold; color: #007bff; margin: 20px 0; text-align: center; }"
                + "        .email-body a { display: inline-block; margin-top: 20px; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; font-size: 16px; }"
                + "        .email-body a:hover { background-color: #0056b3; }"
                + "        .email-footer { text-align: center; padding: 10px; font-size: 12px; color: #777; background: #f9f9f9; border-top: 1px solid #ddd; }"
                + "    </style>"
                + "</head>"
                + "<body>"
                + "    <div class='email-container'>"
                + "        <div class='email-header'>Email Verification</div>"
                + "        <div class='email-body'>"
                + "            <p>Hello,</p>"
                + "            <p>We received a request to verify your email address. Please use the verification code below to complete the process:</p>"
                + "            <div class='verification-code'>" + verificationCode + "</div>"
                + "            <a href='http://localhost:8080/demo/verify-code.html'>Verify Email</a>"
                + "            <p>If you did not request this, please ignore this email.</p>"
                + "        </div>"
                + "        <div class='email-footer'>"
                + "            &copy; 2024 Your Company. All rights reserved."
                + "        </div>"
                + "    </div>"
                + "</body>"
                + "</html>";
    }
}
