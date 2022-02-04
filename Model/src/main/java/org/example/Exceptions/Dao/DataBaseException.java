package org.example.Exceptions.Dao;

import org.example.Exceptions.LocalizedRunTimeException;

public class DataBaseException extends LocalizedRunTimeException {
    public DataBaseException(String messageKey) {
        super(messageKey);
    }
}
