package com.group4.capstone.roadio.service;

public interface ChargingStationService {
    public String getStationsAlongRoute(String origin, String outletType, String destination, String freeFlowSpeedTable, String initialCharge, String maxCharge, String chargingCurve, String maxChargeAfterChargingStation);
    public String getStationsNearMe(String location, String radius);
}
