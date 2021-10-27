package com.techelevator.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.model.Auction;

public class AuctionService {

	private final String API_URL;
	private final String API_KEY;
	protected RestTemplate restTemplate = new RestTemplate();
    private final ConsoleService console = new ConsoleService();

    public AuctionService(String apiUrl, String apiKey) {
        API_URL = apiUrl;
        API_KEY = apiKey;
    }

    public Auction[] listAllAuctions() {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_URL + "?apikey=" + API_KEY, Auction[].class);
        } catch (RestClientResponseException ex) {
            console.printError("Could not retrieve the auctions. Is the server running?");
        } catch (ResourceAccessException ex) {
            console.printError("A network error occurred.");
        }
        return auctions;
    }

    public Auction listDetailsForAuction(int id) {
        Auction auction = null;
        try {
            auction = restTemplate.getForObject(API_URL + "/" + id + "?apikey=" + API_KEY, Auction.class);
        } catch (RestClientResponseException ex) {
            console.printError("Could not retrieve the auction.");
        } catch (ResourceAccessException ex) {
            console.printError("A network error occurred.");
        }
        return auction;
    }

    public Auction[] findAuctionsSearchTitle(String title) {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_URL + "?apikey=" + API_KEY + "&title_like=" + title, Auction[].class);
        } catch (RestClientResponseException ex) {
            console.printError("The title was not found. Please try again.");
        } catch (ResourceAccessException ex) {
            console.printError("A network error occurred.");
        }
        return auctions;
    }

    public Auction[] findAuctionsSearchPrice(double price) {
        Auction[] auctions = null;
        try {
            auctions = restTemplate.getForObject(API_URL + "?apikey=" + API_KEY + "&currentBid_lte=" + price, Auction[].class);
        } catch (RestClientResponseException ex) {
            console.printError("No auctions found. Please try again.");
        } catch (ResourceAccessException ex) {
            console.printError("A network error occurred.");
        }
        return auctions;
    }

//    API_URL --> "https://te-pgh-api.azurewebsites.net/api/auctions";

    public Auction add(String auctionString) {
        // URL --> https://te-pgh-api.azurewebsites.net/api/auctions?APIKey=03037
        String url = API_URL + "?APIKey=" + API_KEY;
        Auction auction = this.makeAuction(auctionString);
        HttpEntity<Auction> httpEntity = this.makeEntity(auction);

        try {
            return restTemplate.postForObject(url, httpEntity, Auction.class);
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println("Server was not available.");
        }
        return null;
    }

    public Auction update(String auctionString) {
        // URL --> https://te-pgh-api.azurewebsites.net/api/auctions/{id}?APIKey=03037
        Auction auction = this.makeAuction(auctionString);
        HttpEntity<Auction> httpEntity = this.makeEntity(auction);

        if (auction == null) {
            throw new IllegalArgumentException("CSV was invalid");
        }

        String url = API_URL + "/" + auction.getId() + "?APIKey=" + API_KEY;

        try {
            restTemplate.put(url, httpEntity);
            return auction;
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println("Server was not available.");
        }
        return null;
    }

    public boolean delete(int id) throws RestClientResponseException, ResourceAccessException {
        // URL --> https://te-pgh-api.azurewebsites.net/api/auctions/{id}?APIKey=03037
        String url = API_URL + "/" + id + "?APIKey=" + API_KEY;
        try {
            restTemplate.delete(url);
            return true;
        } catch (RestClientResponseException e) {
            System.out.println(e.getRawStatusCode() + " " + e.getStatusText());
        } catch (ResourceAccessException e) {
            System.out.println("Server was not available.");
        }
        return false;
    }

    private HttpEntity<Auction> makeEntity(Auction auction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Auction> entity = new HttpEntity<>(auction, headers);
        return entity;
    }

    private Auction makeAuction(String CSV) {
        String[] parsed = CSV.split(",");
        // invalid input
        if (parsed.length < 4 || parsed.length > 5) {
            return null;
        }
        // Add method does not include an id and only has 5 strings
        if (parsed.length == 4) {
            // Create a string version of the id and place into an array to be concatenated
            String[] withId = new String[6];
            Auction[] auctions = listAllAuctions();
            if (auctions == null) {
            	return null; // Some exception or other problem occurred.
            }
            String[] idArray = new String[]{auctions.length + 1 + ""};
            // place the id into the first position of the data array
            System.arraycopy(idArray, 0, withId, 0, 1);
            System.arraycopy(parsed, 0, withId, 1, 4);
            parsed = withId;
        }
        return new Auction(Integer.parseInt(parsed[0].trim()), parsed[1].trim(), parsed[2].trim(), parsed[3].trim(), Double.parseDouble(parsed[4].trim()));
    }


}
