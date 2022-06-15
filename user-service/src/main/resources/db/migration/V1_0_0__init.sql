create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);
create table users
(
    id         bigserial    not null,
    created_at timestamp,
    updated_at timestamp,
    email      varchar(255),
    bio        text,
    firstname  varchar(255) not null,
    lastname   varchar(255) not null,
    password   varchar(3000),
    username   varchar(255),
    deleted    boolean,
    primary key (id)
);
alter table users
    add constraint ct_username unique (username);
alter table user_role
    add constraint fk_user_role_users foreign key (user_id) references users;