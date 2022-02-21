package org.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
            , LocalDate.parse("2001-10-15"))
            ,LocalDate.parse("2015-10-16"),200,51.50);

    @Test
    void getID() {
        assertEquals(book.getID(),1);
    }

    @Test
    void getBasicOrderPrice() {
        assertEquals(book.getPrice(),51.50);
    }

    @Test
    void getTitle() {
        assertEquals(book.getTitle(), "Titanic");
    }

    @Test
    void getAuthor() {
        Author a = new Author(1,"xyz", "zyx", LocalDate.parse("2001-10-15"));
        assertEquals(book.getAuthor().getID(), a.getID());
    }

    @Test
    void getPublishDate() {
        LocalDate date = LocalDate.parse("2015-10-16");
        assertEquals(book.getPublishDate(),date);
    }

    @Test
    void getPageCount() {
        assertEquals(book.getPageCount(),200);
    }
}