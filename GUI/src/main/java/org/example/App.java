package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static java.util.ResourceBundle.getBundle;

import org.example.dao.jdbcmodel.JDBCBookSystem;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");

       Author author = new Author(3,"xyz", "zyx", LocalDate.parse("2020-01-08"));
        Client client = new Client(2,"Kate", "yzx"
                ,"795648631","yxz@gmail.com","city 954 nr. 54");
        client.setOrderCount(5);
       try (var bookSystem = new JDBCBookSystem("jdbc:derby:BookSystem;create=true")){
          // bookSystem.createDataBase();
           bookSystem.addClient(client);
           System.out.println(bookSystem.getListofClients().get(1).getOrderCount());
       }

        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}