-- 11. Hollywood is remaking the classic movie "The Blob" and doesn't have a director yet. 
--Add yourself to the person table, and assign yourself as the director of "The Blob" (the movie is already in the movie table). (1 record each)

insert into person
(person_name, birthday, biography, profile_path, home_page)
values ('James McFadden', '8/17/1992', 'Handsome software developer in Pittsburgh, PA', 'https://github.com/james-mcfadden11', 'https://www.linkedin.com/in/jamesmcfadden111/');

update movie
set director_id = (select person_id from person where person_name = 'James McFadden')
where title = 'The Blob';
