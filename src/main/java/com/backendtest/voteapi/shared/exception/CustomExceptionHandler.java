package com.backendtest.voteapi.shared.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  public CustomExceptionHandler() {}

  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    String errorMessage = ex.getBindingResult().getFieldErrors().stream().map((fieldError) -> {
      return String.format("%s [%s]", fieldError.getDefaultMessage(), fieldError.getField());
    }).findFirst().orElse(ex.getBindingResult().getAllErrors().stream().map((validationError) -> {
      return String.format("%s [%s]", validationError.getDefaultMessage(), validationError.getObjectName());
    }).findFirst().orElse(ex.getBindingResult().toString()));
    return ResponseEntity.badRequest().body(new ApplicationError(HttpStatus.BAD_REQUEST, errorMessage, null));
  }

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ApplicationError(HttpStatus.NOT_FOUND, ex.getMessage(), null));
  }

  @ExceptionHandler({BadRequestException.class})
  public ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
    return ResponseEntity.badRequest().body(new ApplicationError(HttpStatus.BAD_REQUEST, ex.getMessage(), null));
  }

  @ExceptionHandler({EntityNotUpdateableException.class})
  public ResponseEntity<Object> handleEntityNotUpdateableException(EntityNotUpdateableException ex, WebRequest request) {
    return ResponseEntity.unprocessableEntity().body(new ApplicationError(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), null));
  }
}
