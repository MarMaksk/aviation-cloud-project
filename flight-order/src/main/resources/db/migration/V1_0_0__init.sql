create table if not exists airplane
(
    id            bigserial not null,
    created_at    timestamp,
    updated_at    timestamp,
    deleted       boolean   not null,
    busy          boolean   not null,
    icao_code     varchar(3),
    load_capacity int4      not null,
    model         varchar(255),
    primary key (id)
);
create table if not exists airport
(
    id         bigserial not null,
    created_at timestamp,
    updated_at timestamp,
    deleted    boolean   not null,
    city       varchar(255),
    country    varchar(255),
    iata_code  varchar(4),
    primary key (id)
);
create table if not exists examination
(
    id          bigserial not null,
    created_at  timestamp,
    updated_at  timestamp,
    deleted     boolean   not null,
    date        date,
    description varchar(255),
    airplane_id int8,
    primary key (id)
);
create table if not exists flight
(
    id                 bigserial not null,
    created_at         timestamp,
    updated_at         timestamp,
    deleted            boolean   not null,
    departure          timestamp,
    flight_number      varchar(7),
    flight_time        time,
    passengers_count   int4      not null,
    product_order_id   int4,
    regular            boolean,
    status             varchar(255),
    ticket_price       float8,
    airplane_id        int8,
    alt_flight_id      int8,
    arrival_airport_id int8,
    dep_airport_id     int8,
    primary key (id)
);
create table if not exists seat
(
    id          bigserial not null,
    created_at  timestamp,
    updated_at  timestamp,
    deleted     boolean   not null,
    business    boolean   not null,
    busy        boolean   not null,
    seat_number varchar(3),
    airplane_id int8,
    primary key (id)
);
alter table airplane
    add constraint airplane_AK unique (icao_code);
alter table airport
    add constraint airport_AK unique (iata_code);
alter table flight
    add constraint flight_number_AK unique (flight_number);
alter table flight
    add constraint flight_AK unique (product_order_id);
alter table examination
    add constraint examination_airplane_FK foreign key (airplane_id) references airplane;
alter table flight
    add constraint flight_airplane_FK foreign key (airplane_id) references airplane;
alter table flight
    add constraint flight_flight_FK foreign key (alt_flight_id) references flight;
alter table flight
    add constraint flight_arr_airport_FK foreign key (arrival_airport_id) references airport;
alter table flight
    add constraint flight_dep_airport_FK foreign key (dep_airport_id) references airport;
alter table seat
    add constraint flight_airplane_id_FK foreign key (airplane_id) references airplane