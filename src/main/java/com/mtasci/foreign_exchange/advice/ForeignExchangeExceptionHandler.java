package com.mtasci.foreign_exchange.advice;

import com.mtasci.foreign_exchange.contracts.ResponseBuilder;
import com.mtasci.foreign_exchange.contracts.BaseResponse;
import com.mtasci.foreign_exchange.exceptions.ForeignExchangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.mtasci.foreign_exchange.enums.ApplicationResponseCode.CODE_400;
import static com.mtasci.foreign_exchange.enums.ApplicationResponseCode.CODE_500;

@RestControllerAdvice
public class ForeignExchangeExceptionHandler {

    @ExceptionHandler(ForeignExchangeException.class)
    public ResponseEntity<BaseResponse> handleCustomException(ForeignExchangeException e) {
        return new ResponseEntity<>(ResponseBuilder.error(e.getCode(), e.getMessage(), e.getDetailMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<BaseResponse> handleMethodArgumentNotValid(Exception ex) {

        final String errorMessage = generateErrorMessage(ex);
        return new ResponseEntity<>(ResponseBuilder.error(CODE_400.getCode(), CODE_400.getDescription(), errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseResponse> handleException(Exception ex) {
        return new ResponseEntity<>(
                ResponseBuilder.error(CODE_500.getCode(), CODE_500.getDescription(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String generateErrorMessage(Exception ex) {

        if (ex instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValid((MethodArgumentNotValidException) ex);
        } else if (ex instanceof MethodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex);
        } else {
            return "An unknown error occurred";
        }

    }

    private String handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        StringBuilder errorBuilder = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> errorBuilder.append(error.getDefaultMessage()).append(" "));
        return errorBuilder.toString().trim();
    }

    private String handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return "Invalid field: " + ex.getName();
    }

}
