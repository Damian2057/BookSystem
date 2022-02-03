package org.example.Exceptions.Model;

public class BookalreadyOrderedException extends OrderException {
    public BookalreadyOrderedException() {
        super("OrderedBook");
    }
}
