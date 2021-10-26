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
        String url = this.API_URL + "?apikey=" + this.API_KEY;
        return restTemplate.getForObject(url, Auction[].class);
    }

    public Auction listDetailsForAuction(int id) {
        String url = this.API_URL + "/" + id + "?apikey=" + this.API_KEY;
        return restTemplate.getForObject(url, Auction.class);
    }

    public Auction[] findAuctionsSearchTitle(String title) {
        String url = this.API_URL + "?title_like=" + title + "&apikey=" + this.API_KEY;
        return restTemplate.getForObject(url, Auction[].class);
    }

    public Auction[] findAuctionsSearchPrice(double price) {
        String url = this.API_URL + "?currentBid_lte=" + price + "&apikey=" + this.API_KEY;
        return restTemplate.getForObject(url, Auction[].class);
    }

}
