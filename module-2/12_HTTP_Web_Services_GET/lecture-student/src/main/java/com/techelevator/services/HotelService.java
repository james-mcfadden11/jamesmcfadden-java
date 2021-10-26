package com.techelevator.services;

import com.techelevator.model.City;
import com.techelevator.model.Hotel;
import com.techelevator.model.Review;
import org.springframework.web.client.RestTemplate;

public class HotelService {

    private final String API_BASE_URL;
    private final String API_KEY;
    private RestTemplate restTemplate = new RestTemplate();

    public HotelService(String apiURL, String apiKey) {
        API_BASE_URL = apiURL;
        API_KEY = apiKey;
    }

    public Hotel[] listHotels() {
        String url = API_BASE_URL + "hotels?apikey=" + API_KEY;
        Hotel[] hotels = restTemplate.getForObject(url, Hotel[].class);
        return hotels;
        // restTemplate.getForObject initializes the array with correct size behind the scenes
    }

    public Review[] listReviews() {
        String url = API_BASE_URL + "reviews?APIKey=" + API_KEY;
        Review[] reviews = restTemplate.getForObject(url, Review[].class);
        return reviews;
    }

    public Hotel getHotelById(int id) {
        String url = API_BASE_URL + "hotels/" + id + "?APIKey=" + API_KEY;
        return restTemplate.getForObject(url, Hotel.class);
    }

    public Review[] getReviewsByHotelId(int hotelID) {
        String url = API_BASE_URL + "hotels/" + hotelID + "/reviews?APIKey=" + API_KEY;
        return restTemplate.getForObject(url, Review[].class);
    }

    public Hotel[] getHotelsByStarRating(int stars) {
        String url = API_BASE_URL + "hotels?apikey=" + API_KEY + "&stars=" + stars;
        Hotel[] hotels = restTemplate.getForObject(url, Hotel[].class);
        return hotels;
    }

    public City getWithCustomQuery(){
        String url = "https://api.teleport.org/api/cities/geonameid:5206379";
        City city = restTemplate.getForObject(url, City.class);
        return city;
    }

}
