insert into users.users
values (1, now(), null, false , 'admin user', 'email0', 'царь', 'бог', '$2a$10$pgQPzY2VRX7IqD081j/p8epnAkBNpnKAu6/0NghNaakYcfQ1QvfHW',
        'admin');

insert into users.user_role
values (1, 'ROLE_SYSTEM');

insert into users.users
values (2, now(), null, false ,'user user', 'email1', 'order', 'user', '$2a$10$4ffA0SwqTDZnvteQvwYOZ.y4o/VzocUnC1vzfyMvSgbld9PPav2d.',
        'user');

insert into users.user_role
values (2, 'ROLE_USER');

insert into users.users
values (3, now(), null, false, 'user ROLE_CATERER', 'email2', 'user', 'catering', '$2a$10$4ffA0SwqTDZnvteQvwYOZ.y4o/VzocUnC1vzfyMvSgbld9PPav2d.',
        'catering');

insert into users.user_role
values (3, 'ROLE_CATERER');
