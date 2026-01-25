package com.rating.service.impl;

import com.rating.dto.RatingRequest;
import com.rating.dto.RatingResponse;
import com.rating.entity.Rating;
import com.rating.exception.handler.RatingNotFound;
import com.rating.exception.handler.UnbaleToSaveRating;
import com.rating.mapper.RatingMapper;
import com.rating.repository.RatingRepository;
import com.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper mapper;
    @Override
    public RatingResponse registerRating(RatingRequest request) {
        try {
            Rating rating = mapper.requestToEntityMapper(request);
            Rating ratingSaved = ratingRepository.save(rating);
            if(!ObjectUtils.isEmpty(ratingSaved)) {
            return mapper.entityToResponseMapper(ratingSaved);
            } else {
                throw new UnbaleToSaveRating("unable to save request "+request.toString());
            }
        } catch (Exception e) {
            log.error("Exception in saved "+e.getMessage());
            throw new UnbaleToSaveRating("unable to save request "+request.toString());
        }
    }

    @Override
    public List<RatingResponse> getHotelRatings(String id) {
        try {
            List<Rating> hotelRatings = ratingRepository.findByHotelId(id);
            if(!CollectionUtils.isEmpty(hotelRatings)) {
                return hotelRatings.stream().map(mapper::entityToResponseMapper).collect(Collectors.toList());
            } else {
                throw new RatingNotFound("Rating not found with Hotel id "+id);
            }
        } catch (Exception e) {
            throw new RatingNotFound("Rating not found with Hotel id "+id);
        }
    }

    @Override
    public List<RatingResponse> getUserRatings(String id) {
        try {
            List<Rating> userRatings = ratingRepository.findByUserId(id);
            if(!CollectionUtils.isEmpty(userRatings)) {
                return userRatings.stream().map(mapper::entityToResponseMapper).collect(Collectors.toList());
            } else {
                throw new RatingNotFound("Rating not found with User id "+id);
            }
        } catch (Exception e) {
            throw new RatingNotFound("Rating not found with User id "+id);
        }
    }

    @Override
    public List<RatingResponse> getRatings() {
        try {
            List<Rating> ratings = ratingRepository.findAll();
            if(!CollectionUtils.isEmpty(ratings)) {
                return ratings.stream().map(mapper::entityToResponseMapper).collect(Collectors.toList());
            } else {
                throw new RatingNotFound("Rating not found");
            }
        } catch (Exception e) {
            throw new RatingNotFound("Rating not found");
        }
    }

    @Override
    public RatingResponse getRating(String id) {
        try {
            Optional<Rating> rating = ratingRepository.findById(id);
            if(rating.isPresent()) {
                return mapper.entityToResponseMapper(rating.get());
            } else {
                throw new RatingNotFound("Rating not found with id "+id);
            }
        } catch (Exception e) {
            throw new RatingNotFound("Rating not found with id "+id);
        }
    }
}
