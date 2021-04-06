package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.AutocompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autocomplete")
@CrossOrigin
public class AutocompleteController {
    @Autowired
    AutocompleteService autocompleteService;

    @GetMapping("/{partial}/{country}")
    public String complete(@PathVariable("partial") String partial, @PathVariable("country") String country) {
        return autocompleteService.complete(partial, country);
    }
}
