package com.example.RestAPI_Universities.controller;


import com.example.RestAPI_Universities.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/universities")
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUniversities() {
        return new ResponseEntity<>(universityService.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = "countries")
    public ResponseEntity<?> getUniversitiesByCountries(@RequestParam("countries") List<String> countries) {
        return new ResponseEntity<>(universityService.getByCountries(countries), HttpStatus.OK);
    }
}