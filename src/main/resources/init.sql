DROP DATABASE IF EXISTS fourthingsplus;
DROP USER IF EXISTS fourthingsplus@localhost;

CREATE DATABASE fourthingsplus;
CREATE USER fourthingsplus;
GRANT ALL PRIVILEGES ON fourthingsplus.* TO fourthingsplus@localhost;

CREATE TABLE shoppinglist (
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50)
);