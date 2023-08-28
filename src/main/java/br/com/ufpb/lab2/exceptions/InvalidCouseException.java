package br.com.ufpb.lab2.exceptions;

public class InvalidCouseException extends AbstractException{

    public InvalidCouseException(String title, String details) {
        super(title, details);
    }
}
