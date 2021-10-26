package com.techelevator.services;

import org.springframework.web.client.RestTemplate;

import com.techelevator.model.Auction;

public class AuctionService {

    public final String API_URL;
    public final String API_KEY;
    public RestTemplate restTemplate = new RestTemplate();
    private final ConsoleService console = new ConsoleService();

    public AuctionService(String apiUrl, String apiKey) {
        API_URL = apiUrl;
        API_KEY = apiKey;
    }

    public Auction[] listAllAuctions() {
        // api code here
        return null;
    }

    public Auction listDetailsForAuction(int id) {
        // api code here
        return null;
    }

    public Auction[] findAuctionsSearchTitle(String title) {
        // api code here
        return null;
    }

    public Auction[] findAuctionsSearchPrice(double price) {
        // api code here
        return null;
    }

}
