package org.example.model.users;

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


}
