create table users
(
    id            bigserial primary key,
    first_name    varchar(255),
    last_name     varchar(255),
    user_status   integer,
    admin         boolean,
    comment       varchar(255),
    created_at    timestamp,
    updated_at    timestamp
);
insert into users (first_name, last_name,user_status,admin,comment,created_at,updated_at)
values ('Иван', 'Иванов',4,false,'Новый клиент','2024-04-15 14:20:19','2024-08-02 20:02:30');

insert into users (first_name, last_name,user_status,admin,comment,created_at,updated_at)
values ('Сергей', 'Сергеев',0,true,'Зав складом','2024-02-05 20:59:59','2024-05-02 17:32:34');

insert into users (first_name, last_name,user_status,admin,comment,created_at,updated_at)
values ('Руслан', 'Бобров',1,false,'Хулиган!','2024-02-25 20:59:59','2024-05-02 21:02:30');

-- ACTIVED(1), BLOCKED(2), ARCHIVED(3), ERROR(4), CREATED(5);