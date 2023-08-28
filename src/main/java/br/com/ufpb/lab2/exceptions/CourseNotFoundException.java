package br.com.ufpb.lab2.exceptions;

public class CourseNotFoundException extends AbstractException {

    public CourseNotFoundException(String title, String details) {
        super(title, details);
    }
}
