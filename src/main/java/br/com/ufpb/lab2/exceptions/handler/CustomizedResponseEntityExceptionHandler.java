package br.com.ufpb.lab2.exceptions.handler;

import br.com.ufpb.lab2.dtos.ErrorDetailsDTO;
import br.com.ufpb.lab2.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static String URI = "https://localhost:8080/api/courses";

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorDetailsDTO> CourseNotFoundException(CourseNotFoundException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EmptyDatabaseException.class)
    public ResponseEntity<ErrorDetailsDTO> EmptyDatabaseException(EmptyDatabaseException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidCommentException.class)
    public ResponseEntity<ErrorDetailsDTO> InvalidCommentException(InvalidCommentException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidCouseException.class)
    public ResponseEntity<ErrorDetailsDTO> InvalidCouseException(InvalidCouseException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidFieldException.class)
    public ResponseEntity<ErrorDetailsDTO> InvalidFieldException(InvalidFieldException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<ErrorDetailsDTO> InvalidIdException(InvalidIdException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidRateException.class)
    public ResponseEntity<ErrorDetailsDTO> InvalidRateException(InvalidRateException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidTagException.class)
    public ResponseEntity<ErrorDetailsDTO> InvalidTagException(InvalidTagException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotRegisteredException.class)
    public ResponseEntity<ErrorDetailsDTO> NotRegisteredException(NotRegisteredException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(TagAlreadyRegisteredException.class)
    public ResponseEntity<ErrorDetailsDTO> TagAlreadyRegisteredException(TagAlreadyRegisteredException exception) {
        ErrorDetailsDTO error = new ErrorDetailsDTO();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTitle(exception.getTitle());
        error.setType(URI);
        error.setDetail(exception.getDetails());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
