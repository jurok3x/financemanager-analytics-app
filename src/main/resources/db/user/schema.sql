CREATE TABLE IF NOT EXISTS USERS
(
    ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME varchar(255),
    EMAIL varchar(255),
    PASSWORD varchar(255),
    ROLE_ID int
);

CREATE TABLE IF NOT EXISTS ROLES
(
    ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME varchar(255)
);

CREATE TABLE IF NOT EXISTS PERMISSIONS
(
    ID int NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME varchar(255)
);

CREATE TABLE IF NOT EXISTS ROLE_PERMISSIONS
(
    ROLE_ID int PRIMARY KEY,
    PERMISSION_ID int
);

 ALTER TABLE USERS
    ADD FOREIGN KEY (ROLE_ID) 
    REFERENCES ROLES(ID);

 ALTER TABLE ROLE_PERMISSIONS
    ADD FOREIGN KEY (ROLE_ID) 
    REFERENCES ROLES(ID);
    
 ALTER TABLE ROLE_PERMISSIONS
    ADD FOREIGN KEY (PERMISSION_ID) 
    REFERENCES PERMISSIONS(ID);