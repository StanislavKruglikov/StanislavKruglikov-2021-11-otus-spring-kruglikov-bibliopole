insert into GENRE (NAME) values ('тест жанр');
insert into GENRE (NAME) values ('тест жанр2');

insert into AUTHOR (FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values ('тест','тестов','тестович');
insert into AUTHOR (FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values ('тест2','тестов2','тестович2');

insert into BOOK (TITLE, GENRE_ID, AUTHOR_ID) values ('Тестовая книга', 1, 1);
insert into BOOK (TITLE, GENRE_ID, AUTHOR_ID) values ('Тестовая книга2', 2, 2);
