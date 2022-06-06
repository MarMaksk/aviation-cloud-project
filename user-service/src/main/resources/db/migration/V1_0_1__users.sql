insert into users.public.users
values (1, now(), null, 'admin user', 'царь', 'бог', '$2a$10$pgQPzY2VRX7IqD081j/p8epnAkBNpnKAu6/0NghNaakYcfQ1QvfHW',
        'admin');

insert into users.public.user_role
values (1, 'ROLE_ADMIN');

insert into users.public.users
values (2, now(), null, 'user user', 'first', 'user', '$2a$10$4ffA0SwqTDZnvteQvwYOZ.y4o/VzocUnC1vzfyMvSgbld9PPav2d.',
        'user');

insert into users.public.user_role
values (2, 'ROLE_USER');
