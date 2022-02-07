package org.example.dao.Storage;

import org.example.Exceptions.Model.WrongBookIDException;
import org.example.dao.ClassFactory;
import org.example.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class BookStorage extends Storage<Book> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;
    public BookStorage(String URL) {
        this.URL = URL;
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

    public void removeElementfromAccessible(int ID) throws Exception {
        for (int i = 0; i < getAllElementsFromStorage().size(); i++) {
            if (ID == getAllElementsFromStorage().get(i).getID()) {
                getAllElementsFromStorage().remove(i);
                logger.info("Book ID:"+ID+" has been deleted");
                // synchronization with data BASE remove
                ClassFactory.getJDBCBookSystem(URL).UpdateBook(ID,0,"status");
                return;
            }
        }
        logger.error("Cannot Find Book with ID: "+ ID);
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
    public void addElement(Book obj) throws Exception {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
        ClassFactory.getJDBCBookSystem(URL).addBook(obj);
    }
}
