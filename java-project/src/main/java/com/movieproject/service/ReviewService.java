package com.movieproject.service;

import com.movieproject.model.Review;
import com.movieproject.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {

    /**
     * Add a new review.
     *
     * @param userId      The ID of the user submitting the review.
     * @param description The content of the review.
     * @param stars       The star rating of the review (1-5).
     * @return True if the review was successfully added, false otherwise.
     */
    public boolean addReview(int userId, String description, int stars) {
        String query = "INSERT INTO Reviews (userId, description, stars, createdDate) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setString(2, description);
            stmt.setInt(3, stars);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieve all reviews from the database.
     *
     * @return A list of all reviews.
     */
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT id, userId, description, stars, createdDate FROM Reviews ORDER BY createdDate DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Review review = new Review(
                        rs.getInt("userId"),
                        rs.getString("description"),
                        rs.getInt("stars") // Corrected to fetch 'stars' value
                );
                review.setId(rs.getInt("id"));
                review.setCreateDate(rs.getTimestamp("createdDate"));
                reviews.add(review);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reviews;
    }
}
