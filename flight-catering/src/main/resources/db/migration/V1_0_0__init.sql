create table orders
(
    id            bigserial not null,
    created_at    timestamp,
    updated_at    timestamp,
    delivery_time timestamp,
    iata_code     varchar(3),
    icao_code     varchar(4),
    last_date     timestamp,
    status        int4,
    primary key (id)
);
create table orders_products
(
    order_id    int8 not null,
    products_id int8 not null,
    primary key (order_id, products_id)
);
create table product
(
    id                bigserial not null,
    created_at        timestamp,
    updated_at        timestamp,
    code              int4      not null,
    expiration_date   int4      not null,
    manufactured_date date,
    name              varchar(255),
    tags              varchar(255),
    primary key (id)
);
alter table orders_products
    add constraint fk_orders_product foreign key (products_id) references product;
alter table orders_products
    add constraint fk_order_orders foreign key (order_id) references orders;