package com.techelevator.dao;

import com.techelevator.model.Park;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class JdbcParkDao implements ParkDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcParkDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Park getPark(long parkId) {
        String sql = "select * from park where parkId = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        if (results.next()) {
            return mapRowToPark(results);
        }
        return null;

    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        String sql = "select * from park where state_abbreviation = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);

        List<Park> parks = new ArrayList<>();

        while (results.next()) {

            parks.add(mapRowToPark(results));
        }

        return parks;
    }

    @Override
    public Park createPark(Park park) {
        String sql = "insert into park (park_name, date_established, area, has_camping) " +
                "values (?, ?, ?, ?) returning park_id;";
        Long parkID = jdbcTemplate.queryForObject(sql, Long.class, park.getParkName(), park.getDateEstablished(), park.getHasCamping());

        if (parkID == null) return null;
        return getPark(parkID);

    }

    @Override
    public void updatePark(Park park) {

    }

    @Override
    public void deletePark(long parkId) {
        String sqlDeleteParkState = "delete from park_state where park_id = ?;";
        jdbcTemplate.update(sqlDeleteParkState, parkId);

        String sqlDeletePark = "delete from park where park_id = ?;";
        jdbcTemplate.update(sqlDeletePark, parkId);
    }

    @Override
    public void addParkToState(long parkId, String stateAbbreviation) {

    }

    @Override
    public void removeParkFromState(long parkId, String stateAbbreviation) {

    }

    private Park mapRowToPark(SqlRowSet rowSet) {
        Park park = new Park();

        park.setParkId(rowSet.getLong("park_id"));
        park.setParkName(rowSet.getString("park_name"));

        Date date = rowSet.getDate("date_established");
        if (date != null) {
            park.setDateEstablished(date.toLocalDate());
        }

        park.setArea(rowSet.getDouble("area"));
        park.setHasCamping(rowSet.getBoolean("has_camping"));

        return park;
    }
}
