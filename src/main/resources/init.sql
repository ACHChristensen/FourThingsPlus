DROP DATABASE IF EXISTS fourthingsplus;
DROP USER IF EXISTS fourthingsplus@localhost;

CREATE DATABASE fourthingsplus;
CREATE USER fourthingsplus@localhost;
GRANT ALL PRIVILEGES ON fourthingsplus.* TO fourthingsplus@localhost;

USE fourthingsplus;
CREATE TABLE shoppinglist (
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(256)
);