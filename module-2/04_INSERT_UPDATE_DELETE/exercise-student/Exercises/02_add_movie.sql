-- 2. Add "Euclidean Pi" to the movie table.
--The overview is "The epic story of Euclid as a pizza delivery boy in ancient Greece" and was released 3/14/2015. 
--Since its an epic, the run length is 3 hours and 14 minutes (194 minutes). (1 row)
insert into movie
(title, overview, release_date, length_minutes) 
values ('Euclidean Pi', 'The epic story of Euclid as a pizza delivery boy in ancient Greece', '3/14/2015', 194);