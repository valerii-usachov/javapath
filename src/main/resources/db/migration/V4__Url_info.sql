create table isp
(
    id   varchar(255) not null,
    name varchar(255),
    primary key (id)
);
create table url_host
(
    id     bigserial not null,
    ip     varchar(255),
    type   varchar(255),
    isp_id varchar(255),
    url_id int8,
    primary key (id)
);
alter table url_host
    add constraint FKocqf30446n63ut5qu1iptj4k foreign key (isp_id) references isp;
alter table url_host
    add constraint FK8y27k4xcno7hc9gxtcuiq08wt foreign key (url_id) references url;