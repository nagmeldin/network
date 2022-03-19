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
        Maker savedMaker =  makerRepository.save(new Maker(null,"Netgear")); //---->(1)
                System.out.println("savedMaker.id() = " + savedMaker);
        deviceRepository.saveAll(Arrays.asList(
                new Device(null, "Cisco", 12, savedMaker),
                new Device(null, "Juniper", 19, savedMaker),
                new Device(null, "Casa", 11, savedMaker),
                new Device(null, "Arris", 6, savedMaker)
        ));

    }
}
