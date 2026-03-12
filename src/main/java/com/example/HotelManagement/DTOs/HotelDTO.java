package com.example.HotelManagement.DTOs;

import lombok.Data;

@Data
public class HotelDTO {

    private String name;
    private String address;
    private String city;
    private float rating;
    private int postalCode;
    private boolean isAvailable;
}
