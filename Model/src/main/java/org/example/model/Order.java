package org.example.model;

import org.example.model.Client.Client;

import java.util.Date;

public class Order {

    private Book book;
    private Client client;
    private Date startdate;
    private int duration;
    private Date endDate;


    public void createOrder(Book book, Client client, Date startdate, int duration) {
        this.book = book;
        this.client = client;
        this.startdate = startdate;
        this.duration = duration;
    }

}
