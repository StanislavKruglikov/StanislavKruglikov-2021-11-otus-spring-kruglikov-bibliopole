DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(
CODE VARCHAR(32)  PRIMARY KEY,
NAME VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(
ID BIGINT PRIMARY KEY,
FIRST_NAME VARCHAR(255) NOT NULL,
LAST_NAME VARCHAR(255) NOT NULL,
PATRONYMIC_NAME VARCHAR(255) NOT NULL);

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(
    ID BIGSERIAL AUTO_INCREMENT PRIMARY KEY,
    TITLE VARCHAR(255) NOT NULL,
    GENRE_CODE VARCHAR(32),
    AUTHOR_ID BIGINT,
    FOREIGN KEY (GENRE_CODE) REFERENCES GENRE(CODE),
    FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID)
);