insert into GENRE (CODE, NAME) values ('th', 'триллер');
insert into GENRE (CODE, NAME) values ('dt', 'детектив');
insert into GENRE (CODE, NAME) values ('sf', 'научная фантастика');

insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (1, 'Заумнов','Сергей','Сергеевич');
insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (2, 'Акабетов','Илья','Петрович');
insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (3, 'Пристли','Джон','Бойнтонг');

insert into BOOK ( TITLE, GENRE_CODE, AUTHOR_ID) values ( 'Гонка на выживание', 'th', 1);
insert into BOOK ( TITLE, GENRE_CODE, AUTHOR_ID) values ( 'Убийство в темном лесу', 'dt', 1);
insert into BOOK ( TITLE, GENRE_CODE, AUTHOR_ID) values ( 'Голос со звезды', 'sf', 2);
insert into BOOK ( TITLE, GENRE_CODE, AUTHOR_ID) values ( 'Чисто английское убийство', 'dt', 3);
