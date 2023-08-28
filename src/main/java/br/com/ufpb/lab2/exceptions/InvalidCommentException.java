package br.com.ufpb.lab2.exceptions;

public class InvalidCommentException extends AbstractException {

    public InvalidCommentException(String title, String details) {
        super(title, details);
    }
}
