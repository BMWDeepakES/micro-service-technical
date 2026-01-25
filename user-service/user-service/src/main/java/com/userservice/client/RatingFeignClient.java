package com.userservice.client;

import com.userservice.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingFeignClient {

    @GetMapping("/api/rating/user/rating/{id}")
    List<Rating> getUserRatingsById(@PathVariable String id);
}
