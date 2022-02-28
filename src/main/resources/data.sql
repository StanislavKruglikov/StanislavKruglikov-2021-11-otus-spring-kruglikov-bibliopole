insert into GENRE (NAME) values ('триллер');
insert into GENRE (NAME) values ('детектив');
insert into GENRE (NAME) values ('научная фантастика');

insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (1, 'Заумнов','Сергей','Сергеевич');
insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (2, 'Акабетов','Илья','Петрович');
insert into AUTHOR (ID, FIRST_NAME, LAST_NAME, PATRONYMIC_NAME) values (3, 'Пристли','Джон','Бойнтонг');

insert into BOOK ( TITLE, GENRE_ID, AUTHOR_ID) values ( 'Гонка на выживание', 1, 1);
insert into BOOK ( TITLE, GENRE_ID, AUTHOR_ID) values ( 'Убийство в темном лесу', 2, 1);
insert into BOOK ( TITLE, GENRE_ID, AUTHOR_ID) values ( 'Голос со звезды', 3, 2);
insert into BOOK ( TITLE, GENRE_ID, AUTHOR_ID) values ( 'Чисто английское убийство', 2, 3);

insert into COMMENT ( TEXT , BOOK_ID) values( 'Замечательное прозиведение, прочел на одном дыхании.', 1);
insert into COMMENT ( TEXT , BOOK_ID) values( 'Захватывает сюжет.', 1);
insert into COMMENT ( TEXT , BOOK_ID) values( 'Слегка затянуто, но почитать на досуге можно.', 2);