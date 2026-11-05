package com.example;

import com.example.dao.UnitDAO;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class UnitDAOTest {

    @Test
    void testGetLocalizedUnitsWithFallback() {
        UnitDAO dao = new UnitDAO("jdbc:mariadb://localhost:3306/converterdb", "root", "root");
        Map<String, String> units = dao.getLocalizedUnits("fi");
        assertNotNull(units);
    }

    @Test
    void testFallbackLanguage() {
        UnitDAO dao = new UnitDAO("jdbc:mariadb://localhost:3306/converterdb", "root", "root");
        Map<String, String> units = dao.getLocalizedUnits("non_existent_lang");
        assertNotNull(units);
    }
}
