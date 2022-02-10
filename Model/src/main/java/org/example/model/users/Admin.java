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

    public void removeClient(int ID) throws Exception {
        getMainStorage().getClientStorage().removeElement(ID);
    }

    public void addWorker(String nickName, String password) throws Exception {
        ClassFactory.getJDBCLoginSystem(PermUrl,user,password).addPersonel(new Worker(nickName
                ,password,ClassFactory.getJDBCLoginSystem(PermUrl,user,password).getListofworkers().get(
                        ClassFactory.getJDBCLoginSystem(PermUrl,user,password).getListofworkers().size()-1).getID()+1));

    }

    public void removeWorker(int ID) throws Exception {
        ClassFactory.getJDBCLoginSystem(PermUrl,user,password).removePersonel(ID);
    }
}
