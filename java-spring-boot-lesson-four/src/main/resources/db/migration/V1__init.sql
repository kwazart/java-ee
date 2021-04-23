DROP TABLE IF EXISTS products;
CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    price BIGINT
);

INSERT INTO products (title, description, price)
VALUES
('MacBook', 'Ultra low and Great Power', 3000),
('iPhone', 'The most expensive phone by credit', 1000),
('iPad', 'More size - more cost', 1500),
('iMac', 'More size - more cost', 4000);

-- тестовая таблица

DROP TABLE IF EXISTS items;
CREATE TABLE items (
                          id SERIAL,
                          title VARCHAR(40)
);

INSERT INTO items (title)
VALUES
('stone'), ('knife'), ('spoon');
