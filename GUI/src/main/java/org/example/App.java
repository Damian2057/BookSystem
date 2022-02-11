package org.example;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.Locale;
import org.example.AppConfiguration.Config;
import org.example.dao.ClassFactory;
import org.example.initProgram.InitializeApp;
import org.example.loginDialog.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {

    public static Config config;

    public void initLoginBase() throws Exception {
        try(var loginSystem= ClassFactory
                .getJDBCLoginSystem("jdbc:derby:LoginSystem;create=true"
                        , "adminnn", "adminnn")) {
        }
    }

    public void initBookSystemBase() throws Exception {
        try(var BookSystem= ClassFactory.getJDBCBookSystem("jdbc:derby:BookSystem;create=true")) {

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        initLoginBase();
        initBookSystemBase();
        Locale.setDefault(new Locale("eng","ENG"));
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");
        File file = new File("@../../Config/configuration");

        try(var system = ClassFactory
                .getFileSaverSystem("@../../Config/configuration")) {
            logger.debug("Loading options");
            system.read().LoadOption();
            //to auth and login scene
            LoginUser loginUser = new LoginUser();
            loginUser.entryPin();

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