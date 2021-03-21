package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestfulController {
    @Autowired
    WebClientService webClientService;

    //API calls can be handled by doing webClientService.CHOOSE_FUNCTION
    //Must be configured similarly to methods in secretcontroller
}
