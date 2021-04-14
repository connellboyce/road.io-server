package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.config.RoadioProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secrets")
@CrossOrigin
public class SecretController {

    private final RoadioProperties properties;

    public SecretController(RoadioProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/here")
    public String getHereKey() {
        return properties.getHereApiKey();
    }

    @GetMapping("/nrel")
    public String getNrelKey() {
        return properties.getNrelApiKey();
    }
}
