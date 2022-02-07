package org.example.dao.Storage;

import org.example.model.users.Personnel;

public class LoginStorage extends Storage<Personnel> {
    private String URL = "jdbc:derby:LoginSystem;create=true";

    @Override
    public void addElement(Personnel obj) {
        getAllElementsFromStorage().add(obj);
    }
}
