package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {
    // CRUD methods
    Movie create(Movie movie);
    Movie getMovieById(int movieId);
    List<Movie> getAll();
    void update(Movie movie);
    void delete(int movieId);

    // additional methods
    List<Movie> getAllByDirector(Integer directorId);
    List<Movie> getAllActedIn(int actorId);
    int getTotalRuntimeOfCollection(String collectionName);
    double getAverageRuntimeByDirector(int directorId);
    Movie getRandomMovieByDirector(int directorId);
    Movie getRandomMovieByGenre(String genreName);
    int getTimesCollaborated(int directorId, int actorId);

}
