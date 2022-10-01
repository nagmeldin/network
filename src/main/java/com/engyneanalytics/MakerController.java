package com.engyneanalytics;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/")
public class MakeController {
    private final MakerRepository repository;

    public MakeController(MakerRepository repository) {
        this.repository = repository;
    }

    @Get("/list")
    List<Maker> list(){
        return (List<Maker>) repository.findAll();

    }

}
