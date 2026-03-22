package com.example.HotelManagement.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class ApiService {
    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Float getData(Long hotelid) {
        return restClient.post()
                .uri("/rating/addratingbyhotelid/{hotelid}", hotelid)
                .retrieve()
                .toEntity(Float.class).getBody();
    }
}
