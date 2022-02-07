package org.example.dao.Storage;

import org.example.Exceptions.Dao.WrongdataChooseException;
import org.example.Exceptions.Model.WrongBookIDException;
import org.example.dao.ClassFactory;
import org.example.model.Book;
import org.example.model.Client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Objects;

public class BookStorage extends Storage<Book> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;
    public BookStorage(String URL) throws Exception {
        this.URL = URL;
        for (Book a :
                ClassFactory.getJDBCBookSystem(URL).getListofBooks()) {
            getAllElementsFromStorage().add(a);
        }
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

                getAllElementsFromStorage().get(i).setAccessible(false);
                logger.info("Book ID:"+ID+" has been moved to the archive");
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

    public void UpdateBook(int ID, String newData ,String partToUpdate) throws Exception {
        if ("status".equals(partToUpdate)) {
            boolean status = Objects.equals(newData, "true");
            getBook(ID).setAccessible(status);
            int bstatus;
            if(Objects.equals(newData, "true")) {
                bstatus = 1;
            } else {
                bstatus = 0;
            }
            ClassFactory.getJDBCBookSystem(URL).UpdateBook(ID, bstatus, partToUpdate);
        } else {
            throw new WrongdataChooseException();
        }
    }

    @Override
    public void addElement(Book obj) throws Exception {
        // synchronization with data BASE aDD
        ClassFactory.getJDBCBookSystem(URL).addBook(obj);

        getAllElementsFromStorage().add(obj);
    }
}
