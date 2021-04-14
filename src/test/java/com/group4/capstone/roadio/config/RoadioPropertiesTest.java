package com.group4.capstone.roadio.config;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoadioPropertiesTest {

    private RoadioProperties properties;

    @BeforeEach
    void setUp() {
        properties = new RoadioProperties();
    }

    @Test
    void whenHereApiKeyIsSet_retrieveSameKeyInGet() {
        properties.setHereApiKey("hereKey");
        Assert.assertEquals("hereKey", properties.getHereApiKey());
    }
    @Test
    void whenNrelApiKeyIsSet_retrieveSameKeyInGet() {
        properties.setNrelApiKey("nrelKey");
        Assert.assertEquals("nrelKey", properties.getNrelApiKey());
    }
}