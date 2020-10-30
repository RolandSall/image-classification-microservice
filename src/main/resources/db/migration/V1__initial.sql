create table if not exists admin
(
    admin_id binary(16)  not null primary key,
    email    varchar(255) null,
    password varchar(255) null,
    username varchar(255) null
);

