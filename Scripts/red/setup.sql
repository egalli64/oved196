-- Red setup

-- from root w/ sysdba rights
--create user oved identified by password account unlock;
--grant connect, resource to oved;
--alter user oved quota unlimited on users;

CREATE TABLE red_teams(
	team_id integer PRIMARY KEY,
	team_name varchar2(10)
);

INSERT INTO red_teams values(1, 'red');
INSERT INTO red_teams values(2, 'blue');
