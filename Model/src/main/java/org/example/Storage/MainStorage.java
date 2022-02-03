package org.example.Storage;

import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Date;

public class MainStorage {

    private AuthorStorage authorStorage;
    private BookStorage bookStorage;
    private ClientStorage clientStorage;
    private OrderStorage orderStorage;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public MainStorage(String URL) {
        authorStorage = new AuthorStorage(URL);
        bookStorage = new BookStorage(URL);
        clientStorage = new ClientStorage(URL);
        orderStorage = new OrderStorage(URL);
    }

    public AuthorStorage getAuthorStorage() {
        return authorStorage;
    }

    public BookStorage getBookStorage() {
        return bookStorage;
    }

    public ClientStorage getClientStorage() {
        return clientStorage;
    }

    public OrderStorage getOrderStorage() {
        return orderStorage;
    }

    public void addClient(String firstName, String lastName, String phoneNumber, String email, String address) {
        clientStorage.addElement(new Client(clientStorage.getTopID()+1,firstName,lastName,phoneNumber,email,address));
    }

    public void addAuthor(String firstName, String lastName, Date birthdate, Date deathDate) {
        if(deathDate == null) {
            authorStorage.addElement(new Author(authorStorage.getTopID()+1,firstName,lastName,birthdate));

        } else {
            authorStorage.addElement(new Author(authorStorage.getTopID()+1,firstName,lastName,birthdate,deathDate));
        }
    }

    public void addBook(String title, Author author, Date publishDate, int pagecount, double price) {
        bookStorage.addElement(new Book(bookStorage.getTopID()+1, title, author, publishDate, pagecount, price));
    }

    public void createOrder(Client client, LocalDate SDate, LocalDate EDate) {
        orderStorage.addElement(new Order(orderStorage.getTopID()+1,client, SDate, EDate));
    }


}
