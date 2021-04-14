package com.group4.capstone.roadio.service;

public interface AutocompleteService {
    /**
     * Autocompletes a string based on a given partial and country
     *
     * @param partial piece of String to be completed
     * @param country country of origin
     * @return JSON of several potential autocompletions
     */
    public String complete(String partial, String country);
}
