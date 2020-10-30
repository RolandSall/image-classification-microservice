-- auto-generated definition
create table datasets_features
(
    id binary(16) not null,
    dataset_id binary(16) not null,
    feature_id binary(16) not null,
    constraint
        foreign key (feature_id) references features (feature_id),
    constraint
        foreign key (dataset_id) references data_sets (dataset_id)
);

