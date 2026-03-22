package com.example.HotelManagement.services;

import com.example.HotelManagement.Communicator.RestClientConfig;
import com.example.HotelManagement.DTOs.HotelDTO;
import com.example.HotelManagement.DTOs.UpdateHotelAddressDTO;
import com.example.HotelManagement.Repository.HotelRepository;
import com.example.HotelManagement.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ApiService apiService;

    public ResponseEntity<Hotel> saveHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setCity(hotelDTO.getCity());
        hotel.setPostalCode(hotelDTO.getPostalCode());
        hotel.setRating(hotelDTO.getRating());
        hotel.setAvailable(hotelDTO.isAvailable());

        hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    public List<Hotel> getAllHotels() {

        return hotelRepository.findAll();
    }

//    public ResponseEntity<Hotel> getHotelById(Long id) {
//        Optional<Hotel> hotelBox = hotelRepository.findById(id);
//
//
//        if(hotelBox.isPresent()){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//    }

    public ResponseEntity<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    Float rating = apiService.getData(id);
                    hotel.setRating(rating);

                    return new ResponseEntity<>(hotel, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    public Hotel updateHotel(HotelDTO hotelDTO, Long id) {
        Hotel hotel = getHotelById(id).getBody();
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
        Hotel hotel = getHotelById(id).getBody();
        if(hotel != null){
            hotelRepository.deleteById(id);
        }
    }

    public Hotel updateHotelAddress(UpdateHotelAddressDTO updateHotelAddressDTO, Long id) {
        Hotel hotel = getHotelById(id).getBody();
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
