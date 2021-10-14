-- 2. The names and birthdays of actors in "The Fifth Element" (15 rows)
select person_name, birthday
from person
join movie_actor on person.person_id = movie_actor.actor_id
join movie using(movie_id)
where movie.title = 'The Fifth Element';
