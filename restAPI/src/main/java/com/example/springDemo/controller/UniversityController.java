package com.example.springDemo.controller;

import com.example.springDemo.entity.University;
import com.example.springDemo.service.UniversityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }


    @GetMapping
    public List<University> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    @PostMapping
    public List<University> getUniversitiesByCountries(@RequestBody List<String> countries) throws ExecutionException, InterruptedException {
        List<CompletableFuture<List<University>>> futures = countries.stream()
                .map(universityService::getUniversitiesByCountry)
                .toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        CompletableFuture<List<List<University>>> allResults = allFutures.thenApply(v -> futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList()));

        return allResults.get().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

}
