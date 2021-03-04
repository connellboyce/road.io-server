package com.group4.capstone.roadio.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secrets")
@CrossOrigin
public class SecretController {

    @Value("${app.hereapi.key}")
    private String hereAPIKey;

    @GetMapping("/here")
    public String getHereKey() {
        return hereAPIKey;
    }

}
