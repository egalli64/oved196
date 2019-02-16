-- Green setup

-- from root w/ sysdba rights
--create user oved identified by password account unlock;
--grant connect, resource to oved;
--alter user oved quota unlimited on users;

-- teams
CREATE TABLE green_teams(
	team_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	team_name varchar2(10) UNIQUE
);

INSERT INTO green_teams (team_name) values('red');
INSERT INTO green_teams (team_name) values('blue');
INSERT INTO green_teams (team_name) values('green');

-- coders
CREATE TABLE green_coders(
	coder_id integer PRIMARY KEY,
	coder_name varchar2(10),
	team_id integer REFERENCES GREEN_TEAMS(team_id)
);

INSERT INTO green_coders values(1, 'Bjarne', 1);
INSERT INTO green_coders values(2, 'Brian', 1);
INSERT INTO green_coders values(3, 'Dennis', 1);
INSERT INTO green_coders values(4, 'Guido', 2);
INSERT INTO green_coders values(5, 'Bob', 2);
INSERT INTO green_coders values(6, 'Robbie', 2);
INSERT INTO green_coders values(7, 'Tom', 3);
INSERT INTO green_coders values(8, 'Bill', 3);
INSERT INTO green_coders values(9, 'Bernie', 3);

-- roles
CREATE TABLE green_roles(
	role_id integer PRIMARY KEY,
	role_name varchar2(20)
);

INSERT INTO green_roles values(1, 'Model');
INSERT INTO green_roles values(2, 'View');
INSERT INTO green_roles values(3, 'Controller');
