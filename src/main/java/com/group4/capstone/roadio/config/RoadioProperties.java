package com.group4.capstone.roadio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "upstream.api")
public class RoadioProperties {
    private String hereApiKey;
    private String nrelApiKey;

    /**
     * Method to retrieve environment variable API key for HERE API
     * @return HERE API Key
     */
    public String getHereApiKey() {
        return hereApiKey;
    }

    /**
     * Method to import the environment variable for HERE API Key
     * @param hereApiKey environment variable HERE_ACCESS_KEY_ID
     */
    public void setHereApiKey(String hereApiKey) {
        this.hereApiKey = hereApiKey;
    }

    /**
     * Method to retrieve environment variable API key for NREL API
     * @return NREL API Key
     */
    public String getNrelApiKey() {
        return nrelApiKey;
    }

    /**
     * Method to import the environment variable for NREL API Key
     * @param nrelApiKey environment variable NREL_ACCESS_KEY_ID
     */
    public void setNrelApiKey(String nrelApiKey) {
        this.nrelApiKey = nrelApiKey;
    }
}
