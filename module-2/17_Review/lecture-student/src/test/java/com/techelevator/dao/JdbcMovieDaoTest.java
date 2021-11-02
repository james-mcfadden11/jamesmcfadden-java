package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.junit.Assert;
import org.junit.Test;

public class JdbcMovieDaoTest extends BaseDaoTests {

    @Test
    public void getTotalRuntimeOfCollection_returns_total_runtime_of_tarantino_films() {
        // Arrange
        JdbcMovieDao sut = new JdbcMovieDao(dataSource);

        // Act
        int totalRuntime = sut.getTotalRuntimeOfCollection("Tarantino");

        // Assert
        Assert.assertEquals(528, totalRuntime);
    }

    @Test
    public void getTotalRuntimeOfCollection_returns_0_for_invalid_collection() {
        // Arrange
        JdbcMovieDao sut = new JdbcMovieDao(dataSource);

        // Act
        int totalRuntime = sut.getTotalRuntimeOfCollection("Blah Blah Blah");

        // Assert
        Assert.assertEquals(0, totalRuntime);
    }

    @Test
    public void update_changes_movie() {
        // Arrange
        JdbcMovieDao sut = new JdbcMovieDao(dataSource);
        Movie movie = sut.getMovieById(1);
        movie.setTitle("Pulp Fiction 2");
        movie.setLengthMinutes(120);
        movie.setCollectionId(null);
        movie.setDirectorId(null);

        // Act
        sut.update(movie);

        // Assert
        Movie updatedMovie = sut.getMovieById(1);
        Assert.assertEquals(movie.getTitle(), updatedMovie.getTitle());
        Assert.assertEquals(movie.getLengthMinutes(), updatedMovie.getLengthMinutes());
        Assert.assertEquals(movie.getCollectionId(), updatedMovie.getCollectionId());
    }
}
