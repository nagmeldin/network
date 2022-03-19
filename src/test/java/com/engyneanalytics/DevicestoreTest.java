package com.engyneanalytics;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
class DevicestoreTest {

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
        Assertions.assertEquals(2,
                deviceRepository.count()
        );
    }



}
