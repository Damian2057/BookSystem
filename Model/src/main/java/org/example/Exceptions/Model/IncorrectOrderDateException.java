package org.example.Exceptions.Model;

public class IncorrectOrderDateException extends OrderException{
    public IncorrectOrderDateException() {
        super("incorrectOrderDate");
    }
}
