create table orders
(
    id               bigserial not null,
    created_at       timestamp,
    updated_at       timestamp,
    deleted          boolean   not null,
    delivery_time    timestamp,
    iata_code        varchar(4),
    icao_code        varchar(3),
    last_date        timestamp,
    product_order_id int4,
    status           varchar(255),
    primary key (id)
);
create table orders_delivered_products
(
    order_id              int8 not null,
    delivered_products_id int8 not null,
    primary key (order_id, delivered_products_id)
);
create table orders_products
(
    order_id    int8 not null,
    products_id int8 not null,
    primary key (order_id, products_id)
);
create table product
(
    id              bigserial not null,
    created_at      timestamp,
    updated_at      timestamp,
    deleted         boolean   not null,
    code            int4,
    expiration_date int4      not null,
    name            varchar(255),
    primary key (id)
);
create table product_tags
(
    product_id int8 not null,
    tags_id    int8 not null,
    primary key (product_id, tags_id)
);
create table tag
(
    id         bigserial    not null,
    created_at timestamp,
    updated_at timestamp,
    deleted    boolean      not null,
    name       varchar(255) not null,
    primary key (id)
);
alter table orders
    add constraint orders_AK unique (product_order_id);
alter table product
    add constraint product_AK unique (code);
alter table tag
    add constraint tag_AK unique (name);
alter table orders_delivered_products
    add constraint orders_delivered_products_FK foreign key (delivered_products_id) references product;
alter table orders_delivered_products
    add constraint orders_delivered_products_order_FK foreign key (order_id) references orders;
alter table orders_products
    add constraint orders_products_products_FK foreign key (products_id) references product;
alter table orders_products
    add constraint orders_products_order_FK foreign key (order_id) references orders;
alter table product_tags
    add constraint product_tags_tag_FK foreign key (tags_id) references tag;
alter table product_tags
    add constraint product_tags_product_FT foreign key (product_id) references product