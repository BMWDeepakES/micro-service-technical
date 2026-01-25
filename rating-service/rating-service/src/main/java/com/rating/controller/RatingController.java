package com.rating.controller;

import com.rating.dto.RatingRequest;
import com.rating.dto.RatingResponse;
import com.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@Slf4j
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    @PostMapping
    public ResponseEntity<RatingResponse> addRating(@RequestBody RatingRequest request) {
        log.info("Request received for save {}",request.toString());
       return new ResponseEntity<>(ratingService.registerRating(request), HttpStatus.CREATED);
    }

    @GetMapping("/hotel/rating/{id}")
    public ResponseEntity<List<RatingResponse>> getHotelRatingsById(@PathVariable String id) {
        return new ResponseEntity<>(ratingService.getHotelRatings(id), HttpStatus.OK);
    }

    @GetMapping("/user/rating/{id}")
    public ResponseEntity<List<RatingResponse>> getUserRatingsById(@PathVariable String id) {
        return new ResponseEntity<>(ratingService.getUserRatings(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RatingResponse>> getAllRatings() {
        return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingResponse> getRatingsById(@PathVariable String id) {
        return new ResponseEntity<>(ratingService.getRating(id), HttpStatus.OK);
    }

}
