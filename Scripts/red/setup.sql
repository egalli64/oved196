-- red setup

-- from root w/ sysdba rights
--create user oved identified by password account unlock;
--grant connect, resource to oved;
--alter user oved quota unlimited on users;

-- teams
CREATE TABLE RED_teams(
	team_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	team_name varchar2(10) UNIQUE
);

INSERT INTO RED_teams (team_name) values('red');
INSERT INTO RED_teams (team_name) values('blue');
INSERT INTO RED_teams (team_name) values('green');

-- coders
CREATE TABLE RED_coders(
	coder_id integer PRIMARY KEY,
	coder_name varchar2(40),
	team_id integer REFERENCES RED_TEAMS(team_id)
);



INSERT INTO RED_coders values(1, 'Luca Marascio', 1);
INSERT INTO RED_coders values(2, 'Giovanni Cufari', 1);
INSERT INTO RED_coders values(3, 'Giovanni Patrone', 1);
INSERT INTO RED_coders values(4, 'Christian Rondeno', 1);
INSERT INTO RED_coders values(5, 'Luana Gigliotti', 2);
INSERT INTO RED_coders values(6, 'Alessandra Napoli', 2);
INSERT INTO RED_coders values(7, 'Michele Vatri', 2);
INSERT INTO RED_coders values(8, 'Enrico Deli', 2);
INSERT INTO RED_coders values(9, 'Alessandra D''Isabella', 3);
INSERT INTO RED_coders values(10, 'Carlo Contestabile', 3);
INSERT INTO RED_coders values(11, 'Riccardo Garritano', 3);
INSERT INTO RED_coders values(12, 'Kelly Miotto', 3);

-- roles
CREATE TABLE RED_roles(
	role_id integer PRIMARY KEY,
	role_name varchar2(20)
);

INSERT INTO RED_roles values(1, 'Model');
INSERT INTO RED_roles values(2, 'View');
INSERT INTO RED_roles values(3, 'Controller');
INSERT INTO RED_roles values(4, 'TeamLeader');

CREATE TABLE RED_coders_roles(
coder_role_id integer PRIMARY KEY,
coder_id integer REFERENCES RED_CODERS(coder_id),
ROLE_id integer REFERENCES RED_ROLES(ROLE_id)
);

INSERT INTO RED_coders_roles VALUES (1, 1 , 4);
INSERT INTO RED_coders_roles VALUES (2, 2 , 1);
INSERT INTO RED_coders_roles VALUES (3, 3 , 2);
INSERT INTO RED_coders_roles VALUES (4, 4 , 3);
INSERT INTO RED_coders_roles VALUES (5, 5 , 1);
INSERT INTO RED_coders_roles VALUES (6, 6 , 3);
INSERT INTO RED_coders_roles VALUES (7, 7 , 2);
INSERT INTO RED_coders_roles VALUES (8, 8 , 4);
INSERT INTO RED_coders_roles VALUES (9, 9 , 4);
INSERT INTO RED_coders_roles VALUES (10, 10 , 1);
INSERT INTO RED_coders_roles VALUES (11, 11 , 3);
INSERT INTO RED_coders_roles VALUES (12, 12 , 2);
