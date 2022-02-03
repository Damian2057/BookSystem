package org.example.Exceptions.Model;

import org.example.Exceptions.LocalizedRunTimeException;

public class BookException extends LocalizedRunTimeException {
    public BookException(String messageKey) {
        super(messageKey);
    }
}
