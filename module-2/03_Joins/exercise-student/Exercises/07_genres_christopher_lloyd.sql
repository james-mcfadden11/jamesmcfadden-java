-- 7. The genres of movies that Christopher Lloyd has appeared in (8 rows)
-- Hint: DISTINCT will prevent duplicate values in your query results.
select distinct genre_name
from genre
join movie_genre using(genre_id)
join movie_actor using(movie_id)
join person on person_id = actor_id
where person_name = 'Christopher Lloyd';
