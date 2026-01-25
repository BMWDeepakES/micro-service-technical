package com.rating.service;

import com.rating.dto.RatingRequest;
import com.rating.dto.RatingResponse;

import java.util.List;

public interface RatingService {
    RatingResponse registerRating(RatingRequest request);

    List<RatingResponse> getHotelRatings(String id);

    List<RatingResponse> getUserRatings(String id);

    List<RatingResponse> getRatings();

    RatingResponse getRating(String id);

}
