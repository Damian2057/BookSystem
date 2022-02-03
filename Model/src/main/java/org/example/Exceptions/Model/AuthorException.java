package org.example.Exceptions.Model;

import org.example.Exceptions.LocalizedRunTimeException;

public class AuthorException extends LocalizedRunTimeException {
    public AuthorException(String messageKey) {
        super(messageKey);
    }
}
