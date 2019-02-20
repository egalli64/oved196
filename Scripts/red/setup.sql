DROP TABLE RED_CODERS;
DROP TABLE RED_CODERS_ROLES;
DROP TABLE RED_ROLES;
DROP TABLE RED_TEAMS;

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
	coder_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	coder_name varchar2(40)UNIQUE,
	team_id integer REFERENCES RED_TEAMS(team_id)
);



INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Luca Marascio', 1);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Giovanni Cufari', 1);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Giovanni Patrone', 1);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Christian Rondeno', 1);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Luana Gigliotti', 2);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Alessandra Napoli', 2);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Michele Vatri', 2);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Enrico Deli', 2);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Alessandra D''Isabella', 3);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Carlo Contestabile', 3);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Riccardo Garritano', 3);
INSERT INTO RED_coders (coder_name, TEAM_ID) values( 'Kelly Miotto', 3);

-- roles
CREATE TABLE RED_roles(
	role_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	role_name varchar2(20)UNIQUE
);

INSERT INTO RED_roles (role_name) values( 'Model');
INSERT INTO RED_roles (role_name) values( 'View');
INSERT INTO RED_roles (role_name) values( 'Controller');
INSERT INTO RED_roles (role_name) values( 'TeamLeader');

CREATE TABLE RED_coders_roles(
coder_role_id integer GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
coder_id integer REFERENCES RED_CODERS(coder_id),
ROLE_id integer REFERENCES RED_ROLES(ROLE_id)
);

INSERT INTO RED_coders_roles (coder_id,ROLE_id) VALUES ( 1 , 4);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 2 , 1);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 3 , 2);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 4 , 3);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 5 , 1);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 6 , 3);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 7 , 2);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 8 , 4);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 9 , 4);
INSERT INTO RED_coders_roles (coder_id,ROLE_id)  VALUES ( 10 , 1);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 11 , 3);
INSERT INTO RED_coders_roles  (coder_id,ROLE_id) VALUES ( 12 , 2);


