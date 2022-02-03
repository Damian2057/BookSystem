package org.example.model.users;

import org.example.Storage.MainStorage;

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


}
