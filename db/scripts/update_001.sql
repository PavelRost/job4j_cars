create table driver(
    id serial primary key,
    name TEXT
);
create table engine(
    id serial primary key,
    name TEXT
);

create table car(
    id serial primary key,
    name TEXT,
    engine_id int not null references engine(id)
);

create table history_owner(
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);