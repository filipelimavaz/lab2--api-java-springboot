package br.com.ufpb.lab2.exceptions;

public class InvalidTagException extends AbstractException {

    public InvalidTagException(String title, String details) {
        super(title, details);
    }
}
