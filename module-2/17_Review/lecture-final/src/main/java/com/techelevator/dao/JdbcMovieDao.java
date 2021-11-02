package com.techelevator.dao;

import com.techelevator.model.Movie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class JdbcMovieDao implements MovieDao{
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
        String sql = "SELECT * FROM movie WHERE movie_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, movieId);
        if(results.next()) {
            return mapRowToMovie(results);
        }

        return null;
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movie;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            movies.add(mapRowToMovie(results));
        }

        return movies;
    }

    @Override
    public void update(Movie movie) {
        String sql = "UPDATE movie\n" +
                "SET     title = ?,\n" +
                "        overview = ?,\n" +
                "        tagline = ?,\n" +
                "        poster_path = ?,\n" +
                "        home_page = ?,\n" +
                "        release_date = ?,\n" +
                "        length_minutes = ?,\n" +
                "        director_id = ?,\n" +
                "        collection_id = ?\n" +
                "WHERE movie_id = ?;";

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
        String sql = "SELECT SUM(length_minutes) AS total_runtime\n" +
                "FROM movie\n" +
                "JOIN collection USING (collection_id)\n" +
                "WHERE collection_name = ?;";

        Integer totalRuntime = jdbcTemplate.queryForObject(sql, Integer.class, collectionName);

        return totalRuntime == null ? 0 : totalRuntime;
    }

    @Override
    public double getAverageRunTimeByDirector(int directorId) {
        return 0;
    }

    @Override
    public Movie getRandomByDirector(int directorId) {
        return null;
    }

    @Override
    public Movie getRandomByGenre(String genreName) {
        String sql = "SELECT movie.*\n" +
                "FROM movie\n" +
                "JOIN movie_genre USING (movie_id)\n" +
                "JOIN genre USING (genre_id)\n" +
                "WHERE genre_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, genreName);

        List<Movie> moviesForGenre = new ArrayList<>();

        while(results.next()) {
            Movie movie = mapRowToMovie(results);

            moviesForGenre.add(movie);
        }

        Random random = new Random();
        int randomMovieIndex = random.nextInt(moviesForGenre.size());

        return moviesForGenre.get(randomMovieIndex);
    }

    private Movie mapRowToMovie(SqlRowSet results) {
        Movie movie = new Movie();

        movie.setMovieId(results.getInt("movie_id"));
        movie.setTitle(results.getString("title"));
        movie.setOverview(results.getString("overview"));
        movie.setTagline(results.getString("tagline"));
        movie.setPosterPath(results.getString("poster_path"));
        movie.setHomePage(results.getString("home_page"));

        Date releaseDate = results.getDate("release_date");
        if(releaseDate != null) {
            movie.setReleaseDate(releaseDate.toLocalDate());
        }

        movie.setLengthMinutes(results.getInt("length_minutes"));
        movie.setDirectorId(results.getInt("director_id"));
        if(results.wasNull()) {
            movie.setDirectorId(null);
        }
        movie.setCollectionId(results.getInt("collection_id"));
        if(results.wasNull()) {
            movie.setCollectionId(null);
        }

        return movie;
    }

    @Override
    public int getTimesCollaborated(int directorId, int actorId) {
        return 0;
    }

    @Override
    public List<Movie> getFavoritesListFor(String username) {
        return null;
    }
}
