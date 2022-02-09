package org.example;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;

import static java.util.ResourceBundle.getBundle;

import org.example.AppConfiguration.Config;
import org.example.dao.ClassFactory;
import org.example.dao.Storage.MainStorage;
import org.example.initProgram.InitializeApp;
import org.example.model.users.Admin;
import org.example.model.users.Personnel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");
        try(var system = ClassFactory
                .getFileSaverSystem("@../../Config/configuration")) {
            system.read();

        } catch (Exception e) {
            InitializeApp app = new InitializeApp();
            app.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}