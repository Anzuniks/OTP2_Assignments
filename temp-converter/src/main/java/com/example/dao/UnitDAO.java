package com.example.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UnitDAO {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPass;

    public UnitDAO(String dbUrl, String dbUser, String dbPass) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
    }

    public Map<String, String> getLocalizedUnits(String lang) {
        Map<String, String> units = new HashMap<>();
        String sql = "SELECT u.unit_key, COALESCE(ut_lang.name, ut_en.name) AS name " +
                     "FROM unit u " +
                     "LEFT JOIN unit_translation ut_en ON u.id = ut_en.unit_id AND ut_en.lang = 'en' " +
                     "LEFT JOIN unit_translation ut_lang ON u.id = ut_lang.unit_id AND ut_lang.lang = ?;";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, lang);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                units.put(rs.getString("unit_key"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }
}
