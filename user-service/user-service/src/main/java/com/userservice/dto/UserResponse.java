package com.userservice.dto;

import com.userservice.model.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String name;
//    private String userType;
//    private String password;
    private String email;
//    private String phone;
//    private String gender;
    private String about;
    private List<Rating> ratings = new ArrayList<>();
}
