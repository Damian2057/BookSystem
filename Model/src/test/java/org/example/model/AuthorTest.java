package org.example.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    private Author author = new Author(1,"xyz", "zyx", LocalDate.parse("2020-01-08"));
    private Author authorD = new Author(2,"xyz", "zyx", LocalDate.parse("2020-01-08")
            , LocalDate.parse("2020-01-08"));


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
        LocalDate date = LocalDate.parse("2020-01-08");
        assertEquals(author.getBirthDay(), date);
    }

    @Test
    void getDeathDate() {
        LocalDate date = LocalDate.parse("2020-01-08");
        assertEquals(author.getDeathDate(), null);
        assertEquals(authorD.getDeathDate(),date);

    }

    @Test
    void setDeathDate() {
        LocalDate date = LocalDate.parse("2020-01-08");
        author.setDeathDate(date);
        assertEquals(author.getDeathDate(),date);
    }
}