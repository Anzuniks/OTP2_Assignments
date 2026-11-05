package com.example.model;

public class TemperatureConverter {
    public static double convert(double value, String fromUnit, String toUnit) {
        if (fromUnit.equalsIgnoreCase(toUnit)) return value;
        
        double celsius = switch (fromUnit.toLowerCase()) {
            case "fahrenheit" -> (value - 32) * 5 / 9;
            case "kelvin" -> value - 273.15;
            default -> value; // celsius
        };

        return switch (toUnit.toLowerCase()) {
            case "fahrenheit" -> (celsius * 9 / 5) + 32;
            case "kelvin" -> celsius + 273.15;
            default -> celsius; // celsius
        };
    }
}
