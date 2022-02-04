package org.example.dao.Storage;

import java.util.ArrayList;

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
