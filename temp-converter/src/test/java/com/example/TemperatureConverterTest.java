package com.example;

import com.example.model.TemperatureConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TemperatureConverterTest {

    @Test
    void testSameUnit() {
        assertEquals(25.0, TemperatureConverter.convert(25.0, "celsius", "celsius"), 0.001);
    }

    @Test
    void testCelsiusToFahrenheit() {
        assertEquals(32.0, TemperatureConverter.convert(0, "celsius", "fahrenheit"), 0.001);
        assertEquals(212.0, TemperatureConverter.convert(100, "celsius", "fahrenheit"), 0.001);
    }

    @Test
    void testCelsiusToKelvin() {
        assertEquals(273.15, TemperatureConverter.convert(0, "celsius", "kelvin"), 0.001);
    }

    @Test
    void testFahrenheitToCelsius() {
        assertEquals(0.0, TemperatureConverter.convert(32, "fahrenheit", "celsius"), 0.001);
    }

    @Test
    void testKelvinToCelsius() {
        assertEquals(0.0, TemperatureConverter.convert(273.15, "kelvin", "celsius"), 0.001);
    }

    @Test
    void testFahrenheitToKelvin() {
        assertEquals(273.15, TemperatureConverter.convert(32, "fahrenheit", "kelvin"), 0.001);
    }
}
