package com.movieproject.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PayHereHashGenerator {

    private static final String MERCHANT_SECRET = "MjY4MzQ3OTIxOTQ0MjEwOTk4MzkwMjk1NzYwOTEyMzk5MTU2Nzg="; // **REPLACE with your actual secret**

    public static String generateHash(String merchantId, String orderId, double amount, String currency) {
        try {
            String data = merchantId + orderId + amount + currency + MERCHANT_SECRET;
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest(data.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the error appropriately in your application
        }
    }


}