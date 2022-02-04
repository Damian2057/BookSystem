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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        Author author = new Author(1,"xyz", "zyx", LocalDate.parse("2001-10-15"));
        var bookSystem = new JDBCBookSystem("jdbc:derby:BookSystem;create=true");
        bookSystem.createDataBase();
        bookSystem.addAuthor(author);
      System.out.println(bookSystem.getListofAuthors());
        logger.debug("Starting Application...");
        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}