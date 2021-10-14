-- 8. The genres of movies that Robert De Niro has appeared in that were released in 2010 or later (6 rows)
select distinct genre_name
from genre
join movie_genre using(genre_id)
join movie_actor using(movie_id)
join person on person_id = actor_id
join movie using(movie_id)
where person_name = 'Robert De Niro' and release_date >= '01/01/2010';
