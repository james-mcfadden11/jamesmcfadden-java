package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JdbcMovieDao implements MovieDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Movie create(Movie movie) {
        return null;
    }

    @Override
    public Movie getMovieById(int movieId) {
        return null;
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }

    @Override
    public void update(Movie movie) {
        String sql = "update movie\n" +
                "set     title = ?, \n" +
                "        overview = ?,\n" +
                "        tagline = ?,\n" +
                "        poster_path = ?,\n" +
                "        home_page = ?,\n" +
                "        release_date = ?,\n" +
                "        length_minutes = ?,\n" +
                "        director_id = ?,\n" +
                "        collection_id = ?\n" +
                "where movie_id = ?;";
        jdbcTemplate.update(sql,
                movie.getTitle(),
                movie.getOverview(),
                movie.getTagline(),
                movie.getPosterPath(),
                movie.getHomePage(),
                movie.getReleaseDate(),
                movie.getLengthMinutes(),
                movie.getDirectorId(),
                movie.getCollectionId(),
                movie.getMovieId());
    }

    @Override
    public void delete(int movieId) {

    }

    @Override
    public List<Movie> getAllByDirector(Integer directorId) {
        return null;
    }

    @Override
    public List<Movie> getAllActedIn(int actorId) {
        return null;
    }

    @Override
    public int getTotalRuntimeOfCollection(String collectionName) {
        String sql = "select sum(length_minutes) as total_runtime from movie join collection using(collection_id) where collection_name = ?;";
        Integer totalRuntime = jdbcTemplate.queryForObject(sql, Integer.class, collectionName);
        if (totalRuntime == null) {
            return 0;
        } else {
            return totalRuntime;
        }
    }

    @Override
    public double getAverageRuntimeByDirector(int directorId) {
        return 0;
    }

    @Override
    public Movie getRandomMovieByDirector(int directorId) {
        return null;
    }

    @Override
    public Movie getRandomMovieByGenre(String genreName) {
        String sql = "select movie.* from movie join movie_genre using (movie_id) " +
                "join genre genre using (genre_id) " +
                "where genre_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreName);
        List<Movie> moviesForGenre = new ArrayList<>();

        while (results.next()) {
            Movie movie = mapRowToMovie(results);
            moviesForGenre.add(movie);
        }

        Random random = new Random();
        int randomMovieIndex = random.nextInt(moviesForGenre.size());

        return moviesForGenre.get(randomMovieIndex);
    }

    private Movie mapRowToMovie(SqlRowSet results) {
        Movie movie = new Movie();
        movie.setTitle(results.getString(results.getInt("movie_id")));
        movie.setOverview(results.getString("overview"));
        movie.setTagline(results.getString("tagline"));
        movie.setPosterPath(results.getString("poster_path"));
        movie.setHomePage(results.getString("home_page"));

        Date releaseDate = results.getDate("release_date");
        if (releaseDate != null) {
            movie.setReleaseDate(releaseDate.toLocalDate());
        }

        movie.setLengthMinutes(results.getInt("length_minutes"));
        movie.setDirectorId(results.getInt("director_id"));
        movie.setCollectionId(results.getInt("collection_id"));

        return movie;
    }

    @Override
    public int getTimesCollaborated(int directorId, int actorId) {
        return 0;
    }
}
