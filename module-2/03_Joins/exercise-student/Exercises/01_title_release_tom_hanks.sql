-- 1. The titles and release dates of movies that Tom Hanks has appeared in (47 rows)
select title, release_date
from movie
join movie_actor using(movie_id)
join person on actor_id = person_id
where person_name = 'Tom Hanks';
