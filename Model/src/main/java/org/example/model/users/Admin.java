package org.example.model.users;

public class Admin extends Personnel {

    public Admin(String nickName, String password, int ID, int permLevel) {
        super(nickName, password, ID, permLevel);
    }

    public void removeClient(int ID) {
        getMainStorage().getClientStorage().removeElement(ID);
    }

    public void addWorker() {

    }

    public void removeWorker() {

    }
}
