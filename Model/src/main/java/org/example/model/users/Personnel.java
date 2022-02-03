package org.example.model.users;

import org.example.Storage.MainStorage;

import java.time.LocalDate;
import java.util.Date;

public abstract class Personnel {

    private String nickName;
    private String password;
    private final int ID;
    private final int permLevel;

    public Personnel(String nickName, String password, int ID, int permLevel) {
        this.nickName = nickName;
        this.password = password;
        this.ID = ID;
        this.permLevel = permLevel;
    }

    public void addClient(MainStorage mainStorage,String firstName, String lastName, String phoneNumber, String email, String address) {
        mainStorage.addClient(firstName,lastName,phoneNumber,email,address);
    }

    public void addAuthor(MainStorage mainStorage, String firstName, String lastName, Date birthdate, Date deathDate) {
        mainStorage.addAuthor(firstName, lastName, birthdate, deathDate);
    }

    public void addBook(MainStorage mainStorage, String title, int AuthorID, Date publishDate
            , int pagecount, double price) throws Exception {
        mainStorage.addBook(title, mainStorage.getAuthorStorage().getAuthor(AuthorID),publishDate,pagecount,price);
    }

    public void createOrder(MainStorage mainStorage, int clientID, LocalDate SDate, LocalDate EDate) throws Exception {
        mainStorage.createOrder(mainStorage.getClientStorage().getClient(clientID),SDate,EDate);
    }



}
