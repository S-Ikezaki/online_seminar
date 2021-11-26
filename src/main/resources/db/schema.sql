create table group_mst
(
    group_id    int primary key,
    group_name  varchar(50) not null,
    role        int          default 0 not null,
    group_bio   varchar(600) not null
);


