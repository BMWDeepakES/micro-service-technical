//package com.userservice.model;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import jakarta.persistence.Entity;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "product")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Product {
//    private Long prodId;
//    private String name;
//    private double price;
//    @ManyToOne
//    @JoinColumn(name = "cat_id")
//    @JsonBackReference
//    private Category category;
//}
