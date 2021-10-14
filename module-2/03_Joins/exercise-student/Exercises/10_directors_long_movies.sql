-- 10. The names of directors who have directed a movie over 3 hours [180 minutes] (15 rows)
select distinct person_name
from person
join movie on director_id = person_id
where length_minutes > (180);
