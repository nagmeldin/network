package com.engyneanalytics;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/maker")
public class MakerController {
    private final NetService netService;

    public MakerController(NetService netService) {
        this.netService = netService;
    }

    @Get("/")
    public String index(){
        return "Welcome to maker portal";
    }
    @Get("/listmakers")       // http://localhost:8080/maker/listmakers
    List<Maker> listmakers(){
        return netService.getAllMakes();
    }

    @Get("/randommaker")    // http://localhost:8080/maker/randommaker
    String randommaker(){
        return netService.getRandomBrand();
    }

}
