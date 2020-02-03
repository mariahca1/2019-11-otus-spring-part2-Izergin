delete from authors where 1=1;
--insert into authors values (default, 'Lev', 'Tolstoy');
--insert into authors values (default, 'Avtor', '0');
--insert into authors values (default, 'Avtor1', '1');
--insert into authors values (default, 'Avtor2', '2');
--insert into authors values (default, 'Avtor3', '3');

delete from genres where 1=1;
insert into genres values(default,'Classic');
insert into genres values(default,'Horror');

delete from books where 1=1;
insert into books values(default,'Classic book', 1);
insert into books values(default,'Horror book', 2);