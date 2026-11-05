package com.example;

import com.example.dao.UnitDAO;
import com.example.dao.ConversionHistoryDAO;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    @Test
    void testUnitDAO() {
        UnitDAO dao = new UnitDAO("jdbc:mariadb://localhost:3306/converterdb", "root", "root");
        Map<String, String> units = dao.getLocalizedUnits("fi");
        assertNotNull(units);
        
        // English fallback
        Map<String, String> fallbackUnits = dao.getLocalizedUnits("unknown_lang");
        assertNotNull(fallbackUnits);
    }

    @Test
    void testConversionHistoryDAO() {
        ConversionHistoryDAO historyDAO = new ConversionHistoryDAO("jdbc:mariadb://localhost:3306/converterdb", "root", "root");
        historyDAO.saveConversion(20.0, "celsius", "fahrenheit", 68.0, "fi");
        List<String> lastConversions = historyDAO.getLastConversions(5);
        assertNotNull(lastConversions);
    }
}
