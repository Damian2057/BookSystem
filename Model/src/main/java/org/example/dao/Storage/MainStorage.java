package org.example.dao.Storage;

import org.example.Exceptions.data.DataConflictException;
import org.example.dao.ClassFactory;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

public class MainStorage {

    private AuthorStorage authorStorage;
    private BookStorage bookStorage;
    private ClientStorage clientStorage;
    private OrderStorage orderStorage;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;


    public MainStorage(String URL) throws Exception {
        this.URL = URL;
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

    public void updateClient(int ID, String newData ,String partToUpdate) throws Exception {
        clientStorage.UpdateClient(ID,newData,partToUpdate);
    }

    public void updateAuthor(int ID, String newData ,String partToUpdate) throws Exception {
        authorStorage.updateAuthor(ID,newData,partToUpdate);
    }

    public void addElementToAccessible(int ID) throws Exception {
        bookStorage.addElementToAccessible(ID);
    }

    public Book getBook(int ID) throws Exception {
        return bookStorage.getBook(ID);
    }

    public ArrayList<Book> getAllBooks() {
        return bookStorage.getAllElementsFromStorage();
    }

    public ArrayList<Author> getAllAuthors() {
        return authorStorage.getAllElementsFromStorage();
    }

    public ArrayList<Client> getAllClients() {
        var list = clientStorage.getAllElementsFromStorage();
        Collections.sort(list);
        return list;
    }

    public ArrayList<Order> getAllOrders() {
        return orderStorage.getAllElementsFromStorage();
    }

    public int createOrder(int clientID,int bookID, LocalDate SDate, LocalDate EDate) throws Exception {
        //checking if the book is available on that date
        var listBook = ClassFactory.getJDBCBookSystem(URL).getOrderBybookID(bookID);
        for(int i = 0; i < listBook.size(); i++) {
            if(SDate.isAfter(listBook.get(i).getStartReservationdate()) && SDate.isBefore(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
            if(EDate.isAfter(listBook.get(i).getStartReservationdate()) && EDate.isBefore(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
            if(SDate.equals(listBook.get(i).getStartReservationdate()) || SDate.equals(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
            if(EDate.equals(listBook.get(i).getStartReservationdate()) || EDate.equals(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
        }

        Order temp = new Order(orderStorage.getTopID()+1,clientStorage.getClient(clientID), SDate, EDate);
        temp.addBookToOrder(bookStorage.getBook(bookID));
        orderStorage.addElement(temp);
        return temp.getID();
    }

    public boolean checkAvailabilityInDay(ArrayList<Order> list,int bookID, int Day) throws Exception {
        if(list.isEmpty()){
            return true;
        } else {
            String temp = "";
            if(Day < 10) {
                temp = "0" + Day;
            } else {
                temp = String.valueOf(Day);
            }
            boolean tempboolean = true;
            LocalDate localDate = null;
            if(LocalDate.now().getMonthValue() < 10) {
                 localDate = LocalDate.parse(LocalDate.now().getYear()+"-0"+LocalDate.now().getMonthValue()+"-"+temp);
            } else {
                 localDate = LocalDate.parse(LocalDate.now().getYear()+"-0"+LocalDate.now().getMonthValue()+"-"+temp);
            }
            for (int i = 0; i < list.size(); i++) {
                if(localDate.isBefore(list.get(i).getStartReservationdate()) && localDate.isBefore(list.get(i).getEndReservationDate())) {
                    tempboolean = true;
                } else if(localDate.isAfter(list.get(i).getEndReservationDate()) && localDate.isAfter(list.get(i).getStartReservationdate())) {
                    tempboolean = true;
                } else {
                    return false;
                }
            }
            return tempboolean;
        }
    }

    public void addBookToOrder(int OrderID, int bookID) throws Exception {
        //checking if the book is available on that date
        var listBook = ClassFactory.getJDBCBookSystem(URL).getOrderBybookID(bookID);
        for(int i = 0; i < listBook.size(); i++) {
            if(getOrder(OrderID).getStartReservationdate().isAfter(listBook.get(i).getStartReservationdate())
                    && getOrder(OrderID).getStartReservationdate().isBefore(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
            if(getOrder(OrderID).getEndReservationDate().isAfter(listBook.get(i).getStartReservationdate())
                    && getOrder(OrderID).getEndReservationDate().isBefore(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
            if(getOrder(OrderID).getStartReservationdate().equals(listBook.get(i).getStartReservationdate())
                    || getOrder(OrderID).getStartReservationdate().equals(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
            if(getOrder(OrderID).getEndReservationDate().equals(listBook.get(i).getStartReservationdate())
                    || getOrder(OrderID).getEndReservationDate().equals(listBook.get(i).getEndReservationDate())) {
                throw new DataConflictException();
            }
        }
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
