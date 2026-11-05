package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConversionHistoryDAO {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    public ConversionHistoryDAO(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public void saveConversion(double valueFrom, String fromUnit, String toUnit, double result, String lang) {
        String sql = "INSERT INTO conversion_history (value_from, from_unit, to_unit, result, lang) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, valueFrom);
            pstmt.setString(2, fromUnit);
            pstmt.setString(3, toUnit);
            pstmt.setDouble(4, result);
            pstmt.setString(5, lang);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLastConversions(int limit) {
        List<String> history = new ArrayList<>();
        String sql = "SELECT value_from, from_unit, to_unit, result FROM conversion_history ORDER BY id DESC LIMIT ?";
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                history.add(String.format("%.2f %s -> %.2f %s", 
                    rs.getDouble("value_from"), rs.getString("from_unit"), 
                    rs.getDouble("result"), rs.getString("to_unit")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
