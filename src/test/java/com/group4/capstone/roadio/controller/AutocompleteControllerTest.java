package com.group4.capstone.roadio.controller;

import com.group4.capstone.roadio.service.AutocompleteService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class AutocompleteControllerTest {

    private AutocompleteService autocompleteService;
    private AutocompleteController controller;

    /**
     * This tests that the controller parameters are properly passed to the service.
     */
    @Test
    void whenAutocompleteParametersArePresent_thenServiceReceivesSameParameters() {
        autocompleteService = mock(AutocompleteService.class);
        controller = new AutocompleteController(autocompleteService);

        String partial="747 E Be";
        String country="USA";
        String testResult = "{\"completed\": []}";

        doReturn(testResult).when(autocompleteService).complete(partial, country);
        String completedString = controller.complete(partial, country);
        verify(autocompleteService, times(1)).complete(partial, country);
        Assert.assertNotNull(completedString);
        Assert.assertEquals(testResult, completedString);
        Assert.assertTrue(completedString.contains("completed"));
    }
}