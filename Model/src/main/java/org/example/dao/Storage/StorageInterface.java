package org.example.dao.Storage;

import java.util.ArrayList;

public interface StorageInterface<T> {

    void addElement(T obj);
    ArrayList<T> getAllElementsFromStorage();
    int getCountOfElements();
    void initwithBase(String URL);

}
