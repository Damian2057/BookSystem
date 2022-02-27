package org.example.model.users;

import org.example.dao.Storage.MainStorage;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.example.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Personnel {

    private String nickName;
    private String password;
    private final int ID;
    private int permLevel;
    private MainStorage mainStorage;

    public Personnel(String nickName, String password, int ID) {
        this.nickName = nickName;
        this.password = password;
        this.ID = ID;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }

    public int getPermLevel() {
        return permLevel;
    }

    public void setPermLevel(int permLevel) {
        this.permLevel = permLevel;
    }

    public MainStorage getMainStorage() {
        return mainStorage;
    }

    public void addClient(String firstName, String lastName, String phoneNumber, String email, String address) throws Exception {
        mainStorage.addClient(firstName,lastName,phoneNumber,email,address);
    }
}
