package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.ChargingStationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin
public class ChargingStationController {

    private ChargingStationService chargingStationService;

    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    @GetMapping("/along/{origin}/{outletType}/{destination}/{freeFlowSpeedTable}/{initialCharge}/{maxCharge}/{chargingCurve}/{maxChargeAfterChargingStation}")
    public String getStationsOnRoute(@PathVariable("origin") String origin, @PathVariable("outletType") String outletType, @PathVariable("destination") String destination, @PathVariable("freeFlowSpeedTable") String freeFlowSpeedTable, @PathVariable("initialCharge") String initialCharge, @PathVariable("maxCharge") String maxCharge, @PathVariable("chargingCurve") String chargingCurve, @PathVariable("maxChargeAfterChargingStation") String maxChargeAfterChargingStation) {
        return chargingStationService.getStationsAlongRoute(origin, outletType, destination, freeFlowSpeedTable, initialCharge, maxCharge, chargingCurve, maxChargeAfterChargingStation);
    }

    @GetMapping("/near/{latitude}/{longitude}/{radius}")
    public String getNearbyStations(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude, @PathVariable("radius") String radius) {
        return chargingStationService.getStationsNearMe(latitude, longitude, radius);
    }
}
