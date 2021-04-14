package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.config.RoadioProperties;
import com.group4.capstone.roadio.service.ChargingStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class ChargingStationServiceImpl implements ChargingStationService {

    private final Logger logger = LoggerFactory.getLogger(ChargingStationServiceImpl.class);
    private final WebClient webClient;
    private final RoadioProperties properties;


    public ChargingStationServiceImpl(WebClient.Builder webClientBuilder, RoadioProperties properties) {
        this.webClient = webClientBuilder.build();
        this.properties = properties;
    }


    @Override
    public String getStationsAlongRoute(String origin, String outletType, String destination, String freeFlowSpeedTable, String initialCharge, String maxCharge, String chargingCurve, String maxChargeAfterChargingStation) {
        String url = "https://router.hereapi.com/v8/routes?departureTime=any&origin="+origin+"&ev[connectorTypes]="+outletType+"&transportMode=car&destination="+destination+"&return=polyline&ev[freeFlowSpeedTable]="+freeFlowSpeedTable+"&ev[makeReachable]=true&ev[initialCharge]="+initialCharge+"&ev[maxCharge]="+maxCharge+"&ev[chargingCurve]="+chargingCurve+"&ev[maxChargeAfterChargingStation]="+maxChargeAfterChargingStation+"&apiKey="+ properties.getHereApiKey();
        logger.debug("getStationsAlongRoute URL={}",url);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @Override
    public String getStationsNearMe(String latitude, String longitude, String radius) {
        String url = "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?latitude="+latitude+"&longitude="+longitude+"&radius="+radius+"&api_key="+properties.getNrelApiKey()+"&fuel_type=ELEC";
        logger.debug("getStationsNearMe URL={}",url);

        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
