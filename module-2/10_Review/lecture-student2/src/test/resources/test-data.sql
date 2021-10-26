CREATE TABLE collection (collection_id INTEGER NOT NULL, collection_name CHARACTER VARYING(200) NOT NULL, CONSTRAINT pk_collection PRIMARY KEY (collection_id));
CREATE TABLE genre (genre_id INTEGER NOT NULL, genre_name CHARACTER VARYING(50) NOT NULL, CONSTRAINT pk_genre PRIMARY KEY (genre_id));
CREATE TABLE person (person_id INTEGER NOT NULL, person_name CHARACTER VARYING(200) NOT NULL, birthday DATE, deathday DATE, biography TEXT, profile_path CHARACTER VARYING(200), home_page CHARACTER VARYING(200), CONSTRAINT pk_person PRIMARY KEY (person_id));
CREATE TABLE movie (movie_id INTEGER NOT NULL, title CHARACTER VARYING(200) NOT NULL, overview TEXT, tagline CHARACTER VARYING(400), poster_path CHARACTER VARYING(200), home_page CHARACTER VARYING(200), release_date DATE, length_minutes INTEGER NOT NULL, director_id INTEGER, collection_id INTEGER, CONSTRAINT pk_movie PRIMARY KEY (movie_id), CONSTRAINT fk_movie_director FOREIGN KEY (director_id) REFERENCES "person" ("person_id"), CONSTRAINT fk_movie_collection FOREIGN KEY (collection_id) REFERENCES "collection" ("collection_id"));
CREATE TABLE movie_actor (movie_id INTEGER NOT NULL, actor_id INTEGER NOT NULL, CONSTRAINT pk_movie_actor PRIMARY KEY (movie_id, actor_id), CONSTRAINT fk_movie_actor_movie FOREIGN KEY (movie_id) REFERENCES "movie" ("movie_id"), CONSTRAINT fk_movie_actor_actor FOREIGN KEY (actor_id) REFERENCES "person" ("person_id"));
CREATE TABLE movie_genre (movie_id INTEGER NOT NULL, genre_id INTEGER NOT NULL, CONSTRAINT pk_movie_genre PRIMARY KEY (movie_id, genre_id), CONSTRAINT fk_movie_genre_movie FOREIGN KEY (movie_id) REFERENCES "movie" ("movie_id"), CONSTRAINT fk_movie_genre_genre FOREIGN KEY (genre_id) REFERENCES "genre" ("genre_id"));

insert into collection
        (collection_id, collection_name)
values  (1, 'Tarantino');

insert into movie(movie_id, title, length_minutes, collection_id)
values(1, 'pulp fiction', 90, 1),
(2, 'reservoir dogs', 90, 1),
(3, 'django unchained', 180, 1),
(4, 'hateful 8', 168, 1);

insert into movie
(title, length_minutes)
values(5, 'boring documentary', 60);