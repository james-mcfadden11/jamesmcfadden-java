-- 14. The names of actors who've appeared in the movies in the "Back to the Future Collection" (28 rows)
select distinct person_name
from person
join movie_actor on actor_id = person_id
join movie using(movie_id)
join collection using(collection_id)
where collection_name = 'Back to the Future Collection';
