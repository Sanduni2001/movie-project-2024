package com.movieproject.config;


import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getStripeKey() {
        return dotenv.get("STRIPE_SECRET_KEY");
    }

    public static String getDomain() {
        return dotenv.get("APP_DOMAIN", "http://localhost:8080");
    }
}
