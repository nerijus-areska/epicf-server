package com.blank.epicfserver.controller;

import com.blank.epicfserver.HelloResource;
import com.blank.epicfserver.exception.ConstraintValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @ExceptionHandler({
            ConstraintValidationException.class,
    })
    public ResponseEntity<Object> handleConstraintException(ConstraintValidationException ex, WebRequest request) throws Exception {
        List<FieldError> fieldErrors = new ArrayList<>();
        fieldErrors.add(new FieldError("", ex.getField(), ex.getMessage()));
        return new ResponseEntity<>(getValidationErrorsResponse(fieldErrors, (ServletWebRequest) request),
                                    new HttpHeaders(),
                                    HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn("400 error", ex);
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getValidationErrorsResponse(ex.getFieldErrors(), (ServletWebRequest) request),
                                    headers,
                                    status);
    }

    private ValidationErrorsResponse getValidationErrorsResponse(List<FieldError> fieldErrors, ServletWebRequest request) {
        ValidationErrorsResponse response = new ValidationErrorsResponse(
                sdf.format(new Timestamp(System.currentTimeMillis())),
                request.getRequest().getRequestURI()
        );
        for(FieldError error : fieldErrors) {
                response.validationErrors.add( new ValidationError(
                    error.getField(),
                    error.getDefaultMessage()
            ));
        }
        return response;
    }

    public class ValidationErrorsResponse {
        public String status = "400";
        public String timestamp = "";
        public String error = "BadRequest";
        public String path = "";
        public List<ValidationError> validationErrors = new ArrayList<>();

        public ValidationErrorsResponse(String timestamp, String path) {
            this.timestamp = timestamp;
            this.path = path;
        }
    }

    public class ValidationError {
        public String field;
        public String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }
    }
}