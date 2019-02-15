-- from root w/ sysdba rights
--create user oved identified by password account unlock;
--grant connect, resource to oved;
--alter user oved quota unlimited on users;

CREATE TABLE teams(
	team_id integer PRIMARY KEY,
	team_name varchar2(10)
);

INSERT INTO teams values(1, 'red');
INSERT INTO teams values(2, 'blue');

SELECT *
FROM teams;

SELECT *
FROM red_teams;

SELECT *
FROM blue_teams;
