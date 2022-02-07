package org.example.dao.Storage;

import org.example.Exceptions.Model.WrongAuthorExeption;
import org.example.dao.ClassFactory;
import org.example.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Objects;

public class AuthorStorage extends Storage<Author>{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private String URL;
    public AuthorStorage(String URL) throws Exception {
        this.URL = URL;
        for (Author a :
                ClassFactory.getJDBCBookSystem(URL).getListofAuthors()) {
            getAllElementsFromStorage().add(a);
        }
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

    public void updateAuthor(int ID, String newData ,String partToUpdate) throws Exception {
        if(Objects.equals(partToUpdate, "deathdate")) {
            getAuthor(ID).setDeathDate(LocalDate.parse(newData));
            ClassFactory.getJDBCBookSystem(URL).updateAuthor(ID,newData,partToUpdate);
        } else if(Objects.equals(partToUpdate, "lastname")){
            getAuthor(ID).setLastName(newData);
            ClassFactory.getJDBCBookSystem(URL).updateAuthor(ID,newData,partToUpdate);
        } else if(Objects.equals(partToUpdate, "name")) {
            getAuthor(ID).setFirstName(newData);
            ClassFactory.getJDBCBookSystem(URL).updateAuthor(ID,newData,partToUpdate);
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
    public void addElement(Author obj) throws Exception {
        getAllElementsFromStorage().add(obj);
        // synchronization with data BASE aDD
        ClassFactory.getJDBCBookSystem(URL).addAuthor(obj);
    }
}
