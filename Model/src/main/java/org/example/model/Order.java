package org.example.model;

import org.example.model.Client.Client;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Order {

    private ArrayList<Book> books = new ArrayList<Book>();
    private Client client;
    private LocalDate  startReservationdate;
    private LocalDate  endReservationDate;
    private LocalDate  realEndReservation;

    public Order(Client client, LocalDate startReservationdate, LocalDate endReservationDate) {
        this.client = client;
        this.startReservationdate = startReservationdate;
        this.endReservationDate = endReservationDate;
    }

    public int getCountOfOrderedBooks() {
        return books.size();
    }

    public LocalDate getStartReservationdate() {
        return startReservationdate;
    }

    public void setStartReservationdate(LocalDate startReservationdate) {
        this.startReservationdate = startReservationdate;
    }

    public LocalDate getEndReservationDate() {
        return endReservationDate;
    }

    public void setEndReservationDate(LocalDate endReservationDate) {
        this.endReservationDate = endReservationDate;
    }

    public void addBookFromOrder(Book obj) {
        if(!obj.isOrdered()) {
            int period = Period.between(LocalDate.now(),startReservationdate).getDays();
            if(period >= 0) {
                books.add(obj);
                obj.setOrdered(true);
            }
        }
    }

    public void removeBookFromOrder(Book obj) {
        int period = Period.between(LocalDate.now(), startReservationdate).getDays();
        if(period >= 0) {
            if(books.remove(obj)){
                obj.setOrdered(false);
            }
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
        realEndReservation = LocalDate.now();
        return sum*client.getReduction();
    }

}
