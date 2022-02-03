package org.example.Exceptions.Model;

import org.example.Exceptions.LocalizedRunTimeException;

public class ClientException extends LocalizedRunTimeException {
    public ClientException(String messageKey) {
        super(messageKey);
    }
}
