package org.example.Exceptions.Model;

import org.example.Exceptions.LocalizedRunTimeException;

public class OrderException extends LocalizedRunTimeException {
    public OrderException(String messageKey) {
        super(messageKey);
    }
}
