package org.example.Exceptions.Dao;

public class ConnectionException extends DataBaseException{
    public ConnectionException() {
        super("ConnectError");
    }
}
