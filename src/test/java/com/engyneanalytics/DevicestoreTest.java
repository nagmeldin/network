package com.engyneanalytics;

import io.micronaut.http.client.annotation.Client;
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

    @Client("/")
    interface TestDeviceStoreClient extends DeviceStoreApi.DeviceClient{} // can do this because it's unsealed that you can extend here

    // Test List:
    @Test
    void testMaker() {
        Assertions.assertEquals(1,
                makerRepository.count()
        );
    }

    @Test
    void testDeviceList() {
        Assertions.assertEquals(10,
                deviceRepository.count()
        );
    }

    @Test
    void testDeviceWeight(TestDeviceStoreClient client) {
         /*
        Device devices = deviceRepository.findAll().iterator().next(); // error!

        String devices_db = client.describe("device",devices.id());

        Assertions.assertEquals("Cisco", devices_db);
      */
    }

    @Test
    void testBrand() {
        Assertions.assertTrue(this.brands.contains("Cisco"));
        Assertions.assertTrue(this.brands.contains("Juniper"));
        Assertions.assertTrue(this.brands.contains("Casa"));
        Assertions.assertTrue(this.brands.contains("Arris"));
        Assertions.assertTrue(this.brands.contains("Google"));
    }



    @Test
    void testMaker(TestDeviceStoreClient client ) {
        Maker maker = makerRepository.findAll().iterator().next();
        String maker_db = client.describe("maker",maker.id());
        //Test it:
        Assertions.assertEquals("AT&T", maker_db);
    }

}
