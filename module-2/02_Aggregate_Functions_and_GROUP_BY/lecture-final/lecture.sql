-- ORDERING RESULTS

-- Populations of all states from largest to smallest.
SELECT * FROM state ORDER BY population DESC;


-- States sorted alphabetically (A-Z) within their census region. The census regions are sorted in reverse alphabetical (Z-A) order.
SELECT * FROM state ORDER BY census_region DESC, state_name ASC;

-- The biggest park by area
SELECT * FROM park ORDER BY area DESC;


-- LIMITING RESULTS

-- The 10 largest cities by populations
SELECT * FROM city ORDER BY population DESC LIMIT 10;

-- The 20 oldest parks from oldest to youngest in years, sorted alphabetically by name.
SELECT (CURRENT_DATE - date_established) AS age, park_name FROM park ORDER BY age DESC, park_name LIMIT 20;


-- CONCATENATING OUTPUTS

-- All city names and their state abbreviation.
SELECT city_name || ', ' || state_abbreviation AS formatted_city_name FROM city;

-- The all parks by name and date established.
SELECT 'Name: ' || park_name || ', Established: ' || date_established AS formatted_park_name FROM park;

-- The census region and state name of all states in the West & Midwest sorted in ascending order.
SELECT 'Region: ' || census_region || ', Name: ' || state_name AS formatted_state 
FROM state 
WHERE census_region IN ('West','Midwest') 
ORDER BY state_name ASC;


-- AGGREGATE FUNCTIONS

-- Average population across all the states. Note the use of alias, common with aggregated values.
SELECT AVG(population) :: int AS average_population
FROM state;

-- Total population in the West and South census regions
SELECT SUM(population) AS sum_population 
FROM state 
WHERE census_region IN ('West', 'South');

-- The number of cities with populations greater than 1 million
SELECT COUNT(*) AS greater_than_a_mil FROM city WHERE population > 1000000;
SELECT COUNT(census_region) FROM state;

-- The number of state nicknames.
SELECT COUNT(state_nickname) AS states_with_nicknames FROM state;
SELECT COUNT(*) AS state_with_nicknames FROM state WHERE state_nickname IS NOT NULL;

-- The area of the smallest and largest parks.
SELECT MIN(area), MAX(area) FROM park;


-- GROUP BY

-- Count the number of cities in each state, ordered from most cities to least.
SELECT state_abbreviation, COUNT(city_name) AS count_cities 
FROM city 
GROUP BY state_abbreviation 
ORDER BY count_cities DESC;


-- Determine the average park area depending upon whether parks allow camping or not.
SELECT (AVG(area) :: int) AS average_area, has_camping 
FROM park 
GROUP BY has_camping;

-- Sum of the population of cities in each state ordered by state abbreviation.
SELECT state_abbreviation, SUM(population) 
FROM city 
GROUP BY state_abbreviation 
ORDER BY state_abbreviation;


-- The smallest city population in each state ordered by city population.
SELECT MIN(population) AS min_pop, state_abbreviation
FROM city
GROUP BY state_abbreviation
ORDER BY min_pop;


-- Miscelleneous

-- While you can use LIMIT to limit the number of results returned by a query,
-- it's recommended to use OFFSET and FETCH if you want to get
-- "pages" of results.
-- For instance, to get the first 10 rows in the city table
-- ordered by the name, you could use the following query.
-- (Skip 0 rows, and return only the first 10 rows from the sorted result set.)
SELECT *
FROM city
OFFSET 30 ROWS FETCH NEXT 10 ROWS ONLY;

-- SUBQUERIES (optional)

-- Include state name rather than the state abbreviation while counting the number of cities in each state,



SELECT (SELECT state_name FROM state WHERE state_abbreviation = c.state_abbreviation), COUNT(city_name) AS count_cities 
FROM city AS c
GROUP BY state_abbreviation 
ORDER BY count_cities DESC;

-- Include the names of the smallest and largest parks

SELECT park_name, area
FROM park, 
     (SELECT MIN(area) AS min_area, MAX(area) AS max_area FROM park) AS sub
WHERE park.area = sub.min_area OR park.area = sub.max_area;

SELECT park_name, area
FROM park
WHERE park.area = (SELECT MIN(area) FROM park) OR park.area = (SELECT MAX(area) FROM park);


-- List the capital cities for the states in the Northeast census region.

SELECT *
FROM city
WHERE state_abbreviation IN (SELECT state_abbreviation FROM state WHERE census_region = 'Northeast');

SELECT (SELECT city_name FROM city WHERE city.city_id = state.capital), state_name 
FROM state 
WHERE census_region = 'Northeast';

SELECT *
FROM city
WHERE city_id IN (SELECT capital FROM state WHERE census_region = 'Northeast');
