package br.com.ufpb.lab2.exceptions;

import lombok.Data;

@Data
public abstract class AbstractException extends RuntimeException{

    private String title;
    private String details;

    public AbstractException(String title, String details) {
        this.details = details;
        this.title = title;
    }
}