drop
database seminar_DB;
create
database seminar_DB;
use
seminar_DB;

create table user_mst
(
    user_id     varchar(16) primary key,
    user_name   varchar(50) not null,
    user_role   int         not null default 0,
    delete_flag boolean     not null default false
);

create table certification_mst
(
    user_id     varchar(16) primary key,
    password    varchar(64) not null,
    delete_flag boolean     not null default false,
    foreign key (user_id) references user_mst (user_id)
);

create table school_mst
(
    school_id   varchar(16) primary key,
    school_name varchar(50) not null,
    delete_flag boolean     not null default false
);

create table enterprise_mst
(
    enterprise_id   varchar(16) primary key,
    enterprise_name varchar(50) not null,
    delete_flag     boolean     not null default false
);

create table teacher_mst
(
    user_id       varchar(16) primary key,
    school_id     varchar(16) not null,
    permission_cd int         not null default 0,
    delete_flag   boolean     not null default false,
    foreign key (user_id) references user_mst (user_id),
    foreign key (school_id) references school_mst (school_id)
);

create table student_mst
(
    user_id         varchar(16) primary key,
    school_id       varchar(16) not null,
    school_grade    int         not null,
    graduation_date date        not null,
    delete_flag     boolean     not null default false,
    foreign key (user_id) references user_mst (user_id),
    foreign key (school_id) references school_mst (school_id)
);

create table employee_mst
(
    user_id       varchar(16) primary key,
    enterprise_id varchar(16) not null,
    delete_flag   boolean     not null default false,
    foreign key (user_id) references user_mst (user_id),
    foreign key (enterprise_id) references enterprise_mst (enterprise_id)
);


create table request_mst
(
    request_id        varchar(16) primary key,
    create_user_id    varchar(16)  not null,
    create_user_name  varchar(50)  not null,
    address_user_id   varchar(16)  not null,
    address_user_name varchar(50)  not null,
    request_content   varchar(500) not null,
    request_datetime  datetime     not null default (current_date),
    delete_flag       boolean      not null default false,
    foreign key (create_user_id) references user_mst (user_id),
    foreign key (address_user_id) references user_mst (user_id)
);

create table direct_message_mst
(
    direct_message_id       varchar(16)  not null primary key,
    create_user_id          varchar(16)  not null,
    create_user_name        varchar(50)  not null,
    address_user_id         varchar(16)  not null,
    address_user_name       varchar(50)  not null,
    direct_message_contents varchar(500) not null,
    create_datetime         datetime              default (current_date) not null,
    delete_flag             boolean      not null default false,
    foreign key (create_user_id) references user_mst (user_id),
    foreign key (address_user_id) references user_mst (user_id)
);

create table tag_mst
(
    tag_id      bigint auto_increment primary key,
    tag_name    varchar(100) not null,
    delete_flag boolean      not null default false
);

create table group_mst
(
    group_id    varchar(16) primary key,
    group_name  varchar(50)  not null,
    role        int                   default 0 not null,
    group_bio   varchar(600) not null,
    delete_flag boolean      not null default false
);

create table tag_group_mst
(
    id          bigint auto_increment primary key,
    tag_id      bigint      not null,
    group_id    varchar(16) not null,
    delete_flag boolean     not null default false,
    foreign key (tag_id) references tag_mst (tag_id),
    foreign key (group_id) references group_mst (group_id)
);

create table group_message_mst
(
    group_message_id varchar(16) primary key,
    user_id          varchar(16)   not null,
    user_name        varchar(50)   not null,
    create_datetime  datetime      not null default (current_date),
    message_contents varchar(1024) not null,
    group_id         varchar(16),
    delete_flag      boolean       not null default false,
    foreign key (group_id) references group_mst (group_id),
    foreign key (user_id) references user_mst (user_id)
);

create table group_member_mst
(
    group_id    varchar(16) not null,
    user_id     varchar(16) not null,
    user_name   varchar(50) not null,
    group_role  int         not null default 0,
    delete_flag boolean     not null default false,
    primary key (group_id, user_id),
    foreign key (group_id) references group_mst (group_id),
    foreign key (user_id) references user_mst (user_id)
);

create table tag_request_mst
(
    id             bigint auto_increment primary key,
    tag_id         bigint      not null,
    tag_request_id varchar(16) not null,
    delete_flag    boolean     not null default false,
    foreign key (tag_id) references tag_mst (tag_id),
    foreign key (tag_request_id) references request_mst (request_id)
);

create table tag_user_mst
(
    id          bigint auto_increment primary key,
    tag_id      bigint      not null,
    user_id     varchar(16) not null,
    delete_flag boolean     not null default false,
    foreign key (tag_id) references tag_mst (tag_id),
    foreign key (user_id) references user_mst (user_id)
);

create table meeting_mst
(
    meeting_id  varchar(16) primary key,
    group_id    varchar(16),
    user_name   varchar(50) not null,
    status      int         not null default 0,
    delete_flag boolean     not null default false,
    foreign key (group_id) references group_mst (group_id)
);

create table meeting_member_mst
(
    meeting_id  varchar(16) not null,
    user_id     varchar(16) not null,
    user_name   varchar(50) not null,
    delete_flag boolean     not null default false,
    foreign key (meeting_id) references meeting_mst (meeting_id)
);

create table meeting_chat_mst
(
    meeting_chat_id       varchar(16) primary key,
    meeting_chat_contents varchar(100) not null,
    meeting_id            varchar(16)  not null,
    user_id               varchar(16)  not null,
    user_name             varchar(50)  not null,
    meeting_chat_datetime datetime     not null default (current_date),
    delete_flag           boolean      not null default false,
    foreign key (meeting_id) references meeting_mst (meeting_id)
);

create table file_mst
(
    file_id         varchar(16) primary key,
    file_name       varchar(16)  not null,
    file_pass       varchar(500) not null,
    user_id         varchar(16)  not null,
    user_name       varchar(50)  not null,
    create_datetime datetime     not null default (current_date),
    group_id        varchar(16),
    delete_flag     boolean      not null default false,
    unique key (file_name,file_pass),
    foreign key (user_id) references user_mst (user_id),
    foreign key (group_id) references group_mst (group_id)
);
