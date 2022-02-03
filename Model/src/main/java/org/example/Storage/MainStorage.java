package org.example.Storage;

import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainStorage {

    private AuthorStorage authorStorage;
    private BookStorage bookStorage;
    private ClientStorage clientStorage;
    private OrderStorage orderStorage;
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public MainStorage(String URL) {
        authorStorage = new AuthorStorage(URL);
        bookStorage = new BookStorage(URL);
        clientStorage = new ClientStorage(URL);
        orderStorage = new OrderStorage(URL);
    }

    public void addClient(String firstName, String lastName, String phoneNumber, String email, String address) {
        clientStorage.addElement(new Client(clientStorage.getTopID()+1,firstName,lastName,phoneNumber,email,address));
    }
}
