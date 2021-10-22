package com.techelevator.dao;

import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JdbcParkDaoTests extends BaseDaoTests {

    private static final Park PARK_1 =
            new Park(1, "Park 1", LocalDate.parse("1800-01-02"), 100, true);
    private static final Park PARK_2 =
            new Park(2, "Park 2", LocalDate.parse("1900-12-31"), 200, false);
    private static final Park PARK_3 =
            new Park(3, "Park 3", LocalDate.parse("2000-06-15"), 300, false);

    private static final Park TEST_PARK =
            new Park(4, "Park 4", LocalDate.parse("2000-10-20"), 400, true);


    private JdbcParkDao sut;

    @Before
    public void setup() {
        sut = new JdbcParkDao(dataSource);
    }

    @Test
    public void getPark_returns_correct_park_for_id() {
        // arrange
        // act
        Park actualPark = sut.getPark(1);
        // assert
        this.assertParksMatch(PARK_1, actualPark);
    }

    @Test
    public void getPark_returns_null_when_id_not_found() {
        // arrange
        // act
        Park actualPark = sut.getPark(-1);
        // assert
        Assert.assertNull(actualPark);
    }

    @Test
    public void getParksByState_returns_all_parks_for_state() {
        List<Park> actualParks = sut.getParksByState("AA");
        Assert.assertNotNull(actualParks);
        Assert.assertEquals(2, actualParks.size());
        assertParksMatch(PARK_1, actualParks.get(0));
        assertParksMatch(PARK_3, actualParks.get(1));
    }

    @Test
    public void getParksByState_returns_empty_list_for_abbreviation_not_in_db() {
        List<Park> actualParks = sut.getParksByState("EE");
        Assert.assertNotNull(actualParks);
        Assert.assertEquals(0, actualParks.size());
    }

    @Test
    public void createPark_returns_park_with_id_and_expected_values() {
        Park actualNewPark = sut.createPark(TEST_PARK);
        assertParksMatch(TEST_PARK, actualNewPark);
    }

    @Test
    public void created_park_has_expected_values_when_retrieved() {
        // arrange
        Park createdPark = sut.createPark(TEST_PARK);
        // act
        Park actualPark = sut.getPark(createdPark.getParkId());
        // assert
        assertParksMatch(createdPark, actualPark);
    }

    @Test
    public void updated_park_has_expected_values_when_retrieved() {
        Park existingPark = sut.getPark(1);
        existingPark.setHasCamping(!existingPark.getHasCamping());
        existingPark.setArea(existingPark.getArea() + 100);
        existingPark.setDateEstablished(existingPark.getDateEstablished().plusDays(2));
        existingPark.setParkName(existingPark.getParkName() + "abcd");

        sut.updatePark(existingPark);
        Park updatedPark = sut.getPark(1);

        assertParksMatch(existingPark, updatedPark);

    }

    @Test
    public void deleted_park_cant_be_retrieved() {
        sut.deletePark(3);
        Assert.assertNull(sut.getPark(3));
    }

    @Test
    public void park_added_to_state_is_in_list_of_parks_by_state() {
        sut.addParkToState(1, "BB");
        List<Park> actualParks = sut.getParksByState("BB");
        Assert.assertNotNull(actualParks);
        Assert.assertTrue(actualParks.size() >= 1);
        assertParksMatch(PARK_1, actualParks.get(0));
    }

    @Test
    public void park_removed_from_state_is_not_in_list_of_parks_by_state() {
        sut.removeParkFromState(3, "CC");
        List<Park> actualParks = sut.getParksByState("CC");
        Assert.assertNotNull(actualParks);
        Assert.assertTrue(actualParks.size() == 0);
    }

    private void assertParksMatch(Park expected, Park actual) {
        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getParkName(), actual.getParkName());
        Assert.assertEquals(expected.getDateEstablished(), actual.getDateEstablished());
        Assert.assertEquals(expected.getArea(), actual.getArea(), 0.1);
        Assert.assertEquals(expected.getHasCamping(), actual.getHasCamping());
    }

}
