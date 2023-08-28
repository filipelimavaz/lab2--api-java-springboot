package br.com.ufpb.lab2.exceptions;

public class EmptyDatabaseException extends AbstractException{

    public EmptyDatabaseException(String title, String details) {
        super(title, details);
    }
}
