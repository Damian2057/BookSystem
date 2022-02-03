package org.example.Storage;

import org.example.Exceptions.Model.WrongAuthorExeption;
import org.example.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorStorage extends Storage<Author>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Author getAuthor(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        logger.error("Cannot Find Author with ID: "+ ID);
        throw new WrongAuthorExeption();
    }

    @Override
    public void addElement(Author obj) {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
    }
}
