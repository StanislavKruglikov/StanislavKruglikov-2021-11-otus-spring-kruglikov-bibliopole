insert into GENRE (CODE, NAME) values ('test_code', 'тест жанр');
insert into GENRE (CODE, NAME) values ('test_code2', 'тест жанр2');

insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (1, 'тест','тестов','тестович');
insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (2, 'тест2','тестов2','тестович2');

insert into BOOK (TITLE, GENRE_CODE, AUTHOR_ID) values ('Тестовая книга', 'test_code', 1);
insert into BOOK (TITLE, GENRE_CODE, AUTHOR_ID) values ('Тестовая книга2', 'test_code2', 2);
