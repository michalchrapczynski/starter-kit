insert into library (id, library_name) values (1, 'Politechnika Wrocławska');
insert into library (id, library_name) values (2, 'Uniwersytet');


insert into author (id, first_name, last_name) values (1, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (2, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (3, 'Janusz', 'Jankowski');

insert into book (id, title, library_id) values (1, 'Pierwsza książka', 1);
insert into book (id, title, library_id) values (2, 'Druga książka', 2);
insert into book (id, title, library_id) values (3, 'Trzecia książka', 1);


insert into book_author (id_book, id_author) values (1,1);
insert into book_author (id_book, id_author) values (2,2);
insert into book_author (id_book, id_author) values (3,3);
