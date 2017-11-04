package io.tchepannou.academy.user.controller;

import io.tchepannou.academy.user.client.dto.ErrorDto;
import io.tchepannou.academy.user.client.ErrorResponse;
import io.tchepannou.academy.user.exception.BusinessError;
import io.tchepannou.academy.user.exception.BusinessException;
import io.tchepannou.academy.user.exception.InvalidRequestException;
import io.tchepannou.academy.user.exception.NotFoundException;
import io.tchepannou.academy.user.exception.SessionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler extends BaseController{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final ErrorResponse response = init(new ErrorResponse());
        final List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError validationError : validationErrors){
            ErrorDto error;
            if (validationError instanceof FieldError){
                final FieldError fieldError = (FieldError)validationError;
                error = createError(
                        BusinessError.BAD_REQUEST.getCode(),
                        String.format("%s=%s - %s",
                                fieldError.getField(),
                                fieldError.getRejectedValue(),
                                fieldError.getDefaultMessage())
                );
            } else {
                error = createError(BusinessError.BAD_REQUEST.getCode(), validationError.getDefaultMessage());
            }

            response.getErrors().add(error);
        }

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final NotFoundException ex) {
        final ErrorResponse response = createErrorResponse(ex);
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SessionException.class)
    public ResponseEntity<ErrorResponse> handleSessionException(final SessionException ex) {
        final ErrorResponse response = createErrorResponse(ex);
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final InvalidRequestException ex) {
        final ErrorResponse response = createErrorResponse(ex);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException ex) {
        final ErrorResponse response = createErrorResponse(ex);
        return new ResponseEntity(response, HttpStatus.CONFLICT);
    }


    private ErrorResponse createErrorResponse(final BusinessException ex){
        final ErrorResponse response = init(new ErrorResponse());
        response.getErrors().add(createError(ex.getErrorCode()));
        return response;
    }

    private ErrorDto createError(BusinessError code){
        return createError(code.getCode(), code.getDescription());
    }

    private ErrorDto createError(String code, String description){
        final ErrorDto error = new ErrorDto();
        error.setCode(code);
        error.setDescription(description);
        return error;
    }

}
