package org.example.model;

import java.util.Date;

public class Book {
    private final int ID;
    private final String title;
    private Author author;
    private Date publishDate;
    private int pageCount;
    private double basicOrderPrice;

    public Book(int ID,String title, Author author, Date publishDate, int pageCount, double basicOrderPrice) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
        this.basicOrderPrice = basicOrderPrice;
    }

    public int getID() {
        return ID;
    }

    public double getBasicOrderPrice() {
        return basicOrderPrice;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public int getPageCount() {
        return pageCount;
    }
}
