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
    public static String BookURL = "jdbc:derby:BookSystem;create=true";
    public static String LoginURL = "jdbc:derby:LoginSystem;create=true";
    public static String user = "admin";
    public static String password = "admin123";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void initLoginBase() throws Exception {
        logger.debug("DataBase creation attempt");
        try(var loginSystem= ClassFactory
                .getJDBCLoginSystem(LoginURL
                        , user, password)) {
            logger.info("BookSystem Database exist");
        }
    }

    public void initBookSystemBase() throws Exception {
        logger.debug("DataBase creation attempt");
        try(var BookSystem= ClassFactory.getJDBCBookSystem(BookURL)) {
            logger.info("BookSystem Database exist");

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        initLoginBase();
        initBookSystemBase();
        Locale.setDefault(new Locale("eng","ENG"));
        logger.debug("Starting Application...");
        File file = new File("@../../Config/configuration");

        try(var system = ClassFactory
                .getFileSaverSystem("@../../Config/configuration")) {
            logger.debug("Loading options");
            system.read().LoadOption();
            logger.debug("Done");
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