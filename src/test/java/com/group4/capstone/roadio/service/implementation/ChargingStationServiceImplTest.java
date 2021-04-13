package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.service.ChargingStationService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ChargingStationServiceImplTest {

    private ChargingStationService chargingStationService;
    private WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Test
    void whenRoutingParametersArePresent_upstreamUrlIsProperlyBuilt() {
        String expectedURI = "https://router.hereapi.com/v8/routes?departureTime=any&origin=40.79358,-77.86056&ev[connectorTypes]=iec62196Type1Combo&transportMode=car&destination=41.88425,-87.63245&return=polyline&ev[freeFlowSpeedTable]=110,0.165&ev[makeReachable]=true&ev[initialCharge]=45&ev[maxCharge]=60&ev[chargingCurve]=0,50,9,52,12,54,15,54,18,54,21,54,24,55,27,55,30,55,33,37,36,37,39,37,42,23,45,23,48,23,51,16,54,16,57,10,60,4&ev[maxChargeAfterChargingStation]=60&apiKey=null";

        String origin="40.79358,-77.86056";
        String chargerType="iec62196Type1Combo";
        String destination="41.88425,-87.63245";
        String speedTable="110,0.165";
        String currentCharge="45";
        String maxCharge="60";
        String chargingCurve="0,50,9,52,12,54,15,54,18,54,21,54,24,55,27,55,30,55,33,37,36,37,39,37,42,23,45,23,48,23,51,16,54,16,57,10,60,4";
        String maxChargeAfterStation="60";
        String testResult = "{\"routes\": []}";

        webClientBuilder = mock(WebClient.Builder.class);
        webClient = mock(WebClient.class);

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

        chargingStationService = new ChargingStationServiceImpl(webClientBuilder);
        verify(webClientBuilder, times(1)).build();

        String stationsAlongRoute = chargingStationService.getStationsAlongRoute(origin, chargerType, destination, speedTable, currentCharge, maxCharge, chargingCurve, maxChargeAfterStation);
        verify(webClient, times(1)).get();
        verify(webClientGet, times(1)).uri(expectedURI);
        verify(webClientUri, times(1)).retrieve();
        verify(webClientRetrieve, times(1)).bodyToMono(String.class);
        verify(stringMono, times(1)).block();

        Assert.assertNotNull(stationsAlongRoute);
        Assert.assertEquals(testResult, stationsAlongRoute);
    }

    @Test
    void whenNearbyStationParametersArePresent_upstreamUrlIsProperlyBuilt() {
        String expectedURI = "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?latitude=40.79358&longitude=-77.86056&radius=25&api_key=null&fuel_type=ELEC";

        String latitude = "40.79358";
        String longitude = "-77.86056";
        String radius = "25";
        String testResult = "{\"stations\": []}";

        webClientBuilder = mock(WebClient.Builder.class);
        webClient = mock(WebClient.class);

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

        chargingStationService = new ChargingStationServiceImpl(webClientBuilder);
        verify(webClientBuilder, times(1)).build();

        String nearbyStations = chargingStationService.getStationsNearMe(latitude, longitude, radius);
        verify(webClient, times(1)).get();
        verify(webClientGet, times(1)).uri(expectedURI);
        verify(webClientUri, times(1)).retrieve();
        verify(webClientRetrieve, times(1)).bodyToMono(String.class);
        verify(stringMono, times(1)).block();

        Assert.assertNotNull(nearbyStations);
        Assert.assertEquals(testResult, nearbyStations);
    }
}