package com.group4.capstone.roadio.service;

public interface ChargingStationService {
    public Object getStationsAlongRoute(String origin, String outletType, String destination, String freeFlowSpeedTable, String initialCharge, String maxCharge, String chargingCurve, String maxChargeAfterChargingStation);
    public Object getStationsNearMe(String location, String radius);
}
