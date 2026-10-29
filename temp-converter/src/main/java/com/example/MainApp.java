package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Locale locale = new Locale("en");
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.fxml"), bundle);
        Scene scene = new Scene(loader.load(), 400, 350);
        stage.setScene(scene);
        stage.setTitle("Temperature & Journey Tracker");
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}
