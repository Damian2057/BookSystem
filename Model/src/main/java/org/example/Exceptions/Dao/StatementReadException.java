package org.example.Exceptions.Dao;

public class StatementReadException extends DataBaseException{
    public StatementReadException() {
        super("SQLSTATEMENTMISS");
    }
}
