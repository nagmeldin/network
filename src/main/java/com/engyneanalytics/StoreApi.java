package com.engyneanalytics;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

public sealed interface StoreApi {

    @Get("/")
    String index();
    // Test it: http://localhost:8080/   -> Welcome to network routers portal

    @Get("describe/{type}/{id}")      // Test it http://localhost:8080/describe/device/2 -> Juniper or http://localhost:8080/describe/maker/2  --> AT &T
    String describe(String type, Long id);


    @Controller("/")
    final class HomeController implements StoreApi {

        private final NetService netService;

        public HomeController(NetService netService) {
            this.netService = netService;
        }

        public String index(){
            return "Welcome to the HOME network routers portal";
        }

        public String describe(String type, Long id) {return netService.describe(type, id); }

    }
}
