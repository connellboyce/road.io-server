package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.service.WebClientService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientServiceImpl implements WebClientService {

    private final WebClient webClient;

    public WebClientServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Object getThese(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Object.class);
    }

    @Override
    public Object getThis(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }

}
