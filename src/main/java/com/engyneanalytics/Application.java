package com.engyneanalytics;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.Arrays;

@Singleton
public class Application {
    private final DeviceRepository deviceRepository;
    private final MakerRepository makerRepository;

    public Application(DeviceRepository deviceRepository, MakerRepository makerRepository) {
        this.deviceRepository = deviceRepository;
        this.makerRepository = makerRepository;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    @EventListener
    @Transactional
    void startup (StartupEvent startupEvent){
        Maker savedMaker =  makerRepository.save(new Maker(null,"AT&T")); //---->(1)
                System.out.println("savedMaker.id() = " + savedMaker);
        deviceRepository.saveAll(Arrays.asList(                                    //---->(2)
                new Device(null, "Cisco", 12, savedMaker),
                new Device(null, "Juniper", 19, savedMaker),
                new Device(null, "Casa", 11, savedMaker),
                new Device(null, "Arris", 6, savedMaker),
                new Device(null, "D-link", 13, savedMaker),
                new Device(null, "Encore", 7, savedMaker),
                new Device(null, "Google", 5, savedMaker),
                new Device(null, "Microlink", 8, savedMaker),
                new Device(null, "Motorala", 15, savedMaker),
                new Device(null, "Mercury", 21, savedMaker)
        ));

    }
}
// Test it: http://localhost:8080/   -> Welcome to network routers portal
//          http://localhost:8080/any -> (refresh to get different device everytime)
//          http://localhost:8080/describe/maker/1 -> AT&T
//          http://localhost:8080/describe/device/2 -> Juniper