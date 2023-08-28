package br.com.ufpb.lab2.exceptions;

public class NotRegisteredException extends AbstractException {

    public NotRegisteredException(String title, String details) {
        super(title, details);
    }
}
