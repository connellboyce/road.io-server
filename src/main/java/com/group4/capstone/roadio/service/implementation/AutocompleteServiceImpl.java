package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.config.RoadioProperties;
import com.group4.capstone.roadio.service.AutocompleteService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {
    private final WebClient webClient;
    private final RoadioProperties properties;

    /**
     * Service Constructor
     *
     * @param webClientBuilder builder for the WebFLux WebClient
     * @param properties       system variables
     */
    public AutocompleteServiceImpl(WebClient.Builder webClientBuilder, RoadioProperties properties) {
        this.webClient = webClientBuilder.build();
        this.properties = properties;
    }

    /**
     * Autocompletes a string based on a given partial and country
     *
     * @param partial piece of String to be completed
     * @param country country of origin
     * @return JSON of several potential autocompletions
     */
    @Override
    public String complete(String partial, String country) {
        String url = "https://autosuggest.search.hereapi.com/v1/autosuggest?q=" + partial.replace(" ", "+") + "&at=40.599830,-97.120535&in=countryCode:" + country + "&apiKey=" + properties.getHereApiKey();
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
