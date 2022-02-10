package org.example;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.Locale;
import static java.util.ResourceBundle.getBundle;
import org.example.AppConfiguration.Config;
import org.example.dao.ClassFactory;
import org.example.dao.jdbcmodel.JDBCLoginSystem;
import org.example.initProgram.InitializeApp;
import org.example.model.users.Admin;
import org.example.model.users.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {

    public static Config config;

    public void initLoginBase() throws Exception {
        try(var loginSystem= ClassFactory.getJDBCLoginSystem("jdbc:derby:LoginSystem;create=true", "adminnn", "adminnn")) {
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        initLoginBase();
        Locale.setDefault(new Locale("eng","ENG"));
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");
        File file = new File("@../../Config/configuration");

        try(var system = ClassFactory
                .getFileSaverSystem("@../../Config/configuration")) {
            system.read().LoadOption();
            //to auth and login scene

        } catch (Exception e) {
            //to Init all base ect
            InitializeApp app = new InitializeApp();
            app.show();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}