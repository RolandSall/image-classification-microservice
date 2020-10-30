create table if not exists features
(
    feature_id binary(16)  not null
        primary key,
    name       varchar(255) null
);

