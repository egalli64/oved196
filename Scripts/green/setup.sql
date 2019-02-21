

DROP TABLE GREEN_ROLES CASCADE CONSTRAINTS PURGE;
DROP TABLE GREEN_TEAMS CASCADE CONSTRAINTS PURGE;
DROP TABLE GREEN_CODERS CASCADE CONSTRAINTS PURGE;

DROP TABLE GREEN_COD_ROLE;

CREATE TABLE GREEN_TEAMS (
TEAM_ID INT generated BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),
TEAM_NAME VARCHAR(30) UNIQUE,
PRIMARY KEY(TEAM_ID)
);

CREATE TABLE GREEN_CODERS(
CODER_ID INT generated BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),
FIRST_NAME VARCHAR(30),
LAST_NAME VARCHAR(30),
TEAM_ID INT,
PRIMARY KEY (CODER_ID),
FOREIGN KEY(TEAM_ID) REFERENCES GREEN_TEAMS(TEAM_ID)
);

CREATE TABLE GREEN_ROLES (
ROLE_ID INT generated BY DEFAULT AS IDENTITY (START WITH 1 INCREMENT BY 1),
ROLE_NAME VARCHAR(30),
PRIMARY KEY (ROLE_ID)
);


INSERT INTO GREEN_TEAMS (TEAM_NAME)
VALUES ('BLUE');
INSERT INTO GREEN_TEAMS (TEAM_NAME)
VALUES ('RED');
INSERT INTO GREEN_TEAMS (TEAM_NAME)
VALUES ('GREEN');
INSERT INTO GREEN_TEAMS (TEAM_NAME)
VALUES ('NUOVI');


INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('ALESSANDRA','D_ISABELLA',1);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('KELLY','MIOTTO',1);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('CARLO','CONTESTABILE',1);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('RICCARDO','GARRITANO',1);

INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('LUCA','MARASCIO',2);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('CHRISTIAN','RONDENA',2);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('GIOVANNI','PATRONE',2);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('GIOVANNI','CUFARI',2);

INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('LUANA','GIGLIOTTI',3);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('ALESSANDRA','NAPOLI',3);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('ENRICO','DELIBORI',3);
INSERT INTO GREEN_CODERS(FIRST_NAME,LAST_NAME,TEAM_ID)
VALUES ('MICHELE','VATRI',3);

INSERT INTO GREEN_ROLES(ROLE_NAME)
VALUES('Project manager');
INSERT INTO GREEN_ROLES(ROLE_NAME)
VALUES('Model');
INSERT INTO GREEN_ROLES(ROLE_NAME)
VALUES('Controller');
INSERT INTO GREEN_ROLES(ROLE_NAME)
VALUES('View');

CREATE TABLE GREEN_COD_ROLE(

CODER_ID INT REFERENCES GREEN_CODERS(CODER_ID),
ROLE_ID INT REFERENCES GREEN_ROLES(ROLE_ID),

primary key(CODER_ID, ROLE_ID)

);

INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(4,4);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(2,1);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(3,2);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(1,3);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(1,7);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(2,5);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(4,6);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(3,8);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(4,9);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(3,10);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(2,11);
INSERT INTO GREEN_COD_ROLE(ROLE_ID, CODER_ID)
VALUES(1,12);




