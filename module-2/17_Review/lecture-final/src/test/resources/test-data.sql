BEGIN TRANSACTION;

DROP TABLE IF EXISTS movie_genre, movie_actor, movie, person, genre, collection;

CREATE TABLE collection (collection_id SERIAL NOT NULL, collection_name CHARACTER VARYING(200) NOT NULL, CONSTRAINT pk_collection PRIMARY KEY (collection_id));
CREATE TABLE genre (genre_id SERIAL NOT NULL, genre_name CHARACTER VARYING(50) NOT NULL, CONSTRAINT pk_genre PRIMARY KEY (genre_id));
CREATE TABLE person (person_id SERIAL NOT NULL, person_name CHARACTER VARYING(200) NOT NULL, birthday DATE, deathday DATE, biography TEXT, profile_path CHARACTER VARYING(200), home_page CHARACTER VARYING(200), CONSTRAINT pk_person PRIMARY KEY (person_id));
CREATE TABLE movie (movie_id SERIAL NOT NULL, title CHARACTER VARYING(200) NOT NULL, overview TEXT, tagline CHARACTER VARYING(400), poster_path CHARACTER VARYING(200), home_page CHARACTER VARYING(200), release_date DATE, length_minutes INTEGER NOT NULL, director_id INTEGER, collection_id INTEGER, CONSTRAINT pk_movie PRIMARY KEY (movie_id), CONSTRAINT fk_movie_director FOREIGN KEY (director_id) REFERENCES "person" ("person_id"), CONSTRAINT fk_movie_collection FOREIGN KEY (collection_id) REFERENCES "collection" ("collection_id"));
CREATE TABLE movie_actor (movie_id INTEGER NOT NULL, actor_id INTEGER NOT NULL, CONSTRAINT pk_movie_actor PRIMARY KEY (movie_id, actor_id), CONSTRAINT fk_movie_actor_movie FOREIGN KEY (movie_id) REFERENCES "movie" ("movie_id"), CONSTRAINT fk_movie_actor_actor FOREIGN KEY (actor_id) REFERENCES "person" ("person_id"));
CREATE TABLE movie_genre (movie_id INTEGER NOT NULL, genre_id INTEGER NOT NULL, CONSTRAINT pk_movie_genre PRIMARY KEY (movie_id, genre_id), CONSTRAINT fk_movie_genre_movie FOREIGN KEY (movie_id) REFERENCES "movie" ("movie_id"), CONSTRAINT fk_movie_genre_genre FOREIGN KEY (genre_id) REFERENCES "genre" ("genre_id"));

INSERT INTO collection (collection_name) VALUES ('Tarantino');

INSERT INTO movie
        (title, length_minutes, collection_id)
VALUES  ('Pulp Fiction', 90, 1),
        ('Reservoir Dogs', 90, 1),
        ('Django Unchained', 180, 1),
        ('Hateful 8', 168, 1);

INSERT INTO movie
        (title, length_minutes)
VALUES  ('The Notebook', 60);

COMMIT;