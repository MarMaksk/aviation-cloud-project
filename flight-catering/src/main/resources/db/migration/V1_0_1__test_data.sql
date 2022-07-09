insert into flight.catering.orders
values (1, now(), null, false, null, 1111, 'M08', '12/17/2022 03:37:16.00 PST', 1, 'CREATED');

insert into flight.catering.product
values (1, now(), null, false, 67823190, 365, 'Sugar');

insert into flight.catering.product
values (2, now(), null, false, 67823191, 30, 'Water');

insert into flight.catering.orders_products
values (1, 1);

insert into flight.catering.orders_products
values (1, 2);

insert into flight.catering.orders_delivered_products
values (1, 1);

insert into flight.catering.tag
values (1, now(), null, false, 'Alcohol');

insert into flight.catering.tag
values (2, now(), null, false, 'Sweet');