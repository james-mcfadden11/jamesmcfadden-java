-- 3. Did you know Eric Stoltz was originally cast as Marty McFly in "Back to the Future"? 
--Add Eric Stoltz to the list of actors for "Back to the Future" (1 row)
insert into movie_actor
(actor_id, movie_id)
values (
        (select distinct actor_id 
        from movie_actor 
        join person on person_id = actor_id
        where person_name = 'Eric Stoltz'), 
        (select movie_id from movie where title = 'Back to the Future')
        );



