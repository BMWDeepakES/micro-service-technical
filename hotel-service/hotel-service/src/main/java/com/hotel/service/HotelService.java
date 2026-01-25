package com.hotel.service;

import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;

import java.util.List;

public interface HotelService {
    HotelResponse registerHotel(HotelRequest request);

    List<HotelResponse> getHotels();

    HotelResponse getHotel(String id);

}
