package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.ChargingStationService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ChargingStationControllerTest {

    private ChargingStationService chargingStationService;
    private ChargingStationController controller;

    /**
     * This tests that the controller parameters are properly passed to the service.
     */
    @Test
    void whenRoutingParametersArePresent_thenServiceReceivesSameParameters() {
        chargingStationService = mock(ChargingStationService.class);
        controller = new ChargingStationController(chargingStationService);

        String origin = "40.79358,-77.86056";
        String chargerType = "iec62196Type1Combo";
        String destination = "41.88425,-87.63245";
        String speedTable = "110,0.165";
        String currentCharge = "45";
        String maxCharge = "60";
        String chargingCurve = "0,50,9,52,12,54,15,54,18,54,21,54,24,55,27,55,30,55,33,37,36,37,39,37,42,23,45,23,48,23,51,16,54,16,57,10,60,4";
        String maxChargeAfterStation = "60";
        String testResult = "{\"routes\": []}";

        doReturn(testResult).when(chargingStationService).getStationsAlongRoute(origin, chargerType, destination, speedTable, currentCharge, maxCharge, chargingCurve, maxChargeAfterStation);
        String stationsOnRoute = controller.getStationsOnRoute(origin, chargerType, destination, speedTable, currentCharge, maxCharge, chargingCurve, maxChargeAfterStation);
        verify(chargingStationService, times(1)).getStationsAlongRoute(origin, chargerType, destination, speedTable, currentCharge, maxCharge, chargingCurve, maxChargeAfterStation);
        Assert.assertNotNull(stationsOnRoute);
        Assert.assertEquals(testResult, stationsOnRoute);
        Assert.assertTrue(stationsOnRoute.contains("routes"));
    }

    /**
     * This tests that the controller parameters are being properly passed to the service.
     */
    @Test
    void whenNearbyStationParametersArePresent_thenServiceReceivesSameParameters() {
        chargingStationService = mock(ChargingStationService.class);
        controller = new ChargingStationController(chargingStationService);

        String latitude = "40.79358";
        String longitude = "-77.86056";
        String radius = "25";
        String testResult = "{\"stations\": []}";

        doReturn(testResult).when(chargingStationService).getStationsNearMe(latitude, longitude, radius);
        String nearbyStations = controller.getNearbyStations(latitude, longitude, radius);
        verify(chargingStationService, times(1)).getStationsNearMe(latitude, longitude, radius);
        Assert.assertNotNull(nearbyStations);
        Assert.assertEquals(testResult, nearbyStations);
        Assert.assertTrue(nearbyStations.contains("stations"));
    }
}