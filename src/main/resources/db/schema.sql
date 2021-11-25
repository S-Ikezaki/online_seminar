create table group_mst
(
    group_id    varchar(16) primary key auto_increment,
    group_name  varchar(50) not null,
    role        int          default 0 not null,
    group_bio   varchar(600) not null,
);



