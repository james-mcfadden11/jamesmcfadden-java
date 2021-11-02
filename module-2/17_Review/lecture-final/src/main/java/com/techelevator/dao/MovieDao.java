package com.techelevator.dao;

import com.techelevator.model.Movie;

import java.util.List;

public interface MovieDao {

    Movie create(Movie movie);

    Movie getMovieById(int movieId);

    List<Movie> getAll();

    void update(Movie movie);

    void delete(int movieId);

    List<Movie> getAllByDirector(Integer directorId);

    List<Movie> getAllActedIn(int actorId);

    int getTotalRuntimeOfCollection(String collectionName);

    double getAverageRunTimeByDirector(int directorId);

    Movie getRandomByDirector(int directorId);
    Movie getRandomByGenre(String genreName);

    int getTimesCollaborated(int directorId, int actorId);

    List<Movie> getFavoritesListFor(String username);
}
