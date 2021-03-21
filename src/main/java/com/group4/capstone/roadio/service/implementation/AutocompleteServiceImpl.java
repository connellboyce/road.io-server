package com.group4.capstone.roadio.service.implementation;

import com.group4.capstone.roadio.service.AutocompleteService;
import com.group4.capstone.roadio.service.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {
    @Autowired
    WebClientService webClientService;

    @Value("${app.hereapi.key}")
    private String hereKey;

    @Override
    public Object complete(String partial, String country) {
        String url = "https://autosuggest.search.hereapi.com/v1/autosuggest?q="+partial.replace(" ", "+")+"&at=40.599830,-97.120535&in=countryCode:"+country+"&apiKey="+hereKey;
        return webClientService.getThis(url);
    }
}
