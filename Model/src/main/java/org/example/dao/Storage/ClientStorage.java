package org.example.dao.Storage;

import org.example.Exceptions.Model.WrongBookIDException;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientStorage extends Storage<Client>{

    private String URL;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public ClientStorage(String URL) {
        this.URL = URL;
        initwithBase(URL);
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

    public void removeElement(int ID) {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                //checking if there are any active orders or delete whole method
                getAllElementsFromStorage().remove(i);
                logger.info("Client ID:"+ID+" has been deleted");
                // synchronization with data BASE remove
                return;
            }
        }
        logger.error("Cannot Find Client with ID: "+ ID);
        throw new WrongBookIDException();
    }

    public int getTopID() {
        if(getAllElementsFromStorage().isEmpty()) {
            return 0;
        } else {
            return getAllElementsFromStorage().get(getAllElementsFromStorage().size()-1).getID();
        }
    }

    @Override
    public void addElement(Client obj) {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
    }
}
