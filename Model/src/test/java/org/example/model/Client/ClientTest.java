package org.example.model.Client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private Client client = new Client(1,"Kate", "yzx"
            ,"795648631","yxz@gmail.com","city 954 nr. 54");

    @Test
    void getID() {
        assertEquals(client.getID(), 1);
    }

    @Test
    void getOrderCount() {
        assertEquals(client.getOrderCount(),0);
    }

    @Test
    void setOrderCount() {
        client.setOrderCount(3);
        assertEquals(client.getOrderCount(),3);
    }

    @Test
    void addOrderCount() {
        client.addOrderCount();
        assertEquals(client.getOrderCount(),1);
    }

    @Test
    void getFirstName() {
        assertEquals(client.getFirstName(), "Kate");
    }

    @Test
    void getLastName() {
        assertEquals(client.getLastName(), "yzx");
    }

    @Test
    void getPhoneNumber() {
        assertEquals(client.getPhoneNumber(), "795648631");
    }

    @Test
    void getEmailAddress() {
        assertEquals(client.getEmailAddress(), "yxz@gmail.com");
    }

    @Test
    void getAddress() {
        assertEquals(client.getAddress(), "city 954 nr. 54");
    }

    @Test
    void setPhoneNumber() {
        client.setPhoneNumber("555555555");
        assertEquals(client.getPhoneNumber(), "555555555");
    }

    @Test
    void setEmailAddress() {
        client.setEmailAddress("dasd@wp.com");
        assertEquals(client.getEmailAddress(), "dasd@wp.com");
    }

    @Test
    void setAddress() {
        client.setAddress("other city 55 68");
        assertEquals(client.getAddress(), "other city 55 68");
    }

    @Test
    void setFirstName() {
        client.setFirstName("Annie");
        assertEquals(client.getFirstName(), "Annie");
    }

    @Test
    void setLastName() {
        client.setLastName("other lName");
        assertEquals(client.getLastName(), "other lName");
    }

    @Test
    void reductionTest() {
        Client client2 = new Client(1,"Kate", "yzx"
                ,"795648631","yxz@gmail.com","city 954 nr. 54");
        assertEquals(client2.getReduction(),1);
        client2.setOrderCount(4);
        assertEquals(client2.getReduction(),1);
        client2.addOrderCount();
        assertEquals(client2.getReduction(),0.7);

    }
}