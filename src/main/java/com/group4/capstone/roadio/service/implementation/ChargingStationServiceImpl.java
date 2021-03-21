package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.service.ChargingStationService;
import com.group4.capstone.roadio.service.WebClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ChargingStationServiceImpl implements ChargingStationService {
    public WebClientService webClientService;

    @Value("${app.hereapi.key}")
    String hereKey;

    @Value("${app.nrel.key}")
    private String nrelKey;

    public ChargingStationServiceImpl(WebClientService webClientService) {
        this.webClientService = webClientService;
    }


    @Override
    public Object getStationsAlongRoute(String origin, String outletType, String destination, String freeFlowSpeedTable, String initialCharge, String maxCharge, String chargingCurve, String maxChargeAfterChargingStation) {
        String url = "https://router.hereapi.com/v8/routes?departureTime=any&origin="+origin+"&ev[connectorTypes]="+outletType+"&transportMode=car&destination="+destination+"&return=polyline&ev[freeFlowSpeedTable]="+freeFlowSpeedTable+"&ev[makeReachable]=true&ev[initialCharge]="+initialCharge+"&ev[maxCharge]="+maxCharge+"&ev[chargingCurve]="+chargingCurve+"&ev[maxChargeAfterChargingStation]="+maxChargeAfterChargingStation+"&apiKey="+ hereKey;
        return webClientService.getThis(url);
    }

    @Override
    public Object getStationsNearMe(String location, String radius) {
        String url = "https://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?location="+location+"&radius="+radius+"&api_key="+nrelKey;
        return webClientService.getThis(url);
    }
}
