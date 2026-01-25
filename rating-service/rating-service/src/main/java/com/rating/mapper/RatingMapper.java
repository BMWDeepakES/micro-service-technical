package com.rating.mapper;

import com.rating.dto.RatingRequest;
import com.rating.dto.RatingResponse;
import com.rating.entity.Rating;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RatingMapper {

    public Rating requestToEntityMapper(RatingRequest request) {
        return Rating.builder()
                .ratingId(UUID.randomUUID().toString())
                .hotelId(request.getHotelId())
                .userId(request.getUserId())
                .feedback(request.getFeedback())
                .rating(request.getRating())
                .build();
    }

    public RatingResponse entityToResponseMapper(Rating request) {
        return RatingResponse.builder()
                .ratingId(request.getRatingId())
                .hotelId(request.getHotelId())
                .userId(request.getUserId())
                .feedback(request.getFeedback())
                .rating(request.getRating())
                .build();
    }
}
