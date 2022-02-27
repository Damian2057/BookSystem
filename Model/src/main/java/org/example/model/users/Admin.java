package org.example.model.users;

import org.example.dao.ClassFactory;

public class Admin extends Personnel {

    private String PermUrl ="jdbc:derby:LoginSystem";
    private String user ="adminnn";
    private String password= ClassFactory.getFileSaverSystem("@../../Config/configuration")
            .read().getAppGeneralPassword();

    public Admin(String nickName, String password, int ID) throws Exception {
        super(nickName, password, ID);
        setPermLevel(1);
    }
}
