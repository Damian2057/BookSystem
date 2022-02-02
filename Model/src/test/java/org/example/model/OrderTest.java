package org.example.model;

import org.example.model.Client.Client;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Client client = new Client(1,"Kate", "yzx"
            ,"795648631","yxz@gmail.com","city 954 nr. 54");

    private Book book = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
            , new Date(2001,10,15))
            ,new Date(2015,10,16),200,51.50);

    @Test
    void getCountOfOrderedBooks() {
        Order order2 = new Order(client, LocalDate.now().plusDays(1),LocalDate.now().plusDays(5));

        assertEquals(order2.getCountOfOrderedBooks(),0);
        order2.addBookFromOrder(book);
        assertEquals(order2.getCountOfOrderedBooks(),1);

        order2.addBookFromOrder(book);
        assertEquals(order2.getCountOfOrderedBooks(),1);
    }

    @Test
    void addBookToOrder() {
        Order orderByOldDate = new Order(client, LocalDate.now().minusDays(5),LocalDate.now().minusDays(3));
        Book book2 = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , new Date(2001,10,15))
                ,new Date(2015,10,16),200,51.50);

        orderByOldDate.addBookFromOrder(book2);
        assertEquals(orderByOldDate.getCountOfOrderedBooks(),0);

        Order orderByOldDatebyActual = new Order(client, LocalDate.now().minusDays(5),LocalDate.now().plusDays(5));
        orderByOldDate.addBookFromOrder(book2);
        assertEquals(orderByOldDate.getCountOfOrderedBooks(),0);

        Order orderByPreviousOrder = new Order(client, LocalDate.now().plusDays(3),LocalDate.now().plusDays(5));
        orderByPreviousOrder.addBookFromOrder(book2);
        assertEquals(orderByPreviousOrder.getCountOfOrderedBooks(),1);

    }

    @Test
    void removeBookToOrder() throws Throwable {
        Book book2 = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , new Date(2001,10,15))
                ,new Date(2015,10,16),200,51.50);
        Book book3 = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , new Date(2001,10,15))
                ,new Date(2015,10,16),200,51.50);
        Order orderByPreviousOrder = new Order(client, LocalDate.now().plusDays(3),LocalDate.now().plusDays(5));
        orderByPreviousOrder.addBookFromOrder(book2);
        assertEquals(orderByPreviousOrder.getCountOfOrderedBooks(),1);
        orderByPreviousOrder.removeBookFromOrder(1);
        assertEquals(orderByPreviousOrder.getCountOfOrderedBooks(),0);

        orderByPreviousOrder.addBookFromOrder(book2);
        assertEquals(orderByPreviousOrder.getCountOfOrderedBooks(),1);

        Order orderActual = new Order(client, LocalDate.now().plusDays(2),LocalDate.now().plusDays(5));
        orderActual.addBookFromOrder(book3);
        assertEquals(orderActual.getCountOfOrderedBooks(),1);
        orderActual.setStartReservationdate(LocalDate.now().minusDays(5));
        orderActual.removeBookFromOrder(1);
        assertEquals(orderActual.getCountOfOrderedBooks(),1);

        orderActual.addBookFromOrder(book3);
        assertEquals(orderActual.getCountOfOrderedBooks(),1);

        orderByPreviousOrder.addBookFromOrder(book3);
        assertEquals(orderByPreviousOrder.getCountOfOrderedBooks(),1);

        orderActual.setStartReservationdate(LocalDate.now().plusDays(2));
        orderActual.removeBookFromOrder(1);
        assertEquals(orderActual.getCountOfOrderedBooks(),0);

        Order orderOLD = new Order(client, LocalDate.now().plusDays(2),LocalDate.now().plusDays(5));
        orderOLD.addBookFromOrder(book3);
        assertEquals(orderOLD.getCountOfOrderedBooks(), 1);
        orderOLD.setEndReservationDate(LocalDate.now().minusDays(5));
        orderOLD.setStartReservationdate(LocalDate.now().minusDays(3));
        orderOLD.removeBookFromOrder(1);
        assertEquals(orderOLD.getCountOfOrderedBooks(), 1);

    }

    @Test
    void endOrder() {
        Book book3 = new Book(1,"Titanic", new Author(1,"xyz", "zyx"
                , new Date(2001,10,15))
                ,new Date(2015,10,16),200,5);
        Order order = new Order(client, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3));
        order.addBookFromOrder(book3);
        order.setStartReservationdate(LocalDate.now().minusDays(5));
        assertEquals(order.endOrder(),25);

        Order order2 = new Order(client, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3));
        order2.addBookFromOrder(book3);
        order2.setStartReservationdate(LocalDate.now().minusDays(5));
        order2.setEndReservationDate(LocalDate.now());
        assertEquals(order2.endOrder(),25);

        Order order3 = new Order(client, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3));
        order3.addBookFromOrder(book3);
        order3.setStartReservationdate(LocalDate.now().minusDays(5));
        order3.setEndReservationDate(LocalDate.now().minusDays(2));
        assertEquals(order3.endOrder(),29);

        assertEquals(client.getOrderCount(),3);
        client.addOrderCount(); //4

        Order order5 = new Order(client, LocalDate.now().plusDays(2),LocalDate.now().plusDays(3));
        order5.addBookFromOrder(book3);
        order5.setStartReservationdate(LocalDate.now().minusDays(5));
        order5.setEndReservationDate(LocalDate.now());
        assertEquals(order5.endOrder(), 25*0.7);


    }
}