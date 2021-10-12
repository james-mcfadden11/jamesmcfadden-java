-- ORDERING RESULTS

-- Populations of all states from largest to smallest.
SELECT state_name, population FROM state ORDER BY population DESC;

-- States sorted alphabetically (A-Z) within their census region. The census regions are sorted in reverse alphabetical (Z-A) order.
select * from state order by census_region desc, state_name asc;

-- The biggest park by area
select * from park order by area desc;


-- LIMITING RESULTS

-- The 10 largest cities by populations
select * from city order by population desc limit 10;

-- The 20 oldest parks from oldest to youngest in years, sorted alphabetically by name.
select (current_date - date_established) as age, park_name from park order by age desc, park_name limit 20;



-- CONCATENATING OUTPUTS

-- All city names and their state abbreviation.
select (city_name || ', ' || state_abbreviation) as formatted_city_name from city;

-- The all parks by name and date established.
select 'Name: ' || park_name || ', Established: ' || date_established as formatted_name from park;

-- The census region and state name of all states in the West & Midwest sorted in ascending order.
select 'Region: ' || census_region || ', Name: ' || state_name as region_state 
from state 
where census_region in ('West', 'Midwest') 
order by state_name asc;


-- AGGREGATE FUNCTIONS

-- Average population across all the states. Note the use of alias, common with aggregated values.
select avg(population) :: int as avg_population
from state;

-- Total population in the West and South census regions
select sum(population) as sum_population from state
where census_region in ('West', 'South');

-- The number of cities with populations greater than 1 million
select count(*) as greater_than_mil 
from city
where population > 1000000;

-- The number of state nicknames.
select count(state_nickname) as states_w_nicknames from state;
-- count does not count null values

-- The area of the smallest and largest parks.
select min(area), max(area) from park;


-- GROUP BY

-- Count the number of cities in each state, ordered from most cities to least.
select state_abbreviation, count(city_name) from city 
group by state_abbreviation
order by count(city_name) desc;

-- Determine the average park area depending upon whether parks allow camping or not.
select avg(area) :: int, has_camping from park group by has_camping;

-- Sum of the population of cities in each state ordered by state abbreviation.
select state_abbreviation, sum(population) 
from city 
group by state_abbreviation 
order by state_abbreviation;

-- The smallest city population in each state ordered by city population.
select min(population) as min_pop, state_abbreviation
from city
group by state_abbreviation
order by min_pop;


-- Miscelleneous

-- While you can use LIMIT to limit the number of results returned by a query,
-- it's recommended to use OFFSET and FETCH if you want to get
-- "pages" of results.
-- For instance, to get the first 10 rows in the city table
-- ordered by the name, you could use the following query.
-- (Skip 0 rows, and return only the first 10 rows from the sorted result set.)
select *
from city
offset 30 rows fetch next 10 rows only;


-- SUBQUERIES (optional)

-- Include state name rather than the state abbreviation while counting the number of cities in each state,
select (select state_name from state where state.state_abbreviation = city.state_abbreviation), count(city_name) as city_count
from city 
group by state_abbreviation
order by count(city_name) desc;

-- Include the names of the smallest and largest parks
select park_name, area
from park, (select min(area) as min_area, max(area) as max_area from park) as sub
where park.area = sub.min_area or park.area = sub.max_area;

-- List the capital cities for the states in the Northeast census region.
select * 
from city 
where state_abbreviation IN (select * from state where census_region = 'Northeast');

select city_name
from city
where city.city_id = state.capital;
