package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempConverterTest {
    @Test
    public void testCelsiusToFahrenheit() {
        assertEquals(32.0, TempConverter.celsiusToFahrenheit(0), 0.001);
        assertEquals(212.0, TempConverter.celsiusToFahrenheit(100), 0.001);
        assertEquals(-40.0, TempConverter.celsiusToFahrenheit(-40), 0.001);
    }
}
