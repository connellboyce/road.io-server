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

    /**
     * Controller constructor
     *
     * @param properties Field injected values
     */
    public SecretController(RoadioProperties properties) {
        this.properties = properties;
    }

    /**
     * Allows retrieval of the key for the HERE API from the program
     *
     * @return environment variable value of HERE_ACCESS_KEY_ID
     */
    @GetMapping("/here")
    public String getHereKey() {
        return properties.getHereApiKey();
    }

    /**
     * Allows retrieval of the key for the NREL API from the program
     *
     * @return environment variable value of NREL_ACCESS_KEY_ID
     */
    @GetMapping("/nrel")
    public String getNrelKey() {
        return properties.getNrelApiKey();
    }
}
