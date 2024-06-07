package com.example.RestAPI_Universities.service;

import com.example.RestAPI_Universities.enity.University;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface UniversityService {

    University[] getAll();
    List<University> getByCountries(List<String> countries);
}