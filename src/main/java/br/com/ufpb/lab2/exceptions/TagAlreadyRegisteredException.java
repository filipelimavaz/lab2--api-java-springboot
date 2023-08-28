package br.com.ufpb.lab2.exceptions;

public class TagAlreadyRegisteredException extends AbstractException {

    public TagAlreadyRegisteredException(String title, String details) {
        super(title, details);
    }
}
