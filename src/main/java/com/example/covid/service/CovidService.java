package com.example.covid.service;

import com.example.covid.model.TimeServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CovidService {
    private final RestTemplate restTemplate;

    //The main goal is to finish what we did in class. You should have a working GET request where I can pass in
    // a year, month, and day (of when someone was exposed to covid) (api/v1/date/{year}/{month}/{day}

    //This should then make a request to the timeserver to get current time,
    // and then calculate how many days left of quarantine. This number should be returned to the original API call.

    public String getQuarantineTime(int year, int month, int day) {
        //GET request to timeserver to get current time:
        TimeServerResponse responseCurrentTime = restTemplate.exchange("http://localhost:8080/api/v1/getTime",
                HttpMethod.GET,
                null,
                TimeServerResponse.class).getBody();
        //convert parameters to string:
        String exposureYear = Integer.toString(year);
        String exposureMonth = Integer.toString(month);
        String exposureDay = Integer.toString(day);
        String exposureTime = exposureYear + "-" + exposureMonth + "-" + exposureDay;
        String currentTime = responseCurrentTime.getLocalTime();

        //Parsing the date
        DateTimeFormatter dateFormat = new DateTimeFormatter("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'ZZZ");
        LocalDate dateOfExposure = LocalDate.parse(exposureTime, dateFormat);
        System.out.println(dateOfExposure);
        LocalDate dateNow = LocalDate.parse(currentTime);
        System.out.println(dateNow);

        //calculating number of days in between
        long daysLeft = ChronoUnit.DAYS.between(dateOfExposure, dateNow);

        //displaying the number of days
        return Long.toString(daysLeft);
    }
}
