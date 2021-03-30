package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.service.AutocompleteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {
    private final WebClient webClient;

    public AutocompleteServiceImpl(WebClient.Builder webClientBuilder) { this.webClient = webClientBuilder.build(); }

    @Value("${app.hereapi.key}")
    private String hereKey;

    @Override
    public Object complete(String partial, String country) {
        String url = "https://autosuggest.search.hereapi.com/v1/autosuggest?q="+partial.replace(" ", "+")+"&at=40.599830,-97.120535&in=countryCode:"+country+"&apiKey="+hereKey;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
