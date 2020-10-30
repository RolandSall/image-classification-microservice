create table if not exists data_sets
(
    dataset_id  binary(16)  not null
        primary key,
    description varchar(255) null,
    origin      varchar(255) null
);


