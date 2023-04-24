DROP TABLE IF EXISTS users_roles;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS authorities CASCADE;

DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS products;

CREATE TABLE users (
    id serial PRIMARY KEY,
    email VARCHAR ( 255 ) UNIQUE NOT NULL,
    first_name varchar(64) NOT NULL,
    last_name varchar(128) NOT NULL,
    password varchar(64) NOT NULL,
    enabled boolean NOT NULL
);

CREATE TABLE roles (
    id serial PRIMARY KEY,
    name varchar(50) NOT NULL
);

CREATE TABLE users_roles (
    id serial PRIMARY KEY,
    user_id int NOT NULL,
    role_id int NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(role_id) REFERENCES roles(id)
);


CREATE TABLE products (
    id serial PRIMARY KEY,
    owner_id int NOT NULL,
    name varchar(64) NOT NULL,
    description varchar(256) NOT NULL,
    price numeric(10, 2) NOT NULL,
    uploaded_at timestamp NOT NULL DEFAULT now(),
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE messages (
    id serial NOT NULL,
    product_id int NOT NULL,
    content varchar(256) NOT NULL,
    sent_by int NOT NULL,
    sent_at timestamp NOT NULL DEFAULT now(),
    FOREIGN KEY (sent_by) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);
