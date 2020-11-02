package com.example.covid.controller;

import com.example.covid.service.CovidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CovidController {

    private final CovidService covidService;

    @GetMapping("/date/{year}/{month}/{day}")
    public String getQuarantineTime(@PathVariable int year,
                                    @PathVariable int month,
                                    @PathVariable int day) {
        return covidService.getQuarantineTime(year, month, day);
    }
}
