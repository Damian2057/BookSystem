package org.example.Exceptions.data;

import org.example.Exceptions.LocalizedRunTimeException;

public class DataException extends LocalizedRunTimeException {
    public DataException(String messageKey) {
        super(messageKey);
    }
}
