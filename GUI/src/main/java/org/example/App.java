package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import static java.util.ResourceBundle.getBundle;

import org.example.dao.jdbcmodel.JDBCBookSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = LoggerFactory.getLogger(this.getClass());
//
//        var bookSystem = new JDBCBookSystem("jdbc:derby:BookSystem;create=true");
//        bookSystem.createDataBase();
        logger.debug("Starting Application...");
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}