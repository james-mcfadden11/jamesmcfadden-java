-- 13. The name, abbreviation, and population of all records in the state table with no official nickname (NULL) (5 rows)
SELECT state_name, state_abbreviation, population from STATE WHERE state_nickname IS NULL;
