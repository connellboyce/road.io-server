package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.AutocompleteService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autocomplete")
@CrossOrigin
public class AutocompleteController {

    private AutocompleteService autocompleteService;

    /**
     * Controller constructor
     *
     * @param autocompleteService Autocomplete Service interface to handle logic
     */
    public AutocompleteController(AutocompleteService autocompleteService) {
        this.autocompleteService = autocompleteService;
    }

    /**
     * Autocompletes a string based on a given partial and country
     *
     * @param partial piece of String to be completed
     * @param country country of origin
     * @return JSON of several potential autocompletions
     */
    @GetMapping("/{partial}/{country}")
    public String complete(@PathVariable("partial") String partial, @PathVariable("country") String country) {
        return autocompleteService.complete(partial, country);
    }
}
