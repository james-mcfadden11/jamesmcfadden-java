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
            new Park(-1, "Park 4", LocalDate.parse("2021-10-20"), 400, true);

    private JdbcParkDao sut;

    @Before
    public void setup() {
        sut = new JdbcParkDao(dataSource);
    }

    @Test
    public void getPark_returns_correct_park_for_id() {
        // Act
        Park actualPark = sut.getPark(1);

        // Assert
        assertParksMatch(PARK_1, actualPark);
    }

    @Test
    public void getPark_returns_null_when_id_not_found() {
        // Act
        Park actualPark = sut.getPark(-1);

        // Assert
        Assert.assertNull(actualPark);
    }

    @Test
    public void getParksByState_returns_all_parks_for_state() {
        // Act
        List<Park> actualParks = sut.getParksByState("AA");

        // Assert
        Assert.assertNotNull(actualParks);
        Assert.assertEquals(2, actualParks.size());
        assertParksMatch(PARK_1, actualParks.get(0));
        assertParksMatch(PARK_3, actualParks.get(1));
    }

    @Test
    public void getParksByState_returns_empty_list_for_abbreviation_not_in_db() {
        // Act
        List<Park> actualParks = sut.getParksByState("EE");

        // Assert
        Assert.assertNotNull(actualParks);
        Assert.assertEquals(0, actualParks.size());
    }

    @Test
    public void createPark_returns_park_with_id_and_expected_values() {
        // Act
        Park actualNewPark = sut.createPark(TEST_PARK);

        TEST_PARK.setParkId(actualNewPark.getParkId());

        // Assert
        assertParksMatch(TEST_PARK, actualNewPark);
    }

    @Test
    public void getPark_returns_created_park_when_retrieved() {
        // Arrange
        Park createdPark = sut.createPark(TEST_PARK);

        // Act
        Park actualPark = sut.getPark(createdPark.getParkId());

        // Assert
        assertParksMatch(createdPark, actualPark);
    }

    @Test
    public void updated_park_has_expected_values_when_retrieved() {
        // Arrange
        Park existingPark = sut.getPark(1);
        existingPark.setHasCamping(!existingPark.getHasCamping());
        existingPark.setArea(existingPark.getArea() + 100);
        existingPark.setDateEstablished(existingPark.getDateEstablished().plusMonths(1));
        existingPark.setParkName(existingPark.getParkName() + "ABCD");

        // Act
        sut.updatePark(existingPark);
        Park updatedPark = sut.getPark(1);

        // Assert
        assertParksMatch(existingPark, updatedPark);
    }

    @Test
    public void deleted_park_cant_be_retrieved() {
        // Act
        sut.deletePark(3);

        // Assert
        Assert.assertNull(sut.getPark(3));
    }

    @Test
    public void park_added_to_state_is_in_list_of_parks_by_state() {
        // Act
        sut.addParkToState(1, "BB");

        // Assert
        List<Park> actualParks = sut.getParksByState("BB");
        Assert.assertNotNull(actualParks);
        Assert.assertTrue(actualParks.size() >= 1);
        assertParksMatch(PARK_1, actualParks.get(0));
    }

    @Test
    public void park_removed_from_state_is_not_in_list_of_parks_by_state() {
        // Act
        sut.removeParkFromState(3, "CC");

        // Assert
        List<Park> actualParks = sut.getParksByState("CC");
        Assert.assertNotNull(actualParks);
        Assert.assertEquals(0, actualParks.size());
    }

    private void assertParksMatch(Park expected, Park actual) {
        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getParkName(), actual.getParkName());
        Assert.assertEquals(expected.getDateEstablished(), actual.getDateEstablished());
        Assert.assertEquals(expected.getArea(), actual.getArea(), 0.1);
        Assert.assertEquals(expected.getHasCamping(), actual.getHasCamping());
    }

}
