package com.userservice.service.impl;

import com.userservice.client.RatingFeignClient;
import com.userservice.model.Rating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RatingFeignFallback implements RatingFeignClient {

    @Override
    public List<Rating> getUserRatingsById(String id) {
        log.warn("Rating service is DOWN. Returning fallback for user {}", id);

        Rating rating = Rating.builder()
                .ratingId("fallback")
                .userId(id)
                .hotelId("NA")
                .rating(0)
                .feedback("Rating service unavailable")
                .build();

        return List.of(rating);
    }
}
