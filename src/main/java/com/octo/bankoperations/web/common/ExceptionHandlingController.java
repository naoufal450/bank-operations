package com.octo.bankoperations.web.common;

import com.octo.bankoperations.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.ConnectException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<String> connectionException(ConnectException ex, WebRequest request) {
        return error(ex, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(ExecutionException.class)
    public ResponseEntity<String> outOfBoundException(ExecutionException ex, WebRequest request) {
        return error(ex, HttpStatus.UPGRADE_REQUIRED);
    }

    @ExceptionHandler(ObligationNotFoundException.class)
    public ResponseEntity<String> handleObligationNotFoundException(ObligationNotFoundException ex, WebRequest request) {
        return error(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleClientNotFoundException(ClientNotFoundException ex, WebRequest request) {
        return error(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompteNonExistantException.class)
    public ResponseEntity<String> handleCompteNotFoundException(CompteNonExistantException ex, WebRequest request) {
        return error(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<String> handleTransferNotFoundException(TransferNotFoundException ex, WebRequest request) {
        return error(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NegativeOrNullAmountException.class)
    public ResponseEntity<String> handleNegativeOrNullAmountException(NegativeOrNullAmountException ex, WebRequest request) {
        return error(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        StringBuilder sb = new StringBuilder();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(", ");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> error(final Exception exception, final HttpStatus httpStatus) {
        if (exception instanceof IndexOutOfBoundsException)
            return new ResponseEntity<>("", httpStatus);
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(message, httpStatus);
    }
}
