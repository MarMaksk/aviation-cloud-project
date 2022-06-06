create table airplane
(
    id            bigserial not null,
    created_at    timestamp,
    updated_at    timestamp,
    deleted       boolean   not null,
    iata_code     varchar(3),
    load_capacity int4      not null,
    model         varchar(255),
    primary key (id)
);
create table airport
(
    id         bigserial not null,
    created_at timestamp,
    updated_at timestamp,
    city       varchar(255),
    country    varchar(255),
    deleted    boolean   not null,
    icao_code  varchar(4),
    primary key (id)
);
create table examination
(
    id          bigserial not null,
    created_at  timestamp,
    updated_at  timestamp,
    date        date,
    deleted     boolean   not null,
    description varchar(255),
    airplane_id int8,
    primary key (id)
);
create table flight
(
    id                 bigserial not null,
    created_at         timestamp,
    updated_at         timestamp,
    deleted            boolean   not null,
    departure          timestamp,
    flight_number      varchar(7),
    flight_time        float8    not null,
    passengers_count   int4      not null,
    product_order_id   int8,
    regular            boolean   not null,
    ticket_price       float8,
    airplane_id        int8,
    arrival_airport_id int8,
    dep_airport_id     int8,
    flight_id          int8,
    primary key (id)
);
create table seat
(
    id          bigserial not null,
    created_at  timestamp,
    updated_at  timestamp,
    business    boolean   not null,
    busy        boolean   not null,
    deleted     boolean   not null,
    seat_number varchar(3),
    airplane_id int8,
    primary key (id)
);
alter table examination
    add constraint fk_examination_airplane foreign key (airplane_id) references airplane;
alter table flight
    add constraint fk_flight_airplane foreign key (airplane_id) references airplane;
alter table flight
    add constraint fk_flight_arr_airport foreign key (arrival_airport_id) references airport;
alter table flight
    add constraint fk_flight_dep_airport foreign key (dep_airport_id) references airport;
alter table flight
    add constraint fk_flight_flight foreign key (flight_id) references flight;
alter table seat
    add constraint fk_seat_aiplane foreign key (airplane_id) references airplane;