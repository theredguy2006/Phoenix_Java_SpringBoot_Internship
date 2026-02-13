package com.red.jounrnalpostgre.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {


//    in postman if we call this method via post it will give error and not allowed.
    @GetMapping("/health-check")
    public String healthcheck() {
        return "okay";
    }
}
