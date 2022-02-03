package org.example.model;

import org.example.Exceptions.Model.BookalreadyOrderedException;
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

    public Order(int ID, Client client, LocalDate startReservationdate, LocalDate endReservationDate) {

        if(Period.between(startReservationdate,endReservationDate).getDays() < 0) {
            throw new IncorrectOrderDateException();
        }

        this.ID = ID;
        this.client = client;
        this.startReservationdate = startReservationdate;
        this.endReservationDate = endReservationDate;
        logger.info("Create Order ID:"+ID+", client ID: "+client.getID()+", SDate:"+startReservationdate+"" +
                ", EDate:"+endReservationDate);

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

    public void addBookToOrder(Book obj) {
        if(!obj.isOrdered()) {
            int period = Period.between(LocalDate.now(),startReservationdate).getDays();
            if(period >= 0) {
                logger.info("Book ID:"+ obj.getID()+" successfully added");
                books.add(obj);
                obj.setOrdered(true);
            } else {
                logger.error("Book ID:"+ obj.getID()+" cannot be added");
                throw new OrderTimeException();
            }
        } else {
            logger.error("Book ID:"+ obj.getID()+" is already on loan");
            throw new BookalreadyOrderedException();
        }
    }

    public void removeBookFromOrder(int objID) {
        int period = Period.between(LocalDate.now(), startReservationdate).getDays();
        if(period >= 0) {
            for (int i = 0; i < books.size(); i++) {
                if(objID == books.get(i).getID()) {
                    books.get(i).setOrdered(false);
                    logger.info("Book ID:"+ objID+" successfully removed");
                    books.remove(i);
                }
            }
        } else {
            throw new OrderTimeException();
        }
    }

    public double endOrder() {
        client.addOrderCount();
        double sum = 0;
        int aheadTime  = Period.between(LocalDate.now(), endReservationDate).getDays();
        if(aheadTime >= 0) {
            int period = Period.between(startReservationdate, LocalDate.now()).getDays();
            for (Book o : books) {
                sum += o.getBasicOrderPrice()*period;
                o.setOrdered(false);
            }
        } else {
            int extraDuration = Math.abs(aheadTime);
            int normalDuration = Period.between(startReservationdate, endReservationDate).getDays();
            for (Book o : books) {
                sum += o.getBasicOrderPrice()*normalDuration
                        + (o.getBasicOrderPrice()+2)*extraDuration;
                o.setOrdered(false);
            }
        }
        logger.info("Order successfully removed, amount to pay: "+ sum*client.getReduction());
        realEndReservation = LocalDate.now();
        return sum*client.getReduction();
    }

    public int getID() {
        return ID;
    }
}
