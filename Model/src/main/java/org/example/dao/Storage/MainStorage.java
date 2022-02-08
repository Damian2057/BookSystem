package org.example.dao.Storage;

import org.example.dao.ClassFactory;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainStorage {

    private AuthorStorage authorStorage;
    private BookStorage bookStorage;
    private ClientStorage clientStorage;
    private OrderStorage orderStorage;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public MainStorage(String URL) throws Exception {
        try {
            ClassFactory.getJDBCBookSystem(URL).createDataBase();
        } catch (Exception e) {
            logger.info("Data base exist");
        }
        logger.debug("Initializing of Storage");
        authorStorage = new AuthorStorage(URL);
        bookStorage = new BookStorage(URL);
        clientStorage = new ClientStorage(URL);
        orderStorage = new OrderStorage(URL);
        logger.info("Initializing of Storage status: complete");
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

    public void addClient(String firstName, String lastName, String phoneNumber, String email, String address) throws Exception {
        clientStorage.addElement(new Client(clientStorage.getTopID()+1,firstName,lastName,phoneNumber,email,address));
    }

    public void removeClient(int ID) throws Exception {
        clientStorage.removeElement(ID);
    }

    public Client getClient(int ID) throws Exception {
        return clientStorage.getClient(ID);
    }


    public void addAuthor(String firstName, String lastName, LocalDate  birthdate, LocalDate deathDate) throws Exception {
            authorStorage.addElement(new Author(authorStorage.getTopID()+1
                    ,firstName,lastName,birthdate,deathDate));
    }

    public void addAuthor(String firstName, String lastName, LocalDate  birthdate) throws Exception {
        authorStorage.addElement(new Author(authorStorage.getTopID()+1
                ,firstName,lastName,birthdate));
    }

    public Author getAuthor(int ID) throws Exception {
        return authorStorage.getAuthor(ID);
    }

    public void addBook(String title, int authorID, LocalDate publishDate, int pagecount, double price) throws Exception {
        bookStorage.addElement(new Book(bookStorage.getTopID()+1, title, getAuthorStorage().getAuthor(authorID), publishDate, pagecount, price));
    }

    public void removeElementfromAccessible(int ID) throws Exception {
        bookStorage.removeElementfromAccessible(ID);
    }

    public void updateBook(int ID, String newData ,String partToUpdate) throws Exception {
        bookStorage.UpdateBook(ID,newData,partToUpdate);
    }

    public void addElementToAccessible(int ID) throws Exception {
        bookStorage.addElementToAccessible(ID);
    }

    public Book getBook(int ID) throws Exception {
        return bookStorage.getBook(ID);
    }

    public void createOrder(int clientID,int bookID, LocalDate SDate, LocalDate EDate) throws Exception {
        //checking if the book is available on that date

        Order temp = new Order(orderStorage.getTopID()+1,clientStorage.getClient(clientID), SDate, EDate);
        temp.addBookToOrder(bookStorage.getBook(bookID));
        orderStorage.addElement(temp);
    }

    public void addBookToOrder(int OrderID, int bookID) throws Exception {
        //checking if the book is available on that date
        orderStorage.addBookToOrder(OrderID,bookStorage.getBook(bookID));
    }

    public void removeBookFromOrder(int OrderID, int bookID) throws Exception {
        orderStorage.removeBookFromOrder(OrderID,bookID);
    }

    public Order getOrder(int ID) throws Exception {
        return orderStorage.getOrder(ID);
    }

    public ArrayList<Order> getOrdersByClientID(int clientID) {
        return  orderStorage.getOrdersByClientID(clientID);
    }

    public double endOrderandGetSum(int ID) throws Exception {
        return orderStorage.endOrder(ID);
    }

}
