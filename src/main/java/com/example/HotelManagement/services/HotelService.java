package com.example.HotelManagement.services;

import com.example.HotelManagement.DTOs.HotelDTO;
import com.example.HotelManagement.DTOs.UpdateHotelAddressDTO;
import com.example.HotelManagement.Repository.HotelRepository;
import com.example.HotelManagement.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    public Hotel saveHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setCity(hotelDTO.getCity());
        hotel.setPostalCode(hotelDTO.getPostalCode());
        hotel.setRating(hotelDTO.getRating());
        hotel.setAvailable(hotelDTO.isAvailable());

        hotelRepository.save(hotel);
        return hotel;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotelBox = hotelRepository.findById(id);
        return hotelBox.orElse(null);

    }

    public Hotel updateHotel(HotelDTO hotelDTO, Long id) {
        Hotel hotel = getHotelById(id);
        if(hotel != null){
            hotel.setName(hotelDTO.getName());
            hotel.setAddress(hotelDTO.getAddress());
            hotel.setCity(hotelDTO.getCity());
            hotel.setPostalCode(hotelDTO.getPostalCode());
            hotel.setRating(hotelDTO.getRating());
            hotel.setAvailable(hotelDTO.isAvailable());
            hotelRepository.save(hotel);
            return hotel;
        }
        else return hotel;
    }

    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        if(hotel != null){
            hotelRepository.deleteById(id);
        }
    }

    public Hotel updateHotelAddress(UpdateHotelAddressDTO updateHotelAddressDTO, Long id) {
        Hotel hotel = getHotelById(id);
        if(hotel != null){
            hotel.setAddress(updateHotelAddressDTO.getAddress());
            hotel.setCity(updateHotelAddressDTO.getCity());
            hotel.setPostalCode(updateHotelAddressDTO.getPostalCode());
            hotelRepository.save(hotel);
            return hotel;
        }
        else return hotel;
    }
}
