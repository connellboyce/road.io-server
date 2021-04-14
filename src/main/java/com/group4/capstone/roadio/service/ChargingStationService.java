package com.group4.capstone.roadio.service;

public interface ChargingStationService {
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
    public String getStationsAlongRoute(String origin, String outletType, String destination, String freeFlowSpeedTable, String initialCharge, String maxCharge, String chargingCurve, String maxChargeAfterChargingStation);

    /**
     * Finds the closest stations to a specified location
     *
     * @param latitude  origin latitude
     * @param longitude origin longitude
     * @param radius    radius around the vehicle to search within
     * @return JSON of local charging station information
     */
    public String getStationsNearMe(String latitude, String longitude, String radius);
}
