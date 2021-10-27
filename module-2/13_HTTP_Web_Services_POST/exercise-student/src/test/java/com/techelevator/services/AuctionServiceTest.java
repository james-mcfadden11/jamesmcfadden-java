package com.techelevator.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.regex.Pattern;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.auction.App;
import com.techelevator.model.Auction;

public class AuctionServiceTest {
	private final AuctionService auctionService = new AuctionService(App.API_URL, App.API_KEY);

	@Before
	public void setUp() {
		resetData();
	}

	@AfterClass
	public static void tearDown() {
		resetData();
	}

	@Test
	public void listAllAuctions() {
		// Arrange
		Auction[] expectedAuctions = {
				new Auction(1, "Bell Computer Monitor", "4K LCD monitor from Bell Computers, HDMI & DisplayPort",
						"Queenie34", 100.39),
				new Auction(2, "Pineapple Smart Watch", "Pears with Pineapple ePhone", "Miller.Fahey", 377.44),
				new Auction(3, "Mad-dog Sneakers", "Soles check. Laces check.", "Cierra_Pagac", 125.23),
				new Auction(4, "Annie Sunglasses", "Keep the sun from blinding you", "Sallie_Kerluke4", 69.67),
				new Auction(5, "Byson Vacuum", "Clean your house with a spherical vacuum", "Lisette_Crist", 287.73),
				new Auction(6, "Fony Headphones", "Listen to music, movies, games and not bother people around you!",
						"Chester67", 267.38),
				new Auction(7, "Molex Gold Watch", "Definitely not fake gold watch", "Stuart27", 188.39) };

		// Act
		Auction[] actualAuctions = auctionService.listAllAuctions();

		// Assert
		Assert.assertNotNull("Expected a list of auctions, but received null", actualAuctions);
		Assert.assertEquals(7, actualAuctions.length);
		MatcherAssert.assertThat(actualAuctions, Matchers.samePropertyValuesAs(expectedAuctions));
	}

	@Test
	public void listDetailsForAuction() {
		// Arrange
		Auction expectedAuctionOne = new Auction(1, "Bell Computer Monitor",
				"4K LCD monitor from Bell Computers, HDMI & DisplayPort", "Queenie34", 100.39);
		Auction expectedAuctionFour = new Auction(4, "Annie Sunglasses", "Keep the sun from blinding you",
				"Sallie_Kerluke4", 69.67);
		Auction expectedAuctionSeven = new Auction(7, "Molex Gold Watch", "Definitely not fake gold watch", "Stuart27",
				188.39);

		// Act
		Auction actualOne = auctionService.listDetailsForAuction(1);
		Auction actualFour = auctionService.listDetailsForAuction(4);
		Auction actualSeven = auctionService.listDetailsForAuction(7);

		// Assert
		MatcherAssert.assertThat(actualOne, Matchers.samePropertyValuesAs(expectedAuctionOne));
		MatcherAssert.assertThat(actualFour, Matchers.samePropertyValuesAs(expectedAuctionFour));
		MatcherAssert.assertThat(actualSeven, Matchers.samePropertyValuesAs(expectedAuctionSeven));
	}

	@Test
	public void findAuctionsSearchTitle() {
		// Arrange
		Auction[] expectedWatchAuctions = {
				new Auction(2, "Pineapple Smart Watch", "Pears with Pineapple ePhone", "Miller.Fahey", 377.44),
				new Auction(7, "Molex Gold Watch", "Definitely not fake gold watch", "Stuart27", 188.39) };

		// Act
		Auction[] actualWatchAuctions = auctionService.findAuctionsSearchTitle("watch");

		// Assert
		Assert.assertNotNull("Expected a list of auctions, but received null", actualWatchAuctions);
		Assert.assertEquals(2, actualWatchAuctions.length);
		MatcherAssert.assertThat(actualWatchAuctions, Matchers.samePropertyValuesAs(expectedWatchAuctions));
	}

	@Test
	public void findAuctionsSearchTitleNotFound() {
		// Arrange

		// Act
		Auction[] actualAuctions = auctionService.findAuctionsSearchTitle("NoSuchTitle");

		// Assert
		Assert.assertNotNull("Expected an empty list of auctions, but received null", actualAuctions);
		Assert.assertEquals(0, actualAuctions.length);
		MatcherAssert.assertThat(actualAuctions, Matchers.samePropertyValuesAs(new Auction[0]));
	}

	@Test
	public void findAuctionsSearchPrice() {
		// Arrange
		Auction[] expectedAuctions = {
				new Auction(1, "Bell Computer Monitor", "4K LCD monitor from Bell Computers, HDMI & DisplayPort",
						"Queenie34", 100.39),
				new Auction(3, "Mad-dog Sneakers", "Soles check. Laces check.", "Cierra_Pagac", 125.23),
				new Auction(4, "Annie Sunglasses", "Keep the sun from blinding you", "Sallie_Kerluke4", 69.67) };

		// Act
		Auction[] actualAuctions = auctionService.findAuctionsSearchPrice(150);

		// Assert
		Assert.assertNotNull("Expected a list of auctions, but received null", actualAuctions);
		Assert.assertEquals(3, actualAuctions.length);
		MatcherAssert.assertThat(actualAuctions, Matchers.samePropertyValuesAs(expectedAuctions));
	}

	@Test
	public void findAuctionsSearchPriceNotFound() {
		// Arrange

		// Act
		Auction[] actualAuctions = auctionService.findAuctionsSearchPrice(1.0);

		// Assert
		Assert.assertNotNull("Expected an empty list of auctions, but received null", actualAuctions);
		Assert.assertEquals(0, actualAuctions.length);
		MatcherAssert.assertThat(actualAuctions, Matchers.samePropertyValuesAs(new Auction[0]));
	}

	@Test
	public void add_should_return_new_auction() {
		// Arrange
		String testAddString = "a,b,c,99.99";
		Auction expectedAuction = new Auction(3, "a", "b", "c", 99.99);

		// Act
		Auction actualAuction = auctionService.add(testAddString);

		// Assert
		// Ignore id property when testing
		MatcherAssert.assertThat(actualAuction, Matchers.samePropertyValuesAs(expectedAuction, "id"));
	}

	@Test
	public void add_should_return_null_for_failure_response_code() {
		// Arrange
		Auction[] fakeAuctions = { new Auction(0, "Zero", "Zero Auction", "User0", 0.0),
				new Auction(1, "One", "One Auction", "User1", 1.1) };

		String testAddString = "a,b,c,99.99";
		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);

		when(mockRestTemplate.getForObject(anyString(), Mockito.eq(Auction[].class)))
				.thenReturn(fakeAuctions);

		when(mockRestTemplate.postForObject(anyString(), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(RestClientResponseException.class);

		when(mockRestTemplate.exchange(anyString(), Mockito.eq(HttpMethod.POST), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(RestClientResponseException.class);

		auctionService.restTemplate = mockRestTemplate;

		// Act
		Auction actualAuction = auctionService.add(testAddString);

		// Assert
		assertNull("auctionService.add() should return null when RestTemplate throws a RestClientResponseException",
				actualAuction);
	}

	@Test
	public void add_should_return_null_for_communication_failure() {
		// Arrange
		Auction[] fakeAuctions = { new Auction(0, "Zero", "Zero Auction", "User0", 0.0),
				new Auction(1, "One", "One Auction", "User1", 1.1) };

		String testAddString = "a,b,c,99.99";
		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);

		when(mockRestTemplate.getForObject(anyString(), Mockito.eq(Auction[].class)))
				.thenReturn(fakeAuctions);

		when(mockRestTemplate.postForObject(anyString(), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(ResourceAccessException.class);

		when(mockRestTemplate.exchange(anyString(), Mockito.eq(HttpMethod.POST), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(ResourceAccessException.class);

		auctionService.restTemplate = mockRestTemplate;

		// Act
		Auction actualAuction = auctionService.add(testAddString);

		// Assert
		assertNull("auctionService.add() should return null when RestTemplate throws a ResourceAccessException",
				actualAuction);
	}

	@Test
	public void update_should_return_updated_auction() {
		// Arrange
		String testAddString = "a,b,c,99.99";
		Auction addedAuction = auctionService.add(testAddString);

		if (addedAuction == null) {
			Assert.fail("Auction was not added");
		}

		String testUpdateString = addedAuction.getId() + ",b,c,d,101.01";
		Auction expectedAuction = new Auction(addedAuction.getId(), "b", "c", "d", 101.01);

		// Act
		Auction updatedAuction = auctionService.update(testUpdateString);

		// Assert
		MatcherAssert.assertThat(updatedAuction, Matchers.samePropertyValuesAs(expectedAuction));
	}

	@Test
	public void update_should_return_null_for_failure_response_code() {
		// Arrange
		String testUpdateString = "9,q,w,e,11.11";

		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);

		Mockito.doThrow(RestClientResponseException.class)
				.when(mockRestTemplate)
				.put(anyString(), any(HttpEntity.class));

		when(mockRestTemplate
				.exchange(anyString(), Mockito.eq(HttpMethod.PUT), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(RestClientResponseException.class);

		auctionService.restTemplate = mockRestTemplate;

		// Act
		Auction updatedAuction = auctionService.update(testUpdateString);

		// Assert
		assertNull("auctionService.update() should return null when RestTemplate throws a RestClientResponseException",
				updatedAuction);
	}

	@Test
	public void update_should_return_null_for_communication_failure() {
		// Arrange
		String testUpdateString = "9,q,w,e,11.11";

		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);

		Mockito.doThrow(ResourceAccessException.class)
				.when(mockRestTemplate)
				.put(anyString(), any(HttpEntity.class));

		when(mockRestTemplate
				.exchange(anyString(), Mockito.eq(HttpMethod.PUT), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(ResourceAccessException.class);

		auctionService.restTemplate = mockRestTemplate;

		// Act
		Auction updatedAuction = auctionService.update(testUpdateString);

		// Assert
		assertNull("auctionService.update() should return null when RestTemplate throws a ResourceAccessException",
				updatedAuction);
	}

	@Test
	public void delete_should_return_true_when_resource_successfully_deleted() {
		String testAddString = "a,b,c,99.99";
		Auction addedAuction = auctionService.add(testAddString);

		if (addedAuction == null) {
			Assert.fail("Auction was not added");
		}

		// Act
		boolean result = auctionService.delete(addedAuction.getId());

		// Assert
		assertTrue("auctionService.delete() should return true for successful deletion.", result);
	}

	@Test
	public void delete_should_return_false_for_failure_response_code() {
		// Arrange
		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);

		Mockito.doThrow(RestClientResponseException.class)
				.when(mockRestTemplate)
				.delete(anyString());

		Mockito.doThrow(ResourceAccessException.class)
				.when(mockRestTemplate)
				.delete(anyString(), any(HttpEntity.class));

		when(mockRestTemplate
				.exchange(anyString(), Mockito.eq(HttpMethod.PUT), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(ResourceAccessException.class);

		auctionService.restTemplate = mockRestTemplate;

		// Act
		boolean result = auctionService.delete(1);

		// Assert
		assertFalse("auctionService.delete() should return false when a RestClientResponseException is thrown.",
				result);
	}

	@Test
	public void delete_should_return_false_for_communication_failure() {
		// Arrange
		RestTemplate mockRestTemplate = Mockito.mock(RestTemplate.class);

		Mockito.doThrow(ResourceAccessException.class)
				.when(mockRestTemplate)
				.delete(anyString());

		Mockito.doThrow(ResourceAccessException.class)
				.when(mockRestTemplate)
				.delete(anyString(), any(HttpEntity.class));

		when(mockRestTemplate
				.exchange(anyString(), Mockito.eq(HttpMethod.PUT), any(HttpEntity.class), Mockito.eq(Auction.class)))
				.thenThrow(ResourceAccessException.class);

		auctionService.restTemplate = mockRestTemplate;

		// Act
		boolean result = auctionService.delete(1);

		// Assert
		assertFalse("auctionService.delete() should return false when a ResourceAccessException is thrown.", result);
	}

	private static void resetData() {
		if(App.API_URL == null || App.API_KEY == null || App.API_URL.isBlank() || App.API_KEY.isBlank()) {
			Assert.fail("Missing API_URL or API_KEY");
		} else {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject("https://te-pgh-api.azurewebsites.net/api/auctions/resetdata?apikey=" + App.API_KEY, Null.class);
		}
	}
}
