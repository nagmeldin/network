package com.engyneanalytics;

import io.micronaut.data.repository.CrudRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

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


    // Unseal version of DeviceStore Api(TBD in testing):
     non-sealed interface DeviceClient extends DeviceStoreApi { }


    //Inside class implementation of above parent interface:

    // Interface function implementation
    @Controller("/")
    final class DeviceController implements DeviceStoreApi {

        // Device and Maker repos injection:
        private final DeviceRepository deviceRepository;
        private final MakerRepository makerRepository;

        // DeviceService injection:
        private final DeviceService deviceService;

        public DeviceController(DeviceRepository deviceRepository, MakerRepository makerRepository, DeviceService deviceService) {
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

        public String describe(String type, Long id){
            //1. Declare generic repo based on entity 'type' above:
            CrudRepository< ? extends  Entity<Long>, Long> crudRepository =
                    switch(type.toLowerCase()){
                             case "device" -> this.deviceRepository;
                             case "maker" -> this.makerRepository;
                             default ->null;
                    };


            //2. Query device os and maker name based on entity using J-17 Pattern Matching:

                  Entity entity = crudRepository.findById(id).orElse(null);
                 /*   return switch(entity){
                        case Device device -> device.os();
                        case Maker maker -> maker.name();
                         default -> null;
                     }; Similarly: */
                if ( crudRepository !=null && entity instanceof  Maker maker){
                    return maker.name();
                }else if (crudRepository !=null && entity instanceof Device device){
                    return device.os();
                }
            return null;
        }

    }

}
