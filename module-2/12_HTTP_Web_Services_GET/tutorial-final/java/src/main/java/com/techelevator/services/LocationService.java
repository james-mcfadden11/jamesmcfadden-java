package com.techelevator.services;

import com.techelevator.model.Location;
import org.springframework.web.client.RestTemplate;

public class LocationService {
	private final String API_URL;
    private final String API_KEY;
    private RestTemplate restTemplate = new RestTemplate();
    
    public LocationService(String apiUrl, String apiKey) {
    	API_URL = apiUrl;
    	API_KEY = apiKey;
    }

    public Location[] getAll() {
        return restTemplate.getForObject(API_URL + "?apikey=" + API_KEY, Location[].class);
    }

    public Location getOne(int id) {
        return restTemplate.getForObject(API_URL + "/" + id + "?apikey=" + API_KEY , Location.class);
    }

}
