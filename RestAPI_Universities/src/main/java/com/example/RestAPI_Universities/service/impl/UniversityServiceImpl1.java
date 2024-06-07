package com.example.RestAPI_Universities.service.impl;

import com.example.RestAPI_Universities.enity.University;
import com.example.RestAPI_Universities.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class UniversityServiceImpl1 implements UniversityService {

    private final RestTemplate rt;
    private final ExecutorService pool;

    @Value("${university-url}")
    private String url;

    @Autowired
    public UniversityServiceImpl1(RestTemplate rt, ExecutorService pool) {
        this.rt = rt;
        this.pool = pool;
    }

    @Override
    public University[] getAll() {
        return rt.getForObject(url, University[].class);
    }

    @Override
    public List<University> getByCountries(List<String> countries) {
        List<CompletableFuture<University[]>> futures = new ArrayList<>();
        countries.forEach(c ->
                futures.add(
                        CompletableFuture.supplyAsync(() -> rt.getForObject(url + "?country=" + c, University[].class), pool)
                )
        );
        List<University> res = new ArrayList<>();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenAccept(Void ->
                        futures.forEach(cf -> {
                            University[] data = cf.join();
                            for(University u: cf.join()) {
                                res.add(u);
                            }
                        })
                ).join();
        return res;
    }
}