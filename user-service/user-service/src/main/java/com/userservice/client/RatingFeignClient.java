package com.userservice.client;

import com.userservice.model.Rating;
import com.userservice.service.impl.RatingFeignFallback;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")//, fallback = RatingFeignFallback.class)
public interface RatingFeignClient {

    @GetMapping("/api/rating/user/rating/{id}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    List<Rating> getUserRatingsById(@PathVariable String id);
}
