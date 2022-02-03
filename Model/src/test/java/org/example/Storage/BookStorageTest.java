package org.example.Storage;

import org.example.model.Author;
import org.example.model.Book;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookStorageTest {

    @Test
    void addElement() throws Exception {
        Book book = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , new Date(2001,10,15))
                ,new Date(2015,10,16),200,51.50);
        BookStorage bookStorage = new BookStorage("test");

        bookStorage.addElement(book);

        ClientStorage clientStorage = new ClientStorage("test");
        System.out.println(bookStorage.getAllElementsFromStorage().get(bookStorage.getAllElementsFromStorage().size()-1));

    }
}