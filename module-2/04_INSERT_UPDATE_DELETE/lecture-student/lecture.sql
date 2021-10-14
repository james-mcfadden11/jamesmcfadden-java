-- INSERT

-- Add Disneyland to the park table. 
--(It was established on 7/17/1955, has an area of 2.1 square kilometers 
--and does not offer camping.)
insert into park (park_name, date_established, area, has_camping) 
values ('Disneyland', '7/17/1955', 2.1, false);

-- Add Hawkins, IN (with a population of 30,000 and an area of 38.1 square kilometers) 
--and Cicely, AK (with a popuation of 839 and an area of 11.4 square kilometers) 
--to the city table.
insert into city
        (city_name, state_abbreviation, population, area)
values ('Hawkins', 'IN', 30000, 38.1),
        ('Cicely', 'AK', 839, 11.4);

-- Since Disneyland is in California (CA), add a record representing that 
--to the park_state table.
insert into park_state (park_id, state_abbreviation)
values ((select park_id from park where park_name = 'Disneyland') , 'CA');


-- UPDATE

-- Change the state nickname of California to "The Happiest Place on Earth."
select * from state where state_abbreviation = 'CA';

update state
set state_nickname = 'The Happiest Place on Earth'
where state_abbreviation = 'CA';

-- Increase the population of California by 1,000,000.
update state
set population = population + 1000000
where state_abbreviation = 'CA';

-- Change the capital of California to Anaheim.
update state
set capital = (select city_id from city where city_name = 'Anaheim')
where state_abbreviation = 'CA';

-- Change California's nickname back to "The Golden State", reduce the population by 1,000,000, and change the capital back to Sacramento.
update state
set state_nickname = 'The Golden State',
        population = population - 1000000, 
        capital = (select city_id from city where city_name = 'Sacramento')
where state_abbreviation = 'CA';

-- DELETE

-- Delete Hawkins, IN from the city table.
select * from city where city_name = 'Hawkins' and state_abbreviation = 'IN';
delete from city where city_name = 'Hawkins' and state_abbreviation = 'IN';

-- Delete all cities with a population of less than 1,000 people from the city table.
select * from city where population < 1000;
delete from city where population < 1000;


-- REFERENTIAL INTEGRITY

-- Try adding a city to the city table with "XX" as the state abbreviation.
insert into city
        (city_name, state_abbreviation, population, area)
values ('Hawkins', 'XX', 30000, 38.1);

-- Try deleting California from the state table.
delete from state where state_abbreviation = 'CA';

-- Try deleting Disneyland from the park table. Try again after deleting its record from the park_state table.
delete from park_state where park_id = (select park_id from park where park_name = 'Disneyland');
delete from park where park_name = 'Disneyland';


-- CONSTRAINTS

-- NOT NULL constraint
-- Try adding Smallville, KS to the city table without specifying its population or area.
insert into city (city_name, state_abbreviation) values ('Smallville', 'KS');

-- DEFAULT constraint
-- Try adding Smallville, KS again, specifying an area but not a population.
insert into city (city_name, state_abbreviation, area) 
values ('Smallville', 'KS', 555);

-- Retrieve the new record to confirm it has been given a default, non-null value for population.


-- UNIQUE constraint
-- Try changing California's nickname to "Vacationland" (which is already the nickname of Maine).
update state
set state_nickname = 'Vacationland'
where state_abbreviation = 'CA';

-- CHECK constraint
-- Try changing the census region of Florida (FL) to "Southeast" (which is not a valid census region).
update state
set census_region = 'Southeast'
where state_abbreviation = 'FL';

/*
CREATE TABLE state 
(state_abbreviation CHARACTER(2) NOT NULL, 
state_name CHARACTER VARYING(50) NOT NULL, 
population INTEGER NOT NULL, 
area INTEGER NOT NULL, 
capital INTEGER NOT NULL, 
sales_tax NUMERIC(5,3) NOT NULL, 
state_nickname CHARACTER VARYING(100), 
census_region CHARACTER VARYING(10), 
CONSTRAINT pk_state PRIMARY KEY (state_abbreviation), 
CONSTRAINT fk_state_city FOREIGN KEY (capital) REFERENCES "city" ("city_id"), 
CONSTRAINT uq_state_name UNIQUE (state_name), 
CONSTRAINT uq_state_nickname UNIQUE (state_nickname), 
CONSTRAINT chk_census_region CHECK ((census_region IS NULL) OR ((census_region)::text = ANY ((ARRAY['Northeast'::character varying, 'South'::character varying, 'Midwest'::character varying, 'West'::character varying])::text[]))));
*/


-- TRANSACTIONS

-- Delete the record for Smallville, KS within a transaction.
start transaction;
select * from city where city_name = 'Smallville' and state_abbreviation = 'KS';
        delete from city where city_name = 'Smallville' and state_abbreviation = 'KS';
commit;
        

-- Delete all of the records from the park_state table, 
--but then "undo" the deletion by rolling back the transaction.
start transaction;
delete from park_state;
select * from park_state;
rollback;

-- Update all of the cities to be in the state of Texas (TX), 
--but then roll back the transaction.
start transaction;
update city set state_abbreviation = 'TX';
select * from city;
rollback;

-- Demonstrate two different SQL connections trying to access 
--the same table where one is inside of a transaction but hasn't committed yet.
start transaction;
delete from park_state;
select * from park_state;
rollback;

