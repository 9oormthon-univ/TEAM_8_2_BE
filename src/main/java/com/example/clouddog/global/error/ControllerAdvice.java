package com.example.clouddog.global.error;

import com.example.clouddog.global.error.dto.ErrorResponse;
import com.example.clouddog.image.exception.NotFoundImageException;
import com.example.clouddog.member.exception.ExistsFriendShipException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({
            NotFoundImageException.class,
            ExistsFriendShipException.class
    })
    public ResponseEntity<ErrorResponse> handleInvalidData(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
