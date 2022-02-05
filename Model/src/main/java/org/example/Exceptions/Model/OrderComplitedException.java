package org.example.Exceptions.Model;

public class OrderComplitedException extends OrderException{
    public OrderComplitedException() {
        super("OrderIsComplited");
    }
}
