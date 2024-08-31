create table clients
(
    id            bigserial primary key,
    first_name    varchar(255),
    last_name     varchar(255),
    client_status integer,
    is_admin      boolean,
    comment       varchar(255),
    created_at    timestamp,
    updated_at    timestamp
);
insert into clients (first_name, last_name,client_status,is_admin,comment)
values ('Иван', 'Иванов',4,false,'Новый клиент');

insert into clients (first_name, last_name,client_status,is_admin,comment)
values ('Сергей', 'Сергеев',0,true,'Зав складом');

insert into clients (first_name, last_name,client_status,is_admin,comment)
values ('Руслан', 'Бобров',1,false,'Хулиган!');

-- ACTIVED(1), BLOCKED(2), ARCHIVED(3), ERROR(4), CREATED(5);