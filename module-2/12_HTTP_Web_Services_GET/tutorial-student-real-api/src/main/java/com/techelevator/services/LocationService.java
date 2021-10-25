package com.techelevator.services;

import com.techelevator.model.Location;

public class LocationService {
	private final String API_URL;
    private final String API_KEY;
    
	public LocationService(String apiUrl, String apiKey) {
    	API_URL = apiUrl;
    	API_KEY = apiKey;
    }

    public Location[] getAll() {
        return null;
    }

    public Location getOne(int id) {
        return null;
    }

}
