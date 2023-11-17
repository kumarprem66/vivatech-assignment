package com.placement.OtpAssignment.exception;


import jdk.management.jfr.RemoteRecordingStream;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<String> noHandlerFoundException(NoHandlerFoundException e){
        return new ResponseEntity<>("There is no Handler for this request", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity<>("Method argument is not valid", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OtpException.class)
    public ResponseEntity<String> otpNotMatchedException(OtpException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MyErrorDetails> otherException(Exception e, WebRequest req){

        MyErrorDetails myErrorDetails = new MyErrorDetails();
        myErrorDetails.setTimestamp(LocalDateTime.now());
        myErrorDetails.setMessage(e.getMessage());
        myErrorDetails.setDetails(req.getDescription(false));

        return new ResponseEntity<>(myErrorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
