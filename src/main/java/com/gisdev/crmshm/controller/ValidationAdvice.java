package com.gisdev.crmshm.controller;

import com.gisdev.crmshm.dto.general.ResponseError;
import com.gisdev.crmshm.exception.BadRequestException;
import com.gisdev.crmshm.exception.ConflictException;
import com.gisdev.crmshm.exception.NotFoundException;
import com.gisdev.crmshm.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = fieldErrors.get(0).getDefaultMessage();

        return ResponseEntity.badRequest().body(new ResponseError(errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> notFoundException(NotFoundException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> badRequestException(BadRequestException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<?> conflictException(ConflictException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerError(Exception ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError("Gabim i brendshem i serverit!"), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<?> unauthorizedException(UnauthorizedException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), UNAUTHORIZED);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<?> missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), BAD_REQUEST);
    }
}
