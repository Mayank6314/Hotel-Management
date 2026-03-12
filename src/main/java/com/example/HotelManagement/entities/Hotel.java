package com.example.HotelManagement.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String city;
    private float rating;
    private int postalCode;

    @Column(name = "available")
    private boolean isAvailable;
}
