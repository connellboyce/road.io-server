package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.ChargingStationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin
public class ChargingStationController {

    private ChargingStationService chargingStationService;

    /**
     * Controller constructor
     *
     * @param chargingStationService Charging Station Service interface to handle logic
     */
    public ChargingStationController(ChargingStationService chargingStationService) {
        this.chargingStationService = chargingStationService;
    }

    /**
     * Finds the proper routing to keep an electric vehicle charged during long distance drives
     *
     * @param origin                        starting point
     * @param outletType                    plug type for the specified electric vehicle
     * @param destination                   ending point
     * @param freeFlowSpeedTable            table of how much energy the vehicle will consume at a given speed
     * @param initialCharge                 current state of vehicle's charge
     * @param maxCharge                     max potential charge of the vehicle
     * @param chargingCurve                 table of how fast the vehicle will charge at the station
     * @param maxChargeAfterChargingStation state of charge the vehicle will have after each stop
     * @return JSON of routing information from origin to destination with charging stations included
     */
    @GetMapping("/along/{origin}/{outletType}/{destination}/{freeFlowSpeedTable}/{initialCharge}/{maxCharge}/{chargingCurve}/{maxChargeAfterChargingStation}")
    public String getStationsOnRoute(@PathVariable("origin") String origin, @PathVariable("outletType") String outletType, @PathVariable("destination") String destination, @PathVariable("freeFlowSpeedTable") String freeFlowSpeedTable, @PathVariable("initialCharge") String initialCharge, @PathVariable("maxCharge") String maxCharge, @PathVariable("chargingCurve") String chargingCurve, @PathVariable("maxChargeAfterChargingStation") String maxChargeAfterChargingStation) {
        return chargingStationService.getStationsAlongRoute(origin, outletType, destination, freeFlowSpeedTable, initialCharge, maxCharge, chargingCurve, maxChargeAfterChargingStation);
    }

    /**
     * Finds the closest stations to a specified location
     *
     * @param latitude  origin latitude
     * @param longitude origin longitude
     * @param radius    radius around the vehicle to search within
     * @return JSON of local charging station information
     */
    @GetMapping("/near/{latitude}/{longitude}/{radius}")
    public String getNearbyStations(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude, @PathVariable("radius") String radius) {
        return chargingStationService.getStationsNearMe(latitude, longitude, radius);
    }
}
