package com.group4.capstone.roadio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "upstream.api")
public class RoadioProperties {
    private String hereApiKey;
    private String nrelApiKey;

    public String getHereApiKey() {
        return hereApiKey;
    }

    public void setHereApiKey(String hereApiKey) {
        this.hereApiKey = hereApiKey;
    }

    public String getNrelApiKey() {
        return nrelApiKey;
    }

    public void setNrelApiKey(String nrelApiKey) {
        this.nrelApiKey = nrelApiKey;
    }
}
