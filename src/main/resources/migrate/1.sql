CREATE TABLE shoppinglist
(
    asInt       int PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(256)
);

UPDATE properties
SET value = '1'
WHERE name = "version";