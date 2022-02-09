package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;

import static java.util.ResourceBundle.getBundle;

import org.example.dao.Storage.MainStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");
       try {
           MainStorage mainStorage = new MainStorage("jdbc:derby:BookSystem;create=true");
          //mainStorage.addBookToOrder(1,10);
           mainStorage.createOrder(2,2,LocalDate.parse("2022-02-13"),LocalDate.parse("2022-02-15"));
       } catch (Exception e) {
           e.printStackTrace();
       }

        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}