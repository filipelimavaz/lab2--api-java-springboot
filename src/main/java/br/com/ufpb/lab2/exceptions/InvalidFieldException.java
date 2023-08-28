package br.com.ufpb.lab2.exceptions;

public class InvalidFieldException extends AbstractException {

    public InvalidFieldException(String title, String details) {
        super(title, details);
    }
}
