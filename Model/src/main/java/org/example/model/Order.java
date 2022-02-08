package org.example.model;

import org.example.Exceptions.Model.BookalreadyOrderedException;
import org.example.Exceptions.Model.OrderComplitedException;
import org.example.Exceptions.Model.OrderTimeException;
import org.example.Exceptions.Model.IncorrectOrderDateException;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Order {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final int ID;
    private ArrayList<Book> books = new ArrayList<Book>();
    private Client client;
    private LocalDate  startReservationdate;
    private LocalDate  endReservationDate;
    private LocalDate  realEndReservation;
    private boolean isCompleted = false;

    public Order(int ID, Client client, LocalDate startReservationdate, LocalDate endReservationDate) {

        if(Period.between(startReservationdate,endReservationDate).getDays() < 0) {
            logger.error("Attempting to create an order with an incorrect date ");
            throw new IncorrectOrderDateException();
        }

        this.ID = ID;
        this.client = client;
        this.startReservationdate = startReservationdate;
        this.endReservationDate = endReservationDate;
        logger.info("Create Order ID:"+ID+", client ID: "+client.getID()+", SDate:"+startReservationdate+"" +
                ", EDate:"+endReservationDate);

    }

    public ArrayList<Book> getListofBooks() {
        return books;
    }

    public int getClientID() {
        return client.getID();
    }

    public int getCountOfOrderedBooks() {
        return books.size();
    }

    public LocalDate getStartReservationdate() {
        return startReservationdate;
    }

    public void setStartReservationdate(LocalDate startReservationdate) {
        logger.info("Order SDate change to: " + startReservationdate);
        this.startReservationdate = startReservationdate;
    }

    public LocalDate getEndReservationDate() {
        return endReservationDate;
    }

    public void setEndReservationDate(LocalDate endReservationDate) {
        logger.info("Order EDate change to: " + endReservationDate);
        this.endReservationDate = endReservationDate;
    }

    public void onlyForInit(Book obj) {
        books.add(obj);
    }

    public void addBookToOrder(Book obj) {
        if(obj.isAccessible()) {
            int period = Period.between(LocalDate.now(),startReservationdate).getDays();
            if(period >= 0) {
                logger.info("Book ID:"+ obj.getID()+" successfully added");
                books.add(obj);
            } else {
                logger.error("Book ID:"+ obj.getID()+" cannot be added");
                throw new OrderTimeException();
            }
        } else {
            logger.error("Book ID:"+ obj.getID()+" is not accessible");
            throw new BookalreadyOrderedException();
        }
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void removeBookFromOrder(int objID) {
        int period = Period.between(LocalDate.now(), startReservationdate).getDays();
        if(period >= 0) {
            for (int i = 0; i < books.size(); i++) {
                if(objID == books.get(i).getID()) {
                    logger.info("Book ID:"+ objID+" successfully removed");
                    books.remove(i);
                }
            }
        } else {
            throw new OrderTimeException();
        }
    }

    public double endOrder() {
        if(!isCompleted()) {
            setCompleted(true);
            client.addOrderCount();
            double sum = 0;
            int aheadTime  = Period.between(LocalDate.now(), endReservationDate).getDays();
            if(aheadTime >= 0) { //ending before / on time
                logger.info("Order ID: {} delivered on time ", ID);
                int period = Period.between(startReservationdate, LocalDate.now()).getDays();
                if(period <= 0) {
                    logger.info("Order ID: {} canceled and removed  ", ID);
                    return 0;
                }
                for (Book o : books) {
                    sum += o.getBasicOrderPrice()*period;
                }
            } else { //time extension
                logger.info("Order ID: {} not delivered on time ", ID);
                int extraDuration = Math.abs(aheadTime);
                int normalDuration = Period.between(startReservationdate, endReservationDate).getDays();
                for (Book o : books) {
                    sum += o.getBasicOrderPrice()*normalDuration
                            + (o.getBasicOrderPrice()+2)*extraDuration;
                }
            }
            logger.info("Order successfully removed, amount to pay: "+ sum*client.getReduction());
            realEndReservation = LocalDate.now();
            return sum*client.getReduction();
        } else {
            throw new OrderComplitedException();
        }
    }

    public int getID() {
        return ID;
    }
}
