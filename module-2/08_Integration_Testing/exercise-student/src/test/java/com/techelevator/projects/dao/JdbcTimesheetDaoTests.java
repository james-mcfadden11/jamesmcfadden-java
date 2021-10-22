package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1L, 1L, 1L, 
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2L, 1L, 1L,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3L, 2L, 1L,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4L, 2L, 2L,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");

    // creating a new Timesheet since timesheet_id increments with each new one added
    final Timesheet TEST_TIMESHEET = new Timesheet(5L, 2L, 2L,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 5");

    private JdbcTimesheetDao sut;

    @Before
    public void setup() {
        sut = new JdbcTimesheetDao(dataSource);
    }

    @Test
    public void getTimesheet_returns_correct_timesheet_for_id() {
        Timesheet expected = sut.getTimesheet(1L);
        assertTimesheetsMatch(expected, TIMESHEET_1);
    }

    @Test
    public void getTimesheet_returns_null_when_id_not_found() {
        Timesheet expected = sut.getTimesheet(987654L);
        Assert.assertNull(expected);
    }

    // bug - used if instead of while
    @Test
    public void getTimesheetsByEmployeeId_returns_list_of_all_timesheets_for_employee() {
        List<Timesheet> actual = sut.getTimesheetsByEmployeeId(1L);
        List<Timesheet> expected = new ArrayList<>();
        expected.add(TIMESHEET_1);
        expected.add(TIMESHEET_2);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.size(), actual.size());
        assertTimesheetsMatch(actual.get(0), expected.get(0));
        assertTimesheetsMatch(actual.get(1), expected.get(1));
    }

    // bug - String sql matched employee_id, not project_id
    @Test
    public void getTimesheetsByProjectId_returns_list_of_all_timesheets_for_project() {
        List<Timesheet> actual = sut.getTimesheetsByProjectId(1L);
        List<Timesheet> expected = new ArrayList<>();
        expected.add(TIMESHEET_1);
        expected.add(TIMESHEET_2);
        expected.add(TIMESHEET_3);

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.size(), actual.size());
        assertTimesheetsMatch(actual.get(0), expected.get(0));
        assertTimesheetsMatch(actual.get(1), expected.get(1));
        assertTimesheetsMatch(actual.get(2), expected.get(2));
    }

    @Test
    public void createTimesheet_returns_timesheet_with_id_and_expected_values() {
        Timesheet actual = sut.createTimesheet(TEST_TIMESHEET);
        assertTimesheetsMatch(TEST_TIMESHEET, actual);
    }

    @Test
    public void created_timesheet_has_expected_values_when_retrieved() {
        sut.createTimesheet(TEST_TIMESHEET);
        Timesheet actual = sut.getTimesheet(5L);
        assertTimesheetsMatch(TEST_TIMESHEET, actual);
    }

    // bug - method does not update isBillable
    // also does not check if employee_id or project_id are valid for given timesheet_id - assuming this is not the bug
    // aka, method does not allow to insert ANY value into employee_id or project_id
    // fixing that issue would require validation of employeeId and projectId parameters to match the timesheet ID
    @Test
    public void updated_timesheet_has_expected_values_when_retrieved() {
        Timesheet updatedTimeSheet = sut.getTimesheet(1L);
        updatedTimeSheet.setEmployeeId(updatedTimeSheet.getEmployeeId() + 1);
        updatedTimeSheet.setProjectId(updatedTimeSheet.getProjectId() + 1);
        updatedTimeSheet.setBillable(!updatedTimeSheet.isBillable());
        updatedTimeSheet.setDateWorked(updatedTimeSheet.getDateWorked().plusDays(5));
        updatedTimeSheet.setDescription(updatedTimeSheet.getDescription() + " Additional description");
        updatedTimeSheet.setHoursWorked(updatedTimeSheet.getHoursWorked() + 5);

        sut.updateTimesheet(updatedTimeSheet);
        Timesheet retrievedUpdatedTimesheet = sut.getTimesheet(1L);

        assertTimesheetsMatch(updatedTimeSheet, retrievedUpdatedTimesheet);
    }

    @Test
    public void deleted_timesheet_cant_be_retrieved() {
        sut.deleteTimesheet(1L);
        Assert.assertNull(sut.getTimesheet(1L));
    }

    // bug - does not account for isBillable()
    @Test
    public void getBillableHours_returns_correct_total() {
        // trying all 4 different combinations of employeeId and projectId
        // hard coding values instead of 'TIMESHEET_1.getemployeeId()' etc. etc.
        double actual;
        double expected;

        actual = sut.getBillableHours(1L, 1L);
        expected = 2.5;
        Assert.assertEquals(expected, actual, 0.1);

        actual = sut.getBillableHours(1L, 2L);
        expected = 0.0;
        Assert.assertEquals(expected, actual, 0.1);

        actual = sut.getBillableHours(2L, 1L);
        expected = 0.25;
        Assert.assertEquals(expected, actual, 0.1);

        actual = sut.getBillableHours(2L, 2L);
        expected = 0.0;
        Assert.assertEquals(expected, actual, 0.1);

    }

    private void assertTimesheetsMatch(Timesheet expected, Timesheet actual) {
        Assert.assertEquals(expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(expected.isBillable(), actual.isBillable());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

}
