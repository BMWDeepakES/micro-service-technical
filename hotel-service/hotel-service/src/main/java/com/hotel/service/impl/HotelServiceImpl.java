package com.hotel.service.impl;

import com.hotel.appconstant.ApplicationConstant;
import com.hotel.dto.HotelRequest;
import com.hotel.dto.HotelResponse;
import com.hotel.entity.Hotel;
import com.hotel.exception.handlers.HotelNotFoundException;
import com.hotel.exception.handlers.HotelSaveException;
import com.hotel.mapper.HotelTranformationMapper;
import com.hotel.repository.HotelRepository;
import com.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;
    private final HotelTranformationMapper mapper;
    @Override
    public HotelResponse registerHotel(HotelRequest request) {
        try {
            Hotel hotelRequestEntity = mapper.requestToHotelEntity(request);
            Hotel hotelEntity = repository.save(hotelRequestEntity);
            if(!ObjectUtils.isEmpty(hotelEntity)) {
                return mapper.hotelEntityToResponse(hotelEntity);
            } else {
                throw new HotelSaveException("Uanble to Register Hotel. try again.....");
            }
        } catch (Exception e ) {
          log.error("Exception in Register error {}",e.getMessage());
            throw new HotelSaveException("Uanble to Register Hotel. try again.....");
        }
    }

    @Override
    public List<HotelResponse> getHotels() {
        log.info("Finding all Hotels");
        try {
            List<Hotel> hotelEntities = repository.findAll();
            if(!CollectionUtils.isEmpty(hotelEntities)) {
                return hotelEntities.stream().map(mapper::hotelEntityToResponse).collect(Collectors.toList());
            } else {
                throw new HotelNotFoundException(ApplicationConstant.HOTEL_NOT_FOUNT_ERROR_MESSAGE);
            }
        } catch (Exception e) {
            throw new HotelNotFoundException(ApplicationConstant.HOTEL_NOT_FOUNT_ERROR_MESSAGE);
        }
    }

    @Override
    public HotelResponse getHotel(String id) {
        try {
            Optional<Hotel> hotelEntity = repository.findById(id);
            if (hotelEntity.isPresent()){
                return mapper.hotelEntityToResponse(hotelEntity.get());
            } else {
                throw new HotelNotFoundException(String.format(ApplicationConstant.HOTEL_NOT_FOUNT_ID_ERROR_MESSAGE,id));
            }
        } catch (Exception e) {
            throw new HotelNotFoundException(String.format(ApplicationConstant.HOTEL_NOT_FOUNT_ID_ERROR_MESSAGE,id));
        }
    }
}
