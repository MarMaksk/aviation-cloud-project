insert into users.public.users
values (1, now(), null, 'email0' , 'admin user', 'царь', 'бог', '$2a$10$pgQPzY2VRX7IqD081j/p8epnAkBNpnKAu6/0NghNaakYcfQ1QvfHW',
        'admin', false);

insert into users.public.user_role
values (1, 'ROLE_SYSTEM');

insert into users.public.users
values (2, now(), null, 'email1' ,'user user', 'order', 'user', '$2a$10$4ffA0SwqTDZnvteQvwYOZ.y4o/VzocUnC1vzfyMvSgbld9PPav2d.',
        'user', false);

insert into users.public.user_role
values (2, 'ROLE_USER');

insert into users.public.users
values (3, now(), null, 'email2', 'user ROLE_CATERER', 'user', 'catering', '$2a$10$4ffA0SwqTDZnvteQvwYOZ.y4o/VzocUnC1vzfyMvSgbld9PPav2d.',
        'catering', false);

insert into users.public.user_role
values (3, 'ROLE_CATERER');
