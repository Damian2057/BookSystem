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
        Author author = new Author(1,"xyz", "zyx", LocalDate.parse("2020-01-08"));
        Book book = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , LocalDate.parse("2001-10-15"))
                ,LocalDate.parse("2015-10-16"),200,51.50);

       try (var bookSystem = new JDBCBookSystem("jdbc:derby:BookSystem;create=true")){
//            bookSystem.createDataBase();
//         // bookSystem.addClient(client);
//           bookSystem.addAuthor(author);
//           bookSystem.addBook(book);
           System.out.println(bookSystem.getListofBooks().get(0).isOrdered());

       }

        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}