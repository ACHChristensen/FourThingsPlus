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

CREATE TABLE shoppinglist_item (
    id int NOT NULL AUTO_INCREMENT,
    shoppinglist_id int NOT NULL,
    description VARCHAR(64),
    active BIT(1),
    PRIMARY KEY(id, shoppinglist_id),
    FOREIGN KEY (shoppinglist_id) REFERENCING shoppinglist(id)
);