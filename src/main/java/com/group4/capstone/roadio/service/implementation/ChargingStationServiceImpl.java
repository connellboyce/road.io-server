package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    private final WebClient webClient;

    @Value("${app.hereapi.key}")
    String hereKey;

    @Value("${app.nrel.key}")
    private String nrelKey;

    public ChargingStationServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }


    @Override
    public String getStationsAlongRoute(String origin, String outletType, String destination, String freeFlowSpeedTable, String initialCharge, String maxCharge, String chargingCurve, String maxChargeAfterChargingStation) {
        String url = "https://router.hereapi.com/v8/routes?departureTime=any&origin="+origin+"&ev[connectorTypes]="+outletType+"&transportMode=car&destination="+destination+"&return=polyline&ev[freeFlowSpeedTable]="+freeFlowSpeedTable+"&ev[makeReachable]=true&ev[initialCharge]="+initialCharge+"&ev[maxCharge]="+maxCharge+"&ev[chargingCurve]="+chargingCurve+"&ev[maxChargeAfterChargingStation]="+maxChargeAfterChargingStation+"&apiKey="+ hereKey;

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String getStationsNearMe(String latitude, String longitude, String radius) {
        String url = "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?latitude="+latitude+"&longitude="+longitude+"&radius="+radius+"&api_key="+nrelKey+"&fuel_type=ELEC";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
