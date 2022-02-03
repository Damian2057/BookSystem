package org.example.Storage;

import org.example.Exceptions.Model.WrongBookIDException;
import org.example.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookStorage extends Storage<Book> {

    private String URL;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public BookStorage(String URL) {
        this.URL = URL;
        initwithBase(URL);
    }

    public Book getBook(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                return getAllElementsFromStorage().get(i);
            }
        }
        logger.error("Cannot Find Book with ID: "+ ID);
        throw new WrongBookIDException();
    }

    public void removeElement(int ID) {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                getAllElementsFromStorage().remove(i);
                logger.info("Book ID:"+ID+" has been deleted");
                // synchronization with data BASE remove
                return;
            }
        }
        logger.error("Cannot Find Book with ID: "+ ID);
        throw new WrongBookIDException();
    }

    @Override
    public void addElement(Book obj) {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
    }
}
