-- 15. The title of the movie and the name of director for movies where the director was also an actor in the same movie (73 rows)
select title, person_name
from movie
join person on director_id = person_id
join movie_actor using(movie_id)
where actor_id = director_id;
