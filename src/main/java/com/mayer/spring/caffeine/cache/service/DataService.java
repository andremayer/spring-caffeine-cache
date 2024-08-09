package com.mayer.spring.caffeine.cache.service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Cacheable("dataCache")
    public String getData(String key) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Data for " + key;
    }
    
}