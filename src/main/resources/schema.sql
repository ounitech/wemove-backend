-- src/main/resources/schema.sql

CREATE TABLE IF NOT EXISTS users (
    id Integer AUTO_INCREMENT PRIMARY KEY,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    job VARCHAR(255)
    );