drop table if exists books;
drop table if exists authors;
drop table if exists genres;


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