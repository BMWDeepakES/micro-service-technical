package com.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank(message = "Fullname is required")
    private String name;
//    @NotBlank(message = "User Type is required")
//    private String userType;
//    @NotBlank(message = "Password is required")
//    private String password;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
//    @NotBlank(message = "Phone is required")
//    private String phone;
//    @NotBlank(message = "Gender is required")
//    private String gender;
//    private List<AddressRequest> addresses;
    @NotBlank(message = "About is not blank")
    private String about;
}
