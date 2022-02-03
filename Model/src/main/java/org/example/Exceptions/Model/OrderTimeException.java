package org.example.Exceptions.Model;

public class OrderTimeException extends OrderException{
    public OrderTimeException() {
        super("OrderInProgress");
    }
}
