package com.example.covid.service;

import com.example.covid.model.TimeServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Service
@RequiredArgsConstructor
public class CovidService {
    private final RestTemplate restTemplate;

    public String getQuarantineTime(int year, int month, int day) {
        TimeServerResponse timeServerResponse = restTemplate.exchange("http://localhost:8080/api/v1/getTime",
                HttpMethod.GET,
                null,
                TimeServerResponse.class).getBody();
        return timeServerResponse.getLocalTime();
    }
}
