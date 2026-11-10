package com.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.geometry.NodeOrientation;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {
    @FXML private ComboBox<String> languageSelector;
    @FXML private TextField tempField, speedField, distanceField, timeField;
    @FXML private Label resultLabel;
    
    private ResourceBundle bundle;
    private Locale locale = Locale.ENGLISH;
    private final DbService dbService = new DbService();

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll("English", "Finnish", "Swedish", "Japanese", "Arabic");
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        this.bundle = ResourceBundle.getBundle("messages", locale);
    }

    @FXML
    public void changeLanguage() throws Exception {
        String selection = languageSelector.getValue();
        if (selection == null) return;
        
        String langCode = switch (selection) {
            case "Finnish" -> "fi";
            case "Swedish" -> "sv";
            case "Japanese" -> "ja";
            case "Arabic" -> "ar";
            default -> "en";
        };

        Locale locale = new Locale(langCode);
        bundle = ResourceBundle.getBundle("messages", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.fxml"), bundle);
        Scene scene = new Scene(loader.load(), 400, 350);
        loader.<Controller>getController().setLocale(locale);
        
        scene.setNodeOrientation(langCode.equals("ar") ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        
        Stage stage = (Stage) languageSelector.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void handleConvertAndSave() {
        try {
            double tempC = Double.parseDouble(tempField.getText());
            double tempF = TempConverter.celsiusToFahrenheit(tempC);
            resultLabel.setText(bundle.getString("result.label") + " " + tempF + " F");
            dbService.saveConversion(tempC, "Celsius", "Fahrenheit", tempF, locale.getLanguage());
        } catch (NumberFormatException e) {
            resultLabel.setText(bundle.getString("invalid.input"));
        }
    }
}
