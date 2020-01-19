#Задание на работу с базами данных.
#Используется БД PostgreSQL 12.1 + втроенная H2 база данных
#по умолчанию используется H2 база, все настройки выставлены по умолчанию. Всё что написано ниже, касается только настройки для PostgreSQL

#из под суперюзера выполнить скрипты создания пользователя + базы данных
create role java_izergin with
    encrypted password 'java_izergin'
login;
create database java_db_izergin OWNER=java_izergin;
GRANT ALL PRIVILEGES ON DATABASE java_db_izergin TO java_izergin;
grant connect on database java_db_izergin to java_izergin;


#после подключения из под java_izergin выполнить
create schema if not exists java_izergin;
alter schema java_izergin owner to java_izergin;


#структура данных создается при запуске приложения
create table books
(
    id        serial primary key,
    name      varchar unique,
    author_id int not null,
    genre_id  int not null
);

create table authors
(
    id          serial primary key,
    first_name  varchar not null,
    second_name varchar not null,
    unique (first_name, second_name)
);

create table genres
(
    id   serial primary key,
    name varchar not null unique
);


#данные в таблицы загружаются так же автоматически при запуске приложения
delete from authors where 1=1;
insert into authors values (default, 'Lev', 'Tolstoy');

delete from genres where 1=1;
insert into genres values(default,'lirycs');

delete from books where 1=1;
insert into books values(default,'Voina i mir', 1 , 1);


#скрипты удаления вышесозданного
drop database java_db_izergin;
drop schema java_izergin;
drop user java_izergin;
