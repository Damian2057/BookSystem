package org.example.model.users;

public class Worker extends Personnel {

    public Worker(String nickName, String password, int ID) {
        super(nickName, password, ID);
        setPermLevel(0);
    }
}
