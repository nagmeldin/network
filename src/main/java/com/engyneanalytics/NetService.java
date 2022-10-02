package com.engyneanalytics;


import io.micronaut.context.event.StartupEvent;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author: Nagm Eldin
 */

@Singleton
public class NetService {
     //Repos Dependency Injection:
    private final DeviceRepository deviceRepository;
    private final MakerRepository makerRepository;

    public NetService(DeviceRepository deviceRepository, MakerRepository makerRepository) {
        this.deviceRepository = deviceRepository;
        this.makerRepository = makerRepository;
    }

    @EventListener
    @Transactional
    void onStartup(StartupEvent startupEvent){

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

    public String welcome(){
        return "Welcome to the network routers portal";
    }

    public List<Device> getAllDevices(){
        return  deviceRepository.findAll();  // findAll() must be DeviceRepository Interface
    }

    public List<Maker> getAllMakes(){
        return (List<Maker>) makerRepository.findAll();  // findAll() must be DeviceRepository Interface
    }

    //Hard-coded,No database:
    public List<Device> getBackupDevices(){
        return List.of( new Device(1L, "CISCO", 120, new Maker(1L, "AT&T")),
                new Device(2L, "JUNIPER", 130, new Maker(1L, "AT&T"))
        );
    }

    //Hard-coded,No database:
    public String getRandomBrand(){

        List<String> brands = Arrays.asList("Cisco", "Juniper", "Casa", "Arris", "D-link", "Encore", "Google", "Microlink", "Motorala", "Mercury") ;
        Random  random = new Random();

        return brands.get(random.nextInt(brands.size()));
    }
    // Pattern matching:
    public String describe(String type, Long id){
        //1. Declare generic repo based on entity 'type' above:
        CrudRepository< ? extends Entity<Long>, Long> crudRepository =
                switch(type.toLowerCase()){
                    case "device" -> this.deviceRepository;
                    case "maker" -> this.makerRepository;
                    default ->null;
                };

        //2. Query device os and maker name based on entity using J-17 Pattern Matching(must be enabled in build.gradle):
        Entity entity = crudRepository.findById(id).orElse(null);
                      return switch(entity){
                        case Device device -> device.os();
                        case Maker maker -> maker.name();
                         default -> null;
                     };
        /* Similarly:
        if (entity instanceof  Maker maker){          // No need 4 null check!
            return maker.name();
        }else if (  entity instanceof Device device){ // No need 4 null check!
            return device.os();
        }
        return null; */
    }

}
