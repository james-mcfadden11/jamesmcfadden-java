package com.techelevator.dao;

import org.junit.Assert;
import org.junit.Test;

public class JdbcMovieDaoTest extends BaseDaoTests {

    @Test
    public void getTotalRuntimeOfCollection_returns_total_runtime_of_tarantino_films() {
        JdbcMovieDao sut = new JdbcMovieDao(dataSource);
        int totalRuntime = sut.getTotalRuntimeOfCollection("Tarantino");
        Assert.assertEquals(528, totalRuntime);

    }
}
