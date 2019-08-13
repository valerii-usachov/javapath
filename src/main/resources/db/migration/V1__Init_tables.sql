create table account_type
(
    id             varchar(255) not null,
    name           varchar(255),
    url_exp_period int4,
    primary key (id)
);
create table hash
(
    id         bigserial    not null,
    created_at timestamp,
    hash       varchar(255) not null,
    updated_at timestamp,
    url_id     int8,
    user_id    int8,
    primary key (id)
);
create table hash_tag
(
    hash_id int8 not null,
    tag_id  int8 not null,
    primary key (hash_id, tag_id)
);
create table tag
(
    id         bigserial not null,
    created_at timestamp,
    name       varchar(255),
    primary key (id)
);
create table url
(
    id  bigserial     not null,
    url varchar(2083) not null,
    primary key (id)
);
create table users
(
    id              bigserial    not null,
    avatar          oid,
    created_at      timestamp,
    first_name      varchar(255),
    gender          varchar(255),
    last_name       varchar(255),
    updated_at      timestamp,
    username        varchar(255) not null,
    password        varchar(255),
    account_type_id varchar(255),
    primary key (id)
);
alter table hash
    add constraint UK_nai71mkwiwg089wjitvfsqub7 unique (hash);
alter table tag
    add constraint UK_1wdpsed5kna2y38hnbgrnhi5b unique (name);
alter table url
    add constraint UK_45fan3ph9yobw9f8yamp4tnmf unique (url);
alter table users
    add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);
alter table hash
    add constraint FKeghsk2o1v4mmtiw58fd9pew9u foreign key (url_id) references url;
alter table hash
    add constraint FKb6i59w11jwy2uuqr0ebvva22h foreign key (user_id) references users;
alter table hash_tag
    add constraint FKj9pghifqxy8emic3lu67gttm7 foreign key (tag_id) references tag;
alter table hash_tag
    add constraint FKr0hguky9bxsnlhft6mrtksul5 foreign key (hash_id) references hash;
alter table users
    add constraint FKl7cb2ed0leb6yfcddpbw0ve8b foreign key (account_type_id) references account_type;
