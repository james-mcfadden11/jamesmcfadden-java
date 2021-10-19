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

        String sql = "SELECT * FROM park WHERE park_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);

        if (results.next()) {
            return mapRowToPark(results);
        }

        return null;
    }

    @Override
    public List<Park> getParksByState(String stateAbbreviation) {
        List<Park> parks = new ArrayList<>();

        String sql = "SELECT * FROM park WHERE state_abbreviation = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, stateAbbreviation);

        while(results.next()) {
            parks.add(mapRowToPark(results));
        }

        return parks;
    }

    @Override
    public Park createPark(Park park) {
        String sql = "INSERT INTO park (park_name, date_established, area, has_camping) "
            + "VALUES (?, ?, ?, ?) RETURNING park_id;";

        Long parkId = jdbcTemplate.queryForObject(sql, Long.class,
                park.getParkName(),
                park.getDateEstablished(),
                park.getArea(),
                park.getHasCamping());

        if(parkId == null) return null;

        return getPark(parkId);
    }

    @Override
    public void updatePark(Park park) {
        String sql = "UPDATE park " +
                "SET park_name = ?, date_established = ?, area = ?, has_camping = ? " +
                "WHERE park_id = ?;";
        jdbcTemplate.update(sql, park.getParkName(), park.getDateEstablished(), park.getArea(),
                park.getHasCamping(), park.getParkId());
    }

    @Override
    public void deletePark(long parkId) {
        String sqlDeleteParkState = "DELETE FROM park_state WHERE park_id = ?;";
        String sqlDeletePark = "DELETE FROM park WHERE park_id = ?;";

        jdbcTemplate.update(sqlDeleteParkState, parkId);
        jdbcTemplate.update(sqlDeletePark, parkId);
    }

    @Override
    public void addParkToState(long parkId, String stateAbbreviation) {
        String sql = "INSERT INTO park_state (park_id, state_abbreviation) VALUES (?, ?);";
        jdbcTemplate.update(sql, parkId, stateAbbreviation);
    }

    @Override
    public void removeParkFromState(long parkId, String stateAbbreviation) {
        String sql = "DELETE FROM park_state WHERE park_id = ? AND state_abbreviation = ?;";
        jdbcTemplate.update(sql, parkId, stateAbbreviation);
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
