package com.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "hotels")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hotel {
    @Id
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
