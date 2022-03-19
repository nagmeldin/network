package com.engyneanalytics;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@MicronautTest
class DevicestoreTest {

    List<String> brands = Arrays.asList("Cisco", "Juniper", "Casa", "Arris", "D-link", "Encore", "Google", "Microlink", "Motorala", "Mercury") ;

    @Inject
    MakerRepository makerRepository;
    @Inject
    DeviceRepository deviceRepository;

    @Test
    void testMaker() {
        Assertions.assertEquals(1,
                makerRepository.count()
        );
    }

    @Test
    void testDevice() {
        Assertions.assertEquals(10,
                deviceRepository.count()
        );
    }

    @Test
    void testBrand() {
        Assertions.assertTrue(this.brands.contains("Cisco"));
        Assertions.assertTrue(this.brands.contains("Juniper"));
        Assertions.assertTrue(this.brands.contains("Casa"));
        Assertions.assertTrue(this.brands.contains("Arris"));
        Assertions.assertTrue(this.brands.contains("Google"));
    }


}
