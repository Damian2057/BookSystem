package org.example.Exceptions.Model;

public class WrongOrderIDException extends OrderException{
    public WrongOrderIDException() {
        super("WrongOrderID");
    }
}
