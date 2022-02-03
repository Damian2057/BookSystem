package org.example.dao.Storage;

import org.example.Exceptions.Model.WrongAuthorExeption;
import org.example.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorStorage extends Storage<Author>{

    private String URL;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthorStorage(String URL) {
        this.URL = URL;
        initwithBase(URL);
    }

    public Author getAuthor(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        logger.error("Cannot Find Author with ID: "+ ID);
        throw new WrongAuthorExeption();
    }

    public int getTopID() {
        if(getAllElementsFromStorage().isEmpty()) {
            return 0;
        } else {
            return getAllElementsFromStorage().get(getAllElementsFromStorage().size()-1).getID();
        }
    }

    @Override
    public void addElement(Author obj) {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
    }
}
