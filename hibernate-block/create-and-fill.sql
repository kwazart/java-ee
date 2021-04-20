BEGIN;

DROP TABLE IF EXISTS manufacturers CASCADE;
CREATE TABLE manufacturers (id serial PRIMARY KEY, title VARCHAR(255));
INSERT INTO manufacturers (title) VALUES ('Coca-Cola Company'), ('Danone');


DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (
    id serial PRIMARY KEY,
    title VARCHAR(255),
    price numeric(8, 2),
    manufacturer_id int,
    FOREIGN KEY (manufacturer_id) REFERENCES manufacturers(id)
);
INSERT INTO products (title, manufacturer_id, price)
VALUES ('Sprite', 1, 30.00),
       ('Fanta', 1, 40.00),
       ('Yoga', 2, 100.00);


DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id serial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES ('Jim'), ('John'), ('Mike');



DROP TABLE IF EXISTS big_items CASCADE;
CREATE TABLE big_items (id serial PRIMARY KEY, val int, testCol int, version serial);
INSERT INTO big_items (val, testCol) VALUES (10, 20);

COMMIT;