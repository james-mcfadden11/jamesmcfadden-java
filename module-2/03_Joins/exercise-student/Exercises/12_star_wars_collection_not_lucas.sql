-- 12. The titles of the movies in the "Star Wars Collection" that weren't directed by George Lucas (5 rows)
select title
from movie
join collection using(collection_id)
join person on person_id = director_id
where collection_name = 'Star Wars Collection' and person_name != 'George Lucas';
