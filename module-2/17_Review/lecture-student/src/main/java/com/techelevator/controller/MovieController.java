package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class MovieController {
    private final MovieDao movieDao;

    public MovieController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @RequestMapping(path = "/api/movies", method = RequestMethod.GET)
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @RequestMapping(path = "/api/movies/{movieId}", method = RequestMethod.GET)
    public Movie get(@PathVariable int movieId) {
        return movieDao.getMovieById(movieId);
    }

    @PreAuthorize("hasRole('Admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/api/movies/{movieId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int movieId) {
        movieDao.delete(movieId);
    }

    @PreAuthorize("isAuthenticate()")
    @RequestMapping(path = "/api/movies/favorites", method = RequestMethod.GET)
    public List<Movie> myFavorites(Principal principal) {
        movieDao.getFavoritesListFor(principal.getName());
    }





}
