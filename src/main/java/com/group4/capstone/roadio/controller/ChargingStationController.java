package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.ChargingStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stations")
public class ChargingStationController {
    @Autowired
    ChargingStationService chargingStationService;

    @GetMapping("/along/{origin}/{outletType}/{destination}/{freeFlowSpeedTable}/{initialCharge}/{maxCharge}/{chargingCurve}/{maxChargeAfterChargingStation}")
    public String getStationsOnRoute(@PathVariable("origin") String origin, @PathVariable("outletType") String outletType, @PathVariable("destination") String destination, @PathVariable("freeFlowSpeedTable") String freeFlowSpeedTable, @PathVariable("initialCharge") String initialCharge, @PathVariable("maxCharge") String maxCharge, @PathVariable("chargingCurve") String chargingCurve, @PathVariable("maxChargeAfterChargingStation") String maxChargeAfterChargingStation) {
        return chargingStationService.getStationsAlongRoute(origin, outletType, destination, freeFlowSpeedTable, initialCharge, maxCharge, chargingCurve, maxChargeAfterChargingStation).toString();
    }

    @GetMapping("/near/{location}/{radius}")
    public String getNearbyStations(@PathVariable("location") String location, @PathVariable("radius") String radius) {
        return chargingStationService.getStationsNearMe(location, radius).toString();
    }
}
