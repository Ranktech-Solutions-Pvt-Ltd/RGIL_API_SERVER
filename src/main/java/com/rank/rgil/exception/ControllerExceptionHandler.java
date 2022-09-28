package com.rank.rgil.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(CCMSRestException.class)
  public ResponseEntity<ErrorResponse> resourceNotFoundException(CCMSRestException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse();
    message.setErrorCode(ex.getErrorCode());
    message.setErrorMessage(ex.getErrorMessage());
    
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.I_AM_A_TEAPOT);
  }

  // @ExceptionHandler(Exception.class)
  // public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
  //   ErrorMessage message = new ErrorMessage(
  //       HttpStatus.INTERNAL_SERVER_ERROR.value(),
  //       new Date(),
  //       ex.getMessage(),
  //       request.getDescription(false));
    
  //   return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  // }
}
