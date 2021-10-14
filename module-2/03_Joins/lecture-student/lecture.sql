-- INNER JOIN

-- Write a query to retrieve the name and state abbreviation for the 2 cities named "Columbus" in the database
select state_name, state.state_abbreviation, city.city_name
from state
inner join city on state.state_abbreviation = city.state_abbreviation
where city_name = 'Columbus';

-- Modify the previous query to retrieve the names of the states (rather than their abbreviations).


-- Write a query to retrieve the names of all the national parks with their state abbreviations.
-- (Some parks will appear more than once in the results, because they cross state boundaries.)
select park_name, state_abbreviation
from park
-- join park_state on park.park_id = park_state.park_id;
join park_state using(park_id);
-- inner join is default 

-- The park_state table is an associative table that can be used to connect the park and state tables.
-- Modify the previous query to retrieve the names of the states rather than their abbreviations.
select park_name, state_name
from park
join park_state using(park_id)
join state using(state_abbreviation);

-- Modify the previous query to include the name of the state's capital city.
select park_name, state_name
from park
join park_state using(park_id)
join state using(state_abbreviation);

-- Modify the previous query to include the area of each park.
select park_name, state_name, city_name as state_capital, park.area as park_area
from park
join park_state using(park_id)
join state using(state_abbreviation)
join city on city.city_id = state.capital;

-- Write a query to retrieve the names and populations of all the cities in the Midwest census region.
select city_name, city.population, state_name
from city
join state using(state_abbreviation)
where state.census_region = 'Midwest';

-- Write a query to retrieve the number of cities in the city table for each state in the Midwest census region.
select count(city_name), state_name
from city
join state using(state_abbreviation)
where state.census_region = 'Midwest'
group by state_name;

-- Modify the previous query to sort the results by the number of cities in descending order.
select count(city_name), state_name
from city
join state using(state_abbreviation)
where state.census_region = 'Midwest'
group by state_name
order by count(city_name) desc;


-- LEFT JOIN

-- Write a query to retrieve the state name and the earliest date a park was established in that state (or territory) for every record in the state table that has park records associated with it.
select state_name, min(date_established)
from state
join park_state using(state_abbreviation)
join park using(park_id)
group by state_name;

-- Modify the previous query so the results include entries for all the records in the state table, even if they have no park records associated with them.
select state_name, min(date_established)
from state
left join park_state using(state_abbreviation)
left join park using(park_id)
group by state_name;


-- UNION

-- Write a query to retrieve all the place names in the city and state tables that begin with "W" sorted alphabetically. (Washington is the name of a city and a state--how many times does it appear in the results?)
select city_name as place from city where city_name like 'W%' 
union all
select state_name as place from state where state_name like 'W%' 
order by place;
--throws out duplicates "union"

-- Modify the previous query to include a column that indicates whether the place is a city or state.
select city_name as place, 'City' as identifier from city where city_name like 'W%' 
union all
select state_name as place, 'State' as identifier from state where state_name like 'W%' 
order by place;


-- MovieDB
-- After creating the MovieDB database and running the setup script, make sure it is selected in DbVisualizer and confirm it is working correctly by writing queries to retrieve...

-- The names of all the movie genres
select genre_name from genre;

-- The titles of all the Comedy movies
select title
from movie
join movie_genre using(movie_id)
join genre using(genre_id)
where genre_name = 'Comedy';
