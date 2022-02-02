package org.example.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author = new Author(1,"xyz", "zyx", new Date(2001,05,11));
    private Author authorD = new Author(2,"xyz", "zyx", new Date(2001,05,11)
            , new Date(2080,5,16));


    @Test
    void getFirstName() {
        assertEquals(author.getFirstName(), "xyz");
    }

    @Test
    void getLastName() {
        assertEquals(author.getLastName(), "zyx");
    }

    @Test
    void getBirthDay() {
        Date date = new Date(2001,05,11);
        assertEquals(author.getBirthDay(), date);
    }

    @Test
    void getDeathDate() {
        Date date2 = new Date(2080,5,16);
        assertEquals(author.getDeathDate(), null);
        assertEquals(authorD.getDeathDate(),date2);

    }

    @Test
    void setDeathDate() {
        Date date2 = new Date(2080,5,16);
        author.setDeathDate(date2);
        assertEquals(author.getDeathDate(),date2);
    }
}