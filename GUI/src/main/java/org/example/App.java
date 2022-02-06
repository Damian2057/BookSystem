package org.example;


import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;

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
        Book book2 = new Book(3,"Titanic", new Author(1,"xyz", "zyx"
                , LocalDate.parse("2001-10-15"))
                ,LocalDate.parse("2015-10-16"),200,51.50);
        Client client = new Client(2,"roman", "yzx"
                ,"795648631","yxz@gmail.com","city 954 nr. 54");

       try (var bookSystem = new JDBCBookSystem("jdbc:derby:BookSystem;create=true")){
           //bookSystem.createDataBase();
//           bookSystem.addClient(client);
//           bookSystem.addAuthor(author);
         //  bookSystem.addBook(book);
//           Order order = new Order(5,client, LocalDate.now().plusDays(1),LocalDate.now().plusDays(5));
//           order.addBookToOrder(book);
//           order.addBookToOrder(book2);
//           bookSystem.addOrder(order);
         //  bookSystem.deleteBookInOrder(5,2);
//           bookSystem.addBook(book2);
//              bookSystem.addBookToOrder(5,book2);
              bookSystem.UpdateOrderStatus(5,true);


       }

        System.exit(0);
    }

    public static void main(String[] args) {
        launch();
    }
}