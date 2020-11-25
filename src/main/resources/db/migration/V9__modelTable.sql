create table if not exists model
(
    model_id    int not null primary key AUTO_INCREMENT,
    owner    varchar(255) null,
    dataset varchar(255) null,
    classifier varchar(255) null,
    feature varchar(255) null,
    visible   bit          not null
);

