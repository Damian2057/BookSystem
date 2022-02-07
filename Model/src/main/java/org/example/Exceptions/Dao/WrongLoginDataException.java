package org.example.Exceptions.Dao;

public class WrongLoginDataException extends DataBaseException{
    public WrongLoginDataException() {
        super("wrongloginData");
    }
}
