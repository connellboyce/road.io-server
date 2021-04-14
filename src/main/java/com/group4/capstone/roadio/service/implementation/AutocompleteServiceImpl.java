package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.config.RoadioProperties;
import com.group4.capstone.roadio.service.AutocompleteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {
    private final WebClient webClient;
    private final RoadioProperties properties;

    public AutocompleteServiceImpl(WebClient.Builder webClientBuilder, RoadioProperties properties) { this.webClient = webClientBuilder.build(); this.properties = properties; }


    @Override
    public String complete(String partial, String country) {
        String url = "https://autosuggest.search.hereapi.com/v1/autosuggest?q="+partial.replace(" ", "+")+"&at=40.599830,-97.120535&in=countryCode:"+country+"&apiKey="+properties.getHereApiKey();
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
