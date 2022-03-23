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
    @Inject
    DeviceService deviceService;

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

        Device device = deviceRepository.findAll().iterator().next();

        String device_db = client.describe("device",device.id());  // first entry is Cisco

        Assertions.assertEquals("Cisco", device_db);
        Assertions.assertEquals(12, device.weight());

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
        Maker maker = makerRepository.findAll().iterator().next();  // yield first entry
        String maker_db = client.describe("maker",maker.id());
        //Test it:
        Assertions.assertEquals("AT&T", maker_db);
    }

    @Test
    void testDeviceService() {
        String randomDevice = deviceService.getRandomBrand();
        Assertions.assertTrue( brands.contains(randomDevice));
    }
}
