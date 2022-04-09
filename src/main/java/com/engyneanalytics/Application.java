package com.engyneanalytics;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * @author: Nagm Eldin
 */

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

}
// Test it: http://localhost:8080/   -> Welcome to network routers portal
//          http://localhost:8080/any -> (refresh to get different device everytime)
//          http://localhost:8080/describe/maker/1 -> AT&T
//          http://localhost:8080/describe/device/2 -> Juniper
//          http://localhost:8080/describe/devices -> Juniper,Cisco, etc
//          http://localhost:8080/describe/devicesbackup -> JUNIPER,CISCO