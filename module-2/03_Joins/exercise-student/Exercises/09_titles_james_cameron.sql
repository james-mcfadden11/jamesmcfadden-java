-- 9. The titles of movies directed by James Cameron (6 rows)
select title
from movie
join person on person_id = director_id
where person_name = 'James Cameron';
