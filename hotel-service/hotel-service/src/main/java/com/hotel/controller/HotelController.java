package com.hotel.controller;

import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;
import com.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {


    private final HotelService hotelService;
    @PostMapping()
    public ResponseEntity<HotelResponse> registerHotel(@RequestBody HotelRequest request) {
       log.info("Hotel Register Request {}",request.toString());
      return new ResponseEntity<>(hotelService.registerHotel(request), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<HotelResponse>> getAllHotels() {
        log.info("Get All Hotels");
        return new ResponseEntity<>(hotelService.getHotels(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotel(@PathVariable String id) {
        log.info("Get Hotel by id");
        HotelResponse hotel = hotelService.getHotel(id);
        return new ResponseEntity<>(hotelService.getHotel(id),HttpStatus.OK);
    }
}
