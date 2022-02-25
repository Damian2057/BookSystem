package org.example.dao.usersManager;

import org.example.Exceptions.Dao.AdminInSystemException;
import org.example.Exceptions.Dao.WrongLoginDataException;
import org.example.dao.ClassFactory;
import org.example.dao.Storage.Storage;
import org.example.model.Author;
import org.example.model.users.Personnel;
import org.example.model.users.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LoginStorage extends Storage<Personnel> {

    private final String user;
    private final String password;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;

    public LoginStorage(String URL, String user, String password) throws Exception {
        this.URL = URL;
        this.user = user;
        this.password = password;
        for (Personnel a :
                ClassFactory.getJDBCLoginSystem(URL, user, password).getListofworkers()) {
            getAllElementsFromStorage().add(a);
        }
    }

    public Personnel getPersonnel(int ID) {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        throw new WrongLoginDataException();
    }

    public void addPersonnel(String nickname, String password) throws Exception {
            addElement(new Worker(nickname,password,getTopID()+1));
    }

    public void removePersonnel(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                if(getAllElementsFromStorage().get(i).getPermLevel() == 1) {
                    throw new AdminInSystemException();
                }
                getAllElementsFromStorage().remove(getAllElementsFromStorage().get(i));
                ClassFactory.getJDBCLoginSystem(URL,user,password).removePersonel(ID);
            }
        }
    }

    public void updatePersonnel(int ID, String newData, String partToUpdate) throws Exception {
        if(Objects.equals(partToUpdate, "nickName")) {
            getPersonnel(ID).setNickName(newData);
            ClassFactory.getJDBCLoginSystem(URL,user,password).updatePersonnel(partToUpdate,ID,newData);
        } else if(Objects.equals(partToUpdate, "Password")) {
            getPersonnel(ID).setPassword(newData);
            ClassFactory.getJDBCLoginSystem(URL,user,password).updatePersonnel(partToUpdate,ID,newData);
        } else {
            throw new WrongLoginDataException();
        }
    }

    public int getTopID() {
        if(getAllElementsFromStorage().isEmpty()) {
            return 0;
        } else {
            return getAllElementsFromStorage().get(getAllElementsFromStorage().size()-1).getID();
        }
    }

    @Override
    public void addElement(Personnel obj) throws Exception {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
        ClassFactory.getJDBCLoginSystem(URL,user,password).addPersonel(obj);
    }
}
