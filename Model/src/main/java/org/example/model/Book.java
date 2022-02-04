package org.example.model;

import java.time.LocalDate;
import java.util.Date;

public class Book {
    private final int ID;
    private final String title;
    private Author author;
    private LocalDate publishDate;
    private int pageCount;
    private double basicOrderPricePerDay;
    private boolean isOrdered = false;

    public Book(int ID,String title, Author author, LocalDate publishDate, int pageCount, double basicOrderPrice) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
        this.basicOrderPricePerDay = basicOrderPrice;
    }

    public int getID() {
        return ID;
    }

    public double getBasicOrderPrice() {
        return basicOrderPricePerDay;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isOrdered() {
        return isOrdered;
    }

    public void setOrdered(boolean ordered) {
        isOrdered = ordered;
    }
}
