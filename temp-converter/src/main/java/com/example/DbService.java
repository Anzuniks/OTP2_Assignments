package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbService {
    private static final Logger LOGGER = Logger.getLogger(DbService.class.getName());
    private final String url = setting("DB_URL", "jdbc:mariadb://localhost:3306/converterdb");
    private final String user = setting("DB_USER", "root");
    private final String pass = setting("DB_PASS", "root");

    private static String setting(String name, String defaultValue) {
        String value = System.getProperty(name);
        if (value == null || value.isBlank()) {
            value = System.getenv(name);
        }
        return value == null || value.isBlank() ? defaultValue : value;
    }

    public boolean saveConversion(double valueFrom, String fromUnit, String toUnit,
                                  double result, String lang) {
        String sql = "INSERT INTO conversion_history " +
                "(value_from, from_unit, to_unit, result, lang) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, valueFrom);
            pstmt.setString(2, fromUnit);
            pstmt.setString(3, toUnit);
            pstmt.setDouble(4, result);
            pstmt.setString(5, lang);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Could not save conversion to MariaDB", e);
            return false;
        }
    }
}
