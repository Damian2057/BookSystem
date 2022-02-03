package org.example.Exceptions.Model;

public class WrongBookIDException extends  BookException{
    public WrongBookIDException() {
        super("CannotFindBook");
    }
}
