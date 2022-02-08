package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.Period;

import static java.util.ResourceBundle.getBundle;

import org.example.dao.Storage.MainStorage;
import org.example.dao.jdbcmodel.JDBCBookSystem;
import org.example.dao.jdbcmodel.JDBCLoginSystem;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.users.Admin;
import org.example.model.users.Personnel;
import org.example.model.users.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");
       try {
           MainStorage mainStorage = new MainStorage("jdbc:derby:BookSystem;create=true");
          //  mainStorage.createOrder(2,1,LocalDate.parse("2022-02-09"),LocalDate.parse("2022-02-12"));
          //  mainStorage.addBookToOrder(1,2);
         //   mainStorage.addBookToOrder(1,3);
        //   mainStorage.addBookToOrder(1,5);
          // mainStorage.addBookToOrder(2,2);
       //    System.out.println(mainStorage.endOrderandGetSum(3));
        mainStorage.createOrder(2,3,LocalDate.parse("2022-02-10"),LocalDate.parse("2022-02-11"));
          // mainStorage.addBookToOrder(1,3);
       } catch (Exception e) {
           e.printStackTrace();
       }

        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}