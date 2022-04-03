package com.engyneanalytics;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

public sealed interface DeviceStoreApi {

    @Get("/")
    String index();
    // Test it: http://localhost:8080/   -> Welcome to network routers portal

    @Get("describe/{type}/{id}")
    String describe(String type, Long id);
    // Test it: http://localhost:8080/describe/maker/1 -> AT&T
    //          http://localhost:8080/describe/device/2 -> Juniper

    @Get("/any")
    String getAnyDevice();
    // Test it: http://localhost:8080/any -> (refresh to get different device everytime)

    @Get("/devices")
    List<Device> getAllDevices();
    // Test it: http://localhost:8080/devices  (lists all devices)

    @Get("/devicesbackup")
    List<Device> getBackupDevices();
    // Test it: http://localhost:8080/devicesbackup  (lists all backup devices)

    // Unseal version of DeviceStore Api(TBD for testing):
     non-sealed interface DeviceClient extends DeviceStoreApi { }


    //Inner class implementation of above sealed parent interface - No 'permits' is needed but must be 'final':

    // Interface function implementation
    @Controller("/")
    final class DeviceController implements DeviceStoreApi {

        // Services & Db Repos injections:
        private final NetService deviceService;
        private final DeviceRepository deviceRepository;
        private final MakerRepository makerRepository;

        public DeviceController(DeviceRepository deviceRepository, MakerRepository makerRepository, NetService deviceService) {
            this.deviceRepository = deviceRepository;
            this.makerRepository = makerRepository;
            this.deviceService = deviceService;
        }


        public String index(){
            return "Welcome to network routers portal";
        }

        public String getAnyDevice(){
            return deviceService.getRandomBrand();
        }

        public List<Device> getAllDevices(){
            return deviceService.getAllDevices();
        }
        public List<Device> getBackupDevices(){
            return deviceService.getBackupDevices();
        }

        // This demos j17 pattern matching:
        public String describe(String type, Long id) {return deviceService.describe(type, id); }

    }
}
