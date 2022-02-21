package org.example.model;

import java.time.LocalDate;

public class Book {
    private final int ID;
    private String title;
    private Author author;
    private LocalDate publishDate;
    private int pageCount;
    private double price;
    private boolean accessible = true;

    public Book(int ID,String title, Author author, LocalDate publishDate, int pageCount, double basicOrderPrice) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.pageCount = pageCount;
        this.price = basicOrderPrice;
    }

    public int getID() {
        return ID;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getPageCount() {
        return pageCount;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public String getFullName() {
        return author.fullName();
    }

}
