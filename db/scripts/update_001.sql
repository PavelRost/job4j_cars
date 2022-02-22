CREATE TABLE if NOT EXISTS driver(
    id serial primary key,
    name TEXT
);
CREATE TABLE if NOT EXISTS engine(
    id serial primary key,
    name TEXT
);

CREATE TABLE if NOT EXISTS car(
    id serial primary key,
    name TEXT,
    engine_id int not null references engine(id)
);

CREATE TABLE if NOT EXISTS history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);

CREATE TABLE if NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name TEXT,
    email TEXT,
    password TEXT
);

CREATE TABLE if NOT EXISTS advertisement (
    id serial primary key,
    description TEXT,
    mark TEXT,
    body TEXT,
    created timestamp,
    done boolean,
    car_id INTEGER REFERENCES car (id)
);