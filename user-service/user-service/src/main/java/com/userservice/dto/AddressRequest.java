package com.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String address;
    private String landmark;
    private String taluka;
    private String district;
    private String state;
    private int pin;
}
