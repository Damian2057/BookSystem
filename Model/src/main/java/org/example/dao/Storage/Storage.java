package org.example.dao.Storage;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Storage<T> implements StorageInterface<T> {

    private final ArrayList<T> elementCollection = new ArrayList<>();

    @Override
    public ArrayList<T> getAllElementsFromStorage() {
        return elementCollection;
    }



    @Override
    public int getCountOfElements() {
        return elementCollection.size();
    }

}
