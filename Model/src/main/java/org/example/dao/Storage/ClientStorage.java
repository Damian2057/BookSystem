package org.example.dao.Storage;

import org.example.Exceptions.Dao.WrongdataChooseException;
import org.example.Exceptions.Model.WrongBookIDException;
import org.example.Exceptions.Model.WrongClientIDException;
import org.example.dao.ClassFactory;
import org.example.model.Author;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientStorage extends Storage<Client>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;
    public ClientStorage(String URL) throws Exception {
        this.URL = URL;
        for (Client a :
                ClassFactory.getJDBCBookSystem(URL).getListofClients()) {
            getAllElementsFromStorage().add(a);
        }
    }

    public Client getClient(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        logger.error("Cannot Find Client with ID: "+ ID);
        throw new WrongBookIDException();
    }

    public void removeElement(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {

                //checking if there are any active orders or delete whole method

                if(ClassFactory.getJDBCBookSystem(URL).getOrderByID(ID) == null) {
                    getAllElementsFromStorage().remove(i);
                    logger.info("Client ID:"+ID+" has been deleted");
                    ClassFactory.getJDBCBookSystem(URL).deleteClient(ID);
                    return;
                } else {
                    logger.error("Cannot delete Client due to Orders");
                }
            }
        }
        logger.error("Cannot Find Client with ID: "+ ID);
        throw new WrongClientIDException();
    }

    public void UpdateClient(int ID, String newData ,String partToUpdate) throws Exception {
        switch (partToUpdate) {
            case "address":
                getClient(ID).setAddress(newData);
                ClassFactory.getJDBCBookSystem(URL).UpdateClient(ID,newData,partToUpdate);
                break;
            case "email":
                getClient(ID).setEmailAddress(newData);
                ClassFactory.getJDBCBookSystem(URL).UpdateClient(ID,newData,partToUpdate);
                break;
            case "lastname":
                getClient(ID).setLastName(newData);
                ClassFactory.getJDBCBookSystem(URL).UpdateClient(ID,newData,partToUpdate);
                break;
            case "name":
                getClient(ID).setFirstName(newData);
                ClassFactory.getJDBCBookSystem(URL).UpdateClient(ID,newData,partToUpdate);
                break;
            case "order":
                getClient(ID).setOrderCount(Integer.parseInt(newData));
                ClassFactory.getJDBCBookSystem(URL).UpdateClient(ID,newData,partToUpdate);
                break;
            case "phone":
                getClient(ID).setPhoneNumber(newData);
                ClassFactory.getJDBCBookSystem(URL).UpdateClient(ID,newData,partToUpdate);
                break;
            default:
                throw new WrongdataChooseException();
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
    public void addElement(Client obj) throws Exception {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
        ClassFactory.getJDBCBookSystem(URL).addClient(obj);
    }
}
