delete from authors where 1=1;
insert into authors values (default, 'Lev', 'Tolstoy');
insert into authors values (default, 'Avtor', '0');
insert into authors values (default, 'Avtor1', '1');
insert into authors values (default, 'Avtor2', '2');
insert into authors values (default, 'Avtor3', '3');

delete from genres where 1=1;
insert into genres values(default,'Actions');
insert into genres values(default,'Science fiction');

delete from books where 1=1;
insert into books values(default,'Voina i mir', 1 , 1);
insert into books values(default,'kniga 2', 2 , 2);