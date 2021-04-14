package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.config.RoadioProperties;
import com.group4.capstone.roadio.service.AutocompleteService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AutocompleteServiceImplTest {

    private AutocompleteService autocompleteService;
    private WebClient.Builder webClientBuilder;
    private WebClient webClient;
    private RoadioProperties properties;

    /**
     * This tests that the URL is properly built upstream
     */
    @Test
    void whenAutocompleteParametersArePresent_upstreamUrlIsProperlyBuilt() {
        String expectedURI = "https://autosuggest.search.hereapi.com/v1/autosuggest?q=747+E+Bea&at=40.599830,-97.120535&in=countryCode:USA&apiKey=hereKey";
        String partial = "747 E Bea";
        String country = "USA";
        String testResult = "{\"completed\": []}";

        webClientBuilder = mock(WebClient.Builder.class);
        webClient = mock(WebClient.class);
        properties = mock(RoadioProperties.class);

        WebClient.RequestHeadersUriSpec<?> webClientGet = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec<?> webClientUri = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec webClientRetrieve = mock(WebClient.ResponseSpec.class);
        Mono<String> stringMono = mock(Mono.class);

        doReturn(webClient).when(webClientBuilder).build();

        doReturn(webClientGet).when(webClient).get();
        doReturn(webClientUri).when(webClientGet).uri(expectedURI);
        doReturn(webClientRetrieve).when(webClientUri).retrieve();
        doReturn(stringMono).when(webClientRetrieve).bodyToMono(String.class);
        doReturn(testResult).when(stringMono).block();


        autocompleteService = new AutocompleteServiceImpl(webClientBuilder, properties);
        verify(webClientBuilder, times(1)).build();

        doReturn("hereKey").when(properties).getHereApiKey();

        String completedString = autocompleteService.complete(partial, country);
        verify(properties, times(1)).getHereApiKey();

        verify(webClient, times(1)).get();
        verify(webClientGet, times(1)).uri(expectedURI);
        verify(webClientUri, times(1)).retrieve();
        verify(webClientRetrieve, times(1)).bodyToMono(String.class);
        verify(stringMono, times(1)).block();

        Assert.assertNotNull(completedString);
        Assert.assertEquals(testResult, completedString);
    }
}