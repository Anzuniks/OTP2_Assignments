package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DbService {
    private String url = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/trip_db");
    private String user = System.getenv().getOrDefault("DB_USER", "root");
    private String pass = System.getenv().getOrDefault("DB_PASS", "password");

    public void saveMeasurement(int journeyId, double speed, double distance, double time) {
        String sql = "INSERT INTO measurement (journey_id, speed, distance, time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, journeyId);
            pstmt.setDouble(2, speed);
            pstmt.setDouble(3, distance);
            pstmt.setDouble(4, time);
            pstmt.executeUpdate();
            System.out.println("Saved to DB");
        } catch (Exception e) {
            System.err.println("DB Error: " + e.getMessage());
        }
    }
}
