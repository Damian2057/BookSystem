package org.example;


import javafx.application.Application;
import javafx.stage.Stage;
import java.time.LocalDate;
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

        Author author = new Author(1,"xyz", "zyx", LocalDate.parse("2020-01-08"));
        Book book = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , LocalDate.parse("2001-10-15"))
                ,LocalDate.parse("2015-10-16"),200,51.50);
        Book book2 = new Book(3,"Titanic", new Author(1,"xyz", "zyx"
                , LocalDate.parse("2001-10-15"))
                ,LocalDate.parse("2015-10-16"),200,51.50);
        Client client = new Client(2,"roman", "yzx"
                ,"795648631","yxz@gmail.com","city 954 nr. 54");

       try {
           MainStorage mainStorage = new MainStorage("jdbc:derby:BookSystem;create=true");
          // mainStorage.addAuthor("KAte", "cos", LocalDate.parse("2020-01-08"),LocalDate.parse("2080-01-08"));
           // mainStorage.getAuthorStorage().updateAuthor(3,"name","name");
           System.out.println(mainStorage.getAuthorStorage().getCountOfElements());
       } catch (Exception e) {
           e.printStackTrace();
       }

        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}