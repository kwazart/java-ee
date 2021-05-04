DROP TABLE IF EXISTS users_products;
CREATE TABLE users_products
(
    id         SERIAL PRIMARY KEY,
    user_id    BIGINT,
    product_id BIGINT
);

INSERT INTO users_products (user_id, product_id)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 5);