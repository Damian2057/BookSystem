package org.example.Exceptions.Model;

public class WrongClientIDException extends ClientException{
    public WrongClientIDException() {
        super("CannotFindClient");
    }
}
