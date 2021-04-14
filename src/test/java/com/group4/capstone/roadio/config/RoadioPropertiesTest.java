package com.group4.capstone.roadio.config;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadioPropertiesTest {

    private RoadioProperties properties;

    /**
     * Initialize new properties before each test
     */
    @BeforeEach
    void setUp() {
        properties = new RoadioProperties();
    }

    /**
     * Tests whether the passing between HereApiKey setters and getters does not manipulate value
     */
    @Test
    void whenHereApiKeyIsSet_retrieveSameKeyInGet() {
        properties.setHereApiKey("hereKey");
        Assert.assertEquals("hereKey", properties.getHereApiKey());
    }

    /**
     * Tests whether the passing between NrelApiKey setters and getters does not manipulate value
     */
    @Test
    void whenNrelApiKeyIsSet_retrieveSameKeyInGet() {
        properties.setNrelApiKey("nrelKey");
        Assert.assertEquals("nrelKey", properties.getNrelApiKey());
    }
}