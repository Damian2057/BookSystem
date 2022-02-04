package org.example.Exceptions.Dao;

public class DataBaseCreateException extends DataBaseException{
    public DataBaseCreateException(String messageKey) {
        super("DataBaseError");
    }
}
