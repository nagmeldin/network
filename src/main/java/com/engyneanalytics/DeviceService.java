package com.engyneanalytics;


import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Singleton
public class DeviceService {

    public String getRandomBrand(){

        List<String> brands = Arrays.asList("Cisco", "Juniper", "Casa", "Arris", "D-link", "Encore", "Google", "Microlink", "Motorala", "Mercury") ;
        Random  random = new Random();

        return brands.get(random.nextInt(brands.size()));
    }
}
