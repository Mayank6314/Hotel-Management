package com.example.HotelManagement.controller;

import com.example.HotelManagement.DTOs.HotelDTO;
import com.example.HotelManagement.DTOs.UpdateHotelAddressDTO;
import com.example.HotelManagement.entities.Hotel;
import com.example.HotelManagement.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/createhotel")
    public Hotel createHotel(@RequestBody HotelDTO hotelDTO){
       return hotelService.saveHotel(hotelDTO);
    }

    @GetMapping("/getallhotels")
    public List<Hotel> getAllHotels(){
       return hotelService.getAllHotels();
    }

    @GetMapping("/gethotel/{id}")
    public Hotel getHotelById(@PathVariable Long id){
        return hotelService.getHotelById(id);
    }

    @PutMapping("/updatehotel/{id}")
    public Hotel updateHotel(@RequestBody HotelDTO hotelDTO, @PathVariable Long id){
       return hotelService.updateHotel(hotelDTO, id);
    }

    @DeleteMapping("/deletehotel/{id}")
    public void deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
    }

    @PutMapping("/updatehotelAddress/{id}")
    public Hotel updateHotelAddress(@RequestBody UpdateHotelAddressDTO updateHotelAddressDTO, @PathVariable Long id){
        return hotelService.updateHotelAddress(updateHotelAddressDTO,id);
    }
}
