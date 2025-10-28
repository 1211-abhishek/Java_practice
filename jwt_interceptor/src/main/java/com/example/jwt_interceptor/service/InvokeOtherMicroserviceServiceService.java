package com.example.jwt_interceptor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InvokeOtherMicroserviceServiceService {

    @Autowired
    private RestTemplate restTemplate;

    public void invokeRestTemplateService(){

        String url = "http://localhost:4581/band/";

        BandDto requestBody = new BandDto();
        requestBody.setBands(Bands.BAND7);
        requestBody.setPaidLeaves(12.0f);
        requestBody.setSickLeaves(6.0f);
        requestBody.setCasualLeaves(5.0f);
        requestBody.setUnpaidLeaves(2.0f);
        requestBody.setParentalLeaves(3.0f);

        HttpEntity<BandDto> entity = new HttpEntity<>(requestBody);

        ResponseEntity<BandDto> response = restTemplate.exchange(url, HttpMethod.POST, entity, BandDto.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            BandDto responseBody = response.getBody();
            System.out.println("Band added successfully: " + responseBody);
        } else {
            System.out.println("Failed with status: " + response.getStatusCode());
        }
    }
}
