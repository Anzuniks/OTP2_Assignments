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
    private DbService dbService = new DbService();

    @FXML
    public void initialize() {
        languageSelector.getItems().addAll("English", "Finnish", "Swedish", "Japanese", "Arabic");
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
        
        scene.setNodeOrientation(langCode.equals("ar") ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT);
        
        Stage stage = (Stage) languageSelector.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void handleConvertAndSave() {
        try {
            double tempC = Double.parseDouble(tempField.getText());
            double tempF = TempConverter.celsiusToFahrenheit(tempC);
            bundle = ResourceBundle.getBundle("messages", Locale.getDefault());
            resultLabel.setText(bundle.getString("result.label") + " " + tempF + " F");

            double speed = Double.parseDouble(speedField.getText());
            double distance = Double.parseDouble(distanceField.getText());
            double time = Double.parseDouble(timeField.getText());
            
            dbService.saveMeasurement(1, speed, distance, time);
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input");
        }
    }
}
