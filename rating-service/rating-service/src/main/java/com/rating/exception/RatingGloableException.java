package com.rating.exception;

import com.rating.exception.handler.RatingNotFound;
import com.rating.exception.handler.UnbaleToSaveRating;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RatingGloableException {

    @ExceptionHandler(UnbaleToSaveRating.class)
    public ResponseEntity<String> unableToSaveRating(UnbaleToSaveRating ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(RatingNotFound.class)
    public ResponseEntity<String> ratingNotVailable(RatingNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
