package org.example.model.users;

public class Admin extends Personnel {

    public Admin(String nickName, String password, int ID, int permLevel, String URL) {
        super(nickName, password, ID, permLevel, URL);
    }

    public void removeClient(int ID) {
        getMainStorage().getClientStorage().removeElement(ID);
    }

    public void addWorker() {

    }

    public void removeWorker() {

    }
}
