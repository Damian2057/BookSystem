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
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Application...");

     var bookSystem = new JDBCBookSystem("jdbc:derby:BookSystem;create=true");
        Client client = new Client(1,"Kate", "yzx"
                ,"795648631","yxz@gmail.com","city 954 nr. 54");
        bookSystem.createDataBase();
        bookSystem.addClient(client);
        System.out.println(bookSystem.getListofClients());
        bookSystem.deleteClient(1);
        System.out.println(bookSystem.getListofClients());


        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}