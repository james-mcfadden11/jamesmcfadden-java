-- 6. The genres of "The Wizard of Oz" (3 rows)
select genre_name
from genre
join movie_genre using(genre_id)
join movie using(movie_id)
where title = 'The Wizard of Oz';