

CREATE TABLE movie
(
        movie_id SERIAL PRIMARY KEY
        
);

CREATE TABLE person
(
        person_id SERIAL PRIMARY KEY
);

CREATE TABLE movie_actor
(
        movie_id INTEGER REFERENCES movie (movie_id),
        person_id INTEGER REFERENCES person (person_id),
        
        CONSTRAINT pk_movie_id_person_id PRIMARY KEY (movie_id, person_id)
);

/*
        movie
        movie_id        title
        1               Lawrence of Arabia
        2               Step Brothers
        3               Elf
        
        person
        person_id       name    
        1               Will Ferrell
        2               John C. Reilly
        
        movie_actor
        movie_id        actor_id
        2               1
        3               1
        2               2
        


*/



