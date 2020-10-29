DROP DATABASE IF EXISTS fourthingsplus;
DROP USER IF EXISTS fourthingsplus@localhost;

CREATE DATABASE fourthingsplus;
CREATE USER fourthingsplus@localhost;
GRANT ALL PRIVILEGES ON fourthingsplus.* TO fourthingsplus@localhost;

USE fourthingsplus;

CREATE TABLE properties
(
    name VARCHAR(255) PRIMARY KEY,
    value VARCHAR(255) NOT NULL
);

INSERT INTO properties (name, value) VALUES ("version", "0");

