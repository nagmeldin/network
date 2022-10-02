package com.engyneanalytics;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/device")
public class DeviceController {

    private final NetService netService;

    public DeviceController(NetService netService) {
        this.netService = netService;
    }

    @Get("/")
    public String index(){
        return "Welcome to device portal";
    }
    @Get("/listdevices")        // http://localhost:8080/device/listdevices
    List<Device> listdevices(){
        return netService.getAllDevices();
    }

    @Get("/listbackupdevices") // http://localhost:8080/device/listbackupdevices
    List<Device> listbackupdevices(){
        return netService.getBackupDevices();
    }


}


