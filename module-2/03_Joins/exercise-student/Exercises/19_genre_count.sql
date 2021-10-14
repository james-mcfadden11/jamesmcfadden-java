-- 19. The genre name and the number of movies in each genre. Name the count column 'num_of_movies'. 
-- (19 rows, expected result for Action is around 180).
select genre_name, count(movie_id) as num_of_movies
from movie_genre
join genre using(genre_id)
group by genre_name;
