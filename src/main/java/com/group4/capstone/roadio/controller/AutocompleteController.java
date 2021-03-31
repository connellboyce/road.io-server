package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.AutocompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autocomplete")
public class AutocompleteController {
    @Autowired
    AutocompleteService autocompleteService;

    @GetMapping("/{partial}/{country}")
    public String complete(@PathVariable("partial") String partial, @PathVariable("country") String country) {
        return autocompleteService.complete(partial, country);
    }
}
