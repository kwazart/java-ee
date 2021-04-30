DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) UNIQUE  NOT NULL,
    password VARCHAR(255) UNIQUE  NOT NULL
);

INSERT INTO users (login, password) VALUES
('login1', 'pass1'),
('login2', 'pass2'),
('login3', 'pass3');