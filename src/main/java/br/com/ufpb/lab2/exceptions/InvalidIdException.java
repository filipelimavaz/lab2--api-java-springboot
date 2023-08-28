package br.com.ufpb.lab2.exceptions;

public class InvalidIdException extends AbstractException{

    public InvalidIdException(String title, String details) {
        super(title, details);
    }
}
