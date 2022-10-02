package com.engyneanalytics;

import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;

/**
 * @author: Nagm Eldin
 */

@Singleton
public class Application {
    /*private final DeviceRepository deviceRepository;
    private final MakerRepository makerRepository;

    public Application(DeviceRepository deviceRepository, MakerRepository makerRepository) {
        this.deviceRepository = deviceRepository;
        this.makerRepository = makerRepository;
    } */

    private final NetService netService;

    public Application(NetService netService) {
        this.netService = netService;
    }

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }


    @EventListener
    @Transactional
    void onStartup(StartupEvent event) {
      /* repository.saveAll(
                List.of(
                        new Person("Anne", "Doe","female", LocalDate.of(1991,8,13), 139),
                        new Person("Jack", "Doe","male", LocalDate.of(1990,2,26), 128),
                        new Person("Jared", "Doe","male", LocalDate.of(2015,12,11), 99)
                )*/

        netService.onStartup(event);
    }


}
// Test it: http://localhost:8080/   -> Welcome to network routers portal
//          http://localhost:8080/any -> (refresh to get different device everytime)
//          http://localhost:8080/describe/maker/1 -> AT&T
//          http://localhost:8080/describe/device/2 -> Juniper
//          http://localhost:8080/describe/devices -> Juniper,Cisco, etc
//          http://localhost:8080/describe/devicesbackup -> JUNIPER,CISCO