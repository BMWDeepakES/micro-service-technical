package com.hotel.mapper;

import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;
import com.hotel.entity.Hotel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HotelTranformationMapper {

    public Hotel requestToHotelEntity(HotelRequest hotelRequest) {
        return Hotel.builder()
                .hotelId(UUID.randomUUID().toString())
                .location(hotelRequest.getLocation())
                .name(hotelRequest.getName())
                .about(hotelRequest.getAbout())
                .build();
    }

    public HotelResponse hotelEntityToResponse(Hotel hotel) {
        return HotelResponse.builder()
                .hotelId(hotel.getHotelId())
                .name(hotel.getName())
                .about(hotel.getAbout())
                .location(hotel.getLocation())
                .build();
    }
}
