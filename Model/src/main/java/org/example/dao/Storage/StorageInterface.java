package org.example.dao.Storage;

import java.util.ArrayList;

public interface StorageInterface<T> {

    void addElement(T obj) throws Exception;
    ArrayList<T> getAllElementsFromStorage();
    int getCountOfElements();

}
