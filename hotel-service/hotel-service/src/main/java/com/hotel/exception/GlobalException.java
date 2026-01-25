package com.hotel.exception;

import com.hotel.exception.handlers.HotelNotFoundException;
import com.hotel.exception.handlers.HotelSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(HotelSaveException.class)
    public ResponseEntity<String> unableToSaveHotel(HotelSaveException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<String> hotelNotFound(HotelNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
