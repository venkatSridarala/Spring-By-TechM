# Spring-By-TechM
DROP DATABASE supplementsdb;
CREATE DATABASE supplementdb;
use supplementdb;
CREATE TABLE users (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER'
);
CREATE TABLE products (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL
);
CREATE TABLE cart_item (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    quantity INT NOT NULL,
    product_id BIGINT UNSIGNED,
    user_id BIGINT UNSIGNED,
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
INSERT INTO users (name, email, password, role)
VALUES ('Venkat', 'venkat@example.com', 'pass123', 'CUSTOMER');

INSERT INTO products (name, description, price)
VALUES ('Whey Protein', 'Premium whey protein for muscle growth', 1499.99);

INSERT INTO cart_item (quantity, product_id, user_id)
VALUES (2, 1, 1);

INSERT INTO products (name, description, price, category, image_url)
VALUES 
('Whey Protein', 'High-quality protein for muscle recovery', 2999.00, 'Protein', 'https://m.media-amazon.com/images/I/61f7VVy4+HL._SX679_.jpg'),
('Creatine Monohydrate', 'Boost performance and muscle gains', 1499.00, 'Creatine', 'https://m.media-amazon.com/images/I/61X4HDEomzL._SX679_.jpg'),
('Multivitamins', 'Essential vitamins for daily health', 799.00, 'Vitamins', 'https://m.media-amazon.com/images/I/71V8U2bq5rL._SX679_.jpg');

INSERT INTO products (name, description, price, image_url)
VALUES ('Whey Protein', 'High-quality whey supplement', 1999.00, 'whey.jpg');

INSERT INTO products (name, description, price, image_url)
VALUES ('Whey Protein', 'High-quality whey supplement', 1999.00, 'https://m.media-amazon.com/images/I/71-SdWZQ4xL._SL1500_.jpg');
