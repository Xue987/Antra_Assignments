package com.example.springDemo.service;
import com.example.springDemo.entity.University;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface UniversityService {
    List<University> getAllUniversities();
    CompletableFuture<List<University>> getUniversitiesByCountry(String country);

}
