insert into flight."orders".airplane
values (1, now(), null, false, false, 'M01', 525, 'Airbus A380');

insert into flight."orders".airplane
values (2, now(), null, false, false, 'M02', 169, 'Airbus A220');

insert into flight."orders".airplane
values (3, now(), null, false, false, 'M03', 116, 'Airbus A319');

insert into flight."orders".airplane
values (4, now(), null, false, false, 'M04', 525, 'Airbus A380');

insert into flight."orders".airplane
values (5, now(), null, false, false, 'M05', 300, 'Ил-96-300');

insert into flight."orders".airplane
values (6, now(), null, false, false, 'M06', 108, 'Sukhoi Superjet 100');

insert into flight."orders".airplane
values (7, now(), null, false, false, 'M07', 250, 'Boeing 787 Dreamliner');

insert into flight."orders".airplane
values (8, now(), null, false, true, 'M08', 83, 'Ан-148');

insert into flight."orders".airplane
values (9, now(), null, false, false, 'M09', 162, 'Boeing 737 MAX');

insert into flight."orders".airplane
values (10, now(), null, false, true, 'M10', 525, 'Airbus A380');

insert into flight."orders".airport
values (1, now(), null, false, 'Moscow', 'Russia', 1111);

insert into flight."orders".airport
values (2, now(), null, false, 'Tarkov', 'Russia', 1112);

insert into flight."orders".airport
values (3, now(), null, false, 'Tambov', 'Russia', 1113);

insert into flight."orders".airport
values (4, now(), null, false, 'Ekaterinburg', 'Russia', 1114);

insert into flight."orders".airport
values (5, now(), null, false, 'Honkong', 'China', 1115);

insert into flight."orders".airport
values (6, now(), null, false, 'Minsk', 'Belarus', 1116);

insert into flight."orders".examination
values (1, now(), null, false, now(), 'Предполётная подготовка', 1);

insert into flight."orders".examination
values (2, now(), null, false, now(), 'Замена фильтров, колёс', 8);

insert into flight."orders".flight
values (1, now(), null, false, '12/17/2022 07:37:16.00 PST', 'M081111', '4:05:00', 40, 1, false,
        'CREATED', 50.0, 1, null, 1, 2)