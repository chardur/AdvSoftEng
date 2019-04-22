# Shmoozed Database

## Naming Convention

To keep SQL scripts organized, a naming convention of `###_description_of_script.sql` should be followed.

## MySQL Configuration

MySQL configuration stuff goes here...

## MySQL For Back-end Dev Using Docker

See [Back-end Docker MySQL](/BackEnd/README.md#docker-mysql) for documentation on running MySQL in a Docker Container
for local Back-end Development.

## "Invalid default value for 'Last_update_Date'"

By default, many Docker images of MySQL create databases in "Strict Mode". MySQL by default sets a `timestamp` field to the 
value of `0000-00-00 00:00:00` which in many cases is actually an invalid date since many "start time" at 
`1970-01-01 00:00:01` (the epoch). In "Strict Mode" it rejects the zero-date as an invalid.

You can run `SHOW VARIABLES LIKE 'sql_mode';` to show what restrictions your database currently has.

To remove this restriction, run `SET SQL_MODE='ALLOW_INVALID_DATES';` to allow the database to create a table with a 
default `timestamp` of the zero-date.
