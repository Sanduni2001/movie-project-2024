package com.movieproject.controller.auth;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class GoogleOAuthController extends HttpServlet {
    private static final String CLIENT_ID = "463424559278-8t5i7ps358estth7m195smafcigdepng.apps.googleusercontent.com";
    private static final String REDIRECT_URI = "http://localhost:8080/movie-booking/callback";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oauthUrl = "https://accounts.google.com/o/oauth2/auth?client_id=" + CLIENT_ID
                + "&redirect_uri=" + REDIRECT_URI
                + "&response_type=code"
                + "&scope=openid%20email%20profile";

        response.sendRedirect(oauthUrl);
    }
}

