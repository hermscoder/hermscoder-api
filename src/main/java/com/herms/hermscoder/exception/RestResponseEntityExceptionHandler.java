package com.herms.hermscoder.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionSystemException.class)
    private ResponseEntity<ApiError> handleConstraintViolation(Exception ex) {
        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException) {
            ConstraintViolationException exception = ((ConstraintViolationException) cause);

            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
            apiError.setMessage("Validation errors \n");
//            apiError.addValidationErrors(exception.getConstraintViolations());
            return buildResponseEntity(apiError);
        }

        return buildResponseEntity(null);
    }

//    @ExceptionHandler(UsernameAlreadyExistException.class)
//    private ResponseEntity<ApiError> handleUsernameAlreadyExistException(UsernameAlreadyExistException ex) {
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
//        apiError.setMessage(ex.getMessage());
//        apiError.addSubError(ex.getField(), ex.getMessage());
//        return buildResponseEntity(apiError);
//    }

//    @ExceptionHandler(IncorrectUsernameOrPasswordException.class)
//    private ResponseEntity<ApiError> handleBadCredentialsException(IncorrectUsernameOrPasswordException ex) {
//        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }

    @ExceptionHandler(OutdatedTokenException.class)
    private ResponseEntity<ApiError> handleException(OutdatedTokenException ex) {
        ex.printStackTrace();
        ApiError apiError = new ApiError(ex.getHttpStatus());
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ApiError> handleException(Exception ex) {
        ex.printStackTrace();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

}