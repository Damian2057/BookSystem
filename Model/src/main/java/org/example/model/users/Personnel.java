package org.example.model.users;

import org.example.dao.Storage.MainStorage;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public abstract class Personnel {

    private String nickName;
    private String password;
    private final int ID;
    private final int permLevel;
    private MainStorage mainStorage;

    public Personnel(String nickName, String password, int ID, int permLevel, MainStorage mainStorage) {
        this.nickName = nickName;
        this.password = password;
        this.ID = ID;
        this.permLevel = permLevel;
        this.mainStorage = mainStorage;
    }

    public void addClient(String firstName, String lastName, String phoneNumber, String email, String address) {
        mainStorage.addClient(firstName,lastName,phoneNumber,email,address);
    }

    public Client getClient(int ID) throws Exception {
        return mainStorage.getClient(ID);
    }

    public void addAuthor(String firstName, String lastName, Date birthdate, Date deathDate) {
        mainStorage.addAuthor(firstName, lastName, birthdate, deathDate);
    }

    public Author getAuthor(int ID) throws Exception {
        return mainStorage.getAuthor(ID);
    }

    public void addBook(String title, int AuthorID, Date publishDate
            , int pagecount, double price) throws Exception {
        mainStorage.addBook(title, mainStorage.getAuthorStorage().getAuthor(AuthorID),publishDate,pagecount,price);
    }

    public Book getBook(int ID) throws Exception {
        return mainStorage.getBook(ID);
    }

    public void removeBook(int ID) {
        mainStorage.removeBook(ID);
    }

    public void createOrder(int clientID, LocalDate SDate, LocalDate EDate) throws Exception {
        mainStorage.createOrder(mainStorage.getClientStorage().getClient(clientID),SDate,EDate);
    }

    public Order getOrder(int ID) throws Exception {
        return mainStorage.getOrder(ID);
    }

    public void addBookToOrder(int orderID, int bookID) throws Exception {
        mainStorage.addBookToOrder(orderID, bookID);
    }

    public void removeBookFromOrder(int orderID, int bookID) throws Exception {
        mainStorage.removeBookFromOrder(orderID, bookID);
    }

    public ArrayList<Order> getOrdersByClientID(int clientID) {
        return mainStorage.getOrdersByClientID(clientID);
    }


}
