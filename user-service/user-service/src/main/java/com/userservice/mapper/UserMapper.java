package com.userservice.mapper;

import com.userservice.dto.AddressRequest;
import com.userservice.dto.UserRatingResponse;
import com.userservice.dto.UserRequest;
import com.userservice.dto.UserResponse;
//import com.userservice.model.Address;
import com.userservice.model.Rating;
import com.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserMapper {

    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
//                .userType(user.getUserType())
//                .password(user.getPassword())
                .email(user.getEmail())
//                .phone(user.getPhone())
//                .gender(user.getGender())
                .about(user.getAbout())
//                .ratings(user.getRatings())
                .build();
    }

    public User mapUserRequestToUser(UserRequest request) {
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
//                .password(request.getPassword())
//                .userType(request.getUserType())
//                .phone(request.getPhone())
//                .gender(request.getGender())
                .about(request.getAbout())
                .build();

//        List<Address> addressList = request.getAddresses().stream().map(addr->addressBuilder(addr,user)).toList();
//        user.setAddresses(addressList);
        return user;
    }

//    private Address addressBuilder(AddressRequest request,User user) {
//        return Address.builder()
//                .address(request.getAddress())
//                .taluka(request.getTaluka())
//                .district(request.getDistrict())
//                .state(request.getState())
//                .pin(request.getPin())
////                .user(user)
//                .build();
//    }

    public UserResponse addRatingByEntity(User user, List<Rating> rating) {
        UserResponse response = mapUserToUserResponse(user);
        response.setRatings(rating);
        return response;
    }

    public UserRatingResponse ratingReponseMapper(User user, List<Rating> ratings) {
           return UserRatingResponse.builder()
                   .userName(user.getName())
                   .hotelName(ratings.get(0).getHotel().getName())
                   .ratings(ratings.get(0).getRating())
                   .build();
    }
}
