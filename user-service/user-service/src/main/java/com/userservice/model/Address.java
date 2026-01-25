//package com.userservice.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "address")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Address {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long addrId;
//    private String address;
//    private String landmark;
//    private String taluka;
//    private String district;
//    private String state;
//    private int pin;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @JsonBackReference
//    private User user;
//}
