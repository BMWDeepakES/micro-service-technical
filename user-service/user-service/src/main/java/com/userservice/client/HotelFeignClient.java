package com.userservice.client;

import com.userservice.dto.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelFeignClient {

    @GetMapping("/api/hotels/{id}")
    Hotel getHotel(@PathVariable String id);
}
